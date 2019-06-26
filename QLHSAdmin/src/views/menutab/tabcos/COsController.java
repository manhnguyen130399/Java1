/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabcos;

import com.jfoenix.controls.JFXButton;
import controllers.COController;
import controllers.HocSinhController;
import controllers.PhuHuynhController;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.Accounts;
import models.CO;
import models.HocKi_NamHoc;
import models.HocSinh;
import models.Model;
import models.PhuHuynh;
import views.mainmenu.MainMenuFormController;
import views.viewselements.thogntinlilichpane.ThongTinLiLichFormController;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class COsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<HocSinh> tableviewHocSinhs;
    private COsTableFormController tableviewHocSinhsFormController;

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
    @FXML
    private SplitPane splitpaneContent;
    @FXML
    private BorderPane borderpaneLiLichHS;
    @FXML
    private ComboBox<HocSinh> comboboxLLHSMaHS;
    @FXML
    private TextField textfieldLLHSTenHS;
    @FXML
    private TextField textfieldLLHSGioiTinh;
    @FXML
    private TextField textfieldLLHSNgaySinh;
    @FXML
    private TextField textfieldLLHSDanToc;
    @FXML
    private TextField textfieldLLHSTonGiao;
    @FXML
    private TextField textfieldLLHSDiaChi;
    @FXML
    private VBox vboxLLHSPhuHuynh;
    @FXML
    private JFXButton buttonLLHSPlush;
    @FXML
    private ToggleButton togglebuttonLLHS;
    @FXML
    private ToggleButton togglebuttonLLHSLine;
    @FXML
    private ToggleButton togglebuttonDSvaLLHS;
    @FXML
    private ToggleButton togglebuttonDSvaLLHSLine;

    private void HideBorderpaneModelsList(boolean isHide) {
        isHide = !isHide;
        borderpaneContentList.setVisible(isHide);
        borderpaneContentList.setManaged(isHide);
    }

    private void HideSplitpaneContent(boolean isHide) {
        isHide = !isHide;
        splitpaneContent.setVisible(isHide);
        splitpaneContent.setManaged(isHide);
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
            tableviewHocSinhsFormController.setData(
                    (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            tableviewHocSinhsFormController.setData(
                    (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance()
                            .GetList()));

        }
    }

    private BorderPane CreateThongTinLiLichForm(PhuHuynh phuHuynh, CO co) throws IOException {
        final FXMLLoader fxmll = new FXMLLoader(this.getClass()
                .getResource("/views/viewselements/thogntinlilichpane/ThongTinLiLichForm.fxml")
        );
        final BorderPane result = fxmll.load();
        ((ThongTinLiLichFormController) fxmll.getController()).setPhuhuynh(phuHuynh);
        ((ThongTinLiLichFormController) fxmll.getController()).setCO(co);
        ((ThongTinLiLichFormController) fxmll.getController()).getStatusProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READY:
                    break;
                case DELETE:
                    COController.getInstance().Delete(co);
                    PhuHuynhController.getInstance().Delete(phuHuynh);
                    this.vboxLLHSPhuHuynh.getChildren().remove(result);
                    break;
                case NONE:
                    break;
                default:
                    throw new AssertionError(newValue.name());
            }
        });

        return result;
    }

    private void ChangeLiLichInformation(HocSinh newValue) {
        if (newValue != null) {
            this.vboxLLHSPhuHuynh.getChildren().clear();
            ArrayList<CO> arrCO
                    = (ArrayList<CO>) ((ArrayList<? extends Model>) COController.getInstance()
                            .GetList("SELECT * FROM CO WHERE CO.MAHS = '" + newValue.getMaHS() + "'"));
            for (CO i : arrCO) {
                ArrayList<PhuHuynh> arrPhuHuynh
                        = (ArrayList<PhuHuynh>) ((ArrayList<? extends Model>) PhuHuynhController.getInstance()
                                .GetList("SELECT * FROM PHUHUYNH WHERE PHUHUYNH.MAPH = '" + i.getMaPH() + "'"));

                for (PhuHuynh j : arrPhuHuynh) {
                    try {
                        BorderPane borderPaneLLHS = this.CreateThongTinLiLichForm(j, i);
                        this.vboxLLHSPhuHuynh.getChildren().add(borderPaneLLHS);
                    } catch (IOException ex) {
                        Logger.getLogger(COsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
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

    private void ResetDataLiLichHocSinh() {
        this.comboboxLLHSMaHS.getSelectionModel().select(null);
        this.textfieldLLHSDanToc.setText("");
        this.textfieldLLHSDiaChi.setText("");
        this.textfieldLLHSGioiTinh.setText("");
        this.textfieldLLHSNgaySinh.setText("");
        this.textfieldLLHSTenHS.setText("");
        this.textfieldLLHSTonGiao.setText("");
        this.vboxLLHSPhuHuynh.getChildren().clear();
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
            fxmll = new FXMLLoader(this.getClass().getResource("COsTableForm.fxml"));
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
        this.togglebuttonLLHS.setToggleGroup(toggleGroupMenu);
        this.togglebuttonDSvaLLHS.setToggleGroup(toggleGroupMenu);

        ///Chức năng binding giữa Toggle button và Toggle button line///
        this.togglebuttonList.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonListLine.setSelected(newValue);
        });
        this.togglebuttonListLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonList.setSelected(newValue);
        });

        this.togglebuttonLLHS.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonLLHSLine.setSelected(newValue);
        });
        this.togglebuttonLLHSLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonLLHS.setSelected(newValue);
        });

        this.togglebuttonDSvaLLHS.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonDSvaLLHSLine.setSelected(newValue);
        });

        this.togglebuttonDSvaLLHSLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonDSvaLLHS.setSelected(newValue);
        });

        ///Chức năng chuyển tab///
        this.toggleGroupMenu.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                this.HideSplitpaneContent(true);
            } else {
                this.HideSplitpaneContent(false);

                if (newValue.equals(this.togglebuttonList)) {
                    this.splitpaneContent.setDividerPositions(1);
                } else if (newValue.equals(this.togglebuttonLLHS)) {
                    this.splitpaneContent.setDividerPositions(0);
                } else {
                    this.splitpaneContent.setDividerPositions(0.75);
                }
            }
        });

        ///Chức năng giữa nguyên tab khi resize///
        this.borderpaneRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (this.togglebuttonLLHS.isSelected() && this.togglebuttonList.isSelected() == false) {
                this.splitpaneContent.setDividerPositions(0);
            } else if (this.togglebuttonLLHS.isSelected() == false && this.togglebuttonList.isSelected()) {
                this.splitpaneContent.setDividerPositions(1);
            }
        });
        this.borderpaneRoot.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (this.togglebuttonLLHS.isSelected() && this.togglebuttonList.isSelected() == false) {
                this.splitpaneContent.setDividerPositions(0);
            } else if (this.togglebuttonLLHS.isSelected() == false && this.togglebuttonList.isSelected()) {
                this.splitpaneContent.setDividerPositions(1);
            }
        });

        ///Chức năng mở menu trong content list///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.HideBorderpaneMenu(false);
                ((MaterialDesignIconView) this.togglebuttonListMenu.getGraphic()).setGlyphName("MENU_RIGHT");
                this.splitpaneContent.setOpacity(0.25);
            } else {
                this.HideBorderpaneMenu(true);
                ((MaterialDesignIconView) this.togglebuttonListMenu.getGraphic()).setGlyphName("MENU_LEFT");
                this.splitpaneContent.setOpacity(1);

            }
        });

        ///Binding dữ liệu giữa toggle button trong menu list và hiển thị borderpane chức năng///
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

        ///Binding dữ liệu khi chọn MaHS trong combobox sửa HS///
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

        ///Binding số hàng được chọn trong bảng với số trên menu///
        this.tableviewHocSinhs.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends HocSinh> c) -> {
            if (this.borderpaneContentListMenu.isVisible()) {
                if (c.next()) {
                    this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                            + this.tableviewHocSinhs.getSelectionModel().getSelectedItems().size() + ")");
                }
            }
        });

        ///Binding số hàng được chọn trong bảng với số trên menu khi mở menu lên///
        this.borderpaneContentListMenu.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewHocSinhs.getSelectionModel().getSelectedItems().size() + ")");
            }
        });

        ///Binding dữ liệu của bảng với dữ liệu trong combobox thông tin lí lịch///
        this.tableviewHocSinhs.getItems().addListener((ListChangeListener.Change<? extends HocSinh> c) -> {
            if (this.splitpaneContent.getDividerPositions()[0] < 1) {
                if (c.next()) {
                    this.comboboxLLHSMaHS.getItems().clear();
                    this.comboboxLLHSMaHS.getItems().addAll(c.getList());
                    this.comboboxLLHSMaHS.getSelectionModel().select(
                            this.tableviewHocSinhsFormController.getSelectedItem());
                }
            }
        });

        ///Binding dữ liệu khi chọn 1 dòng trong bảng cho combobox lí lịch///
        this.tableviewHocSinhs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (this.splitpaneContent.getDividerPositions()[0] < 1) {
                this.comboboxLLHSMaHS.getSelectionModel().select(newValue);
                this.ChangeLiLichInformation(newValue);
            }
        });

        ///Binding dữ liệu khi chọn MaHS trong combobox lí lịch///
        this.comboboxLLHSMaHS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (this.splitpaneContent.getDividerPositions()[0] < 1) {
                if (newValue != null) {
                    HocSinh hocsinhSelected = newValue;
                    this.textfieldLLHSDanToc.setText(hocsinhSelected.getDanToc());
                    this.textfieldLLHSDiaChi.setText(hocsinhSelected.getDiaChi());
                    this.textfieldLLHSGioiTinh.setText(hocsinhSelected.getGioiTinh());
                    this.textfieldLLHSNgaySinh.setText(
                            new SimpleDateFormat("yyyy-MM-dd").format(hocsinhSelected.getNgaySinh())
                    );
                    this.textfieldLLHSTenHS.setText(hocsinhSelected.getTenHs());
                    this.textfieldLLHSTonGiao.setText(hocsinhSelected.getTonGiao());

                    this.ChangeLiLichInformation(newValue);
                } else {
                    this.ResetDataLiLichHocSinh();
                }
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

        ///Chức năng thêm thông tin lí lịch///
        this.buttonLLHSPlush.setOnAction((event) -> {
            if (this.splitpaneContent.getDividerPositions()[0] < 1) {
                PhuHuynh phuHuynh = new PhuHuynh(PhuHuynhController.getInstance().CreateAutoID());
                HocSinh hocSinh = this.comboboxLLHSMaHS.getSelectionModel().getSelectedItem();
                if (hocSinh != null) {
                    CO co = new CO(phuHuynh.getMaPH(), hocSinh.getMaHS(), null);

                    phuHuynh.ChangeToNotNull();
                    co.ChangeToNotNull();

                    try {
                        PhuHuynhController.getInstance().Add(phuHuynh);
                        COController.getInstance().Add(co);
                        BorderPane borderPaneLLHS = this.CreateThongTinLiLichForm(phuHuynh, co);
                        this.vboxLLHSPhuHuynh.getChildren().add(borderPaneLLHS);
                    } catch (IOException ex) {
                        Logger.getLogger(COsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

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
        });
        this.buttonDeleteReset.setOnAction((event) -> {
            this.tableviewHocSinhsFormController.ClearSelectedItems();
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
