/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabkts;

import controllers.GiangDayController;
import controllers.GiaoVienController;
import controllers.HocController;
import controllers.HocKi_NamHocController;
import controllers.HocSinhController;
import controllers.KTController;
import controllers.LopController;
import controllers.MonHocController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import models.Accounts;
import models.GiangDay;
import models.GiaoVien;
import models.Hoc;
import models.HocSinh;
import models.KT;
import models.Lop;
import models.Model;
import models.MonHoc;
import views.viewselements.chitietkiemtra.ChiTietKiemTraController;

public class KTsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ObservableList<Lop> olLops = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Lop> comboboxChonLops;
    @FXML
    private ListView<HocSinh> listviewChonHocSinhs;
    @FXML
    private VBox vboxSuaDiem;

    private void SetDataToOlLops() {
        if (this.currentAccountProperty.get().getMaGV() != null) {
            this.olLops.clear();
            for (Model model
                    : GiangDayController.getInstance().Search(new GiangDay(
                            this.currentAccountProperty.get().getMaGV(),
                            null,
                            null,
                            HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                            HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()))) {
                GiangDay giangDay = (GiangDay) model;
                MonHoc monHoc = (MonHoc) MonHocController.getInstance().GetModel(new MonHoc(giangDay.getMaMH()));
                Lop lop = (Lop) LopController.getInstance().GetModel(new Lop(giangDay.getMaLop()));

                if (lop != null) {
                    if (olLops.contains(lop) == false) {
                        this.olLops.add(lop);
                    }
                }
            }
        }
    }

    private ArrayList<MonHoc> GetDataMonHocs(Lop lop) {
        ArrayList<MonHoc> alMonHocs = new ArrayList<>();
        if (this.currentAccountProperty.get().getMaGV() != null) {
            for (Model model
                    : GiangDayController.getInstance().Search(new GiangDay(
                            this.currentAccountProperty.get().getMaGV(),
                            null,
                            lop.getMaLop(),
                            HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                            HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()))) {
                GiangDay giangDay = (GiangDay) model;
                MonHoc monHoc = (MonHoc) MonHocController.getInstance().GetModel(new MonHoc(giangDay.getMaMH()));

                if (monHoc != null) {
                    alMonHocs.add(monHoc);
                }
            }
        }

        return alMonHocs;
    }

    private ArrayList<HocSinh> GetDataHocSinh(Lop lop) {
        ArrayList<HocSinh> alHocSinhs = new ArrayList<>();

        for (Model model
                : HocController.getInstance().Search(new Hoc(
                        lop.getMaLop(),
                        null,
                        HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                        HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()))) {
            Hoc hoc = (Hoc) model;
            HocSinh hocSinh = (HocSinh) HocSinhController.getInstance().GetModel(new HocSinh(hoc.getMaHs()));

            if (hocSinh != null) {
                alHocSinhs.add(hocSinh);
            }
        }

        return alHocSinhs;
    }

    private void AddNewDiemMonHoc(HocSinh hocSinh, Lop lop, MonHoc monHoc) {
        if (this.currentAccountProperty.get().getMaGV() != null) {
            try {
                GiaoVien giaoVien = (GiaoVien) GiaoVienController.getInstance()
                        .GetModel(new GiaoVien(this.currentAccountProperty.get().getMaGV()));

                FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("/views/viewselements/chitietkiemtra/ChiTietKiemTra.fxml"));
                this.vboxSuaDiem.getChildren().add(fxmll.load());

                ChiTietKiemTraController ctktc = (ChiTietKiemTraController) fxmll.getController();
                ctktc.setGiaoVienProperty(giaoVien);
                ctktc.setHocSinhProperty(hocSinh);
                ctktc.setLopProperty(lop);
                ctktc.setMonhocProperty(monHoc);
                ctktc.CreateNewKts();
            } catch (IOException ex) {
                Logger.getLogger(KTsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    public void RefreshAllData() {
        this.SetDataToOlLops();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Binding data khi account thay đổi///
        this.currentAccountProperty.addListener((observable, oldValue, newValue) -> {
            this.RefreshAllData();
        });

        ///Binding dữ liệu lớp khi thay đổi olLops///
        this.olLops.addListener((ListChangeListener.Change<? extends Lop> c) -> {
            this.comboboxChonLops.getItems().clear();

            if (c.next()) {
                this.comboboxChonLops.getItems().addAll(this.olLops);
            }
        });

        ///Binding khi chọn lớp trong combobox///
        this.comboboxChonLops.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.listviewChonHocSinhs.getItems().clear();

            if (newValue != null) {
                this.listviewChonHocSinhs.getItems().addAll(this.GetDataHocSinh(newValue));
            }
        });

        ///Binding khi chọn học sinh trong Listview///
        this.listviewChonHocSinhs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.vboxSuaDiem.getChildren().clear();

            if (newValue != null) {
                Lop lop = this.comboboxChonLops.getSelectionModel().getSelectedItem();

                for (MonHoc monHoc : this.GetDataMonHocs(lop)) {
                    this.AddNewDiemMonHoc(newValue, lop, monHoc);
                }
            }
        });
    }
}
