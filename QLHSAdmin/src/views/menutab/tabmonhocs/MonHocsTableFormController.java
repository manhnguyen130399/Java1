/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabmonhocs;

import controllers.MonHocController;
import models.MonHoc;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class MonHocsTableFormController implements Initializable {

    @FXML
    private TableView<MonHoc> tableView;

    @FXML
    private TableColumn<MonHoc, String> tableColumn_MAMH;

    @FXML
    private TableColumn<MonHoc, String> tableColumn_TENMH;

    private void initialize_tableColumn_MAMH() {
        this.tableColumn_MAMH.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
    }

    private void initialize_tableColumn_TENMH() {
        this.tableColumn_TENMH.setCellValueFactory(new PropertyValueFactory<>("TenMH"));
//        this.tableColumn_TENMH.setCellFactory(TextFieldTableCell.<MonHoc>forTableColumn());
//        this.tableColumn_TENMH.setOnEditCommit((event) -> {
//            event.getRowValue().setTenHs(event.getNewValue());
//        });
    }

    public MonHoc getFocusedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public MonHoc getSelectedItem() {
        return this.tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return this.tableView.getSelectionModel().getSelectedIndex();
    }

    public ObservableList<MonHoc> getSelectedItems() {
        return this.tableView.getSelectionModel().getSelectedItems();
    }

    public TableView<MonHoc> getRoot() {
        return tableView;
    }

    public TableView<MonHoc> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<MonHoc> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<MonHoc> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(MonHoc newAccount) {
        boolean result = MonHocController.getInstance().Edit(newAccount, newAccount);
        if (result) {
            this.Refresh();
        }
    }

    public void Refresh() {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        if (this.tableView.getItems().isEmpty() == false) {
            this.tableView.getItems().clear();
        }
        this.tableView.getItems().addAll((ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController.getInstance().GetList()));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(MonHoc hocSinh) {
        this.tableView.scrollTo(hocSinh);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                MonHoc hs = (MonHoc) i;
                boolean result = MonHocController.getInstance().Delete(hs);
            }

            this.Refresh();
        }
    }

    public void AddItem(MonHoc hs) {
        boolean result = MonHocController.getInstance().Add(hs);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(MonHoc searchKeyword) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController.getInstance().Search(searchKeyword)));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tableColumn_MAMH();
        initialize_tableColumn_TENMH();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }

}
