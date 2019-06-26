/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewscontroller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Accounts;
import views.loadingscreen.LoadingScreenController;
import views.loginform.LoginFormController;
import views.mainmenu.MainMenuFormController;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class MainWindowController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;

    @FXML
    private StackPane stackpaneContent;

    @FXML
    private BorderPane borderpaneContent;

    @FXML
    private JFXButton buttonMinimize;

    @FXML
    private JFXButton buttonMaximize;

    @FXML
    private MaterialDesignIconView mdivMaximize;

    @FXML
    private JFXButton buttonClose;

    @FXML
    private JFXButton buttonResize;

    @FXML
    private JFXButton buttonMove;

    private static enum STATUS {
        READY,
        LOGIN, LOADING_SCREEN,
        UPDATE_INFORMATION,
        ERROR,
        NONE
    }

    private double doubleMousePressedX;
    private double doubleMousePressedY;
    private double doubleMousePressedStageWidth;
    private double doubleMousePressedStageHeight;
    private double doubleMouseSceneOffsetX;
    private double doubleMouseSceneOffsetY;
    private boolean isOnResize;
    private Stage stage;

    private BorderPane LoginForm;
    private BorderPane LoadingScreen;
    private BorderPane MainMenuForm;

    private LoginFormController loginFormController;
    private LoadingScreenController loadingScreenController;
    private MainMenuFormController mainMenuFormController;

    private final ObjectProperty<STATUS> statusProperty = new SimpleObjectProperty<>(STATUS.NONE);
    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Button close event///
        this.buttonClose.setOnAction((event) -> {
            if (this.stage == null) {
                this.stage = (Stage) this.buttonClose.getScene().getWindow();
            }
            this.stage.close();
        });

        ///Button minimize event///
        this.buttonMinimize.setOnAction((event) -> {
            if (this.stage == null) {
                this.stage = (Stage) this.buttonMinimize.getScene().getWindow();
            }
            this.stage.setIconified(true);
        });

        ///Button maximize event///
        this.buttonMaximize.setOnAction((event) -> {
            if (this.stage == null) {
                this.stage = (Stage) this.buttonMaximize.getScene().getWindow();
            }

            if (this.stage.isFullScreen()) {
                this.stage.setFullScreen(false);
                this.mdivMaximize.setGlyphName("WINDOW_MAXIMIZE");
            } else {
                this.stage.setFullScreen(true);
                this.mdivMaximize.setGlyphName("WINDOW_RESTORE");
            }
        });

        ///Khởi gán giá trị các biến///
        this.isOnResize = false;

        try {
            FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("/views/loginform/LoginForm.fxml"));
            this.LoginForm = fxmll.load();
            this.loginFormController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("/views/loadingscreen/LoadingScreen.fxml"));
            this.LoadingScreen = fxmll.load();
            this.loadingScreenController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("/views/mainmenu/MainMenuForm.fxml"));
            this.MainMenuForm = fxmll.load();
            this.mainMenuFormController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ///Binding dữ liệu account và hoc kì năm học với main menu///
        this.mainMenuFormController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.mainMenuFormController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.mainMenuFormController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.mainMenuFormController.getCurrentAccountProperty().get()) == false) {
                this.mainMenuFormController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        ///Di chuyển screen khi nhấn và kéo thả trên nút buttonMove///
        this.buttonMove.setOnMousePressed((event) -> {
            this.doubleMouseSceneOffsetX = event.getSceneX();
            this.doubleMouseSceneOffsetY = event.getSceneY();
        });
        this.buttonMove.setOnMouseDragged((event) -> {
            if (this.stage == null) {
                this.stage = (Stage) this.buttonMove.getScene().getWindow();
            }

            double x = event.getScreenX();
            double y = event.getScreenY();

            if (y <= 0 || x <= 0) {
                this.stage.setX(0);
                this.stage.setY(0);
                this.stage.setFullScreen(true);
                this.mdivMaximize.setGlyphName("WINDOW_RESTORE");
            } else {
                if (this.stage.isFullScreen()) {
                    this.stage.setFullScreen(false);
                    this.mdivMaximize.setGlyphName("WINDOW_MAXIMIZE");

                    this.doubleMouseSceneOffsetX = this.stage.getWidth() / 2.0;
                    this.doubleMouseSceneOffsetY = 10;
                }

                this.stage.setX(x - this.doubleMouseSceneOffsetX);
                this.stage.setY(y - this.doubleMouseSceneOffsetY);
            }
        });

        ///Chức năng resize////
        this.buttonResize.setOnMousePressed((event) -> {
            this.isOnResize = true;
            this.doubleMousePressedX = event.getScreenX();
            this.doubleMousePressedY = event.getScreenY();

            if (this.stage == null) {
                this.stage = (Stage) (this.borderpaneRoot.getScene().getWindow());
            }

            this.doubleMousePressedStageWidth = this.stage.getWidth();
            this.doubleMousePressedStageHeight = this.stage.getHeight();
        });
        this.buttonResize.setOnMouseDragged((event) -> {
            if (this.isOnResize) {
                double newWidth = this.doubleMousePressedStageWidth + event.getScreenX() - this.doubleMousePressedX;
                double newHeight = this.doubleMousePressedStageHeight + event.getScreenY() - this.doubleMousePressedY;

                if (newWidth >= this.borderpaneRoot.getMinWidth()) {
                    this.stage.setWidth(newWidth);
                }

                if (newHeight >= this.borderpaneRoot.getMinHeight()) {
                    this.stage.setHeight(newHeight);
                }
            }
        });
        this.buttonResize.setOnMouseReleased((event) -> {
            this.isOnResize = false;
            this.doubleMousePressedX = 0;
            this.doubleMousePressedY = 0;
        });

        ///Sự kiện thay đổi status///
        this.statusProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READY:
                    this.statusProperty.set(STATUS.LOGIN);
                    break;
                case LOGIN:
                    this.loginFormController.getStatusProperty().set(LoginFormController.STATUS.READY);
                    this.borderpaneContent.setCenter(this.LoginForm);
                    break;
                case LOADING_SCREEN:
                    this.borderpaneContent.setCenter(this.LoadingScreen);
                    this.loadingScreenController.RunProgress();
                    break;
                case UPDATE_INFORMATION:
                    this.borderpaneContent.setCenter(this.MainMenuForm);
                    break;
                case ERROR:
                    break;
                case NONE:
                    break;
                default:
                    throw new AssertionError(newValue.name());
            }
        });

        this.loginFormController.getStatusProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READY:
                    break;
                case LOGIN_SUCCESS:
                    this.currentAccountProperty.set(this.loginFormController.getAccounts());
                    this.loadingScreenController.setAccountsLogined(this.loginFormController.getAccounts());
                    this.statusProperty.set(STATUS.LOADING_SCREEN);
                    break;
                case LOGIN_FAIL:
                    break;
                case ERROR:
                    break;
                case NONE:
                    break;
                default:
                    throw new AssertionError(newValue.name());
            }
        });

        this.loadingScreenController.getStatusProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READY:
                    break;
                case RUNNING:
                    break;
                case ERROR:
                    break;
                case CANCEL:
                    this.statusProperty.set(STATUS.LOGIN);
                    break;
                case COMPLETE:
                    this.statusProperty.set(STATUS.UPDATE_INFORMATION);
                    break;
                case NONE:
                    break;
                default:
                    throw new AssertionError(newValue.name());
            }
        });

        this.mainMenuFormController.getStatusProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READY:
                    break;
                case RUNNING:
                    break;
                case BACK:
                    this.statusProperty.set(STATUS.LOGIN);
                    this.mainMenuFormController.getStatusProperty().set(MainMenuFormController.STATUS.READY);
                    break;
                case NONE:
                    break;
                default:
                    throw new AssertionError(newValue.name());
            }
        });

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }
}
