/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabcos;

import controllers.HocSinhController;
import models.HocSinh;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class COsTableFormController implements Initializable {

    @FXML
    private TableView<HocSinh> tableView;

    @FXML
    private TableColumn<HocSinh, String> tableColumn_MAHS;

    @FXML
    private TableColumn<HocSinh, String> tableColumn_TENHS;

    @FXML
    private TableColumn<HocSinh, String> tableColumn_GIOITINH;

    @FXML
    private TableColumn<HocSinh, String> tableColumn_DANTOC;

    @FXML
    private TableColumn<HocSinh, String> tableColumn_TONGIAO;

    @FXML
    private TableColumn<HocSinh, Date> tableColumn_NGAYSINH;

    @FXML
    private TableColumn<HocSinh, String> tableColumn_DIACHI;

    private void initialize_tableColumn_DIACHI() {
        this.tableColumn_DIACHI.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
//        this.tableColumn_DIACHI.setCellFactory(TextFieldTableCell.<HocSinh>forTableColumn());
//        this.tableColumn_DIACHI.setOnEditCommit((event) -> {
//            event.getRowValue().setDiaChi(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_GIOITINH() {
        this.tableColumn_GIOITINH.setCellValueFactory((TableColumn.CellDataFeatures<HocSinh, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getGioiTinh());
        });
//        this.tableColumn_GIOITINH.setCellFactory(ComboBoxTableCell.forTableColumn("Nam", "Nữ", "Khác"));
//        this.tableColumn_GIOITINH.setOnEditCommit((event) -> {
//            event.getRowValue().setGioiTinh(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_DANTOC() {
        this.tableColumn_DANTOC.setCellValueFactory((TableColumn.CellDataFeatures<HocSinh, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getDanToc());
        });
//        this.tableColumn_DANTOC.setCellFactory(ComboBoxTableCell.forTableColumn(HocSinhController.getInstance().getArrDanToc()));
//        this.tableColumn_DANTOC.setOnEditCommit((event) -> {
//            event.getRowValue().setGioiTinh(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_TONGIAO() {
        this.tableColumn_TONGIAO.setCellValueFactory((TableColumn.CellDataFeatures<HocSinh, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getTonGiao());
        });
//        this.tableColumn_TONGIAO.setCellFactory(ComboBoxTableCell.forTableColumn(HocSinhController.getInstance().getArrTonGiao()));
//        this.tableColumn_TONGIAO.setOnEditCommit((event) -> {
//            event.getRowValue().setGioiTinh(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_MAHS() {
        this.tableColumn_MAHS.setCellValueFactory(new PropertyValueFactory<>("MaHS"));
    }

    private void initialize_tableColumn_NGAYSINH() {
        this.tableColumn_NGAYSINH.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
//        this.tableColumn_NGAYSINH.setCellFactory(DatePickerTableCell.forTableColumn());
//        this.tableColumn_NGAYSINH.setOnEditCommit((event) -> {
//            event.getRowValue().setNgaySinh(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_TENHS() {
        this.tableColumn_TENHS.setCellValueFactory(new PropertyValueFactory<>("TenHs"));
//        this.tableColumn_TENHS.setCellFactory(TextFieldTableCell.<HocSinh>forTableColumn());
//        this.tableColumn_TENHS.setOnEditCommit((event) -> {
//            event.getRowValue().setTenHs(event.getNewValue());
//        });
    }

    public HocSinh getFocusedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public HocSinh getSelectedItem() {
        return this.tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return this.tableView.getSelectionModel().getSelectedIndex();
    }

    public ObservableList<HocSinh> getSelectedItems() {
        return this.tableView.getSelectionModel().getSelectedItems();
    }

    public TableView<HocSinh> getRoot() {
        return tableView;
    }

    public TableView<HocSinh> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<HocSinh> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<HocSinh> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void EditItem(HocSinh newAccount) {
        boolean result = HocSinhController.getInstance().Edit(newAccount, newAccount);
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
        this.tableView.getItems().addAll((ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance().GetList()));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(HocSinh hocSinh) {
        this.tableView.scrollTo(hocSinh);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                HocSinh hs = (HocSinh) i;
                boolean result = HocSinhController.getInstance().Delete(hs);
            }

            this.Refresh();
        }
    }

    public void AddItem(HocSinh hs) {
        boolean result = HocSinhController.getInstance().Add(hs);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(HocSinh searchKeyword) {
        TableColumn sortColumn = null;
        SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }

        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance().Search(searchKeyword)));

        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tableColumn_DIACHI();
        initialize_tableColumn_GIOITINH();
        initialize_tableColumn_MAHS();
        initialize_tableColumn_NGAYSINH();
        initialize_tableColumn_TENHS();
        initialize_tableColumn_DANTOC();
        initialize_tableColumn_TONGIAO();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }

}
