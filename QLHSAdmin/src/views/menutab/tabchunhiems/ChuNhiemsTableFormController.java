/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabchunhiems;

import controllers.ChuNhiemController;
import views.menutab.tabgiaoviens.*;
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
import models.ChuNhiem;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class ChuNhiemsTableFormController implements Initializable {

    @FXML
    private TableView<ChuNhiem> tableView;

    @FXML
    private TableColumn<ChuNhiem, String> tableColumn_MAGV;

    @FXML
    private TableColumn<ChuNhiem, String> tableColumn_MALOP;

    @FXML
    private TableColumn<ChuNhiem, Integer> tableColumn_HOCKI;

    @FXML
    private TableColumn<ChuNhiem, Integer> tableColumn_NAMHOC;

  
    private void initialize_tableColumn_HOCKY() {
        this.tableColumn_HOCKI.setCellValueFactory((TableColumn.CellDataFeatures<ChuNhiem, Integer> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getHocKi());
        });

    }

     private void initialize_tableColumn_NAMHOC() {
        this.tableColumn_NAMHOC.setCellValueFactory((TableColumn.CellDataFeatures<ChuNhiem, Integer> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getNam());
        });
    }
     private void initialize_tableColumn_MALOP() {
        this.tableColumn_MALOP.setCellValueFactory((TableColumn.CellDataFeatures<ChuNhiem, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getMaLop());
        });
    }

     private void initialize_tableColumn_MAGV() {
        this.tableColumn_MAGV.setCellValueFactory((TableColumn.CellDataFeatures<ChuNhiem, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getMaGV());
        });
    }


    public ChuNhiem getSelectedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public TableView<ChuNhiem> getRoot() {
        return tableView;
    }

    public TableView<ChuNhiem> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<ChuNhiem> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<ChuNhiem> al) {
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
        this.tableView.getItems().addAll((ArrayList<ChuNhiem>) ((ArrayList<? extends Model>) ChuNhiemController.getInstance().GetList()));
    
        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(ChuNhiem giaoVien) {
        this.tableView.scrollTo(giaoVien);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                ChuNhiem gv = (ChuNhiem) i;
                boolean result = ChuNhiemController.getInstance().Delete(gv);
            }
            
            this.Refresh();
        }
    }

    public void AddItem(ChuNhiem gv) {
        boolean result = ChuNhiemController.getInstance().Add(gv);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(ChuNhiem searchKeyword) {
        TableColumn sortcolumn = null;
        TableColumn.SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortcolumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortcolumn.getSortType();
        }
        
        this.ClearSelectedItems();
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<ChuNhiem>) ((ArrayList<? extends Model>) ChuNhiemController.getInstance().Search(searchKeyword)));
        this.tableView.sort();
        
        if (sortcolumn != null) {
            this.tableView.getSortOrder().add(sortcolumn);
            sortcolumn.setSortType(sortType);
            sortcolumn.setSortable(true);
        }
    }

    void EditItem(ChuNhiem model) {
        boolean result = ChuNhiemController.getInstance().Edit(model, model);
        if (result) {
            this.Refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initialize_tableColumn_MAGV();
       initialize_tableColumn_HOCKY();
       initialize_tableColumn_NAMHOC();
       initialize_tableColumn_MALOP();
       

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }
}
