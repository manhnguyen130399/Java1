/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabhocs;

import controllers.LopController;
import models.Lop;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Accounts;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class LopsTableFormController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);

    @FXML
    private TableView<Lop> tableView;

    @FXML
    private TableColumn<Lop, String> tableColumn_MALOP;

    @FXML
    private TableColumn<Lop, String> tableColumn_TENLOP;

    private void initialize_tableColumn_MALOP() {
        this.tableColumn_MALOP.setCellValueFactory(new PropertyValueFactory<>("MaLop"));
    }

    private void initialize_tableColumn_TENLOP() {
        this.tableColumn_TENLOP.setCellValueFactory(new PropertyValueFactory<>("TenLop"));
//        this.tableColumn_TENLOP.setCellFactory(TextFieldTableCell.<Lop>forTableColumn());
//        this.tableColumn_TENLOP.setOnEditCommit((event) -> {
//            event.getRowValue().setTenHs(event.getNewValue());
//        });
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    public Lop getFocusedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public Lop getSelectedItem() {
        return this.tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return this.tableView.getSelectionModel().getSelectedIndex();
    }

    public ObservableList<Lop> getSelectedItems() {
        return this.tableView.getSelectionModel().getSelectedItems();
    }

    public TableView<Lop> getRoot() {
        return tableView;
    }

    public TableView<Lop> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<Lop> al) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        if (this.tableView.getItems().isEmpty() == false) {
            this.tableView.getItems().clear();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void AddData(ArrayList<Lop> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(Lop newAccount) {
        boolean result = LopController.getInstance().Edit(newAccount, newAccount);
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

        if (currentAccountProperty.get() != null) {
            this.setData(
                    (ArrayList<Lop>) ((ArrayList<? extends Model>) LopController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            this.setData(
                    (ArrayList<Lop>) ((ArrayList<? extends Model>) LopController.getInstance()
                            .GetList()));

        }

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(Lop hocSinh) {
        this.tableView.scrollTo(hocSinh);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                Lop hs = (Lop) i;
                boolean result = LopController.getInstance().Delete(hs);
            }

            this.Refresh();
        }
    }

    public void AddItem(Lop hs) {
        boolean result = LopController.getInstance().Add(hs);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(Lop searchKeyword) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<Lop>) ((ArrayList<? extends Model>) LopController.getInstance().Search(searchKeyword)));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tableColumn_MALOP();
        initialize_tableColumn_TENLOP();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }

}
