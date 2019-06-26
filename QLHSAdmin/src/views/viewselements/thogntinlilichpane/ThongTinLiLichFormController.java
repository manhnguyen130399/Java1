/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.thogntinlilichpane;

import com.jfoenix.controls.JFXButton;
import controllers.COController;
import controllers.PhuHuynhController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.CO;
import models.PhuHuynh;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class ThongTinLiLichFormController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private TextField textfieldMaPH;
    @FXML
    private TextField textfieldTenPH;
    @FXML
    private TextField textfieldDiaChi;
    @FXML
    private TextField textfieldSDT;
    @FXML
    private TextField textfieldLoai;
    @FXML
    private VBox vboxMenu;
    @FXML
    private JFXButton buttonReload;
    @FXML
    private JFXButton buttonSave;
    @FXML
    private JFXButton buttonDelete;

    public static enum STATUS {
        READY, DELETE, NONE
    }

    private final ObjectProperty<STATUS> statusProperty = new SimpleObjectProperty<>(STATUS.NONE);
    private final ObjectProperty<PhuHuynh> phuhuynhProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<CO> coProperty = new SimpleObjectProperty<>(null);

    private void HideMenu(boolean isHide) {
        isHide = !isHide;
        this.vboxMenu.setVisible(isHide);
        this.vboxMenu.setManaged(isHide);
    }

    private void ReloadPhuHuynh() {
        PhuHuynh phuHuynh = this.phuhuynhProperty.get();
        if (phuHuynh != null) {
            this.textfieldDiaChi.setText(phuHuynh.getDiaChi());
            this.textfieldMaPH.setText(phuHuynh.getMaPH());
            this.textfieldSDT.setText(phuHuynh.getSDT());
            this.textfieldTenPH.setText(phuHuynh.getTenPH());
        }
    }

    private void ReloadCo() {
        CO co = this.coProperty.get();
        if (co != null) {
            this.textfieldLoai.setText(co.getLoai());
        }
    }

    private boolean CheckChange() {
        PhuHuynh phuHuynh = this.phuhuynhProperty.get();
        if (phuHuynh != null) {
            if (this.textfieldDiaChi.getText().equals(phuHuynh.getDiaChi()) == false
                    || this.textfieldMaPH.getText().equals(phuHuynh.getMaPH()) == false
                    || this.textfieldSDT.getText().equals(phuHuynh.getSDT()) == false
                    || this.textfieldTenPH.getText().equals(phuHuynh.getTenPH()) == false) {
                return false;
            }
        }

        CO co = this.coProperty.get();
        if (co != null) {
            if (this.textfieldLoai.getText().equals(co.getLoai()) == false) {
                return false;
            }
        }

        return true;
    }

    private void HideSaveButton(boolean isHide) {
        isHide = !isHide;
        this.buttonSave.setVisible(isHide);
        this.buttonSave.setManaged(isHide);
    }

    public ObjectProperty<STATUS> getStatusProperty() {
        return statusProperty;
    }

    public ObjectProperty<PhuHuynh> getPhuhuynhProperty() {
        return phuhuynhProperty;
    }

    public ObjectProperty<CO> getCoProperty() {
        return coProperty;
    }

    public void setPhuhuynh(PhuHuynh phuHuynh) {
        this.phuhuynhProperty.set(phuHuynh);
    }

    public void setCO(CO co) {
        this.coProperty.set(co);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Binding dữ liệu theo phuhuynhProperty///
        this.phuhuynhProperty.addListener((observable) -> {
            ReloadPhuHuynh();
        });

        ///Binding dữ liệu theo coProperty///
        this.coProperty.addListener((observable) -> {
            ReloadCo();
        });

        ///Chức năng reload///
        this.buttonReload.setOnAction((event) -> {
            ReloadPhuHuynh();
            ReloadCo();
        });

        ///Chức năng lưu///
        this.buttonSave.setOnAction((event) -> {
            if (this.phuhuynhProperty.get() != null) {
                PhuHuynh phuHuynh = (PhuHuynh) this.phuhuynhProperty.get().clone();

                if (this.textfieldDiaChi.getText().isEmpty() == false) {
                    phuHuynh.setDiaChi(this.textfieldDiaChi.getText());
                }
                if (this.textfieldMaPH.getText().isEmpty() == false) {
                    phuHuynh.setMaPH(this.textfieldMaPH.getText());
                }
                if (this.textfieldSDT.getText().isEmpty() == false) {
                    phuHuynh.setSDT(this.textfieldSDT.getText());
                }
                if (this.textfieldTenPH.getText().isEmpty() == false) {
                    phuHuynh.setTenPH(this.textfieldTenPH.getText());
                }

                if (this.phuhuynhProperty.get().equalsAll(phuHuynh) == false) {
                    //Lưu dữ liệu xuống CSDL
                    PhuHuynhController.getInstance().Edit(this.phuhuynhProperty.get(), phuHuynh);
                    this.phuhuynhProperty.set(phuHuynh);
                }
            }

            if (this.coProperty.get() != null) {
                CO co = (CO) this.coProperty.get().clone();

                if (this.textfieldLoai.getText().isEmpty() == false) {
                    co.setLoai(this.textfieldLoai.getText());
                }

                if (this.coProperty.get().equalsAll(co) == false) {
                    //Lưu dữ liệu xuống CSDL
                    COController.getInstance().Edit(this.coProperty.get(), co);
                    this.coProperty.set(co);
                }
            }
            
            this.HideSaveButton(true);
        });

        ///Chức năng Delete///
        this.buttonDelete.setOnAction((event) -> {
            this.statusProperty.set(STATUS.DELETE);
        });

        ///Chức năng nhấn phím delete để reload dữ liệu///
        this.textfieldDiaChi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                PhuHuynh phuHuynh = this.phuhuynhProperty.get();
                if (phuHuynh != null) {
                    this.textfieldDiaChi.setText(phuHuynh.getDiaChi());
                }
            }
        });
        this.textfieldLoai.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                CO co = this.coProperty.get();
                if (co != null) {
                    this.textfieldLoai.setText(co.getLoai());
                }
            }
        });
        this.textfieldMaPH.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                PhuHuynh phuHuynh = this.phuhuynhProperty.get();
                if (phuHuynh != null) {
                    this.textfieldMaPH.setText(phuHuynh.getMaPH());
                }
            }
        });
        this.textfieldSDT.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                PhuHuynh phuHuynh = this.phuhuynhProperty.get();
                if (phuHuynh != null) {
                    this.textfieldSDT.setText(phuHuynh.getSDT());
                }
            }
        });
        this.textfieldTenPH.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                PhuHuynh phuHuynh = this.phuhuynhProperty.get();
                if (phuHuynh != null) {
                    this.textfieldTenPH.setText(phuHuynh.getTenPH());
                }
            }
        });

        ///Chức năng hiện nút save///
        this.textfieldDiaChi.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(this.phuhuynhProperty.get().getDiaChi()) == false) {
                    if (this.buttonSave.isVisible() == false) {
                        this.HideSaveButton(false);
                    }
                }
            }
        });
        this.textfieldMaPH.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(this.phuhuynhProperty.get().getMaPH()) == false) {
                    if (this.buttonSave.isVisible() == false) {
                        this.HideSaveButton(false);
                    }
                }
            }
        });
        this.textfieldSDT.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(this.phuhuynhProperty.get().getSDT()) == false) {
                    if (this.buttonSave.isVisible() == false) {
                        this.HideSaveButton(false);
                    }
                }
            }
        });
        this.textfieldTenPH.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(this.phuhuynhProperty.get().getTenPH()) == false) {
                    if (this.buttonSave.isVisible() == false) {
                        this.HideSaveButton(false);
                    }
                }
            }
        });
        this.textfieldLoai.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(this.coProperty.get().getLoai()) == false) {
                    if (this.buttonSave.isVisible() == false) {
                        this.HideSaveButton(false);
                    }
                }
            }
        });

        ///Ẩn nút Save///
        this.HideSaveButton(true);

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }
}
