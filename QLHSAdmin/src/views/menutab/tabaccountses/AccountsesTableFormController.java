/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabaccountses;

import controllers.AccountsController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import models.Accounts;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class AccountsesTableFormController implements Initializable {

    @FXML
    private TableView<Accounts> tableView;

    @FXML
    private TableColumn<Accounts, String> tablecolumnUsername;

    @FXML
    private TableColumn<Accounts, String> tablecolumnPassword;

    @FXML
    private TableColumn<Accounts, String> tablecolumnDisplayname;

    @FXML
    private TableColumn<Accounts, String> tablecolumnType;

    @FXML
    private TableColumn<Accounts, String> tablecolumnMaGV;

    private void initialize_tablecolumnDisplayname() {
        this.tablecolumnDisplayname.setCellValueFactory(new PropertyValueFactory<>("Displayname"));
        this.tablecolumnDisplayname.setCellFactory(TextFieldTableCell.<Accounts>forTableColumn());
        this.tablecolumnDisplayname.setOnEditCommit((event) -> {
            event.getRowValue().setDisplayname(event.getNewValue());
        });
    }

    private void initialize_tablecolumnMaGV() {
        this.tablecolumnMaGV.setCellValueFactory(new PropertyValueFactory<>("MaGV"));
        this.tablecolumnMaGV.setCellFactory(TextFieldTableCell.<Accounts>forTableColumn());
        this.tablecolumnMaGV.setOnEditCommit((event) -> {
            event.getRowValue().setDisplayname(event.getNewValue());
        });
    }

    private void initialize_tablecolumnType() {
        this.tablecolumnType.setCellValueFactory((TableColumn.CellDataFeatures<Accounts, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getType());
        });
        this.tablecolumnType.setCellFactory(ComboBoxTableCell.forTableColumn(
                AccountsController.getInstance().getListAccountType()
        ));
        this.tablecolumnType.setOnEditCommit((event) -> {
            event.getRowValue().setType(event.getNewValue());
        });
    }

    private void initialize_tablecolumnUsername() {
        this.tablecolumnUsername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        this.tablecolumnUsername.setCellFactory(TextFieldTableCell.<Accounts>forTableColumn());
        this.tablecolumnUsername.setOnEditCommit((event) -> {
            event.getRowValue().setUsername(event.getNewValue());
        });
    }

    private void initialize_tablecolumnPassword() {
//        this.tablecolumnPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
//        this.tablecolumnPassword.setCellFactory(TextFieldTableCell.<Accounts>forTableColumn());
//        this.tablecolumnPassword.setOnEditCommit((event) -> {
//            event.getRowValue().setPassword(event.getNewValue());
//        });
    }

    public Accounts getSelectedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public TableView<Accounts> getRoot() {
        return tableView;
    }

    public TableView<Accounts> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<Accounts> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<Accounts> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(Accounts newAccount) {
        boolean result = AccountsController.getInstance().Edit(newAccount, newAccount);        
        this.Refresh();
    }

    public void Refresh() {
        if (this.tableView.getItems().isEmpty() == false) {
            this.tableView.getItems().clear();
        }
        this.tableView.getItems().addAll((ArrayList<Accounts>) ((ArrayList<? extends Model>) AccountsController.getInstance().GetList()));
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(Accounts accounts) {
        this.tableView.scrollTo(accounts);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                Accounts accounts = (Accounts) i;
                boolean result = AccountsController.getInstance().Delete(accounts);

                if (result) {
                    this.tableView.getItems().remove((Accounts) i);
                }
            }
        }
    }

    public void ResetPasswordChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                Accounts accounts = (Accounts) i;
                boolean result = AccountsController.getInstance().ResetPassword(accounts);
            }

            this.Refresh();
        }
    }

    public void AddItem(Accounts accounts) {
        boolean result = AccountsController.getInstance().Add(accounts);

        if (result) {
            this.tableView.getItems().add(accounts);
            this.tableView.scrollTo(accounts);
        }
    }

    public void SearchItems(Accounts searchKeyword) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<Accounts>) ((ArrayList<? extends Model>) AccountsController.getInstance().Search(searchKeyword)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tablecolumnDisplayname();
        initialize_tablecolumnType();
        initialize_tablecolumnUsername();
        initialize_tablecolumnMaGV();
        initialize_tablecolumnPassword();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }
}
