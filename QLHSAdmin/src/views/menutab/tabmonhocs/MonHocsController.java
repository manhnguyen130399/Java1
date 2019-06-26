/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabmonhocs;

import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import models.Accounts;
import models.HocKi_NamHoc;
import models.MonHoc;
import models.Model;
import views.mainmenu.MainMenuFormController;

public class MonHocsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<MonHoc> tableviewMonHocs;
    private MonHocsTableFormController tableviewMonHocsFormController;

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
    private TextField textfieldAddMaMH;
    @FXML
    private TextField textfieldAddTenMH;
    @FXML
    private JFXButton buttonAdd;
    @FXML
    private JFXButton buttonAddReset;
    @FXML
    private BorderPane borderpaneEdit;
    @FXML
    private JFXButton buttonEditBack;
    @FXML
    private TextField textfieldEditTenMH;
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
    private TextField textfieldFindMaMH;
    @FXML
    private TextField textfieldFindTenMH;
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
    private CheckBox checkboxFindMaMH;
    @FXML
    private CheckBox checkboxFindTenMH;
    @FXML
    private ComboBox<MonHoc> comboboxEditMaMH;
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
                tableviewMonHocsFormController.setData(
                        (ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController.getInstance()
                                .GetList(currentAccountProperty.get())));
            
        } else {
                tableviewMonHocsFormController.setData(
                        (ArrayList<MonHoc>) ((ArrayList<? extends Model>) MonHocController.getInstance()
                                .GetList()));
            
        }
    }

    private void ResetDataAdd() {
        this.textfieldAddMaMH.setText("");
        this.textfieldAddTenMH.setText("");
    }

    private void ResetDataFind() {
        this.textfieldFindMaMH.setText("");
        this.textfieldFindTenMH.setText("");
        this.checkboxFindMaMH.setSelected(false);
        this.checkboxFindTenMH.setSelected(false);
    }

    private void ResetDataEdit() {
        this.comboboxEditMaMH.getSelectionModel().select(null);
        this.textfieldEditTenMH.setText("");
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
            fxmll = new FXMLLoader(this.getClass().getResource("MonHocsTableForm.fxml"));
            this.tableviewMonHocs = fxmll.load();
            this.tableviewMonHocsFormController = fxmll.getController();
            this.borderpaneContentTable.setCenter(this.tableviewMonHocs);
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
                        + this.tableviewMonHocs.getSelectionModel().getSelectedItems().size() + ")");
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

        ///Binding dữ liện combobox MaMH với bảng khi mở form sửa lên///
        this.togglebuttonEdit.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.comboboxEditMaMH.getItems().clear();
                this.comboboxEditMaMH.getItems().addAll(this.tableviewMonHocs.getItems());
                this.comboboxEditMaMH.getSelectionModel().select(
                        this.tableviewMonHocsFormController.getSelectedItem());
            }
        });

        ///Binding dữ liện combobox MaMH với bảng khi mở menu list (đang mở form sửa) lên///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (togglebuttonEdit.isSelected()) {
                    this.comboboxEditMaMH.getSelectionModel().select(
                            this.tableviewMonHocsFormController.getSelectedItem());
                }
            }
        });

        ///Binding dữ liệu khi chọn MaMH trong sửa MH///
        this.comboboxEditMaMH.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                MonHoc hocsinhSelected = newValue;
                this.textfieldEditTenMH.setText(hocsinhSelected.getTenMH());
            } else {
                this.ResetDataEdit();
            }
        });

        ///Chức năng thêm môn học///
        this.buttonAdd.setOnAction((event) -> {
            MonHoc model = new MonHoc(
                    this.textfieldAddMaMH.getText(),
                    this.textfieldAddTenMH.getText()
            );

            this.tableviewMonHocsFormController.AddItem(model);
            this.ResetDataAdd();
        });
        this.buttonAddReset.setOnAction((event) -> {
            this.ResetDataAdd();
        });

        ///Chức năng xóa môn học///
        this.buttonDelete.setOnAction((event) -> {
            this.tableviewMonHocsFormController.DeleteChoiseItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewMonHocs.getSelectionModel().getSelectedItems().size() + ")");
        });
        this.buttonDeleteReset.setOnAction((event) -> {
            this.tableviewMonHocsFormController.ClearSelectedItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewMonHocs.getSelectionModel().getSelectedItems().size() + ")");
        });

        ///Chức năng sửa môn học///
        this.buttonEdit.setOnAction((event) -> {
            if (this.comboboxEditMaMH.getSelectionModel().getSelectedItem() != null) {
                MonHoc model = (MonHoc) this.comboboxEditMaMH.getSelectionModel().getSelectedItem().clone();

                model.setTenMH(this.textfieldEditTenMH.getText());

                if (model.equalsAll(this.comboboxEditMaMH.getSelectionModel().getSelectedItem()) == false) {
                    this.tableviewMonHocsFormController.EditItem(model);
                }
            }
        });
        this.buttonEditReset.setOnAction((event) -> {
            this.ResetDataEdit();
        });

        ///Chức năng tìm kiếm///
        this.buttonFind.setOnAction((event) -> {
            MonHoc model = new MonHoc();

            if (this.checkboxFindTenMH.isSelected()) {
                model.setTenMH(this.textfieldFindTenMH.getText());
            }

            if (this.checkboxFindMaMH.isSelected()) {
                model.setMaMH(this.textfieldFindMaMH.getText());
            }

            model.ChangeToNull();

            this.tableviewMonHocsFormController.ClearSelectedItems();
            this.tableviewMonHocsFormController.SearchItems(model);
        });
        this.buttonFindReset.setOnAction((event) -> {
            this.ResetDataFind();
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewMonHocsFormController.Refresh();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng UnselectAll///
        this.togglebuttonUnselectAll.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewMonHocsFormController.ClearSelectedItems();
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewMonHocs.getSelectionModel().getSelectedItems().size() + ")");
                this.togglebuttonUnselectAll.setSelected(false);
            }
        }));

        ///Chức năng reset khi nhấn delete///
        this.textfieldAddMaMH.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddTenMH.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldEditTenMH.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindMaMH.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindTenMH.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });

        this.comboboxEditMaMH.setOnKeyPressed((event) -> {
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
