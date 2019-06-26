/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabhanhkiems;

import controllers.HanhKiemController;
import models.HanhKiem;
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
public class HanhKiemsTableFormController implements Initializable {

    @FXML
    private TableView<HanhKiem> tableView;

    @FXML
    private TableColumn<HanhKiem, String> tableColumn_MAHK;

    @FXML
    private TableColumn<HanhKiem, String> tableColumn_TENHK;

    private void initialize_tableColumn_MaHK() {
        this.tableColumn_MAHK.setCellValueFactory(new PropertyValueFactory<>("MaHK"));
    }

    private void initialize_tableColumn_TenHK() {
        this.tableColumn_TENHK.setCellValueFactory(new PropertyValueFactory<>("TenHK"));
//        this.tableColumn_TenHK.setCellFactory(TextFieldTableCell.<HanhKiem>forTableColumn());
//        this.tableColumn_TenHK.setOnEditCommit((event) -> {
//            event.getRowValue().setTenHs(event.getNewValue());
//        });
    }

    public HanhKiem getFocusedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public HanhKiem getSelectedItem() {
        return this.tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return this.tableView.getSelectionModel().getSelectedIndex();
    }

    public ObservableList<HanhKiem> getSelectedItems() {
        return this.tableView.getSelectionModel().getSelectedItems();
    }

    public TableView<HanhKiem> getRoot() {
        return tableView;
    }

    public TableView<HanhKiem> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<HanhKiem> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<HanhKiem> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(HanhKiem newAccount) {
        boolean result = HanhKiemController.getInstance().Edit(newAccount, newAccount);
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
        this.tableView.getItems().addAll((ArrayList<HanhKiem>) ((ArrayList<? extends Model>) HanhKiemController.getInstance().GetList()));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(HanhKiem hocSinh) {
        this.tableView.scrollTo(hocSinh);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                HanhKiem hs = (HanhKiem) i;
                boolean result = HanhKiemController.getInstance().Delete(hs);
            }

            this.Refresh();
        }
    }

    public void AddItem(HanhKiem hs) {
        boolean result = HanhKiemController.getInstance().Add(hs);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(HanhKiem searchKeyword) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<HanhKiem>) ((ArrayList<? extends Model>) HanhKiemController.getInstance().Search(searchKeyword)));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tableColumn_MaHK();
        initialize_tableColumn_TenHK();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }

}
