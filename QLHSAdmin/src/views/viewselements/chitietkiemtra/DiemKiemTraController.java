/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.chitietkiemtra;

import com.jfoenix.controls.JFXButton;
import controllers.KTController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import models.KT;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class DiemKiemTraController implements Initializable {

    @FXML
    private HBox hboxRoot;
    @FXML
    private TextField textfieldLoaiKiemTra;
    @FXML
    private TextField textfieldDiem;
    @FXML
    private JFXButton buttonDelete;
    @FXML
    private JFXButton buttonSave;
    @FXML
    private TextField textfieldHeSo;

    public static enum STATUS {
        READY, DELETE, NONE
    }

    private final ObjectProperty<STATUS> statusProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<KT> ktProperty = new SimpleObjectProperty<>(null);

    public void HideButtonSave(boolean isHide) {
        isHide = !isHide;
        this.buttonSave.setVisible(isHide);
        this.buttonSave.setManaged(isHide);
    }

    public ObjectProperty<STATUS> getStatusProperty() {
        return statusProperty;
    }

    public void setStatusProperty(STATUS statusProperty) {
        this.statusProperty.set(statusProperty);
    }

    public ObjectProperty<KT> getKtProperty() {
        return ktProperty;
    }

    public void setKtProperty(KT kt) {
        this.ktProperty.set(kt);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Chức năng xóa điểm///
        this.buttonDelete.setOnAction((event) -> {
            this.statusProperty.set(STATUS.DELETE);
        });

        ///Binding dữ liệu kt///
        this.ktProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.textfieldLoaiKiemTra.setText(newValue.getLoaiKT());
                this.textfieldDiem.setText(Double.toString(newValue.getDiem()));
                this.textfieldHeSo.setText(Double.toString(newValue.getHeSo()));
            }
        });

        ///Binding hiện nút Save///
        this.textfieldLoaiKiemTra.textProperty().addListener((observable, oldValue, newValue) -> {
            this.HideButtonSave(newValue.isEmpty());
        });
        this.textfieldDiem.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Double.parseDouble(newValue);
                this.HideButtonSave(false);
            } catch (NumberFormatException ex) {
//                Logger.getLogger(DiemKiemTraController.class.getName()).log(Level.SEVERE, null, ex);
                this.textfieldDiem.setText("0");
            }
        });
        this.textfieldHeSo.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Double.parseDouble(newValue);
                this.HideButtonSave(false);
            } catch (NumberFormatException ex) {
//                Logger.getLogger(DiemKiemTraController.class.getName()).log(Level.SEVERE, null, ex);
                this.textfieldHeSo.setText("0");
            }
        });

        ///Chức năng Save///
        this.buttonSave.setOnAction((event) -> {
            KT newKt = (KT) this.ktProperty.get().clone();

            newKt.setLoaiKT(this.textfieldLoaiKiemTra.getText());
            newKt.setDiem(Double.parseDouble(this.textfieldDiem.getText()));
            newKt.setHeSo(Double.parseDouble(this.textfieldHeSo.getText()));

            if (KTController.getInstance().Edit(this.ktProperty.get(), newKt)) {
                this.HideButtonSave(true);
            }
        });
        
        ///Ẩn nút save///
        this.HideButtonSave(true);

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }

}
