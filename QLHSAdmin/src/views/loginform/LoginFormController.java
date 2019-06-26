/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.loginform;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import controllers.AccountsController;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import models.Accounts;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class LoginFormController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private JFXTextField jfxtextfieldUsername;
    @FXML
    private JFXTextField jfxtextfieldPassword;
    @FXML
    private JFXPasswordField jfxpasswordfieldPassword;
    @FXML
    private JFXButton buttonShowPassword;
    @FXML
    private MaterialDesignIconView mdivShowPassword;
    @FXML
    private JFXButton buttonReset;
    @FXML
    private JFXButton buttonLogin;

    public static enum STATUS {
        READY, LOGIN_SUCCESS, LOGIN_FAIL, ERROR, NONE
    }
    private final BooleanProperty isShowPassword = new SimpleBooleanProperty(false);
    private final ObjectProperty<STATUS> statusProperty = new SimpleObjectProperty<>(STATUS.NONE);
    private Accounts accounts;

    private void Login() {
        this.jfxtextfieldUsername.validate();
        this.jfxpasswordfieldPassword.validate();
        this.jfxtextfieldPassword.validate();

        String username = this.jfxtextfieldUsername.getText();
        String password = this.jfxpasswordfieldPassword.getText();
        if (isShowPassword.get()) {
            password = this.jfxtextfieldPassword.getText();
        }

        if (username.isEmpty() == false && password.isEmpty() == false) {
            try {
                accounts = AccountsController.getInstance().Login(username, password);

                if (accounts != null) {
//                    if (accounts.getType().equals("admin")) {
                        this.statusProperty.set(STATUS.LOGIN_SUCCESS);
//                    } else {
//                        this.statusProperty.set(STATUS.LOGIN_FAIL);
//                    }
                } else {
                    this.statusProperty.set(STATUS.LOGIN_FAIL);
                }
            } catch (Exception e) {
                this.statusProperty.set(STATUS.ERROR);
            }
        }
    }

    private void Reset() {
        this.isShowPassword.set(false);
        this.jfxpasswordfieldPassword.setText("");
        this.jfxtextfieldPassword.setText("");
        this.jfxtextfieldUsername.setText("");
    }

    private void HidePasswordfield(boolean isHide) {
        isHide = !isHide;
        this.jfxpasswordfieldPassword.setVisible(isHide);
        this.jfxpasswordfieldPassword.setManaged(isHide);
    }

    private void HideTextfieldPassword(boolean isHide) {
        isHide = !isHide;
        this.jfxtextfieldPassword.setVisible(isHide);
        this.jfxtextfieldPassword.setManaged(isHide);
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public ObjectProperty<STATUS> getStatusProperty() {
        return statusProperty;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Chức năng showpassword///
        this.buttonShowPassword.setOnAction((event) -> {
            this.isShowPassword.set(!this.isShowPassword.get());
        });
        this.isShowPassword.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.mdivShowPassword.setGlyphName("EYE");
                this.HidePasswordfield(true);
                this.HideTextfieldPassword(false);
                this.jfxtextfieldPassword.setText(this.jfxpasswordfieldPassword.getText());
                this.jfxpasswordfieldPassword.setText("");
            } else {
                this.mdivShowPassword.setGlyphName("EYE_OFF");
                this.HidePasswordfield(false);
                this.HideTextfieldPassword(true);
                this.jfxpasswordfieldPassword.setText(this.jfxtextfieldPassword.getText());
                this.jfxtextfieldPassword.setText("");
            }
            this.jfxpasswordfieldPassword.validate();
            this.jfxtextfieldPassword.validate();
        });
        this.HideTextfieldPassword(true);

        ///Chức năng Reset///
        this.buttonReset.setOnAction((event) -> {
            this.Reset();
        });

        ///Chức năng Login///
        this.buttonLogin.setOnAction((event) -> {
            this.Login();
        });

        ///Chức năng nhấn Enter để thực thi lệnh///
        this.jfxpasswordfieldPassword.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.Login();
            }
        });
        this.jfxtextfieldPassword.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.Login();
            }
        });

        this.jfxtextfieldUsername.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.Login();
            }
        });
        this.buttonShowPassword.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.isShowPassword.set(!this.isShowPassword.get());
            }
        });
        this.buttonLogin.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.Login();
            }
        });
        this.buttonReset.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.Reset();
            }
        });

        ///Chức năng validator - kiểm tra tính đúng của dữ liệu///
        this.jfxpasswordfieldPassword.getValidators().add(new RequiredFieldValidator("Password must not empty"));
        this.jfxpasswordfieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            this.jfxpasswordfieldPassword.validate();
        });
        this.jfxtextfieldPassword.getValidators().add(new RequiredFieldValidator("Password must not empty"));
        this.jfxtextfieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            this.jfxtextfieldPassword.validate();
        });
        this.jfxtextfieldUsername.getValidators().add(new RequiredFieldValidator("Username must not empty"));
        this.jfxtextfieldUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            this.jfxtextfieldUsername.validate();
        });
        this.jfxpasswordfieldPassword.getValidators().add(new RequiredFieldValidator("Password must not empty"));
        this.jfxpasswordfieldPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == false) {
                this.jfxpasswordfieldPassword.validate();
            }
        });
        this.jfxtextfieldPassword.getValidators().add(new RequiredFieldValidator("Password must not empty"));
        this.jfxtextfieldPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == false) {
                this.jfxtextfieldPassword.validate();
            }
        });
        this.jfxtextfieldUsername.getValidators().add(new RequiredFieldValidator("Username must not empty"));
        this.jfxtextfieldUsername.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == false) {
                this.jfxtextfieldUsername.validate();
            }
        });
        this.jfxtextfieldPassword.validate();

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }

}
