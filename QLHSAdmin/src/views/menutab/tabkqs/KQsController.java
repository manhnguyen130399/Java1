/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabkqs;

import com.jfoenix.controls.JFXButton;
import controllers.HanhKiemController;
import controllers.HocLucController;
import controllers.KQController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import models.Accounts;
import models.HanhKiem;
import models.HocLuc;
import models.KQ;
import models.Model;

public class KQsController implements Initializable {

    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);

    private TableView<KQ> tableView;
    private KQsTableController tableviewController;

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private BorderPane borderpaneKQTable;
    @FXML
    private ScrollPane scrollpaneListMenu;
    @FXML
    private ToggleButton togglebuttonEdit;
    @FXML
    private ToggleButton togglebuttonSearch;
    @FXML
    private ToggleButton togglebuttonRefresh;
    @FXML
    private ToggleButton togglebuttonUnselectAll;
    @FXML
    private BorderPane borderpaneEdit;
    @FXML
    private JFXButton buttonEditBack;
    @FXML
    private TextField textfieldEditMaHS;
    @FXML
    private TextField textfieldEditMaLoaiHL;
    @FXML
    private ComboBox<HanhKiem> comboboxEditMaLoaiHK;
    @FXML
    private TextField textfieldEditNam;
    @FXML
    private TextField textfieldEditHocKi;
    @FXML
    private TextField textfieldEditDTB;
    @FXML
    private JFXButton buttonEdit;
    @FXML
    private JFXButton buttonEditReset;
    @FXML
    private BorderPane borderpaneSearch;
    @FXML
    private JFXButton buttonSearchBack;
    @FXML
    private CheckBox checkboxSearchMaHS;
    @FXML
    private TextField textfieldSearchMaHS;
    @FXML
    private CheckBox checkboxSearchMaLoaiHL;
    @FXML
    private TextField textfieldSearchMaLoaiHL;
    @FXML
    private CheckBox checkboxSearchMaLoaiHK;
    @FXML
    private TextField textfieldSearchMaLoaiHK;
    @FXML
    private CheckBox checkboxSearchNam;
    @FXML
    private TextField textfieldSearchNam;
    @FXML
    private CheckBox checkboxSearchHocKi;
    @FXML
    private TextField textfieldSearchHocKi;
    @FXML
    private CheckBox checkboxSearchDTBBiggerThan;
    @FXML
    private TextField textfieldSearchDTBBiggerThan;
    @FXML
    private CheckBox checkboxSearchDTBSmallerThan;
    @FXML
    private TextField textfieldSearchDTBSmallerThan;
    @FXML
    private JFXButton buttonSearch;
    @FXML
    private JFXButton buttonSearchReset;
    @FXML
    private ToggleButton togglebuttonListMenu;
    @FXML
    private ScrollPane scrollpaneKQTable;
    @FXML
    private StackPane stackpaneMenu;

    private void SetDataToComboboxHanhKiem() {
        this.comboboxEditMaLoaiHK.getItems().clear();
        this.comboboxEditMaLoaiHK.getItems().setAll(
                (ArrayList<HanhKiem>) (ArrayList<? extends Model>) HanhKiemController.getInstance()
                        .GetList(this.currentAccountProperty.get()));
    }

    private void HideScrollpaneKQTable(boolean isHide) {
        if (isHide) {
            this.scrollpaneKQTable.setOpacity(0.1);
        } else {
            this.scrollpaneKQTable.setOpacity(1);
        }
    }

    private void HideScrollpaneListMenu(boolean isHide) {
        if (isHide) {
            this.scrollpaneListMenu.setOpacity(0.1);
        } else {
            this.scrollpaneListMenu.setOpacity(1);
        }
    }

    private void HideStackpaneMenu(boolean isHide) {
        isHide = !isHide;
        this.stackpaneMenu.setVisible(isHide);
        this.stackpaneMenu.setManaged(isHide);
    }

    private void SetDataToEditForm(KQ kq) {
        if (kq != null) {
            this.textfieldEditDTB.setText(Double.toString(kq.getDTB()));
            this.textfieldEditHocKi.setText(Integer.toString(kq.getHocKi()));
            this.textfieldEditNam.setText(Integer.toString(kq.getNam()));

            HocLuc hocluc = (HocLuc) HocLucController.getInstance().GetModel(new HocLuc(kq.getMaLoaiHL()));
            this.textfieldEditMaLoaiHL.setText(hocluc.toString());

            this.comboboxEditMaLoaiHK.getSelectionModel().select(
                    (HanhKiem) HanhKiemController.getInstance().GetModel(new HanhKiem(kq.getLoaiHK()))
            );
            this.textfieldSearchMaHS.setText(kq.getMaHS());
        } else {
            this.textfieldEditDTB.setText("");
            this.textfieldEditHocKi.setText("");
            this.textfieldEditNam.setText("");
            this.textfieldEditMaLoaiHL.setText("");
            this.comboboxEditMaLoaiHK.getSelectionModel().select(null);
            this.textfieldSearchMaHS.setText("");
        }
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    public void RefreshAllData() {
        this.SetDataToComboboxHanhKiem();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Khởi tạo bảng///
        try {
            FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("KQsTableForm.fxml"));
            this.tableView = fxmll.load();
            this.borderpaneKQTable.setCenter(this.tableView);
            this.tableviewController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(KQsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ///Binding dữ liệu khi account thay đổi///
        this.currentAccountProperty.addListener((observable, oldValue, newValue) -> {
            this.tableviewController.setCurrentAccountProperty(newValue);
            this.RefreshAllData();
        });

        this.tableviewController.getCurrentAccountProperty().addListener((observable, oldValue, newValue) -> {
            this.currentAccountProperty.set(newValue);
        });

        ///Binding ấn nút Menu hiện menu///
        this.togglebuttonListMenu.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneKQTable(newValue);
            this.HideStackpaneMenu(!newValue);
            this.HideScrollpaneListMenu(!newValue);
        });

        ///Binding togglebutton list menu///
        this.borderpaneEdit.visibleProperty().bind(this.togglebuttonEdit.selectedProperty());
        this.borderpaneEdit.managedProperty().bind(this.togglebuttonEdit.selectedProperty());

        this.borderpaneSearch.visibleProperty().bind(this.togglebuttonSearch.selectedProperty());
        this.borderpaneSearch.managedProperty().bind(this.togglebuttonSearch.selectedProperty());

        this.togglebuttonEdit.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneListMenu(newValue);
        });
        this.togglebuttonSearch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.HideScrollpaneListMenu(newValue);
        });

        this.buttonEditBack.setOnAction((event) -> {
            this.togglebuttonEdit.setSelected(false);
        });
        this.buttonSearchBack.setOnAction((event) -> {
            this.togglebuttonSearch.setSelected(false);
        });

        ///Binding dữ liệu chọn trên bảng vs menu///
        this.tableviewController.getSelectedItems().addListener((ListChangeListener.Change<? extends KQ> c) -> {
            if (c.next()) {
                this.togglebuttonUnselectAll.setText("UnSelect these KQs (" + c.getList().size() + ")");
            }
        });
        this.tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.SetDataToEditForm(newValue);
            }
        });

        ///Chức năng sửa//
        this.buttonEdit.setOnAction((event) -> {
            KQ newKQ = (KQ) this.tableviewController.getSelectedItem().clone();

            newKQ.setLoaiHK(this.comboboxEditMaLoaiHK.getSelectionModel().getSelectedItem().getMaHK());

            if (KQController.getInstance().Edit(this.tableviewController.getSelectedItem(), newKQ)) {
                this.SetDataToEditForm(newKQ);
                this.tableviewController.getSelectedItem().ReplaceBy(newKQ);
            }
        });

        ///Chức năng reset sửa//
        this.buttonEditReset.setOnAction((event) -> {
            this.SetDataToEditForm(this.tableviewController.getSelectedItem());
        });

        ///Chức năng Search///
        this.buttonSearch.setOnAction((event) -> {
            KQ kqKeyword = new KQ();

            Double dtbBiggerThan = null;
            Double dtbSmallerThan = null;

            int searchAttributions = 0;

            if (checkboxSearchDTBBiggerThan.isSelected()) {
                if (this.textfieldSearchDTBBiggerThan.getText().isEmpty() == false) {
                    dtbBiggerThan = Double.parseDouble(this.textfieldSearchDTBBiggerThan.getText());
                }

                searchAttributions++;
            }

            if (checkboxSearchDTBSmallerThan.isSelected()) {
                if (this.textfieldSearchDTBSmallerThan.getText().isEmpty() == false) {
                    dtbSmallerThan = Double.parseDouble(this.textfieldSearchDTBSmallerThan.getText());
                }

                searchAttributions++;
            }

            if (checkboxSearchHocKi.isSelected()) {
                if (this.textfieldSearchHocKi.getText().isEmpty() == false) {
                    kqKeyword.setHocKi(Integer.parseInt(this.textfieldSearchHocKi.getText()));
                }

                searchAttributions++;
            }

            if (checkboxSearchNam.isSelected()) {
                if (this.textfieldSearchNam.getText().isEmpty() == false) {
                    kqKeyword.setNam(Integer.parseInt(this.textfieldSearchNam.getText()));
                }

                searchAttributions++;
            }

            if (checkboxSearchMaHS.isSelected()) {
                if (this.textfieldSearchMaHS.getText().isEmpty() == false) {
                    kqKeyword.setMaHS(this.textfieldSearchMaHS.getText());
                }

                searchAttributions++;
            }

            if (checkboxSearchMaLoaiHL.isSelected()) {
                if (this.textfieldSearchMaLoaiHL.getText().isEmpty() == false) {
                    kqKeyword.setMaLoaiHL(this.textfieldSearchMaLoaiHL.getText());
                }

                searchAttributions++;
            }

            if (checkboxSearchMaLoaiHK.isSelected()) {
                if (this.textfieldSearchMaLoaiHK.getText().isEmpty() == false) {
                    kqKeyword.setLoaiHK(this.textfieldSearchMaLoaiHK.getText());
                }

                searchAttributions++;
            }

            kqKeyword.ChangeToNull();
            ArrayList<KQ> alKqsRemoved = new ArrayList<>();

            for (KQ kq : this.tableView.getItems()) {
                int kqSearchAttributions = 0;

                if (dtbBiggerThan != null) {
                    if (kq.getDTB() >= dtbBiggerThan) {
                        kqSearchAttributions++;
                    }
                }

                if (dtbSmallerThan != null) {
                    if (kq.getDTB() <= dtbSmallerThan) {
                        kqSearchAttributions++;
                    }
                }

                if (kqKeyword.getHocKi() != null) {
                    if (Objects.equals(kq.getHocKi(), kqKeyword.getHocKi())) {
                        kqSearchAttributions++;
                    }
                }

                if (kqKeyword.getNam() != null) {
                    if (Objects.equals(kq.getHocKi(), kqKeyword.getHocKi())) {
                        kqSearchAttributions++;
                    }
                }

                if (kqKeyword.getMaHS() != null) {
                    if (kq.getMaHS().equals(kqKeyword.getMaHS())) {
                        kqSearchAttributions++;
                    }
                }

                if (kqKeyword.getLoaiHK() != null) {
                    if (kq.getLoaiHK().equals(kqKeyword.getLoaiHK())) {
                        kqSearchAttributions++;
                    }
                }

                if (kqKeyword.getMaLoaiHL() != null) {
                    if (kq.getMaLoaiHL().equals(kqKeyword.getMaLoaiHL())) {
                        kqSearchAttributions++;
                    }
                }

                if (kqSearchAttributions >= searchAttributions) {
                    alKqsRemoved.add(kq);
                }
            }

            this.tableView.getItems().removeAll(alKqsRemoved);
        });

        ///Chức năng reset Search///
        this.buttonSearchReset.setOnAction((event) -> {
            this.textfieldSearchHocKi.setText("");
            this.textfieldSearchNam.setText("");
            this.textfieldSearchMaLoaiHL.setText("");
            this.textfieldSearchDTBBiggerThan.setText("");
            this.textfieldSearchDTBSmallerThan.setText("");
            this.textfieldSearchMaHS.setText("");
            this.textfieldSearchMaLoaiHK.setText("");

            this.checkboxSearchDTBBiggerThan.setSelected(false);
            this.checkboxSearchDTBSmallerThan.setSelected(false);
            this.checkboxSearchHocKi.setSelected(false);
            this.checkboxSearchMaHS.setSelected(false);
            this.checkboxSearchMaLoaiHK.setSelected(false);
            this.checkboxSearchMaLoaiHL.setSelected(false);
            this.checkboxSearchNam.setSelected(false);

            this.tableviewController.ReFillData();
        });

        ///Chức năng kiểm tra thông tin khi nhấp dữ liệu search///
        this.textfieldSearchDTBBiggerThan.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isEmpty() == false) {
                    try {
                        Double.parseDouble(newValue);

                        if (checkboxSearchDTBBiggerThan.isSelected()
                                && checkboxSearchDTBSmallerThan.isSelected()
                                && this.textfieldSearchDTBSmallerThan.getText().isEmpty() == false) {
                            if (Double.parseDouble(newValue) > Double.parseDouble(this.textfieldSearchDTBSmallerThan.getText())) {
                                this.textfieldSearchDTBBiggerThan.setText("");
                            }
                        }

                    } catch (Exception e) {
                        this.textfieldSearchDTBBiggerThan.setText("");
                    }
                }
            }
        });
        this.textfieldSearchDTBSmallerThan.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isEmpty() == false) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        this.textfieldSearchDTBSmallerThan.setText("");
                    }
                }
            }
        });
        this.textfieldSearchHocKi.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isEmpty() == false) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        this.textfieldSearchHocKi.setText("");
                    }
                }
            }
        });
        this.textfieldSearchNam.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isEmpty() == false) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        this.textfieldSearchNam.setText("");
                    }
                }
            }
        });
        
        ///Chức năng Refresh///
        this.togglebuttonRefresh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                this.RefreshAllData();
                this.togglebuttonRefresh.setSelected(false);
            }
        });

        ///Ẩn hiện views///
        this.HideScrollpaneKQTable(false);
        this.HideScrollpaneListMenu(false);
        this.HideStackpaneMenu(true);
    }
}
