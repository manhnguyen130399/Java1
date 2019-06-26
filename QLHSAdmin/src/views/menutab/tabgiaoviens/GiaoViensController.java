/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabgiaoviens;

import com.jfoenix.controls.JFXButton;
import controllers.GiaoVienController;
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
import models.GiaoVien;
import models.Model;
import views.mainmenu.MainMenuFormController;

public class GiaoViensController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<GiaoVien> tableviewGiaoViens;
    private GiaoViensTableFormController tableviewGiaoViensFormController;

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private ToggleButton togglebuttonList;
    @FXML
    private ScrollPane scrollpaneListMenu;
    @FXML
    private ToggleButton togglebuttonAdd;
    @FXML
    private ToggleButton togglebuttonEdit;
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
    private TextField textfieldAddMaGV;
    @FXML
    private TextField textfieldAddTenGV;
    @FXML
    private ComboBox<String> comboboxAddGioiTinh;
    @FXML
    private DatePicker datepickerAddNgaySinh;
    @FXML
    private TextField textfieldAddDiaChi;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXButton buttonAddReset;
    @FXML
    private BorderPane borderpaneEdit;
    @FXML
    private JFXButton buttonEditBack;
    @FXML
    private TextField textfieldEditTenGV;
    @FXML
    private ComboBox<String> comboboxEditGioiTinh;
    @FXML
    private DatePicker datepickerEditNgaySinh;
    @FXML
    private TextField textfieldEditDiaChi;
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
    private TextField textfieldFindTenGV;
    @FXML
    private ComboBox<String> comboboxFindGioiTinh;
    @FXML
    private DatePicker datepickerFindNgaySinh;
    @FXML
    private TextField textfieldFindDiaChi;
    @FXML
    private JFXButton buttonFind;
    @FXML
    private JFXButton buttonFindReset;
    @FXML
    private ToggleButton togglebuttonListMenu;
    @FXML
    private ToggleButton togglebuttonListLine;
    @FXML
    private JFXButton buttonEdit;
    @FXML
    private JFXButton buttonEditReset;
    @FXML
    private CheckBox checkboxFindMaGV;
    @FXML
    private CheckBox checkboxFindTenGV;
    @FXML
    private CheckBox checkboxFindGioiTinh;
    @FXML
    private CheckBox checkboxFindNgaySinh;
    @FXML
    private CheckBox checkboxFindDiaChi;
    @FXML
    private ComboBox<GiaoVien> comboboxEditMaGV;
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
            tableviewGiaoViensFormController.setData(
                    (ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            tableviewGiaoViensFormController.setData(
                    (ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController.getInstance()
                            .GetList()));

        }
    }

    private void ResetDataAdd() {
        this.textfieldAddMaGV.setText("");
        this.textfieldAddTenGV.setText("");
        this.textfieldAddDiaChi.setText("");
        this.comboboxAddGioiTinh.getSelectionModel().select(0);
        this.datepickerAddNgaySinh.setValue(null);
    }

    private void ResetDataFind() {
        this.textfieldFindMaGV.setText("");
        this.textfieldFindTenGV.setText("");
        this.textfieldFindDiaChi.setText("");
        this.comboboxFindGioiTinh.getSelectionModel().select(null);
        this.datepickerFindNgaySinh.setValue(null);
        this.checkboxFindDiaChi.setSelected(false);
        this.checkboxFindGioiTinh.setSelected(false);
        this.checkboxFindMaGV.setSelected(false);
        this.checkboxFindNgaySinh.setSelected(false);
        this.checkboxFindTenGV.setSelected(false);
    }

    private void ResetDataEdit() {
        this.comboboxEditMaGV.getSelectionModel().select(null);
        this.textfieldEditTenGV.setText("");
        this.textfieldEditDiaChi.setText("");
        this.comboboxEditGioiTinh.getSelectionModel().select(null);
        this.datepickerEditNgaySinh.setValue(null);
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
            fxmll = new FXMLLoader(this.getClass().getResource("GiaoViensTableForm.fxml"));
            this.tableviewGiaoViens = fxmll.load();
            this.tableviewGiaoViensFormController = fxmll.getController();
            this.borderpaneContentTable.setCenter(this.tableviewGiaoViens);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                        + this.tableviewGiaoViens.getSelectionModel().getSelectedItems().size() + ")");
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

        this.borderpaneEdit.visibleProperty().bind(this.togglebuttonEdit.selectedProperty());
        this.borderpaneEdit.managedProperty().bind(this.togglebuttonEdit.selectedProperty());

        this.borderpaneDelete.visibleProperty().bind(this.togglebuttonDelete.selectedProperty());
        this.borderpaneDelete.managedProperty().bind(this.togglebuttonDelete.selectedProperty());

        this.borderpaneFind.visibleProperty().bind(this.togglebuttonSearch.selectedProperty());
        this.borderpaneFind.managedProperty().bind(this.togglebuttonSearch.selectedProperty());

        this.togglebuttonAdd.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneListMenu(newValue);
        });
        this.togglebuttonEdit.selectedProperty().addListener((observable, oldValue, newValue) -> {
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
        this.buttonEditBack.setOnAction((event) -> {
            this.togglebuttonEdit.setSelected(false);
        });
        this.buttonDeleteBack.setOnAction((event) -> {
            this.togglebuttonDelete.setSelected(false);
        });
        this.buttonFindBack.setOnAction((event) -> {
            this.togglebuttonSearch.setSelected(false);
        });

        ///Binding dữ liện combobox MaGV với bảng khi mở form sửa lên///
        this.togglebuttonEdit.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.comboboxEditMaGV.getItems().clear();
                this.comboboxEditMaGV.getItems().addAll(this.tableviewGiaoViens.getItems());
                this.comboboxEditMaGV.getSelectionModel().select(
                        this.tableviewGiaoViensFormController.getSelectedItem());
            }
        });

        ///Binding dữ liện combobox MaGV với bảng khi mở menu list (đang mở form sửa) lên///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (togglebuttonEdit.isSelected()) {
                    this.comboboxEditMaGV.getSelectionModel().select(
                            this.tableviewGiaoViensFormController.getSelectedItem());
                }
            }
        });

        ///Binding dữ liệu khi chọn MaGV trong sửa GV///
        this.comboboxEditMaGV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                GiaoVien hocsinhSelected = newValue;
                this.textfieldEditTenGV.setText(hocsinhSelected.getTenGV());
                this.textfieldEditDiaChi.setText(hocsinhSelected.getDiaChi());
                this.comboboxEditGioiTinh.getSelectionModel().select(hocsinhSelected.getGioiTinh());

                Date ngaySinh = hocsinhSelected.getNgaySinh();
                this.datepickerEditNgaySinh.setValue(LocalDate.of(
                        ngaySinh.getYear() + 1900,
                        ngaySinh.getMonth() + 1,
                        ngaySinh.getDate())
                );
            } else {
                this.ResetDataEdit();
            }
        });

        ///Khởi tạo form thêm giáo viên///
        this.comboboxAddGioiTinh.getItems().addAll(GiaoVienController.getInstance().getArrGioiTinh());

        ///Khởi tạo form tìm giáo viên///
        this.comboboxFindGioiTinh.getItems().addAll(GiaoVienController.getInstance().getArrGioiTinh());

        ///Khởi tạo form sửa giáo viên///
        this.comboboxEditGioiTinh.getItems().addAll(GiaoVienController.getInstance().getArrGioiTinh());

        ///Chức năng thêm giáo viên///
        this.buttonAdd.setOnAction((event) -> {
            Date ngaySinh = null;
            if (this.datepickerAddNgaySinh.getValue() != null) {
                LocalDate localdateNgaySinh = this.datepickerAddNgaySinh.getValue();
                ngaySinh = new Date(
                        localdateNgaySinh.getYear() - 1900,
                        localdateNgaySinh.getMonthValue() - 1,
                        localdateNgaySinh.getDayOfMonth());
            }

            GiaoVien model = new GiaoVien(
                    this.textfieldAddMaGV.getText(),
                    this.textfieldAddTenGV.getText(),
                    ngaySinh,
                    this.comboboxAddGioiTinh.getSelectionModel().getSelectedItem(),
                    this.textfieldAddDiaChi.getText());

            this.tableviewGiaoViensFormController.AddItem(model);
            this.ResetDataAdd();
        });
        this.buttonAddReset.setOnAction((event) -> {
            this.ResetDataAdd();
        });

        ///Chức năng xóa giáo viên///
        this.buttonDelete.setOnAction((event) -> {
            this.tableviewGiaoViensFormController.DeleteChoiseItems();
        });
        this.buttonDeleteReset.setOnAction((event) -> {
            this.tableviewGiaoViensFormController.ClearSelectedItems();
        });

        ///Chức năng sửa giáo viên///
        this.buttonEdit.setOnAction((event) -> {
            if (this.comboboxEditMaGV.getSelectionModel().getSelectedItem() != null) {
                GiaoVien model = (GiaoVien) this.comboboxEditMaGV.getSelectionModel().getSelectedItem().clone();

                Date ngaySinh = null;
                if (this.datepickerEditNgaySinh.getValue() != null) {
                    LocalDate localdateNgaySinh = this.datepickerEditNgaySinh.getValue();
                    ngaySinh = new Date(
                            localdateNgaySinh.getYear() - 1900,
                            localdateNgaySinh.getMonthValue() - 1,
                            localdateNgaySinh.getDayOfMonth());
                }

                model.setNgaySinh(ngaySinh);
                model.setTenGV(this.textfieldEditTenGV.getText());
                model.setDiaChi(this.textfieldEditDiaChi.getText());
                model.setGioiTinh(this.comboboxEditGioiTinh.getSelectionModel().getSelectedItem());

                if (model.equalsAll(this.comboboxEditMaGV.getSelectionModel().getSelectedItem()) == false) {
                    this.tableviewGiaoViensFormController.EditItem(model);
                }
            }
        });
        this.buttonEditReset.setOnAction((event) -> {
            this.ResetDataEdit();
        });

        ///Chức năng tìm kiếm///
        this.buttonFind.setOnAction((event) -> {
            GiaoVien model = new GiaoVien();

            if (this.checkboxFindTenGV.isSelected()) {
                model.setTenGV(this.textfieldFindTenGV.getText());
            }

            if (this.checkboxFindMaGV.isSelected()) {
                model.setMaGV(this.textfieldFindMaGV.getText());
            }

            if (this.checkboxFindNgaySinh.isSelected()) {
                Date ngaySinh = null;
                if (this.datepickerFindNgaySinh.getValue() != null) {
                    LocalDate localdateNgaySinh = this.datepickerFindNgaySinh.getValue();
                    ngaySinh = new Date(
                            localdateNgaySinh.getYear() - 1900,
                            localdateNgaySinh.getMonthValue() - 1,
                            localdateNgaySinh.getDayOfMonth());
                }
                model.setNgaySinh(ngaySinh);
            }

            if (this.checkboxFindDiaChi.isSelected()) {
                model.setDiaChi(this.textfieldFindDiaChi.getText());
            }

            if (this.checkboxFindGioiTinh.isSelected()) {
                model.setGioiTinh(this.comboboxFindGioiTinh.getSelectionModel().getSelectedItem());
            }

            model.ChangeToNull();

            this.tableviewGiaoViensFormController.ClearSelectedItems();
            this.tableviewGiaoViensFormController.SearchItems(model);
        });
        this.buttonFindReset.setOnAction((event) -> {
            this.ResetDataFind();
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewGiaoViensFormController.Refresh();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng UnselectAll///
        this.togglebuttonUnselectAll.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewGiaoViensFormController.ClearSelectedItems();
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewGiaoViens.getSelectionModel().getSelectedItems().size() + ")");
                this.togglebuttonUnselectAll.setSelected(false);
            }
        }));

        ///Chức năng reset khi nhấn delete///
        this.textfieldAddMaGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddTenGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddDiaChi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditTenGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditDiaChi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindMaGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindTenGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindDiaChi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });

        this.comboboxAddGioiTinh.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditMaGV.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditGioiTinh.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxFindGioiTinh.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });

        this.datepickerAddNgaySinh.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((DatePicker) event.getSource()).setValue(null);
            }
        });
        this.datepickerEditNgaySinh.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((DatePicker) event.getSource()).setValue(null);
            }
        });
        this.datepickerFindNgaySinh.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((DatePicker) event.getSource()).setValue(null);
            }
        });

        ///Reset data các from chức năng///
        this.ResetDataAdd();
        this.ResetDataFind();
        this.ResetDataEdit();

        //Ần borderpane content list menu khi mở form///
        this.HideBorderpaneMenu(true);
    }
}
