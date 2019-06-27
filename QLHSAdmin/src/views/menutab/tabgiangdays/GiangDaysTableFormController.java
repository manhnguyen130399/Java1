/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabgiangdays;

import controllers.GiangDayController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.GiangDay;
import models.Model;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class GiangDaysTableFormController implements Initializable {

    @FXML
    private TableView<GiangDay> tableView;

    @FXML
    private TableColumn<GiangDay, String> tableColumn_MAGV;

    @FXML
    private TableColumn<GiangDay, String> tableColumn_MALOP;

    @FXML
    private TableColumn<GiangDay, Integer> tableColumn_HOCKI;

    @FXML
    private TableColumn<GiangDay, Integer> tableColumn_NAMHOC;
    @FXML
    private TableColumn<GiangDay,String> tableColumn_MAMH;

  
    private void initialize_tableColumn_HOCKY() {
        this.tableColumn_HOCKI.setCellValueFactory((TableColumn.CellDataFeatures<GiangDay, Integer> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getHocKi());
        });

    }

     private void initialize_tableColumn_NAMHOC() {
        this.tableColumn_NAMHOC.setCellValueFactory((TableColumn.CellDataFeatures<GiangDay, Integer> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getNam());
        });
    }
     private void initialize_tableColumn_MALOP() {
        this.tableColumn_MALOP.setCellValueFactory((TableColumn.CellDataFeatures<GiangDay, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getMaLop());
        });
    }

     private void initialize_tableColumn_MAGV() {
        this.tableColumn_MAGV.setCellValueFactory((TableColumn.CellDataFeatures<GiangDay, String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getMaGV());
        });
    }
     
    private void initialize_tableColumn_MaMH(){
        this.tableColumn_MAMH.setCellValueFactory((TableColumn.CellDataFeatures<GiangDay,String> param) -> {
            return new SimpleObjectProperty<>(param.getValue().getMaMH()); 
        });
//To change body of generated lambdas, choose Tools | Templates.
    }


    public GiangDay getSelectedItem() {
        return this.tableView.getFocusModel().getFocusedItem();
    }

    public TableView<GiangDay> getRoot() {
        return tableView;
    }

    public TableView<GiangDay> getTableView() {
        return tableView;
    }

    public void setData(ArrayList<GiangDay> al) {
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(al);
    }

    public void AddData(ArrayList<GiangDay> al) {
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
        this.tableView.getItems().addAll((ArrayList<GiangDay>) ((ArrayList<? extends Model>) GiangDayController.getInstance().GetList()));
    
        if (sortColumn != null) {
            this.tableView.getSortOrder().add(sortColumn);
            sortColumn.setSortType(sortType);
            sortColumn.setSortable(true);
        }
    }

    public void ScrollTo(int index) {
        this.tableView.scrollTo(index);
    }

    public void ScrollTo(GiangDay giaoVien) {
        this.tableView.scrollTo(giaoVien);
    }

    public void DeleteChoiseItems() {
        if (this.tableView.getItems().isEmpty() == false) {
            ObservableList ol = this.tableView.getSelectionModel().getSelectedItems();

            for (Object i : ol) {
                GiangDay gv = (GiangDay) i;
                boolean result = GiangDayController.getInstance().Delete(gv);
            }
            
            this.Refresh();
        }
    }

    public void AddItem(GiangDay gv) {
        boolean result = GiangDayController.getInstance().Add(gv);

        if (result) {
            this.Refresh();
        }
    }

    public void SearchItems(GiangDay searchKeyword) {
        TableColumn sortcolumn = null;
        TableColumn.SortType sortType = null;
        if (this.tableView.getSortOrder().size() > 0) {
            sortcolumn = (TableColumn) this.tableView.getSortOrder().get(0);
            sortType = sortcolumn.getSortType();
        }
        
        this.ClearSelectedItems();
        this.tableView.getItems().clear();
        this.tableView.getItems().addAll(
                (ArrayList<GiangDay>) ((ArrayList<? extends Model>) GiangDayController.getInstance().Search(searchKeyword)));
        this.tableView.sort();
        
        if (sortcolumn != null) {
            this.tableView.getSortOrder().add(sortcolumn);
            sortcolumn.setSortType(sortType);
            sortcolumn.setSortable(true);
        }
    }

    void EditItem(GiangDay model) {
        boolean result = GiangDayController.getInstance().Edit(model, model);
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
       initialize_tableColumn_MaMH();
       

        this.tableView.getSelectionModel().setCellSelectionEnabled(false);
        this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableView.setEditable(false);
    }
}
