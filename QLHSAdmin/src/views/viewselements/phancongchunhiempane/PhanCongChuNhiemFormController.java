/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.phancongchunhiempane;

import com.jfoenix.controls.JFXButton;
import controllers.ChuNhiemController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import models.ChuNhiem;
import models.GiaoVien;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class PhanCongChuNhiemFormController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private ComboBox<GiaoVien> comboboxGVs;
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
    private final ObservableList<GiaoVien> olGiaoViens = FXCollections.observableArrayList();
    private final ObjectProperty<ChuNhiem> chunhiemProperty = new SimpleObjectProperty<>();

    private void HideMenu(boolean isHide) {
        isHide = !isHide;
        this.vboxMenu.setVisible(isHide);
        this.vboxMenu.setManaged(isHide);
    }

    private void HideSaveButton(boolean isHide) {
        isHide = !isHide;
        this.buttonSave.setVisible(isHide);
        this.buttonSave.setManaged(isHide);
    }

    private void Reload() {
        this.comboboxGVs.getItems().clear();
        this.comboboxGVs.getItems().addAll(this.olGiaoViens);

        if (this.chunhiemProperty.get() != null) {
            for (GiaoVien giaoVien : olGiaoViens) {
                if (giaoVien.getMaGV().equals(this.chunhiemProperty.get().getMaGV())) {
                    this.comboboxGVs.getSelectionModel().select(giaoVien);
                    break;
                }
            }
        }

        this.ChangeViewsFollowsMaGV(this.comboboxGVs.getSelectionModel().getSelectedItem() != null);

        this.HideSaveButton(true);
    }

    private void ChangeViewsFollowsMaGV(boolean isFounded) {
        if (isFounded) {
            this.borderpaneRoot.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        } else {
            this.borderpaneRoot.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }

    public ObjectProperty<STATUS> getStatusProperty() {
        return statusProperty;
    }

    public ObservableList<GiaoVien> getOlGiaoViens() {
        return olGiaoViens;
    }

    public ReadOnlyObjectProperty<GiaoVien> getGiaovienSelectionProperty() {
        return this.comboboxGVs.getSelectionModel().selectedItemProperty();
    }

    public ObjectProperty<ChuNhiem> getChunhiemProperty() {
        return chunhiemProperty;
    }

    public void setData(ObservableList<GiaoVien> alGiaoViens, ChuNhiem chunhiemProperty) {
        this.olGiaoViens.clear();
        this.olGiaoViens.addAll(alGiaoViens);

        this.chunhiemProperty.set(chunhiemProperty);
        this.HideSaveButton(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Binding từ chunhiem sang giaovien///
        this.chunhiemProperty.addListener((observable) -> {
            this.Reload();
        });

        ///Binding dữ liệu tử observable qua combobox///
        this.olGiaoViens.addListener((ListChangeListener.Change<? extends GiaoVien> c) -> {
            this.comboboxGVs.getItems().clear();
            this.comboboxGVs.getItems().addAll(this.olGiaoViens);
        });
        
        this.comboboxGVs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.ChangeViewsFollowsMaGV(newValue != null);
        });

        ///Chức năng reload///
        this.buttonReload.setOnAction((event) -> {
            this.Reload();
        });

        ///Chức năng Delete///
        this.buttonDelete.setOnAction((event) -> {
            this.statusProperty.set(STATUS.DELETE);
        });

        ///Chức năng Save///
        this.buttonSave.setOnAction((event) -> {
            if (this.chunhiemProperty.get() != null) {
                GiaoVien giaoVien = this.getGiaovienSelectionProperty().get();
                if (giaoVien != null) {

                    ChuNhiem chuNhiem = (ChuNhiem) this.chunhiemProperty.get().clone();
                    chuNhiem.setMaGV(this.comboboxGVs.getSelectionModel().getSelectedItem().getMaGV());

                    if (chuNhiem.equalsAll(this.chunhiemProperty.get()) == false) {
                        if (ChuNhiemController.getInstance()
                                .Search(this.chunhiemProperty.get())
                                .size() > 0) {

                            if (ChuNhiemController.getInstance().Edit(this.chunhiemProperty.get(), chuNhiem)) {
                                this.chunhiemProperty.set(chuNhiem);
                                this.HideSaveButton(true);
                            }

                        } else {
                            if (ChuNhiemController.getInstance().Add(chuNhiem)) {
                                this.chunhiemProperty.set(chuNhiem);
                                this.HideSaveButton(true);
                            }
                        }
                    }
                }
            }
        });

        ///Chức năng hiện nút Save///
        this.comboboxGVs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.HideSaveButton(false);
        });

        ///Ẩn nút Save///
        this.HideSaveButton(true);

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }
}
