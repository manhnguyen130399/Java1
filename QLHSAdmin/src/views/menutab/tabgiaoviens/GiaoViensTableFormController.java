/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabgiaoviens;

import controllers.GiaoVienController;
import models.GiaoVien;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class GiaoViensTableFormController implements Initializable {

    @FXML
    private TableView<GiaoVien> tableView;

    @FXML
    private TableColumn<GiaoVien, String> tableColumn_MAGV;

    @FXML
    private TableColumn<GiaoVien, String> tableColumn_TENGV;

    @FXML
    private TableColumn<GiaoVien, String> tableColumn_GIOITINH;

    @FXML
    private TableColumn<GiaoVien, Date> tableColumn_NGAYSINH;

    @FXML
    private TableColumn<GiaoVien, String> tableColumn_DIACHI;

    private void initialize_tableColumn_DIACHI() {
        this.tableColumn_DIACHI.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
//        this.tableColumn_DIACHI.setCellFactory(TextFieldTableCell.<GiaoVien>forTableColumn());
//        this.tableColumn_DIACHI.setOnEditCommit((event) -> {
//            event.getRowValue().setDiaChi(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_GIOITINH() {
        this.tableColumn_GIOITINH.setCellValueFactory((TableColumn.CellDataFeatures<GiaoVien, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getGioiTinh());
        });
//        this.tableColumn_GIOITINH.setCellFactory(ComboBoxTableCell.forTableColumn("Nam", "Nữ", "Khác"));
//        this.tableColumn_GIOITINH.setOnEditCommit((event) -> {
//            event.getRowValue().setGioiTinh(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_MAGV() {
        this.tableColumn_MAGV.setCellValueFactory(new PropertyValueFactory<>("MaGV"));
    }

    private void initialize_tableColumn_NGAYSINH() {
        this.tableColumn_NGAYSINH.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
//        this.tableColumn_NGAYSINH.setCellFactory(DatePickerTableCell.forTableColumn());
//        this.tableColumn_NGAYSINH.setOnEditCommit((event) -> {
//            event.getRowValue().setNgaySinh(event.getNewValue());
//        });
    }

    private void initialize_tableColumn_TENGV() {
        this.tableColumn_TENGV.setCellValueFactory(new PropertyValueFactory<>("TenGV"));
//        this.tableColumn_TENGV.setCellFactory(TextFieldTableCell.<GiaoVien>forTableColumn());
//        this.tableColumn_TENGV.setOnEditCommit((event) -> {
//            event.getRowValue().setTenGV(event.getNewValue());
//        });
    }

    public GiaoVien getSelectedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public TableView<GiaoVien> getRoot() {
        return tableView;
    }

    public TableView<GiaoVien> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<GiaoVien> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<GiaoVien> al) {
        this.tableView.getItems().addAll(al);
    }

    public void ClearSelectedItems() {
        this.tableView.getSelectionModel().clearSelection();
    }

    public void Refresh() {
        TableColumn sortColumn = null;
        TableColumn.SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortColumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortColumn.getSortType();
        }
        
        if (this.tableView.getItems().isEmpty() == false) {
            this.tableView.getItems().clear();
        }
        this.tableView.getItems().addAll((ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController.getInstance().GetList()));
    
        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(GiaoVien giaoVien) {
        this.tableView.scrollTo(giaoVien);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                GiaoVien gv = (GiaoVien) i;
                boolean result = GiaoVienController.getInstance().Delete(gv);
            }
            
            this.Refresh();
        }
    }

    public void AddItem(GiaoVien gv) {
        boolean result = GiaoVienController.getInstance().Add(gv);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(GiaoVien searchKeyword) {
        TableColumn sortcolumn = null;
        TableColumn.SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortcolumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortcolumn.getSortType();
        }
        
        this.ClearSelectedItems();
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController.getInstance().Search(searchKeyword)));
        this.tableView.sort();
        
        if (sortcolumn != null) {
            this.tableView.getSortOrder().add(sortcolumn);
            sortcolumn.setSortType(sortType);
            sortcolumn.setSortable(true);
        }
    }

    void EditItem(GiaoVien model) {
        boolean result = GiaoVienController.getInstance().Edit(model, model);
        if (result) {
            this.Refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize_tableColumn_DIACHI();
        initialize_tableColumn_GIOITINH();
        initialize_tableColumn_MAGV();
        initialize_tableColumn_NGAYSINH();
        initialize_tableColumn_TENGV();

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }
}
