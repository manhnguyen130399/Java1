/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabhocsinhs;

import com.jfoenix.controls.JFXButton;
import controllers.HocSinhController;
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
import models.HocKi_NamHoc;
import models.HocSinh;
import models.Model;
import views.mainmenu.MainMenuFormController;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class HocSinhsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<HocSinh> tableviewHocSinhs;
    private HocSinhsTableFormController tableviewHocSinhsFormController;

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
    private TextField textfieldAddMaHS;
    @FXML
    private TextField textfieldAddTenHS;
    @FXML
    private ComboBox<String> comboboxAddGioiTinh;
    @FXML
    private DatePicker datepickerAddNgaySinh;
    @FXML
    private ComboBox<String> comboboxAddDanToc;
    @FXML
    private ComboBox<String> comboboxAddTonGiao;
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
    private TextField textfieldEditTenHS;
    @FXML
    private ComboBox<String> comboboxEditGioiTinh;
    @FXML
    private DatePicker datepickerEditNgaySinh;
    @FXML
    private ComboBox<String> comboboxEditDanToc;
    @FXML
    private ComboBox<String> comboboxEditTonGiao;
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
    private TextField textfieldFindMaHS;
    @FXML
    private TextField textfieldFindTenHS;
    @FXML
    private ComboBox<String> comboboxFindGioiTinh;
    @FXML
    private DatePicker datepickerFindNgaySinh;
    @FXML
    private ComboBox<String> comboboxFindDanToc;
    @FXML
    private ComboBox<String> comboboxFindTonGiao;
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
    private CheckBox checkboxFindMaHS;
    @FXML
    private CheckBox checkboxFindTenHS;
    @FXML
    private CheckBox checkboxFindGioiTinh;
    @FXML
    private CheckBox checkboxFindNgaySinh;
    @FXML
    private CheckBox checkboxFindDanToc;
    @FXML
    private CheckBox checkboxFindTonGiao;
    @FXML
    private CheckBox checkboxFindDiaChi;
    @FXML
    private ComboBox<HocSinh> comboboxEditMaHS;
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
//        scrollpaneListMenu.setVisible(isHide);
//        scrollpaneListMenu.setManaged(isHide);
        if (isHide) {
            scrollpaneListMenu.setOpacity(1);
        } else {
            scrollpaneListMenu.setOpacity(0.1);
        }
    }

    private void SetDataToList() {
        if (currentAccountProperty.get() != null) {
                tableviewHocSinhsFormController.setData(
                        (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance()
                                .GetList(currentAccountProperty.get())));
            
        } else {
                tableviewHocSinhsFormController.setData(
                        (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance()
                                .GetList()));
            
        }
    }

    private void ResetDataAdd() {
        this.textfieldAddMaHS.setText("");
        this.textfieldAddTenHS.setText("");
        this.textfieldAddDiaChi.setText("");
        this.comboboxAddGioiTinh.getSelectionModel().select(0);
        this.comboboxAddDanToc.getSelectionModel().select(null);
        this.comboboxAddTonGiao.getSelectionModel().select(null);
        this.datepickerAddNgaySinh.setValue(null);
    }

    private void ResetDataFind() {
        this.textfieldFindMaHS.setText("");
        this.textfieldFindTenHS.setText("");
        this.textfieldFindDiaChi.setText("");
        this.comboboxFindGioiTinh.getSelectionModel().select(null);
        this.comboboxFindDanToc.getSelectionModel().select(null);
        this.comboboxFindTonGiao.getSelectionModel().select(null);
        this.datepickerFindNgaySinh.setValue(null);
        this.checkboxFindDanToc.setSelected(false);
        this.checkboxFindDiaChi.setSelected(false);
        this.checkboxFindGioiTinh.setSelected(false);
        this.checkboxFindMaHS.setSelected(false);
        this.checkboxFindNgaySinh.setSelected(false);
        this.checkboxFindTenHS.setSelected(false);
        this.checkboxFindTonGiao.setSelected(false);
    }

    private void ResetDataEdit() {
        this.comboboxEditMaHS.getSelectionModel().select(null);
        this.textfieldEditTenHS.setText("");
        this.textfieldEditDiaChi.setText("");
        this.comboboxEditGioiTinh.getSelectionModel().select(null);
        this.comboboxEditDanToc.getSelectionModel().select(null);
        this.comboboxEditTonGiao.getSelectionModel().select(null);
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
            fxmll = new FXMLLoader(this.getClass().getResource("HocSinhsTableForm.fxml"));
            this.tableviewHocSinhs = fxmll.load();
            this.tableviewHocSinhsFormController = fxmll.getController();
            this.borderpaneContentTable.setCenter(this.tableviewHocSinhs);
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
                        + this.tableviewHocSinhs.getSelectionModel().getSelectedItems().size() + ")");
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

        ///Binding dữ liện combobox MaHS với bảng khi mở form sửa lên///
        this.togglebuttonEdit.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.comboboxEditMaHS.getItems().clear();
                this.comboboxEditMaHS.getItems().addAll(this.tableviewHocSinhs.getItems());
                this.comboboxEditMaHS.getSelectionModel().select(
                        this.tableviewHocSinhsFormController.getSelectedItem());
            }
        });

        ///Binding dữ liện combobox MaHS với bảng khi mở menu list (đang mở form sửa) lên///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (togglebuttonEdit.isSelected()) {
                    this.comboboxEditMaHS.getSelectionModel().select(
                            this.tableviewHocSinhsFormController.getSelectedItem());
                }
            }
        });

        ///Binding dữ liệu khi chọn MaHS trong sửa HS///
        this.comboboxEditMaHS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                HocSinh hocsinhSelected = newValue;
                this.textfieldEditTenHS.setText(hocsinhSelected.getTenHs());
                this.textfieldEditDiaChi.setText(hocsinhSelected.getDiaChi());
                this.comboboxEditGioiTinh.getSelectionModel().select(hocsinhSelected.getGioiTinh());
                this.comboboxEditDanToc.getSelectionModel().select(hocsinhSelected.getDanToc());
                this.comboboxEditTonGiao.getSelectionModel().select(hocsinhSelected.getTonGiao());

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

        ///Khởi tạo form thêm học sinh///
        this.comboboxAddGioiTinh.getItems().addAll(HocSinhController.getInstance().getArrGioiTinh());
        this.comboboxAddDanToc.getItems().addAll(HocSinhController.getInstance().getArrDanToc());
        this.comboboxAddTonGiao.getItems().addAll(HocSinhController.getInstance().getArrTonGiao());

        ///Khởi tạo form tìm học sinh///
        this.comboboxFindGioiTinh.getItems().addAll(HocSinhController.getInstance().getArrGioiTinh());
        this.comboboxFindDanToc.getItems().addAll(HocSinhController.getInstance().getArrDanToc());
        this.comboboxFindTonGiao.getItems().addAll(HocSinhController.getInstance().getArrTonGiao());

        ///Khởi tạo form sửa học sinh///
        this.comboboxEditGioiTinh.getItems().addAll(HocSinhController.getInstance().getArrGioiTinh());
        this.comboboxEditDanToc.getItems().addAll(HocSinhController.getInstance().getArrDanToc());
        this.comboboxEditTonGiao.getItems().addAll(HocSinhController.getInstance().getArrTonGiao());

        ///Chức năng thêm học sinh///
        this.buttonAdd.setOnAction((event) -> {
            Date ngaySinh = null;
            if (this.datepickerAddNgaySinh.getValue() != null) {
                LocalDate localdateNgaySinh = this.datepickerAddNgaySinh.getValue();
                ngaySinh = new Date(
                        localdateNgaySinh.getYear() - 1900,
                        localdateNgaySinh.getMonthValue() - 1,
                        localdateNgaySinh.getDayOfMonth());
            }

            HocSinh model = new HocSinh(
                    this.textfieldAddMaHS.getText(),
                    this.textfieldAddTenHS.getText(),
                    this.comboboxAddGioiTinh.getSelectionModel().getSelectedItem(),
                    ngaySinh,
                    this.comboboxAddDanToc.getSelectionModel().getSelectedItem(),
                    this.comboboxAddTonGiao.getSelectionModel().getSelectedItem(),
                    this.textfieldAddDiaChi.getText());

            this.tableviewHocSinhsFormController.AddItem(model);
            this.ResetDataAdd();
        });
        this.buttonAddReset.setOnAction((event) -> {
            this.ResetDataAdd();
        });

        ///Chức năng xóa học sinh///
        this.buttonDelete.setOnAction((event) -> {
            this.tableviewHocSinhsFormController.DeleteChoiseItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewHocSinhs.getSelectionModel().getSelectedItems().size() + ")");
        });
        this.buttonDeleteReset.setOnAction((event) -> {
            this.tableviewHocSinhsFormController.ClearSelectedItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewHocSinhs.getSelectionModel().getSelectedItems().size() + ")");
        });

        ///Chức năng sửa học sinh///
        this.buttonEdit.setOnAction((event) -> {
            if (this.comboboxEditMaHS.getSelectionModel().getSelectedItem() != null) {
                HocSinh model = (HocSinh) this.comboboxEditMaHS.getSelectionModel().getSelectedItem().clone();

                Date ngaySinh = null;
                if (this.datepickerEditNgaySinh.getValue() != null) {
                    LocalDate localdateNgaySinh = this.datepickerEditNgaySinh.getValue();
                    ngaySinh = new Date(
                            localdateNgaySinh.getYear() - 1900,
                            localdateNgaySinh.getMonthValue() - 1,
                            localdateNgaySinh.getDayOfMonth());
                }

                model.setNgaySinh(ngaySinh);
                model.setTenHs(this.textfieldEditTenHS.getText());
                model.setDiaChi(this.textfieldEditDiaChi.getText());
                model.setGioiTinh(this.comboboxEditGioiTinh.getSelectionModel().getSelectedItem());
                model.setDanToc(this.comboboxEditDanToc.getSelectionModel().getSelectedItem());
                model.setTonGiao(this.comboboxEditTonGiao.getSelectionModel().getSelectedItem());

                if (model.equalsAll(this.comboboxEditMaHS.getSelectionModel().getSelectedItem()) == false) {
                    this.tableviewHocSinhsFormController.EditItem(model);
                }
            }
        });
        this.buttonEditReset.setOnAction((event) -> {
            this.ResetDataEdit();
        });

        ///Chức năng tìm kiếm///
        this.buttonFind.setOnAction((event) -> {
            HocSinh model = new HocSinh();

            if (this.checkboxFindTenHS.isSelected()) {
                model.setTenHs(this.textfieldFindTenHS.getText());
            }

            if (this.checkboxFindMaHS.isSelected()) {
                model.setMaHS(this.textfieldFindMaHS.getText());
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

            if (this.checkboxFindDanToc.isSelected()) {
                model.setDanToc(this.comboboxFindDanToc.getSelectionModel().getSelectedItem());
            }

            if (this.checkboxFindTonGiao.isSelected()) {
                model.setTonGiao(this.comboboxFindTonGiao.getSelectionModel().getSelectedItem());
            }

            model.ChangeToNull();

            this.tableviewHocSinhsFormController.ClearSelectedItems();
            this.tableviewHocSinhsFormController.SearchItems(model);
        });
        this.buttonFindReset.setOnAction((event) -> {
            this.ResetDataFind();
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewHocSinhsFormController.Refresh();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng UnselectAll///
        this.togglebuttonUnselectAll.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewHocSinhsFormController.ClearSelectedItems();
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewHocSinhs.getSelectionModel().getSelectedItems().size() + ")");
                this.togglebuttonUnselectAll.setSelected(false);
            }
        }));

        ///Chức năng reset khi nhấn delete///
        this.textfieldAddMaHS.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddTenHS.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddDiaChi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditTenHS.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditDiaChi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindMaHS.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindTenHS.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindDiaChi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });

        this.comboboxAddDanToc.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxAddTonGiao.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxAddGioiTinh.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditMaHS.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditDanToc.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditTonGiao.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxEditGioiTinh.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxFindDanToc.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
            }
        });
        this.comboboxFindTonGiao.setOnKeyPressed((event) -> {
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
