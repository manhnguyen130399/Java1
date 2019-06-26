/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabhocs;

import com.jfoenix.controls.JFXButton;
import controllers.HocController;
import controllers.HocKi_NamHocController;
import controllers.HocSinhController;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import models.Accounts;
import models.Hoc;
import models.HocKi_NamHoc;
import models.HocSinh;
import models.Lop;
import models.Model;
import views.mainmenu.MainMenuFormController;

public class HocsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup togglegroupMenu = new ToggleGroup();

    private TableView<HocSinh> tableviewHocSinhsClassed;
    private HocSinhsTableClassedFormController tableviewHocSinhsClassedFormController;

    private TableView<Lop> tableviewLops;
    private LopsTableFormController tableviewLopsFormController;

    private TableView<HocSinh> tableviewHocSinhsNoClassed;
    private HocSinhsTableNoClassedFormController tableviewHocSinhsNoClassedFormController;

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private ToggleButton togglebuttonListHSNoClass;
    @FXML
    private ToggleButton togglebuttonListHSNoClassLine;
    @FXML
    private ToggleButton togglebuttonListHSClass;
    @FXML
    private ToggleButton togglebuttonListHSClassLine;
    @FXML
    private BorderPane borderpaneListHSNoClass;
    @FXML
    private SplitPane splitpaneListHSClass;
    @FXML
    private BorderPane borderpaneLopTable;
    @FXML
    private BorderPane borderpaneMenu;
    @FXML
    private ScrollPane scrollpaneMenu;
    @FXML
    private ToggleButton togglebuttonChuyenLop;
    @FXML
    private ToggleButton togglebuttonRefresh;
    @FXML
    private ToggleButton togglebuttonUnselectAll;
    @FXML
    private BorderPane borderpaneChuyenLop;
    @FXML
    private ComboBox<Lop> comboboxChuyenHSTo;
    @FXML
    private JFXButton buttonChuyen;
    @FXML
    private JFXButton buttonChuyenReset;
    @FXML
    private ToggleButton togglebuttonMenu;
    @FXML
    private BorderPane borderpaneHSClassedTable;
    @FXML
    private BorderPane borderpaneHSNoClassedTable;
    @FXML
    private JFXButton buttonChuyenHSBack;
    @FXML
    private TextField textfieldChuyenSoHSs;

    private void HideBorderpaneListHSNoClass(boolean isHide) {
        isHide = !isHide;
        this.borderpaneListHSNoClass.setVisible(isHide);
        this.borderpaneListHSNoClass.setManaged(isHide);
    }

    private void HideSplitpaneListHSClass(boolean isHide) {
        isHide = !isHide;
        this.splitpaneListHSClass.setVisible(isHide);
        this.splitpaneListHSClass.setManaged(isHide);
    }

    private void HideBorderpaneMenu(boolean isHide) {
        isHide = !isHide;
        this.borderpaneMenu.setVisible(isHide);
        this.borderpaneMenu.setManaged(isHide);
    }

    private void HideScrollpaneMenu(boolean isHide) {
        if (isHide) {
            this.scrollpaneMenu.setOpacity(0.1);
        } else {
            this.scrollpaneMenu.setOpacity(1);
        }
    }

    private ArrayList<HocSinh> GetDataHocSinh(Lop lop) {
        ArrayList<HocSinh> alHocSinhs;

        if (lop == null) {
            alHocSinhs = (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance()
                    .GetList(currentAccountProperty.get()));

        } else {
            alHocSinhs = (ArrayList<HocSinh>) ((ArrayList<? extends Model>) HocSinhController.getInstance()
                    .GetList(lop, currentAccountProperty.get()));
        }

        return alHocSinhs;
    }

    private void SetDataToTableHSLop(Lop lop) {
        this.tableviewHocSinhsClassedFormController.setData(this.GetDataHocSinh(lop));
    }

    private void SetDataToTableHSLopNoClassed() {
        this.tableviewHocSinhsNoClassedFormController.Refresh();
        for (Lop lop : this.tableviewLops.getItems()) {
            this.tableviewHocSinhsNoClassed.getItems().removeAll(this.GetDataHocSinh(lop));
        }
    }

    private void ChangeViewsFollowSelection() {
        if (this.togglegroupMenu.getSelectedToggle().equals(this.togglebuttonListHSClass)) {
            this.togglebuttonUnselectAll.setText("UnSelect These Classed HocSinhs("
                    + this.tableviewHocSinhsClassed.getSelectionModel().getSelectedItems().size() + ")");
            this.textfieldChuyenSoHSs.setText(this.tableviewHocSinhsClassed.getSelectionModel().getSelectedItems().size()
                    + " HocSinhs");
        } else if (this.togglegroupMenu.getSelectedToggle().equals(this.togglebuttonListHSNoClass)) {
            this.togglebuttonUnselectAll.setText("UnSelect These UnClassed HocSinhs("
                    + this.tableviewHocSinhsNoClassed.getSelectionModel().getSelectedItems().size() + ")");
            this.textfieldChuyenSoHSs.setText(this.tableviewHocSinhsNoClassed.getSelectionModel().getSelectedItems().size()
                    + " HocSinhs");
        }
    }

    private void UnselectAllHocSInhs() {
        if (this.togglegroupMenu.getSelectedToggle().equals(this.togglebuttonListHSClass)) {
            this.tableviewHocSinhsClassedFormController.ClearSelectedItems();
        } else {
            this.tableviewHocSinhsNoClassedFormController.ClearSelectedItems();
        }
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    public void RefreshAllData() {
        this.tableviewLopsFormController.Refresh();
        this.SetDataToTableHSLopNoClassed();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Khởi tạo bảng///
        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("HocSinhsTableClassedForm.fxml"));
            this.tableviewHocSinhsClassed = fxmll.load();
            this.tableviewHocSinhsClassedFormController = fxmll.getController();
            this.borderpaneHSClassedTable.setCenter(this.tableviewHocSinhsClassed);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("LopsTableForm.fxml"));
            this.tableviewLops = fxmll.load();
            this.tableviewLopsFormController = fxmll.getController();
            this.borderpaneLopTable.setCenter(this.tableviewLops);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("HocSinhsTableNoClassedForm.fxml"));
            this.tableviewHocSinhsNoClassed = fxmll.load();
            this.tableviewHocSinhsNoClassedFormController = fxmll.getController();
            this.borderpaneHSNoClassedTable.setCenter(this.tableviewHocSinhsNoClassed);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Tab tableviewHocSinhsNoClassedFormController//
        this.tableviewHocSinhsNoClassedFormController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.tableviewHocSinhsNoClassedFormController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.tableviewHocSinhsNoClassedFormController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.tableviewHocSinhsNoClassedFormController.getCurrentAccountProperty().get()) == false) {
                this.tableviewHocSinhsNoClassedFormController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab tableviewLopsFormController//
        this.tableviewLopsFormController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.tableviewLopsFormController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.tableviewLopsFormController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.tableviewLopsFormController.getCurrentAccountProperty().get()) == false) {
                this.tableviewLopsFormController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        ///Binding data khi account thay đổi///
        this.currentAccountProperty.addListener((observable, oldValue, newValue) -> {
            this.RefreshAllData();
        });

        ///Cài đặt Toggle Group///
        this.togglebuttonListHSClass.setToggleGroup(togglegroupMenu);
        this.togglebuttonListHSNoClass.setToggleGroup(togglegroupMenu);

        ///Chức năng binding giữa Toggle button và Toggle button line///
        this.togglebuttonListHSClass.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonListHSClassLine.setSelected(newValue);
        });
        this.togglebuttonListHSClassLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonListHSClass.setSelected(newValue);
        });

        this.togglebuttonListHSNoClass.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonListHSNoClassLine.setSelected(newValue);
        });
        this.togglebuttonListHSNoClassLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonListHSNoClass.setSelected(newValue);
        });

        ///Chức năng chuyển tab///
        this.togglegroupMenu.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(this.togglebuttonListHSClass)) {
                    this.HideSplitpaneListHSClass(false);
                    this.HideBorderpaneListHSNoClass(true);
                } else if (newValue.equals(this.togglebuttonListHSNoClass)) {
                    this.HideSplitpaneListHSClass(true);
                    this.HideBorderpaneListHSNoClass(false);
                } else {
                    this.HideBorderpaneListHSNoClass(true);
                    this.HideSplitpaneListHSClass(true);
                }

                this.ChangeViewsFollowSelection();
            } else {
                this.HideBorderpaneListHSNoClass(true);
                this.HideSplitpaneListHSClass(true);
            }
        });

        ///Chức năng mở menu///
        this.togglebuttonMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.ChangeViewsFollowSelection();
                this.splitpaneListHSClass.setOpacity(0.1);
                this.borderpaneHSNoClassedTable.setOpacity(0.1);
                this.HideBorderpaneMenu(false);
                ((MaterialDesignIconView) this.togglebuttonMenu.getGraphic()).setGlyphName("MENU_RIGHT");
            } else {
                this.splitpaneListHSClass.setOpacity(1);
                this.borderpaneHSNoClassedTable.setOpacity(1);
                this.HideBorderpaneMenu(true);
                ((MaterialDesignIconView) this.togglebuttonMenu.getGraphic()).setGlyphName("MENU_LEFT");
            }
        });

        ///Chức năng giữ division của splitpane = 0.25 khi resize///
        this.splitpaneListHSClass.widthProperty().addListener((observable, oldValue, newValue) -> {
            this.splitpaneListHSClass.setDividerPositions(0.25);
        });

        ///Binding dữ liệu giữa toggle button trong menu list và hiển thị borderpane chức năng///
        this.borderpaneChuyenLop.visibleProperty().bind(this.togglebuttonChuyenLop.selectedProperty());
        this.borderpaneChuyenLop.managedProperty().bind(this.togglebuttonChuyenLop.selectedProperty());

        this.togglebuttonChuyenLop.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneMenu(newValue);
        });

        this.buttonChuyenHSBack.setOnAction((event) -> {
            this.togglebuttonChuyenLop.setSelected(false);
        });

        ///Binding dữ liệu khi chọn lớp mới trong bảng lớp///
        this.tableviewLops.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.SetDataToTableHSLop(newValue);
            } else {
                this.tableviewHocSinhsClassedFormController.setData(new ArrayList<>());
            }
        });

        ///Binding dữ liệu khi chọn một học sinh///
        this.tableviewHocSinhsClassed.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.ChangeViewsFollowSelection();
        });
        this.tableviewHocSinhsNoClassed.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.ChangeViewsFollowSelection();
        });

        ///Binding dữ liệu của table lops với combo box///
        this.tableviewLops.getItems().addListener((ListChangeListener.Change<? extends Lop> c) -> {
            if (c.next()) {
                this.comboboxChuyenHSTo.getItems().clear();
                this.comboboxChuyenHSTo.getItems().addAll(c.getList());
            }
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.RefreshAllData();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng Unselect item///
        this.togglebuttonUnselectAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.UnselectAllHocSInhs();

                this.togglebuttonUnselectAll.setSelected(false);
            }
        });

        ///Chức năng chuyển lớp///
        this.buttonChuyen.setOnAction((event) -> {
            Lop lopChange = this.comboboxChuyenHSTo.getSelectionModel().getSelectedItem();

            ObservableList<HocSinh> olHocSinhs = null;
            if (this.togglegroupMenu.getSelectedToggle().equals(this.togglebuttonListHSClass)) {
                olHocSinhs = this.tableviewHocSinhsClassedFormController.getSelectedItems();
            } else if (this.togglegroupMenu.getSelectedToggle().equals(this.togglebuttonListHSNoClass)) {
                olHocSinhs = this.tableviewHocSinhsNoClassedFormController.getSelectedItems();
            }

            Lop lopCurrent = this.tableviewLopsFormController.getSelectedItem();
            HocKi_NamHoc currentHocKi_NamHoc = HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc();

            if (olHocSinhs != null) {
                for (HocSinh hocSinh : olHocSinhs) {
                    if (lopCurrent != null) {
                        HocController.getInstance().Delete(new Hoc(
                                lopCurrent.getMaLop(),
                                hocSinh.getMaHS(),
                                currentHocKi_NamHoc.getNam(),
                                currentHocKi_NamHoc.getHocKi())
                        );
                    }

                    if (lopChange != null) {
                        HocController.getInstance().Add(new Hoc(
                                lopChange.getMaLop(),
                                hocSinh.getMaHS(),
                                currentHocKi_NamHoc.getNam(),
                                currentHocKi_NamHoc.getHocKi())
                        );
                    }
                }
            }

            this.RefreshAllData();
        });

        ///Chức năng reset chuyển lớp///
        this.buttonChuyenReset.setOnAction((event) -> {
            this.UnselectAllHocSInhs();
        });

        ///Chức năng nhấn Delete để reset data của combobox///
        this.comboboxChuyenHSTo.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                this.comboboxChuyenHSTo.getSelectionModel().select(null);
            }
        });

        ///Ẩn tab///
        this.HideSplitpaneListHSClass(true);
        this.HideBorderpaneListHSNoClass(false);
        this.HideBorderpaneMenu(true);
        this.togglebuttonListHSNoClass.setSelected(true);
    }
}
