/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tablops;

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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import models.Accounts;
import models.ChuNhiem;
import models.GiangDay;
import models.GiaoVien;
import models.Lop;
import models.Model;
import models.MonHoc;
import views.mainmenu.MainMenuFormController;
import views.viewselements.phancongchunhiempane.PhanCongChuNhiemFormController;
import views.viewselements.phanconggiangdaypane.PhanCongGiangDayFormController;

public class LopsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<Lop> tableviewLops;
    private LopsTableFormController tableviewLopsFormController;
    private final ObservableList<GiaoVien> olGiaoViens = FXCollections.observableArrayList();
    private final ObservableList<MonHoc> olMonHocs = FXCollections.observableArrayList();

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
    private TextField textfieldAddMaLOP;
    @FXML
    private TextField textfieldAddTenLOP;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXButton buttonAddReset;
    @FXML
    private BorderPane borderpaneEdit;
    @FXML
    private JFXButton buttonEditBack;
    @FXML
    private TextField textfieldEditTenLOP;
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
    private TextField textfieldFindMaLOP;
    @FXML
    private TextField textfieldFindTenLOP;
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
    private CheckBox checkboxFindMaLOP;
    @FXML
    private CheckBox checkboxFindTenLOP;
    @FXML
    private ComboBox<Lop> comboboxEditMaLOP;
    @FXML
    private ScrollPane scrollpaneContentTable;
    @FXML
    private BorderPane borderpaneContentTable;
    @FXML
    private BorderPane borderpaneContentListMenu;
    @FXML
    private SplitPane splitpaneContent;
    @FXML
    private ToggleButton togglebuttonCNGD;
    @FXML
    private ToggleButton togglebuttonCNGDLine;
    @FXML
    private ScrollPane scrollpaneCNGD;
    @FXML
    private ComboBox<Lop> comboboxCNGDMaLop;
    @FXML
    private VBox vboxCN;
    @FXML
    private VBox vboxGD;
    @FXML
    private TextField textfieldCNGDMaLop;
    @FXML
    private JFXButton buttonCNPlush;
    @FXML
    private JFXButton buttonGDPlush;

    private void HideScrollpaneModelsList(boolean isHide) {
        isHide = !isHide;
        scrollpaneContentTable.setVisible(isHide);
        scrollpaneContentTable.setManaged(isHide);
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

    private void HideSplitPaneContent(boolean isHide) {
        isHide = !isHide;
        this.splitpaneContent.setVisible(isHide);
        this.splitpaneContent.setManaged(isHide);
    }

    private void SetDataToList() {
        if (currentAccountProperty.get() != null) {
            tableviewLopsFormController.setData(
                    (ArrayList<Lop>) ((ArrayList<? extends Model>) LopController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            tableviewLopsFormController.setData(
                    (ArrayList<Lop>) ((ArrayList<? extends Model>) LopController.getInstance()
                            .GetList()));

        }
    }

    private void SetOlGiaoViensData() {
        if (currentAccountProperty.get() != null) {
            this.olGiaoViens.clear();
            this.olGiaoViens.addAll(
                    (ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            this.olGiaoViens.clear();
            this.olGiaoViens.addAll(
                    (ArrayList<GiaoVien>) ((ArrayList<? extends Model>) GiaoVienController.getInstance()
                            .GetList()));

        }
    }

    private void SetOlMonHocsData() {
        if (currentAccountProperty.get() != null) {
            this.olMonHocs.clear();
            this.olMonHocs.addAll(
                    (ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            this.olMonHocs.clear();
            this.olMonHocs.addAll(
                    (ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController.getInstance()
                            .GetList()));

        }
    }

    private BorderPane CreatePhanCongChuNhiemForm(ObservableList<GiaoVien> alGiaoViens, ChuNhiem chuNhiem) throws IOException {
        final FXMLLoader fxmll = new FXMLLoader(this.getClass()
                .getResource("/views/viewselements/phancongchunhiempane/PhanCongChuNhiemForm.fxml")
        );
        final BorderPane result = fxmll.load();
        final PhanCongChuNhiemFormController resultFormController = fxmll.getController();
        resultFormController.setData(alGiaoViens, chuNhiem);
        resultFormController.getStatusProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READY:
                    break;
                case DELETE:
                    ChuNhiemController.getInstance().Delete(resultFormController.getChunhiemProperty().get());
                    this.vboxCN.getChildren().remove(result);
                    break;
                case NONE:
                    break;
                default:
                    throw new AssertionError(newValue.name());
            }
        });

        return result;
    }

    private void AddCN(Lop lop) {
        if (lop != null) {
            ChuNhiem chuNhiem = new ChuNhiem(
                    null,
                    lop.getMaLop(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()
            );

            chuNhiem = (ChuNhiem) ChuNhiemController.getInstance().GetModel(chuNhiem);
            try {
                BorderPane borderPane = this.CreatePhanCongChuNhiemForm(this.olGiaoViens, chuNhiem);
                this.vboxCN.getChildren().clear();
                this.vboxCN.getChildren().addAll(borderPane);
            } catch (IOException ex) {
                Logger.getLogger(LopsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private BorderPane CreatePhanCongGiangDayForm(
            ObservableList<GiaoVien> alGiaoViens,
            GiangDay giangDay,
            ObservableList<MonHoc> alMonHocs,
            MonHoc monHoc
    ) throws IOException {
        final FXMLLoader fxmll = new FXMLLoader(this.getClass()
                .getResource("/views/viewselements/phanconggiangdaypane/PhanCongGiangDayForm.fxml")
        );
        final BorderPane result = fxmll.load();
        final PhanCongGiangDayFormController resultFormController = fxmll.getController();
        resultFormController.setData(alGiaoViens, giangDay, alMonHocs, monHoc);
        resultFormController.getStatusProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case READY:
                    break;
                case DELETE:
                    GiangDayController.getInstance().Delete(giangDay);
                    this.vboxGD.getChildren().remove(result);
                    break;
                case NONE:
                    break;
                default:
                    throw new AssertionError(newValue.name());
            }
        });

        return result;
    }

    private void AddGD(Lop lop, MonHoc monHoc) {
        if (lop != null) {
            GiangDay giangDay = (GiangDay) GiangDayController.getInstance().GetModel(new GiangDay(
                    null,
                    null,
                    lop.getMaLop(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getNam(),
                    HocKi_NamHocController.getInstance().GetCurrentHocKi_NamHoc().getHocKi()
            ));

            try {
                BorderPane borderPane = this.CreatePhanCongGiangDayForm(this.olGiaoViens, giangDay, this.olMonHocs, monHoc);
                this.vboxGD.getChildren().addAll(borderPane);
            } catch (IOException ex) {
                Logger.getLogger(LopsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void ResetDataAdd() {
        this.textfieldAddMaLOP.setText("");
        this.textfieldAddTenLOP.setText("");
    }

    private void ResetDataFind() {
        this.textfieldFindMaLOP.setText("");
        this.textfieldFindTenLOP.setText("");
        this.checkboxFindMaLOP.setSelected(false);
        this.checkboxFindTenLOP.setSelected(false);
    }

    private void ResetDataEdit() {
        this.comboboxEditMaLOP.getSelectionModel().select(null);
        this.textfieldEditTenLOP.setText("");
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    public void RefreshAllData() {
        this.SetDataToList();
        this.SetOlGiaoViensData();
        this.SetOlMonHocsData();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Khởi tạo bảng///
        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("LopsTableForm.fxml"));
            this.tableviewLops = fxmll.load();
            this.tableviewLopsFormController = fxmll.getController();
            this.borderpaneContentTable.setCenter(this.tableviewLops);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ///Binding dữ liệu theo Current accounts///
        this.currentAccountProperty.addListener((observable) -> {
            this.RefreshAllData();
        });

        ///Cài đặt Toggle Group///
        this.togglebuttonList.setToggleGroup(toggleGroupMenu);
        this.togglebuttonCNGD.setToggleGroup(toggleGroupMenu);

        ///Chức năng binding giữa Toggle button và Toggle button line///
        this.togglebuttonList.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonListLine.setSelected(newValue);
        });

        this.togglebuttonListLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonList.setSelected(newValue);
        });

        this.togglebuttonCNGD.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonCNGDLine.setSelected(newValue);
        });

        this.togglebuttonCNGDLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.togglebuttonCNGD.setSelected(newValue);
        });

        ///Chức năng chuyển tab///
        this.toggleGroupMenu.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.HideSplitPaneContent(false);

                if (newValue.equals(this.togglebuttonList)) {
                    this.splitpaneContent.setDividerPositions(1);
                } else if (newValue.equals(this.togglebuttonCNGD)) {
                    this.splitpaneContent.setDividerPositions(0);
                } else {
                    this.splitpaneContent.setDividerPositions(0.5);
                }
            } else {
                this.HideSplitPaneContent(true);
            }
        });

        ///Chức năng giữa nguyên tab khi resize///
        this.borderpaneRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (this.togglebuttonCNGD.isSelected() && this.togglebuttonList.isSelected() == false) {
                this.splitpaneContent.setDividerPositions(0);
            } else if (this.togglebuttonCNGD.isSelected() == false && this.togglebuttonList.isSelected()) {
                this.splitpaneContent.setDividerPositions(1);
            }
        });
        this.borderpaneRoot.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (this.togglebuttonCNGD.isSelected() && this.togglebuttonList.isSelected() == false) {
                this.splitpaneContent.setDividerPositions(0);
            } else if (this.togglebuttonCNGD.isSelected() == false && this.togglebuttonList.isSelected()) {
                this.splitpaneContent.setDividerPositions(1);
            }
        });

        ///Chức năng mở menu trong content list///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewLops.getSelectionModel().getSelectedItems().size() + ")");
                this.HideBorderpaneMenu(false);
                ((MaterialDesignIconView) this.togglebuttonListMenu.getGraphic()).setGlyphName("MENU_RIGHT");
                this.splitpaneContent.setOpacity(0.25);
            } else {
                this.HideBorderpaneMenu(true);
                ((MaterialDesignIconView) this.togglebuttonListMenu.getGraphic()).setGlyphName("MENU_LEFT");
                this.splitpaneContent.setOpacity(1);
            }
        });

        ///Chức năng thên CN///
        this.buttonCNPlush.setOnAction((event) -> {
            this.AddCN(this.comboboxCNGDMaLop.getSelectionModel().getSelectedItem());
        });

        ///Chức năng thên GD///
        this.buttonGDPlush.setOnAction((event) -> {
            this.AddGD(this.comboboxCNGDMaLop.getSelectionModel().getSelectedItem(), null);
        });

        ///Binding dữ liệu giữa tableview và combobox///
        this.tableviewLops.getItems().addListener((ListChangeListener.Change<? extends Lop> c) -> {
            if (c.next()) {
                this.comboboxCNGDMaLop.getItems().clear();
                this.comboboxCNGDMaLop.getItems().addAll(c.getList());
            }
        });

        ///Binding chọn dữ liệu trong tableview vs combobox///
        this.tableviewLops.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.comboboxCNGDMaLop.getSelectionModel().select(newValue);
        });

        ///Binding dữ liệu khi thay đổi combobox///
        this.comboboxCNGDMaLop.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.textfieldCNGDMaLop.setText(newValue.getTenLop());
                this.AddCN(newValue);

                this.vboxGD.getChildren().clear();
                for (MonHoc monHoc : this.olMonHocs) {
                    this.AddGD(newValue, monHoc);
                }
            } else {
                this.vboxCN.getChildren().clear();
                this.vboxGD.getChildren().clear();
            }
        });

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

        ///Binding dữ liện combobox MaLOP với bảng khi mở form sửa lên///
        this.togglebuttonEdit.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.comboboxEditMaLOP.getItems().clear();
                this.comboboxEditMaLOP.getItems().addAll(this.tableviewLops.getItems());
                this.comboboxEditMaLOP.getSelectionModel().select(
                        this.tableviewLopsFormController.getSelectedItem());
            }
        });

        ///Binding dữ liện combobox MaLOP với bảng khi mở menu list (đang mở form sửa) lên///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (togglebuttonEdit.isSelected()) {
                    this.comboboxEditMaLOP.getSelectionModel().select(
                            this.tableviewLopsFormController.getSelectedItem());
                }
            }
        });

        ///Binding dữ liệu khi chọn MaLOP trong sửa LOP///
        this.comboboxEditMaLOP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Lop hocsinhSelected = newValue;
                this.textfieldEditTenLOP.setText(hocsinhSelected.getTenLop());
            } else {
                this.ResetDataEdit();
            }
        });

        ///Chức năng thêm lớp///
        this.buttonAdd.setOnAction((event) -> {
            Lop model = new Lop(
                    this.textfieldAddMaLOP.getText(),
                    this.textfieldAddTenLOP.getText()
            );

            this.tableviewLopsFormController.AddItem(model);
            this.ResetDataAdd();
        });
        this.buttonAddReset.setOnAction((event) -> {
            this.ResetDataAdd();
        });

        ///Chức năng xóa lớp///
        this.buttonDelete.setOnAction((event) -> {
            this.tableviewLopsFormController.DeleteChoiseItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewLops.getSelectionModel().getSelectedItems().size() + ")");
        });
        this.buttonDeleteReset.setOnAction((event) -> {
            this.tableviewLopsFormController.ClearSelectedItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewLops.getSelectionModel().getSelectedItems().size() + ")");
        });

        ///Chức năng sửa lớp///
        this.buttonEdit.setOnAction((event) -> {
            if (this.comboboxEditMaLOP.getSelectionModel().getSelectedItem() != null) {
                Lop model = (Lop) this.comboboxEditMaLOP.getSelectionModel().getSelectedItem().clone();

                model.setTenLop(this.textfieldEditTenLOP.getText());

                if (model.equalsAll(this.comboboxEditMaLOP.getSelectionModel().getSelectedItem()) == false) {
                    this.tableviewLopsFormController.EditItem(model);
                }
            }
        });
        this.buttonEditReset.setOnAction((event) -> {
            this.ResetDataEdit();
        });

        ///Chức năng tìm kiếm///
        this.buttonFind.setOnAction((event) -> {
            Lop model = new Lop();

            if (this.checkboxFindTenLOP.isSelected()) {
                model.setTenLop(this.textfieldFindTenLOP.getText());
            }

            if (this.checkboxFindMaLOP.isSelected()) {
                model.setMaLop(this.textfieldFindMaLOP.getText());
            }

            model.ChangeToNull();

            this.tableviewLopsFormController.ClearSelectedItems();
            this.tableviewLopsFormController.SearchItems(model);
        });
        this.buttonFindReset.setOnAction((event) -> {
            this.ResetDataFind();
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewLopsFormController.Refresh();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng UnselectAll///
        this.togglebuttonUnselectAll.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewLopsFormController.ClearSelectedItems();
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewLops.getSelectionModel().getSelectedItems().size() + ")");
                this.togglebuttonUnselectAll.setSelected(false);
            }
        }));

        ///Chức năng reset khi nhấn delete///
        this.textfieldAddMaLOP.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddTenLOP.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditTenLOP.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindMaLOP.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindTenLOP.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });

        this.comboboxEditMaLOP.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((ComboBox) event.getSource()).getSelectionModel().select(null);
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
