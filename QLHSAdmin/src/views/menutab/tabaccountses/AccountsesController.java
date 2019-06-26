/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabaccountses;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controllers.AccountsController;
import controllers.GiaoVienController;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import models.Accounts;
import models.GiaoVien;
import models.HocKi_NamHoc;
import models.Model;

public class AccountsesController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<HocKi_NamHoc> currentHocKiNamHocProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<Accounts> tableviewAccounts;
    private AccountsesTableFormController tableviewAccountsFormController;

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private ToggleButton togglebuttonAccountsHome;
    @FXML
    private ToggleButton togglebuttonAccountsHomeLine;
    @FXML
    private ToggleButton togglebuttonCurrentAccount;
    @FXML
    private ToggleButton togglebuttonCurrentAccountLine;
    @FXML
    private ToggleButton togglebuttonAccountsList;
    @FXML
    private ToggleButton togglebuttonAccountsListLine;
    @FXML
    private BorderPane borderpaneAccountsHome;
    @FXML
    private TextField textfieldAHUsername;
    @FXML
    private TextField textfieldAHDisplayname;
    @FXML
    private TextField textfieldAHType;
    @FXML
    private BorderPane borderpaneCurrentAccount;
    @FXML
    private TextField textfieldCAUsername;
    @FXML
    private TextField textfieldCADisplayname;
    @FXML
    private TextField textfieldCAType;
    @FXML
    private JFXTextField textfieldCADisplaynameChange;
    @FXML
    private JFXButton buttonCASaveDisplayname;
    @FXML
    private JFXTextField textfieldCAPasswordChange;
    @FXML
    private JFXPasswordField passwordfieldCAPasswordChange;
    @FXML
    private ToggleButton togglebuttonCAShowPassword;
    @FXML
    private MaterialDesignIconView mdivCAShowpassword;
    @FXML
    private JFXTextField textfieldCARePassword;
    @FXML
    private JFXPasswordField passwordfieldCARePassword;
    @FXML
    private JFXButton buttonCASavePassword;
    @FXML
    private BorderPane borderpaneAccountsList;
    @FXML
    private ScrollPane scrollpaneAccountsTable;
    @FXML
    private BorderPane borderpaneAccountsTable;
    @FXML
    private BorderPane borderpaneAccountsListMenu;
    @FXML
    private ScrollPane scrollpaneAccountListMenu;
    @FXML
    private ToggleButton togglebuttonAdd;
    @FXML
    private ToggleButton togglebuttonEdit;
    @FXML
    private ToggleButton togglebuttonDelete;
    @FXML
    private ToggleButton togglebuttonSearch;
    @FXML
    private ToggleButton togglebuttonResetPassword;
    @FXML
    private ToggleButton togglebuttonRefresh;
    @FXML
    private ToggleButton togglebuttonUnselectAll;
    @FXML
    private BorderPane borderpaneAddAccount;
    @FXML
    private JFXButton buttonAddAccBack;
    @FXML
    private TextField textfieldAddAccUsername;
    @FXML
    private TextField textfieldAddAccDisplayname;
    @FXML
    private PasswordField passwordfieldAddAcc;
    @FXML
    private ComboBox<String> comboboxAddAccType;
    @FXML
    private ComboBox<GiaoVien> comboboxAddAccMaGV;
    @FXML
    private JFXButton buttonAddAcc;
    @FXML
    private JFXButton buttonAddAccReset;
    @FXML
    private BorderPane borderpaneEditAccount;
    @FXML
    private JFXButton buttonEditAccBack;
    @FXML
    private TextField textfieldEditAccUsername;
    @FXML
    private TextField textfieldEditAccDisplayname;
    @FXML
    private PasswordField passwordfieldEditAcc;
    @FXML
    private ComboBox<String> comboboxEditAccType;
    @FXML
    private ComboBox<GiaoVien> comboboxEditAccMaGV;
    @FXML
    private JFXButton buttonEditAcc;
    @FXML
    private JFXButton buttonEditAccReset;
    @FXML
    private BorderPane borderpaneDeleteAccount;
    @FXML
    private JFXButton buttonDeleteAccBack;
    @FXML
    private JFXButton buttonDeleteAcc;
    @FXML
    private JFXButton buttonDeleteAccReset;
    @FXML
    private BorderPane borderpaneFindAccount;
    @FXML
    private JFXButton buttonFindAccBack;
    @FXML
    private CheckBox chechboxFindAccUsername;
    @FXML
    private TextField textfieldFindAccUsername;
    @FXML
    private CheckBox checkboxFindAccDisplayname;
    @FXML
    private TextField textfieldFindAccDisplayname;
    @FXML
    private CheckBox checkboxFindAccType;
    @FXML
    private ComboBox<String> comboboxFindAccType;
    @FXML
    private CheckBox checkboxFindAccMaGV;
    @FXML
    private ComboBox<GiaoVien> comboboxFindAccMaGV;
    @FXML
    private JFXButton buttonFindAcc;
    @FXML
    private JFXButton buttonFindAccReset;
    @FXML
    private BorderPane borderpaneResetPassword;
    @FXML
    private JFXButton buttonResetAccBack;
    @FXML
    private JFXButton buttonResetPassAcc;
    @FXML
    private ToggleButton togglebuttonAccountListMenu;

    private void HideBorderpaneAccountsHome(boolean isHide) {
        isHide = !isHide;
        borderpaneAccountsHome.setVisible(isHide);
        borderpaneAccountsHome.setManaged(isHide);
    }

    private void HideBorderpaneCurrentAccount(boolean isHide) {
        isHide = !isHide;
        borderpaneCurrentAccount.setVisible(isHide);
        borderpaneCurrentAccount.setManaged(isHide);
    }

    private void HideBorderpaneAccountsList(boolean isHide) {
        isHide = !isHide;
        borderpaneAccountsList.setVisible(isHide);
        borderpaneAccountsList.setManaged(isHide);
    }

    private void HideBorderpaneMenuAccounts(boolean isHide) {
        isHide = !isHide;
        borderpaneAccountsListMenu.setVisible(isHide);
        borderpaneAccountsListMenu.setManaged(isHide);
    }

    private void HideScrollpaneAccountListMenu(boolean isHide) {
        isHide = !isHide;
//        scrollpaneAccountListMenu.setVisible(isHide);
//        scrollpaneAccountListMenu.setManaged(isHide);
        if (isHide) {
            scrollpaneAccountListMenu.setOpacity(1);
        } else {
            scrollpaneAccountListMenu.setOpacity(0.1);
        }
    }

    private void SetDataToAccountsHome() {
        if (currentAccountProperty.get() != null) {
            this.textfieldAHUsername.setText(this.currentAccountProperty.get().getUsername());
            this.textfieldAHDisplayname.setText(this.currentAccountProperty.get().getDisplayname());
            this.textfieldAHType.setText(this.currentAccountProperty.get().getType());
        }
    }

    private void SetDataToCurrentAccount() {
        if (currentAccountProperty.get() != null) {
            this.textfieldCAUsername.setText(this.currentAccountProperty.get().getUsername());
            this.textfieldCADisplayname.setText(this.currentAccountProperty.get().getDisplayname());
            this.textfieldCAType.setText(this.currentAccountProperty.get().getType());
        }
    }

    private void SetDataToAccountsList() {
        if (currentAccountProperty.get() != null) {
            tableviewAccountsFormController.setData(
                    (ArrayList<Accounts>) ((ArrayList<? extends Model>) AccountsController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {

            tableviewAccountsFormController.setData(
                    (ArrayList<Accounts>) ((ArrayList<? extends Model>) AccountsController.getInstance()
                            .GetList()));

        }
    }

    private void SetShowPasswordDisplay(boolean isShow) {
        this.textfieldCAPasswordChange.setVisible(isShow);
        this.textfieldCAPasswordChange.setManaged(isShow);
        this.passwordfieldCAPasswordChange.setVisible(!isShow);
        this.passwordfieldCAPasswordChange.setManaged(!isShow);

        this.textfieldCARePassword.setVisible(isShow);
        this.textfieldCARePassword.setManaged(isShow);
        this.passwordfieldCARePassword.setVisible(!isShow);
        this.passwordfieldCAPasswordChange.setManaged(!isShow);

        if (isShow) {
            this.mdivCAShowpassword.setGlyphName("EYE");
        } else {
            this.mdivCAShowpassword.setGlyphName("EYE_OFF");
        }
    }

    private void ResetDataAddAccount() {
        this.textfieldAddAccDisplayname.setText("");
        this.textfieldAddAccUsername.setText("");
        this.passwordfieldAddAcc.setText("");
        this.comboboxAddAccType.getSelectionModel().select(0);
        this.comboboxAddAccMaGV.getSelectionModel().select(null);
    }

    private void ResetDataFindAccount() {
        this.textfieldFindAccDisplayname.setText("");
        this.textfieldFindAccUsername.setText("");
        this.comboboxFindAccType.getSelectionModel().select(null);
        this.comboboxFindAccMaGV.getSelectionModel().select(null);
        this.chechboxFindAccUsername.setSelected(false);
        this.checkboxFindAccDisplayname.setSelected(false);
        this.checkboxFindAccMaGV.setSelected(false);
        this.checkboxFindAccType.setSelected(false);
    }

    private void ResetDataEditAccount() {
        this.textfieldEditAccDisplayname.setText("");
        this.textfieldEditAccUsername.setText("");
        this.passwordfieldEditAcc.setText("");
        this.comboboxEditAccType.getSelectionModel().select(null);
        this.comboboxEditAccMaGV.getSelectionModel().select(null);
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    public ObjectProperty<HocKi_NamHoc> getCurrentHocKiNamHocProperty() {
        return currentHocKiNamHocProperty;
    }

    public void setCurrentHocKiNamHocProperty(HocKi_NamHoc currentHocKiNamHocProperty) {
        this.currentHocKiNamHocProperty.set(currentHocKiNamHocProperty);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Khởi tạo bảng accounts///
        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("AccountsesTableForm.fxml"));
            this.tableviewAccounts = fxmll.load();
            this.tableviewAccountsFormController = fxmll.getController();
            this.borderpaneAccountsTable.setCenter(this.tableviewAccounts);
        } catch (IOException ex) {
            Logger.getLogger(AccountsesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ///Binding dữ liệu theo Current accounts///
        this.currentAccountProperty.addListener((observable) -> {
            this.SetDataToAccountsHome();
            this.SetDataToAccountsList();
            this.SetDataToCurrentAccount();
        });

        ///Binding dữ liệu theo HocKi NamHoc///
        this.currentHocKiNamHocProperty.addListener((observable) -> {
            this.SetDataToAccountsHome();
            this.SetDataToAccountsList();
            this.SetDataToCurrentAccount();
        });

        ///Cài đặt Toggle Group///
        this.togglebuttonAccountsHome.setToggleGroup(toggleGroupMenu);
        this.togglebuttonAccountsList.setToggleGroup(toggleGroupMenu);
        this.togglebuttonCurrentAccount.setToggleGroup(toggleGroupMenu);

        ///Chức năng binding giữa Toggle button và Toggle button line///
        this.togglebuttonAccountsHome.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonAccountsHomeLine.setSelected(newValue);
        });

        this.togglebuttonAccountsHomeLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonAccountsHome.setSelected(newValue);
        });

        this.togglebuttonAccountsList.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonAccountsListLine.setSelected(newValue);
        });

        this.togglebuttonAccountsListLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonAccountsList.setSelected(newValue);
        });

        this.togglebuttonCurrentAccount.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonCurrentAccountLine.setSelected(newValue);
        });

        this.togglebuttonCurrentAccountLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonCurrentAccount.setSelected(newValue);
        });

        ///Chức năng chuyển tab///
        this.togglebuttonAccountsHome.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.HideBorderpaneAccountsHome(false);
                this.HideBorderpaneAccountsList(true);
                this.HideBorderpaneCurrentAccount(true);
            }
        });

        this.togglebuttonAccountsList.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.HideBorderpaneAccountsHome(true);
                this.HideBorderpaneAccountsList(false);
                this.HideBorderpaneCurrentAccount(true);
            }
        });

        this.togglebuttonCurrentAccount.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.HideBorderpaneAccountsHome(true);
                this.HideBorderpaneAccountsList(true);
                this.HideBorderpaneCurrentAccount(false);
                this.SetShowPasswordDisplay(false);
            }
        });

        ///Chức năng show password///
        this.togglebuttonCAShowPassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.SetShowPasswordDisplay(newValue);
        });

        ///Chức năng thay đổi Displayname///
        this.buttonCASaveDisplayname.setOnAction((event) -> {
            if (this.textfieldCADisplaynameChange.getText().isEmpty() == false) {
                Accounts newAccounts = (Accounts) this.currentAccountProperty.get().clone();

                newAccounts.setDisplayname(this.textfieldCADisplaynameChange.getText());
                if (AccountsController.getInstance().Edit(this.currentAccountProperty.get(), newAccounts)) {
                    this.currentAccountProperty.set(newAccounts);
                }

                this.textfieldCADisplaynameChange.setText("");
            }
        });

        ///Chức năng thay đổi password///
        this.buttonCASavePassword.setOnAction((event) -> {
            String newPassword = this.passwordfieldCAPasswordChange.getText();
            String rePassword = this.passwordfieldCARePassword.getText();

            if (newPassword.isEmpty() == false && rePassword.isEmpty() == false) {
                if (newPassword.equals(rePassword)) {
                    Accounts newAccounts = (Accounts) this.currentAccountProperty.get().clone();

                    newAccounts.setPassword(this.passwordfieldCAPasswordChange.getText());
                    if (AccountsController.getInstance().Edit(this.currentAccountProperty.get(), newAccounts)) {
                        this.currentAccountProperty.set(newAccounts);
                    }

                    this.passwordfieldCAPasswordChange.setText("");
                    this.passwordfieldCARePassword.setText("");
                }
            }
        });

        ///Binding dữ liệu giữa textfield và password field///
        this.textfieldCAPasswordChange.textProperty().bind(this.passwordfieldCAPasswordChange.textProperty());
        this.textfieldCARePassword.textProperty().bind(this.passwordfieldCARePassword.textProperty());

        ///Chức năng mở menu trong account list///
        this.togglebuttonAccountListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewAccounts.getSelectionModel().getSelectedItems().size() + ")");
                this.HideBorderpaneMenuAccounts(false);
                ((MaterialDesignIconView) this.togglebuttonAccountListMenu.getGraphic()).setGlyphName("MENU_RIGHT");
                this.scrollpaneAccountsTable.setOpacity(0.25);
            } else {
                this.HideBorderpaneMenuAccounts(true);
                ((MaterialDesignIconView) this.togglebuttonAccountListMenu.getGraphic()).setGlyphName("MENU_LEFT");
                this.scrollpaneAccountsTable.setOpacity(1);
            }
        });
        this.borderpaneAccountsTable.disableProperty().bind(this.togglebuttonAccountListMenu.selectedProperty());

        //Binding dữ liệu giữa toggle button trong menu list và hiển thị borderpane chức năng///
        this.borderpaneAddAccount.visibleProperty().bind(this.togglebuttonAdd.selectedProperty());
        this.borderpaneAddAccount.managedProperty().bind(this.togglebuttonAdd.selectedProperty());

        this.borderpaneEditAccount.visibleProperty().bind(this.togglebuttonEdit.selectedProperty());
        this.borderpaneEditAccount.managedProperty().bind(this.togglebuttonEdit.selectedProperty());

        this.borderpaneDeleteAccount.visibleProperty().bind(this.togglebuttonDelete.selectedProperty());
        this.borderpaneDeleteAccount.managedProperty().bind(this.togglebuttonDelete.selectedProperty());

        this.borderpaneFindAccount.visibleProperty().bind(this.togglebuttonSearch.selectedProperty());
        this.borderpaneFindAccount.managedProperty().bind(this.togglebuttonSearch.selectedProperty());

        this.borderpaneResetPassword.visibleProperty().bind(this.togglebuttonResetPassword.selectedProperty());
        this.borderpaneResetPassword.managedProperty().bind(this.togglebuttonResetPassword.selectedProperty());

        this.togglebuttonAdd.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneAccountListMenu(newValue);
        });
        this.togglebuttonEdit.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneAccountListMenu(newValue);
        });
        this.togglebuttonDelete.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneAccountListMenu(newValue);
        });
        this.togglebuttonSearch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneAccountListMenu(newValue);
        });
        this.togglebuttonResetPassword.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneAccountListMenu(newValue);
        });

        this.buttonAddAccBack.setOnAction((event) -> {
            this.togglebuttonAdd.setSelected(false);
        });
        this.buttonEditAccBack.setOnAction((event) -> {
            this.togglebuttonEdit.setSelected(false);
        });
        this.buttonDeleteAccBack.setOnAction((event) -> {
            this.togglebuttonDelete.setSelected(false);
        });
        this.buttonFindAccBack.setOnAction((event) -> {
            this.togglebuttonSearch.setSelected(false);
        });
        this.buttonResetAccBack.setOnAction((event) -> {
            this.togglebuttonResetPassword.setSelected(false);
        });

        ///Khởi tạo form thêm tài khoản///
        this.comboboxAddAccType.getItems().addAll(AccountsController.getInstance().getListAccountType());
        this.comboboxAddAccMaGV.getItems().addAll(
                (ArrayList<GiaoVien>) (ArrayList<? extends Model>) GiaoVienController.getInstance()
                        .GetList(this.currentAccountProperty.get())
        );

        ///Khởi tạo form tìm tài khoản///
        this.comboboxFindAccType.getItems().addAll(AccountsController.getInstance().getListAccountType());
        this.comboboxFindAccMaGV.getItems().addAll(
                (ArrayList<GiaoVien>) (ArrayList<? extends Model>) GiaoVienController.getInstance()
                        .GetList(this.currentAccountProperty.get())
        );

        ///Khởi tạo form sửa tài khoản///
        this.comboboxEditAccType.getItems().addAll(AccountsController.getInstance().getListAccountType());
        this.comboboxEditAccMaGV.getItems().addAll(
                (ArrayList<GiaoVien>) (ArrayList<? extends Model>) GiaoVienController.getInstance()
                        .GetList(this.currentAccountProperty.get())
        );

        ///Chức năng thêm tài khoản///
        this.buttonAddAcc.setOnAction((event) -> {
            if (this.textfieldAddAccUsername.getText().isEmpty()
                    || this.passwordfieldAddAcc.getText().isEmpty()) {
                this.ResetDataAddAccount();
                return;
            }

            String maGV = null;
            if (this.comboboxAddAccMaGV.getSelectionModel().getSelectedItem() != null) {
                maGV = this.comboboxAddAccMaGV.getSelectionModel().getSelectedItem().getMaGV();
            }

            Accounts accounts = new Accounts(
                    this.textfieldAddAccUsername.getText(),
                    this.passwordfieldAddAcc.getText(),
                    this.textfieldAddAccDisplayname.getText(),
                    this.comboboxAddAccType.getSelectionModel().getSelectedItem(),
                    maGV);

            this.tableviewAccountsFormController.AddItem(accounts);
            this.ResetDataAddAccount();
        });
        this.buttonAddAccReset.setOnAction((event) -> {
            this.ResetDataAddAccount();
        });

        ///Chức năng xóa tài khoản///
        this.buttonDeleteAcc.setOnAction((event) -> {
            this.tableviewAccountsFormController.DeleteChoiseItems();
        });
        this.buttonDeleteAccReset.setOnAction((event) -> {
            this.tableviewAccountsFormController.ClearSelectedItems();
        });

        ///Chức năng sửa tài khoản///
        this.buttonEditAcc.setOnAction((event) -> {
            if (this.textfieldEditAccUsername.getText().isEmpty()
                    || this.passwordfieldEditAcc.getText().isEmpty()) {
                this.ResetDataEditAccount();
                return;
            }

            String maGV = null;
            if (this.comboboxEditAccMaGV.getSelectionModel().getSelectedItem() != null) {
                maGV = this.comboboxEditAccMaGV.getSelectionModel().getSelectedItem().getMaGV();
            }

            Accounts newAccounts = new Accounts(
                    this.textfieldEditAccUsername.getText(),
                    this.passwordfieldEditAcc.getText(),
                    this.textfieldEditAccDisplayname.getText(),
                    this.comboboxEditAccType.getSelectionModel().getSelectedItem(),
                    maGV);

            this.tableviewAccountsFormController.EditItem(newAccounts);
        });
        this.buttonEditAccReset.setOnAction((event) -> {
            this.ResetDataEditAccount();
        });

        ///Chức năng tìm kiếm///
        this.buttonFindAcc.setOnAction((event) -> {
            Accounts accounts = new Accounts();

            if (this.chechboxFindAccUsername.isSelected()) {
                accounts.setUsername(this.textfieldFindAccUsername.getText());
            }

            if (this.checkboxFindAccDisplayname.isSelected()) {
                accounts.setDisplayname(this.textfieldFindAccDisplayname.getText());
            }

            if (this.checkboxFindAccMaGV.isSelected()) {
                String maGV = null;
                if (this.comboboxFindAccMaGV.getSelectionModel().getSelectedItem() != null) {
                    maGV = this.comboboxFindAccMaGV.getSelectionModel().getSelectedItem().getMaGV();
                }

                accounts.setMaGV(maGV);
            }

            if (this.checkboxFindAccType.isSelected()) {
                accounts.setType(this.comboboxFindAccType.getSelectionModel().getSelectedItem());
            }

            accounts.ChangeToNull();

            this.tableviewAccountsFormController.SearchItems(accounts);
        });
        this.buttonFindAccReset.setOnAction((event) -> {
            this.ResetDataFindAccount();
        });

        ///Chức năng reset password//
        this.buttonResetPassAcc.setOnAction((event) -> {
            this.tableviewAccountsFormController.ResetPasswordChoiseItems();
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewAccountsFormController.Refresh();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng UnselectAll///
        this.togglebuttonUnselectAll.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewAccountsFormController.ClearSelectedItems();
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewAccounts.getSelectionModel().getSelectedItems().size() + ")");
                this.togglebuttonUnselectAll.setSelected(false);
            }
        }));

        ///Chức năng reset khi nhấn delete///
        this.textfieldAddAccDisplayname.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddAccUsername.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldCADisplaynameChange.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((JFXTextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditAccDisplayname.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditAccUsername.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindAccDisplayname.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindAccUsername.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });

        this.passwordfieldAddAcc.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((PasswordField) event.getSource()).setText("");
            }
        });
        this.passwordfieldCAPasswordChange.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((JFXPasswordField) event.getSource()).setText("");
            }
        });
        this.passwordfieldCARePassword.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((JFXPasswordField) event.getSource()).setText("");
            }
        });
        this.passwordfieldEditAcc.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((PasswordField) event.getSource()).setText("");
            }
        });

        this.comboboxAddAccMaGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxAddAccType.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditAccMaGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditAccType.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxFindAccMaGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxFindAccType.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });

        ///Reset data các from chức năng///
        this.ResetDataAddAccount();
        this.ResetDataFindAccount();
        this.ResetDataEditAccount();

        //Ẩn textfiela password khi mở form///
        this.SetShowPasswordDisplay(false);

        //Ần borderpane account list menu khi mở form///
        this.HideBorderpaneMenuAccounts(true);
    }
}
