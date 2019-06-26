/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.chitietkiemtra;

import com.jfoenix.controls.JFXButton;
import controllers.GiaoVienController;
import controllers.HocKi_NamHocController;
import controllers.KTController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.GiaoVien;
import models.HocSinh;
import models.KT;
import models.Lop;
import models.Model;
import models.MonHoc;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class ChiTietKiemTraController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private VBox vboxDiems;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXButton buttonReset;
    @FXML
    private JFXButton buttonDelete;
    @FXML
    private Label labelMonHoc;

    public static enum STATUS {
        READY, DELETE, NONE
    }

    private final ObjectProperty<STATUS> statusProperty = new SimpleObjectProperty<>(null);
    private final ObservableList<KT> olKts = FXCollections.observableArrayList();
    private final ObjectProperty<Lop> lopProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<MonHoc> monhocProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<HocSinh> hocsinhProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<GiaoVien> giaovienProperty = new SimpleObjectProperty<>(null);

    private HBox CreateNewDiem(KT kt) {
        try {
            FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("DiemKiemTra.fxml"));
            HBox hboxDiem = fxmll.load();
            DiemKiemTraController hboxDiemController = fxmll.getController();

            hboxDiemController.setKtProperty(kt);
            hboxDiemController.HideButtonSave(true);
            hboxDiemController.getStatusProperty().addListener((observable, oldValue, newValue) -> {
                switch (newValue) {
                    case READY:
                        break;
                    case DELETE:
                        if (KTController.getInstance().Delete(kt)) {
                            this.vboxDiems.getChildren().remove(hboxDiem);
                            this.olKts.remove(kt);
                        }
                        break;
                    case NONE:
                        break;
                    default:
                        throw new AssertionError(newValue.name());
                }
            });

            this.vboxDiems.getChildren().add(hboxDiem);

            return hboxDiem;
        } catch (IOException ex) {
            Logger.getLogger(ChiTietKiemTraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void CreateNewKts() {
        GiaoVien giaoVien = (GiaoVien) GiaoVienController.getInstance()
                .GetModel(new GiaoVien(this.giaovienProperty.get().getMaGV()));
        this.olKts.setAll(
                (ArrayList<KT>) (ArrayList<? extends Model>) KTController.getInstance().Search(new KT(
                        monhocProperty.get().getMaMH(),
                        giaoVien.getMaGV(),
                        lopProperty.get().getMaLop(),
                        HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                        HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi(),
                        hocsinhProperty.get().getMaHS(),
                        null
                )));
    }

    public ObjectProperty<STATUS> getStatusProperty() {
        return statusProperty;
    }

    public void setStatusProperty(STATUS statusProperty) {
        this.statusProperty.set(statusProperty);
    }

    public ObservableList<KT> getOlKts() {
        return olKts;
    }

    public void setKts(ArrayList<KT> olKts) {
        this.olKts.clear();
        this.olKts.addAll(olKts);
    }

    public void setKts(ObservableList<KT> olKts) {
        this.olKts.clear();
        this.olKts.addAll(olKts);
    }

    public ObjectProperty<Lop> getLopProperty() {
        return lopProperty;
    }

    public void setLopProperty(Lop lop) {
        this.lopProperty.set(lop);
    }

    public ObjectProperty<MonHoc> getMonhocProperty() {
        return monhocProperty;
    }

    public void setMonhocProperty(MonHoc monhoc) {
        this.monhocProperty.set(monhoc);
    }

    public ObjectProperty<HocSinh> getHocsinhProperty() {
        return hocsinhProperty;
    }

    public ObjectProperty<GiaoVien> getGiaovienProperty() {
        return giaovienProperty;
    }

    public void setHocSinhProperty(HocSinh hocSinh) {
        this.hocsinhProperty.set(hocSinh);
    }

    public void setGiaoVienProperty(GiaoVien giaoVien) {
        this.giaovienProperty.set(giaoVien);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ///Chức năng thêm điểm///
        this.buttonAdd.setOnAction((event) -> {
            KT kt = new KT(
                    this.monhocProperty.get().getMaMH(),
                    "Kiểm tra " + Integer.toString(new Random().nextInt(1000000000)),
                    0.0,
                    this.giaovienProperty.get().getMaGV(),
                    this.lopProperty.get().getMaLop(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi(),
                    0.0,
                    this.hocsinhProperty.get().getMaHS());

            if (KTController.getInstance().Add(kt)) {
                this.olKts.add(kt);
            }
        });

        ///Chức năng reset///
        this.buttonReset.setOnAction((event) -> {
            this.vboxDiems.getChildren().clear();
            this.CreateNewKts();
        });

        ///Chức năng xóa///
        this.buttonDelete.setOnAction((event) -> {
            this.setStatusProperty(STATUS.DELETE);
        });

        ///Binding data trong olKTs với số lượng form diem///
        this.olKts.addListener((ListChangeListener.Change<? extends KT> c) -> {
            if (c.next()) {
                for (KT kt : (List<KT>) c.getAddedSubList()) {
                    this.CreateNewDiem(kt);
                }
            }
        });

        ///Bingding data môn học với views///
        this.monhocProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.labelMonHoc.setText(newValue.toString());
            } else {
                this.labelMonHoc.setText("null");
            }
        });

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }

}
