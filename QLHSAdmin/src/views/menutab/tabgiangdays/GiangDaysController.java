/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabgiangdays;

import views.menutab.tabchunhiems.*;
import views.menutab.tabgiaoviens.*;
import com.jfoenix.controls.JFXButton;
import controllers.ChuNhiemController;
import controllers.GiangDayController;
import controllers.GiaoVienController;
import controllers.HocKi_NamHocController;
import controllers.LopController;
import controllers.MonHocController;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import models.Accounts;
import models.ChuNhiem;
import models.GiangDay;
import models.GiaoVien;
import models.HocKi_NamHoc;
import models.Lop;
import models.Model;
import models.MonHoc;
import views.mainmenu.MainMenuFormController;

public class GiangDaysController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<GiangDay> tableviewGiangDays;
    private GiangDaysTableFormController tableGiangDayController;

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private ToggleButton togglebuttonList;
    @FXML
    private ScrollPane scrollpaneListMenu;
    @FXML
    private ToggleButton togglebuttonAdd;
    @FXML
    private ToggleButton togglebuttonDelete;
    @FXML
    private ToggleButton togglebuttonSearch;
    @FXML
    private ToggleButton togglebuttonRefresh;
    @FXML
    private ToggleButton togglebuttonUnselectAll;
    @FXML
    private BorderPane borderpaneAdd;
    @FXML
    private JFXButton buttonAddBack;
    @FXML
    private ComboBox<GiaoVien> comboboxAddMaGV;
    @FXML
    private ComboBox<Lop> comboboxAddMaLop;
    @FXML
    private ComboBox<HocKi_NamHoc> comboboxAddHK_NH;
    @FXML
    private ComboBox<MonHoc> comboBox_AddMH;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXButton buttonAddReset;
    @FXML
    private BorderPane borderpaneDelete;
    @FXML
    private JFXButton buttonDeleteBack;
    @FXML
    private JFXButton buttonDelete;
    @FXML
    private JFXButton buttonDeleteReset;
    @FXML
    private BorderPane borderpaneFind;
    @FXML
    private JFXButton buttonFindBack;
    @FXML
    private TextField textfieldFindMaGV;
    @FXML
    private TextField textfieldFindMaLop;
    @FXML
    private TextField textfieldFindHocKi;
    @FXML
    private TextField textfielddFindNamHoc;
    @FXML
    private TextField textfieldFindMaMH;
    @FXML
    private JFXButton buttonFind;
    @FXML
    private JFXButton buttonFindReset;
    @FXML
    private ToggleButton togglebuttonListMenu;
    @FXML
    private ToggleButton togglebuttonListLine;
    @FXML
    private CheckBox checkboxFindMaGV;
    @FXML
    private CheckBox checkboxFindMaLop;
    @FXML
    private CheckBox checkboxFindHocKI;
    @FXML
    private CheckBox checkboxFindNamHoc;
    @FXML
    private CheckBox checkboxFindMaMH;
    @FXML
    private BorderPane borderpaneContentList;
    @FXML
    private ScrollPane scrollpaneContentTable;
    @FXML
    private BorderPane borderpaneContentTable;
    @FXML
    private BorderPane borderpaneContentListMenu;

    private void HideBorderpaneModelsList(boolean isHide) {
        isHide = !isHide;
        borderpaneContentList.setVisible(isHide);
        borderpaneContentList.setManaged(isHide);
    }

    private void HideBorderpaneMenu(boolean isHide) {
        isHide = !isHide;
        borderpaneContentListMenu.setVisible(isHide);
        borderpaneContentListMenu.setManaged(isHide);
    }

    private void HideScrollpaneListMenu(boolean isHide) {
        isHide = !isHide;
        if (isHide) {
            scrollpaneListMenu.setOpacity(1);
        } else {
            scrollpaneListMenu.setOpacity(0.1);
        }
    }

    private void SetDataToList() {
        if (currentAccountProperty.get() != null) {
            tableGiangDayController.setData(
                    (ArrayList<GiangDay>) ((ArrayList<? extends Model>) GiangDayController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            tableGiangDayController.setData(
                    (ArrayList<GiangDay>) ((ArrayList<? extends Model>) GiangDayController.getInstance()
                            .GetList()));

        }
    }

    private void ResetDataAdd() {
        this.comboboxAddMaGV.getSelectionModel().select(0);
        this.comboboxAddMaLop.getSelectionModel().select(0);
        this.comboboxAddHK_NH.getSelectionModel().select(0);
        this.comboBox_AddMH.getSelectionModel().select(0);
       
    }

    private void ResetDataFind() {
        
        
        this.textfieldFindHocKi.setText("");
        this.textfielddFindNamHoc.setText("");
        this.textfieldFindMaMH.setText("");
        this.textfieldFindMaGV.setText("");
        this.textfieldFindMaLop.setText("");
        this.checkboxFindMaGV.setSelected(false);
        this.checkboxFindHocKI.setSelected(false);
        this.checkboxFindMaLop.setSelected(false);
        this.checkboxFindNamHoc.setSelected(false);
        this.checkboxFindMaMH.setSelected(false);
        
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Khởi tạo bảng///
        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("GiangDaysTableForm.fxml"));
            this.tableviewGiangDays = fxmll.load();
            this.tableGiangDayController = fxmll.getController();
            this.borderpaneContentTable.setCenter(this.tableviewGiangDays);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ///lay data cho combobox
        this.comboboxAddMaGV.getItems().clear();
        this.comboboxAddMaGV.getItems().addAll((ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController.getInstance().GetList()));
        this.comboboxAddMaLop.getItems().clear();
        this.comboboxAddMaLop.getItems().addAll((ArrayList<Lop>) ((ArrayList<? extends Model>) LopController.getInstance().GetList()));
        this.comboboxAddHK_NH.getItems().clear();
        this.comboboxAddHK_NH.getItems().addAll((ArrayList<HocKi_NamHoc>) ((ArrayList<? extends Model>) HocKi_NamHocController.getInstance().GetList()));
        this.comboBox_AddMH.getItems().clear();
        this.comboBox_AddMH.getItems().addAll((ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController.getInstance().GetList()));
        
       
      
        
///Binding dữ liệu theo Current accounts///
        
        this.currentAccountProperty.addListener((observable) -> {
            this.SetDataToList();
        });
        
        ///Cài đặt Toggle Group///
        this.togglebuttonList.setToggleGroup(toggleGroupMenu);

        ///Chức năng binding giữa Toggle button và Toggle button line///
        this.togglebuttonList.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonListLine.setSelected(newValue);
        });

        this.togglebuttonListLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonList.setSelected(newValue);
        });

        ///Chức năng chuyển tab///
        this.togglebuttonList.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideBorderpaneModelsList(!newValue);
        });

        ///Chức năng mở menu trong content list///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewGiangDays.getSelectionModel().getSelectedItems().size() + ")");
                this.HideBorderpaneMenu(false);
                ((MaterialDesignIconView) this.togglebuttonListMenu.getGraphic()).setGlyphName("MENU_RIGHT");
                this.scrollpaneContentTable.setOpacity(0.25);
            } else {
                this.HideBorderpaneMenu(true);
                ((MaterialDesignIconView) this.togglebuttonListMenu.getGraphic()).setGlyphName("MENU_LEFT");
                this.scrollpaneContentTable.setOpacity(1);
            }
        });
        this.borderpaneContentTable.disableProperty().bind(this.togglebuttonListMenu.selectedProperty());

        //Binding dữ liệu giữa toggle button trong menu list và hiển thị borderpane chức năng///
        this.borderpaneAdd.visibleProperty().bind(this.togglebuttonAdd.selectedProperty());
        this.borderpaneAdd.managedProperty().bind(this.togglebuttonAdd.selectedProperty());
        
        this.borderpaneDelete.visibleProperty().bind(this.togglebuttonDelete.selectedProperty());
        this.borderpaneDelete.managedProperty().bind(this.togglebuttonDelete.selectedProperty());

        this.borderpaneFind.visibleProperty().bind(this.togglebuttonSearch.selectedProperty());
        this.borderpaneFind.managedProperty().bind(this.togglebuttonSearch.selectedProperty());

        this.togglebuttonAdd.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneListMenu(newValue);
        });
       
        this.togglebuttonDelete.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneListMenu(newValue);
        });
        this.togglebuttonSearch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneListMenu(newValue);
        });

        this.buttonAddBack.setOnAction((event) -> {
            this.togglebuttonAdd.setSelected(false);
        });
        this.buttonDeleteBack.setOnAction((event) -> {
            this.togglebuttonDelete.setSelected(false);
        });
        this.buttonFindBack.setOnAction((event) -> {
            this.togglebuttonSearch.setSelected(false);
        });
       

     

        ///Chức năng thêm giáo viên///
        this.buttonAdd.setOnAction((event) -> {
           
            GiaoVien gv=this.comboboxAddMaGV.getValue();
            Lop lop=this.comboboxAddMaLop.getValue();
            HocKi_NamHoc hknh=this.comboboxAddHK_NH.getValue();
            MonHoc mh=this.comboBox_AddMH.getValue();
            

            GiangDay model = new GiangDay(
                    gv.getMaGV(),
                    mh.getMaMH(),
                    lop.getMaLop(),
                    hknh.getNam(),
                    hknh.getHocKi()
                    );
               
                   

            this.tableGiangDayController.AddItem(model);
            this.ResetDataAdd();
        });
        this.buttonAddReset.setOnAction((event) -> {
            this.ResetDataAdd();
        });

        ///Chức năng xóa giáo viên///
        this.buttonDelete.setOnAction((event) -> {
            this.tableGiangDayController.DeleteChoiseItems();
        });
        this.buttonDeleteReset.setOnAction((event) -> {
            this.tableGiangDayController.ClearSelectedItems();
        });


        ///Chức năng tìm kiếm///
        this.buttonFind.setOnAction((event) -> {
            GiangDay model = new GiangDay();

            if (this.checkboxFindMaGV.isSelected()) {
                model.setMaGV(this.textfieldFindMaGV.getText());
            }

            if (this.checkboxFindMaLop.isSelected()) {
                model.setMaLop(this.textfieldFindMaLop.getText());
            }
            if (this.checkboxFindHocKI.isSelected()) {
                model.setHocKi(Integer.parseInt(this.textfieldFindHocKi.getText()));
            }
            if(this.checkboxFindNamHoc.isSelected()){
            model.setNam(Integer.parseInt(this.textfielddFindNamHoc.getText()));
            }
            if(this.checkboxFindMaMH.isSelected()){
                model.setMaMH(this.textfieldFindMaMH.getText());
            }

            model.ChangeToNull();

            this.tableGiangDayController.ClearSelectedItems();
            this.tableGiangDayController.SearchItems(model);
        });
        this.buttonFindReset.setOnAction((event) -> {
            this.ResetDataFind();
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableGiangDayController.Refresh();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng UnselectAll///
        this.togglebuttonUnselectAll.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableGiangDayController.ClearSelectedItems();
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewGiangDays.getSelectionModel().getSelectedItems().size() + ")");
                this.togglebuttonUnselectAll.setSelected(false);
            }
        }));

        ///Chức năng reset khi nhấn delete///
   

        ///Reset data các from chức năng///
        this.ResetDataAdd();
        this.ResetDataFind();

        //Ần borderpane content list menu khi mở form///
        this.HideBorderpaneMenu(true);
    }
}
