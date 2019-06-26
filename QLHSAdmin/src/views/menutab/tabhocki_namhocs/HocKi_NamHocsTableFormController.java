/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabhocki_namhocs;

import controllers.HocKi_NamHocController;
import models.HocKi_NamHoc;
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
public class HocKi_NamHocsTableFormController implements Initializable {

    @FXML
    private TableView<HocKi_NamHoc> tableView;

    @FXML
    private TableColumn<HocKi_NamHoc, String> tableColumn_NAM;

    @FXML
    private TableColumn<HocKi_NamHoc, String> tableColumn_HOCKI;

    private void initialize_tableColumn_NAM() {
        this.tableColumn_NAM.setCellValueFactory(new PropertyValueFactory<>("Nam"));
    }

    private void initialize_tableColumn_HOCKI() {
        this.tableColumn_HOCKI.setCellValueFactory(new PropertyValueFactory<>("HocKi"));
    }

    public HocKi_NamHoc getFocusedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public HocKi_NamHoc getSelectedItem() {
        return this.tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return this.tableView.getSelectionModel().getSelectedIndex();
    }

    public ObservableList<HocKi_NamHoc> getSelectedItems() {
        return this.tableView.getSelectionModel().getSelectedItems();
    }

    public TableView<HocKi_NamHoc> getRoot() {
        return tableView;
    }

    public TableView<HocKi_NamHoc> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<HocKi_NamHoc> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<HocKi_NamHoc> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(HocKi_NamHoc newAccount) {
        boolean result = HocKi_NamHocController.getInstance().Edit(newAccount, newAccount);
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
        this.tableView.getItems().addAll((ArrayList<HocKi_NamHoc>) ((ArrayList<? extends Model>) HocKi_NamHocController.getInstance().GetList()));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(HocKi_NamHoc hocSinh) {
        this.tableView.scrollTo(hocSinh);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                HocKi_NamHoc hs = (HocKi_NamHoc) i;
                boolean result = HocKi_NamHocController.getInstance().Delete(hs);
            }

            this.Refresh();
        }
    }

    public void AddItem(HocKi_NamHoc hs) {
        boolean result = HocKi_NamHocController.getInstance().Add(hs);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(HocKi_NamHoc searchKeyword) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<HocKi_NamHoc>) ((ArrayList<? extends Model>) HocKi_NamHocController.getInstance().Search(searchKeyword)));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tableColumn_NAM();
        initialize_tableColumn_HOCKI();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }

}
