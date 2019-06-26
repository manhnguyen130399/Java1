/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabkqs;

import controllers.ChuNhiemController;
import controllers.DataController;
import controllers.GiangDayController;
import controllers.HanhKiemController;
import controllers.HocController;
import controllers.HocKi_NamHocController;
import controllers.HocLucController;
import controllers.HocSinhController;
import controllers.KQController;
import controllers.KTController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import models.Accounts;
import models.ChuNhiem;
import models.GiangDay;
import models.HanhKiem;
import models.Hoc;
import models.HocKi_NamHoc;
import models.HocLuc;
import models.HocSinh;
import models.KQ;
import models.KT;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class KQsTableController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ObservableList<KQ> olKqs = FXCollections.observableArrayList();

    @FXML
    private TableView<KQ> tableView;
    @FXML
    private TableColumn<KQ, HocSinh> tableColumnMAHS;
    @FXML
    private TableColumn<KQ, HocLuc> tableColumnHOCLUC;
    @FXML
    private TableColumn<KQ, HanhKiem> tableColumnHANHKIEM;
    @FXML
    private TableColumn<KQ, HocKi_NamHoc> tableColumnNAMHocKi;
    @FXML
    private TableColumn<KQ, Double> tableColumnDTB;

    private void InitializeColumnMAHS() {
        this.tableColumnMAHS.setCellValueFactory(((TableColumn.CellDataFeatures<KQ, HocSinh> param) -> {
            return new SimpleObjectProperty<>(
                    (HocSinh) HocSinhController.getInstance()
                            .GetModel(new HocSinh(param.getValue().getMaHS())));
        }));
    }

    private void InitializeColumnHOCLUC() {
        this.tableColumnHOCLUC.setCellValueFactory(((TableColumn.CellDataFeatures<KQ, HocLuc> param) -> {
            return new SimpleObjectProperty<>(
                    (HocLuc) HocLucController.getInstance()
                            .GetModel(new HocLuc(param.getValue().getMaLoaiHL())));
        }));
    }

    private void InitializeColumnHANHKIEM() {
        this.tableColumnHANHKIEM.setCellValueFactory(((TableColumn.CellDataFeatures<KQ, HanhKiem> param) -> {
            return new SimpleObjectProperty<>(
                    (HanhKiem) HanhKiemController.getInstance()
                            .GetModel(new HanhKiem(param.getValue().getLoaiHK())));
        }));
    }

    private void InitializeColumnNAM() {
        this.tableColumnNAMHocKi.setCellValueFactory(((TableColumn.CellDataFeatures<KQ, HocKi_NamHoc> param) -> {
            return new SimpleObjectProperty<>(new HocKi_NamHoc(param.getValue().getNam(), param.getValue().getHocKi()));
        }));
    }

    private void InitializeColumnDTB() {
        this.tableColumnDTB.setCellValueFactory(((TableColumn.CellDataFeatures<KQ, Double> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getDTB());
        }));
    }

    public KQ getFocusedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public KQ getSelectedItem() {
        return this.tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return this.tableView.getSelectionModel().getSelectedIndex();
    }

    public ObservableList<KQ> getSelectedItems() {
        return this.tableView.getSelectionModel().getSelectedItems();
    }

    public TableView<KQ> getRoot() {
        return tableView;
    }

    public TableView<KQ> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<KQ> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<KQ> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(KQ newAccount) {
        boolean result = KQController.getInstance().Edit(newAccount, newAccount);
        if (result) {
            this.Refresh();
        }
    }

    public void Refresh() {
        TableColumn sortColumn = null;
        SortType sortType = null;
        
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        if (this.tableView.getItems().isEmpty() == false) {
            this.tableView.getItems().clear();
        }
        
        this.olKqs.clear();

        DataController.getInstance().ExecuteUpdate("DELETE FROM KQ");

        //Lấy học lực đầu tiên//
        HocLuc hocLuc = (HocLuc) HocLucController.getInstance().GetList(this.currentAccountProperty.get()).get(0);

        //Lấy hạnh kiểm đầu tiên//
        HanhKiem hanhKiem = (HanhKiem) HanhKiemController.getInstance().GetList(this.currentAccountProperty.get()).get(0);

        //Lấy chuNhiem xem gv đó chủ nhiệm lớp nào//
        ChuNhiem chuNhiem = (ChuNhiem) ChuNhiemController.getInstance().GetModel(new ChuNhiem(
                this.currentAccountProperty.get().getMaGV(),
                null,
                HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()
        ));

        //Lấy Hoc xem từng HocSinh của lớp//
        for (Model modelHoc : HocController.getInstance().Search(new Hoc(
                chuNhiem.getMaLop(),
                null,
                HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()
        ))) {

            Hoc hoc = (Hoc) modelHoc;

            double dtb = 0;
            int heso = 0;

            //Lấy GiangDay xem từng môn mà hs lớp đó đc dạy//
            for (Model modelGiangDay : GiangDayController.getInstance().Search(new GiangDay(
                    chuNhiem.getMaGV(),
                    null,
                    chuNhiem.getMaLop(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()
            ))) {

                GiangDay giangDay = (GiangDay) modelGiangDay;

                double dtbMon = 0;
                int hesoMon = 0;

                //Lấy kt xem từng điểm kt của học sinh đó//
                for (Model modelKT : KTController.getInstance().Search(new KT(
                        giangDay.getMaMH(),
                        null,
                        giangDay.getMaLop(),
                        HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                        HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi(),
                        hoc.getMaHs(),
                        null))) {
                    KT kt = (KT) modelKT;

                    dtbMon += kt.getDiem();
                    hesoMon += kt.getHeSo();
                }

                if (hesoMon <= 0) {
                    hesoMon = 1;
                }

                dtb += dtbMon / hesoMon;
                heso++;
            }

            if (heso <= 0) {
                heso = 1;
            }

            dtb /= heso;

            //Thêm dữ liệu KQ mới vào csdl//
            KQ kq = new KQ(
                    hoc.getMaHs(),
                    hocLuc.getMaHL(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi(),
                    hanhKiem.getMaHK(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                    dtb
            );

            KQController.getInstance().Add(kq);
        }

        this.tableView.getItems().setAll((ArrayList<KQ>) ((ArrayList<? extends Model>) KQController.getInstance()
                .GetList(currentAccountProperty.get())));
        this.olKqs.setAll(this.tableView.getItems());

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }
    
    public void ReFillData(){
        this.tableView.getItems().setAll(this.olKqs);
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(KQ hocSinh) {
        this.tableView.scrollTo(hocSinh);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                KQ hs = (KQ) i;
                boolean result = KQController.getInstance().Delete(hs);
            }

            this.Refresh();
        }
    }

    public void AddItem(KQ hs) {
        boolean result = KQController.getInstance().Add(hs);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(KQ searchKeyword) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<KQ>) ((ArrayList<? extends Model>) KQController.getInstance().Search(searchKeyword)));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    public void RefreshAllData() {
        this.Refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InitializeColumnDTB();
        InitializeColumnHANHKIEM();
        InitializeColumnHOCLUC();
        InitializeColumnMAHS();
        InitializeColumnNAM();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);

        this.currentAccountProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.RefreshAllData();
            }
        });
    }

}
