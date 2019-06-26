/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabhoclucs;

import controllers.HocLucController;
import models.HocLuc;
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
public class HocLucsTableFormController implements Initializable {

    @FXML
    private TableView<HocLuc> tableView;

    @FXML
    private TableColumn<HocLuc, String> tableColumn_MAHL;

    @FXML
    private TableColumn<HocLuc, String> tableColumn_TENHL;

    private void initialize_tableColumn_MAHL() {
        this.tableColumn_MAHL.setCellValueFactory(new PropertyValueFactory<>("MaHL"));
    }

    private void initialize_tableColumn_TENHL() {
        this.tableColumn_TENHL.setCellValueFactory(new PropertyValueFactory<>("TenHL"));
//        this.tableColumn_TENHL.setCellFactory(TextFieldTableCell.<HocLuc>forTableColumn());
//        this.tableColumn_TENHL.setOnEditCommit((event) -> {
//            event.getRowValue().setTenHs(event.getNewValue());
//        });
    }

    public HocLuc getFocusedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public HocLuc getSelectedItem() {
        return this.tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return this.tableView.getSelectionModel().getSelectedIndex();
    }

    public ObservableList<HocLuc> getSelectedItems() {
        return this.tableView.getSelectionModel().getSelectedItems();
    }

    public TableView<HocLuc> getRoot() {
        return tableView;
    }

    public TableView<HocLuc> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<HocLuc> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<HocLuc> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(HocLuc newAccount) {
        boolean result = HocLucController.getInstance().Edit(newAccount, newAccount);
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
        this.tableView.getItems().addAll((ArrayList<HocLuc>) ((ArrayList<? extends Model>) HocLucController.getInstance().GetList()));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(HocLuc hocSinh) {
        this.tableView.scrollTo(hocSinh);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                HocLuc hs = (HocLuc) i;
                boolean result = HocLucController.getInstance().Delete(hs);
            }

            this.Refresh();
        }
    }

    public void AddItem(HocLuc hs) {
        boolean result = HocLucController.getInstance().Add(hs);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(HocLuc searchKeyword) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<HocLuc>) ((ArrayList<? extends Model>) HocLucController.getInstance().Search(searchKeyword)));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tableColumn_MAHL();
        initialize_tableColumn_TENHL();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }

}
