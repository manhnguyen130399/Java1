/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.loadingscreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import controllers.SessionDataController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import models.Accounts;
import models.HocKi_NamHoc;
import models.SessionData;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class LoadingScreenController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private JFXProgressBar jfxprogressbarProgress;
    @FXML
    private TextField textfieldProgressContent;
    @FXML
    private JFXButton buttonCancel;

    public static enum STATUS {
        READY, RUNNING, ERROR, CANCEL, COMPLETE, NONE
    }

    private final ObjectProperty<STATUS> statusProperty = new SimpleObjectProperty<>(STATUS.NONE);
//    private SessionData sessionData;
    private Thread threadProgress;
    private Accounts accountsLogined;
    private HocKi_NamHoc hocKi_NamHoc;

    private void InitializeThreadProgress() {
        this.threadProgress = new Thread(() -> {
            try {
                StringProperty contentLoading = new SimpleStringProperty();
                DoubleProperty progressLoading = new SimpleDoubleProperty();

                contentLoading.addListener((observable, oldValue, newValue) -> {
                    Platform.runLater(() -> {
                        this.textfieldProgressContent.setText(newValue);
                    });
                });

                progressLoading.addListener((observable, oldValue, newValue) -> {
                    Platform.runLater(() -> {
                        jfxprogressbarProgress.setProgress((double) newValue);
                    });
                });

//                SessionDataController.getInstance().setContentLoading(contentLoading);
//                SessionDataController.getInstance().setProgressLoading(progressLoading);

//                if (this.accountsLogined == null && this.hocKi_NamHoc == null) {
//                    this.sessionData = SessionDataController.getInstance().getSessionData();
//                } else if (this.accountsLogined == null && this.hocKi_NamHoc != null) {
//                    this.sessionData = SessionDataController.getInstance()
//                            .getSessionData(this.hocKi_NamHoc.getNam(), this.hocKi_NamHoc.getHocKi());
//                } else if (this.accountsLogined != null && this.hocKi_NamHoc == null) {
//                    this.sessionData = SessionDataController.getInstance()
//                            .getSessionData(this.accountsLogined);
//                } else {
//                    this.sessionData = SessionDataController.getInstance()
//                            .getSessionData(
//                                    this.accountsLogined,
//                                    this.hocKi_NamHoc.getNam(),
//                                    this.hocKi_NamHoc.getHocKi()
//                            );
//                }
//
//                this.sessionData.setIsInitializing(true);
//                this.sessionData.setAccountsLogined(accountsLogined);
//                this.sessionData.setIsInitializing(false);

                this.textfieldProgressContent.setText("Starting program");
                int n = 100;
                for (int i = 0; i < n; i++) {
                    this.jfxprogressbarProgress.setProgress((i + 1) * 1.0 / n);
                    Thread.sleep(1);
                }

                Platform.runLater(() -> {
                    statusProperty.set(LoadingScreenController.STATUS.COMPLETE);
                });

                this.threadProgress.stop();
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setAccountsLogined(Accounts accountsLogined) {
        this.accountsLogined = accountsLogined;
    }

    public Accounts getAccountsLogined() {
        return accountsLogined;
    }

    public HocKi_NamHoc getHocKi_NamHoc() {
        return hocKi_NamHoc;
    }

    public void setHocKi_NamHoc(HocKi_NamHoc hocKi_NamHoc) {
        this.hocKi_NamHoc = hocKi_NamHoc;
    }

    public ObjectProperty<STATUS> getStatusProperty() {
        return statusProperty;
    }

//    public SessionData getSessionData() {
//        return sessionData;
//    }

    public void RunProgress() {
        this.statusProperty.set(STATUS.RUNNING);

        if (this.accountsLogined == null) {
            this.statusProperty.set(STATUS.ERROR);
            return;
        }

        this.InitializeThreadProgress();
        this.threadProgress.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Khởi tạo thread///
        this.InitializeThreadProgress();

        ///Chức năng cancel///
        this.buttonCancel.setOnAction((event) -> {
            this.threadProgress.stop();
//            this.sessionData.ClearData();
            this.statusProperty.set(STATUS.CANCEL);
        });

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }

}
