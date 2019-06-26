/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabhocki_namhocs;

import com.jfoenix.controls.JFXButton;
import controllers.HocKi_NamHocController;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import models.Accounts;
import models.HocKi_NamHoc;
import models.Model;
import views.mainmenu.MainMenuFormController;

public class HocKi_NamHocsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);
    private final ToggleGroup toggleGroupMenu = new ToggleGroup();

    private TableView<HocKi_NamHoc> tableviewHocKi_NamHocs;
    private HocKi_NamHocsTableFormController tableviewHocKi_NamHocsFormController;

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
    private JFXButton buttonFind;
    @FXML
    private JFXButton buttonFindReset;
    @FXML
    private ToggleButton togglebuttonListMenu;
    @FXML
    private ToggleButton togglebuttonListLine;
    @FXML
    private CheckBox checkboxFindNam;
    @FXML
    private CheckBox checkboxFindHocKi;
    @FXML
    private BorderPane borderpaneContentList;
    @FXML
    private ScrollPane scrollpaneContentTable;
    @FXML
    private BorderPane borderpaneContentTable;
    @FXML
    private BorderPane borderpaneContentListMenu;
    @FXML
    private TextField textfieldAddNam;
    @FXML
    private TextField textfieldAddHocKi;
    @FXML
    private TextField textfieldFindNam;
    @FXML
    private TextField textfieldFindHocKi;

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
            tableviewHocKi_NamHocsFormController.setData(
                    (ArrayList<HocKi_NamHoc>) ((ArrayList<? extends Model>) HocKi_NamHocController.getInstance()
                            .GetList(currentAccountProperty.get())));

        } else {
            tableviewHocKi_NamHocsFormController.setData(
                    (ArrayList<HocKi_NamHoc>) ((ArrayList<? extends Model>) HocKi_NamHocController.getInstance()
                            .GetList()));

        }
    }

    private void ResetDataAdd() {
        this.textfieldAddNam.setText("");
        this.textfieldAddHocKi.setText("");
    }

    private void ResetDataFind() {
        this.textfieldFindNam.setText("");
        this.textfieldFindHocKi.setText("");
        this.checkboxFindNam.setSelected(false);
        this.checkboxFindHocKi.setSelected(false);
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
            fxmll = new FXMLLoader(this.getClass().getResource("HocKi_NamHocsTableForm.fxml"));
            this.tableviewHocKi_NamHocs = fxmll.load();
            this.tableviewHocKi_NamHocsFormController = fxmll.getController();
            this.borderpaneContentTable.setCenter(this.tableviewHocKi_NamHocs);
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
                        + this.tableviewHocKi_NamHocs.getSelectionModel().getSelectedItems().size() + ")");
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

        ///Chức năng thêm học kì năm học///
        this.buttonAdd.setOnAction((event) -> {
            HocKi_NamHoc model = new HocKi_NamHoc(
                    Integer.parseInt(this.textfieldAddNam.getText()),
                    Integer.parseInt(this.textfieldAddHocKi.getText())
            );

            this.tableviewHocKi_NamHocsFormController.AddItem(model);
            this.ResetDataAdd();
        });
        this.buttonAddReset.setOnAction((event) -> {
            this.ResetDataAdd();
        });

        ///Chức năng xóa học kì năm học///
        this.buttonDelete.setOnAction((event) -> {
            this.tableviewHocKi_NamHocsFormController.DeleteChoiseItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewHocKi_NamHocs.getSelectionModel().getSelectedItems().size() + ")");
        });
        this.buttonDeleteReset.setOnAction((event) -> {
            this.tableviewHocKi_NamHocsFormController.ClearSelectedItems();
            this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                    + this.tableviewHocKi_NamHocs.getSelectionModel().getSelectedItems().size() + ")");
        });

        ///Chức năng tìm kiếm///
        this.buttonFind.setOnAction((event) -> {
            HocKi_NamHoc model = new HocKi_NamHoc();

            if (this.checkboxFindHocKi.isSelected()) {
                model.setHocKi(Integer.parseInt(this.textfieldFindHocKi.getText()));
            }

            if (this.checkboxFindNam.isSelected()) {
                model.setNam(Integer.parseInt(this.textfieldFindNam.getText()));
            }

            model.ChangeToNull();

            this.tableviewHocKi_NamHocsFormController.ClearSelectedItems();
            this.tableviewHocKi_NamHocsFormController.SearchItems(model);
        });
        this.buttonFindReset.setOnAction((event) -> {
            this.ResetDataFind();
        });

        ///Chức năng refresh data///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewHocKi_NamHocsFormController.Refresh();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Chức năng UnselectAll///
        this.togglebuttonUnselectAll.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                this.tableviewHocKi_NamHocsFormController.ClearSelectedItems();
                this.togglebuttonUnselectAll.setText("UnSelect These Rows("
                        + this.tableviewHocKi_NamHocs.getSelectionModel().getSelectedItems().size() + ")");
                this.togglebuttonUnselectAll.setSelected(false);
            }
        }));

        ///Chức năng reset khi nhấn delete///
        this.textfieldAddNam.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldAddHocKi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindNam.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });
        this.textfieldFindHocKi.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                ((TextField) event.getSource()).setText("");
            }
        });

        ///Reset data các from chức năng///
        this.ResetDataAdd();
        this.ResetDataFind();

        //Ần borderpane content list menu khi mở form///
        this.HideBorderpaneMenu(true);
    }
}
