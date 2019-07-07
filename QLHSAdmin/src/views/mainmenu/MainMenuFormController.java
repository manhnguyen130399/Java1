/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.mainmenu;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import models.Accounts;
import views.menutab.tabaccountses.AccountsesController;
import views.menutab.tabchunhiems.ChuNhiemsController;
import views.menutab.tabcos.COsController;
import views.menutab.tabgiangdays.GiangDaysController;
import views.menutab.tabgiaoviens.GiaoViensController;
import views.menutab.tabhanhkiems.HanhKiemsController;
import views.menutab.tabhocki_namhocs.HocKi_NamHocsController;
import views.menutab.tabhoclucs.HocLucsController;
import views.menutab.tabhocs.HocsController;
import views.menutab.tabkqs.KQsController;
import views.menutab.tabkts.KTsController;
import views.menutab.tablops.LopsController;
import views.menutab.tabmonhocs.MonHocsController;
import views.menutab.tabthongke.ThongKeController;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class MainMenuFormController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private JFXTabPane tabpaneContent;
    @FXML
    private BorderPane borderpaneMenu;
    @FXML
    private ScrollPane scrollpaneContent;
    @FXML
    private JFXMasonryPane masonrypaneContent;
    @FXML
    private ToggleButton togglebuttonAccountses;
    @FXML
    private ToggleButton togglebuttonCOs;
    @FXML
    private ToggleButton togglebuttonGiaoViens;
    @FXML
    private ToggleButton togglebuttonHanhKiems;
    @FXML
    private ToggleButton togglebuttonHocs;
    @FXML
    private ToggleButton togglebuttonHocKi_NamHocs;
    @FXML
    private ToggleButton togglebuttonHocLucs;
    @FXML
    private ToggleButton togglebuttonKQs;
    @FXML
    private ToggleButton togglebuttonKTs;
    @FXML
    private ToggleButton togglebuttonLops;
    @FXML
    private ToggleButton togglebuttonMonHocs;
    @FXML
    private ToggleButton togglebuttonOpenAll;
    @FXML
    private ToggleButton togglebuttonCloseAll;
    @FXML
    private ToggleButton togglebuttonBack;
    @FXML
    private ToggleButton togglebuttonThongKe;
    @FXML
    private ToggleButton toggleButtonChuNhiem;
    @FXML
    private ToggleButton toggleButtonGiangDay;
    public static enum STATUS {
        READY, RUNNING, BACK, NONE
    }

    private final int TABS_AVAIABLE = 1 + 15;
    private final ObjectProperty<STATUS> statusProperty = new SimpleObjectProperty<>(STATUS.NONE);
    private final ObjectProperty<Accounts> currentAccountProperty = new SimpleObjectProperty<>(null);

    private final Tab tabAccountses = new Tab("Accountses");
    private final Tab tabCOs = new Tab("Học sinh");
    private final Tab tabChuNhiems = new Tab("Chủ nhiệm");
    private final Tab tabGiangDays = new Tab("Giảng dạy");
    private final Tab tabGiaoViens = new Tab("Giáo viên");
    private final Tab tabHanhKiems = new Tab("Hạnh kiểm");
    private final Tab tabHocs = new Tab("Phân lớp");
    private final Tab tabHocKi_NamHocs = new Tab("Học kì - Năm học");
    private final Tab tabHocLucs = new Tab("Học lực");
    private final Tab tabKQs = new Tab("Kết quả");
    private final Tab tabKTs = new Tab("Nhập điểm");
    private final Tab tabLops = new Tab("Lớp");
    private final Tab tabMonHocs = new Tab("Môn học");
    private final Tab tabThongKe = new Tab("Thống kê");

    private AccountsesController accountsesController;
    private COsController cosController;
    private GiaoViensController giaoviensController;
    private HanhKiemsController hanhkiemsController;
    private HocsController hocsController;
    private HocKi_NamHocsController hocki_namhocsController;
    private HocLucsController hoclucsController;
    private KQsController kqsController;
    private KTsController ktsController;
    private LopsController lopsController;
    private MonHocsController monhocsController;
    private ThongKeController thongKeController;
    private ChuNhiemsController chuNhiemsController;
    private GiangDaysController giangDaysController;

    private void SetOpenAllTabAvaiable(boolean isOpen) {
        this.togglebuttonAccountses.setSelected(isOpen);
        this.togglebuttonCOs.setSelected(isOpen);
        this.togglebuttonGiaoViens.setSelected(isOpen);
        this.togglebuttonHanhKiems.setSelected(isOpen);
        this.togglebuttonHocs.setSelected(isOpen);
        this.togglebuttonHocKi_NamHocs.setSelected(isOpen);
        this.togglebuttonHocLucs.setSelected(isOpen);
        this.togglebuttonKQs.setSelected(isOpen);
        this.togglebuttonKTs.setSelected(isOpen);
        this.togglebuttonLops.setSelected(isOpen);
        this.togglebuttonMonHocs.setSelected(isOpen);
        this.togglebuttonThongKe.setSelected(isOpen);
        this.toggleButtonChuNhiem.setSelected(isOpen);
        this.toggleButtonGiangDay.setSelected(isOpen);
    }

    public ObjectProperty<STATUS> getStatusProperty() {
        return statusProperty;
    }

    public ObjectProperty<Accounts> getCurrentAccountProperty() {
        return currentAccountProperty;
    }

    public void setCurrentAccountProperty(Accounts currentAccount) {
        this.currentAccountProperty.set(currentAccount);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///Khởi tạo tab//
        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabaccountses/AccountsesForm.fxml"));
            this.tabAccountses.setContent(fxmll.load());
            this.accountsesController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        try{
            FXMLLoader fxmll;
            fxmll=new FXMLLoader(this.getClass().getResource("/views/menutab/tabchunhiems/ChuNhiemsForm.fxml"));
            this.tabChuNhiems.setContent(fxmll.load());
            this.chuNhiemsController=fxmll.getController();
        }catch(IOException ex){
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            FXMLLoader fxmll;
            fxmll=new FXMLLoader(this.getClass().getResource("/views/menutab/tabgiangdays/GiangDaysForm.fxml"));
            this.tabGiangDays.setContent(fxmll.load());
            this.giangDaysController=fxmll.getController();
        }catch(IOException ex){
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabcos/COsForm.fxml"));
            this.tabCOs.setContent(fxmll.load());
            this.cosController = fxmll.getController();
        } catch (IOException ex) {
            
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabgiaoviens/GiaoViensForm.fxml"));
            this.tabGiaoViens.setContent(fxmll.load());
            this.giaoviensController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabhanhkiems/HanhKiemsForm.fxml"));
            this.tabHanhKiems.setContent(fxmll.load());
            this.hanhkiemsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabhocs/HocsForm.fxml"));
            this.tabHocs.setContent(fxmll.load());
            this.hocsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabhocki_namhocs/HocKi_NamHocsForm.fxml"));
            this.tabHocKi_NamHocs.setContent(fxmll.load());
            this.hocki_namhocsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabhoclucs/HocLucsForm.fxml"));
            this.tabHocLucs.setContent(fxmll.load());
            this.hoclucsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabkqs/KQsForm.fxml"));
            this.tabKQs.setContent(fxmll.load());
            this.kqsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabkts/KTsForm.fxml"));
            this.tabKTs.setContent(fxmll.load());
            this.ktsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tablops/LopsForm.fxml"));
            this.tabLops.setContent(fxmll.load());
            this.lopsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabmonhocs/MonHocsForm.fxml"));
            this.tabMonHocs.setContent(fxmll.load());
            this.monhocsController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FXMLLoader fxmll;
            fxmll = new FXMLLoader(this.getClass().getResource("/views/menutab/tabthongke/ThongKeForm.fxml"));
            this.tabThongKe.setContent(fxmll.load());
            this.thongKeController = fxmll.getController();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ///Khởi tạo binding dữ liệu cho từng tab///
        //Tab Accountses//
        this.accountsesController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.accountsesController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.accountsesController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.accountsesController.getCurrentAccountProperty().get()) == false) {
                this.accountsesController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab GiaoViens//
        this.giaoviensController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.giaoviensController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.giaoviensController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.giaoviensController.getCurrentAccountProperty().get()) == false) {
                this.giaoviensController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab hocki_namhocs//
        this.hocki_namhocsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.hocki_namhocsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.hocki_namhocsController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.hocki_namhocsController.getCurrentAccountProperty().get()) == false) {
                this.hocki_namhocsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab Lops//
        this.lopsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.lopsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.lopsController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.lopsController.getCurrentAccountProperty().get()) == false) {
                this.lopsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab MonHocs//
        this.monhocsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.monhocsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.monhocsController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.monhocsController.getCurrentAccountProperty().get()) == false) {
                this.monhocsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab HocLucs//
        this.hoclucsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.hoclucsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.hoclucsController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.hoclucsController.getCurrentAccountProperty().get()) == false) {
                this.hoclucsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab HanhKiems//
        this.hanhkiemsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.hanhkiemsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.hanhkiemsController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.hanhkiemsController.getCurrentAccountProperty().get()) == false) {
                this.hanhkiemsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });

        //Tab HanhKiems//
        this.cosController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.cosController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.cosController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.cosController.getCurrentAccountProperty().get()) == false) {
                this.cosController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });
        
        //Tab Hocs//
        this.hocsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.hocsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.hocsController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.hocsController.getCurrentAccountProperty().get()) == false) {
                this.hocsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });
        
        //Tab Kts//
        this.ktsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.ktsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.ktsController.getCurrentAccountProperty().get());
            }
        });
        this.currentAccountProperty.addListener((observable) -> {
            if (this.currentAccountProperty.get()
                    .equalsAll(this.ktsController.getCurrentAccountProperty().get()) == false) {
                this.ktsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
            }
        });
        //Tab chu nhiem
        this.chuNhiemsController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.chuNhiemsController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.chuNhiemsController.getCurrentAccountProperty().get());
            }
        });
       this.currentAccountProperty.addListener((observable) -> {
           if(this.currentAccountProperty.get().equalsAll(this.chuNhiemsController.getCurrentAccountProperty().get())==false){
               this.chuNhiemsController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
           }
       });
       //Tab giang day
       this.giangDaysController.getCurrentAccountProperty().addListener((observable) -> {
            if (this.giangDaysController.getCurrentAccountProperty().get()
                    .equalsAll(this.currentAccountProperty.get()) == false) {
                this.currentAccountProperty.set(this.giangDaysController.getCurrentAccountProperty().get());
            }
        });
       this.currentAccountProperty.addListener((observable) -> {
           if(this.currentAccountProperty.get().equalsAll(this.giangDaysController.getCurrentAccountProperty().get())==false){
               this.giangDaysController.getCurrentAccountProperty().set(this.currentAccountProperty.get());
           }
       });
       
        

        ///Chức năng thêm, gỡ tab///
        this.tabpaneContent.getTabs().addListener((ListChangeListener.Change<? extends Tab> c) -> {
            if (c.next()) {
                if (c.getList().size() <= 0) {
                    this.togglebuttonCloseAll.setSelected(true);
                } else {
                    this.togglebuttonCloseAll.setSelected(false);

                    if (c.getList().size() >= this.TABS_AVAIABLE) {
                        this.togglebuttonOpenAll.setSelected(true);
                    } else {
                        this.togglebuttonOpenAll.setSelected(false);
                    }
                }
            }
        });

        ///Binding mở tắt button, mở đóng tab///
        this.togglebuttonAccountses.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabAccountses) == false) {
                    this.tabpaneContent.getTabs().add(this.tabAccountses);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabAccountses);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabAccountses) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabAccountses);
                }
            }
        });
        this.togglebuttonCOs.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabCOs) == false) {
                    this.tabpaneContent.getTabs().add(this.tabCOs);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabCOs);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabCOs) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabCOs);
                }
            }
        });
        this.togglebuttonGiaoViens.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabGiaoViens) == false) {
                    this.tabpaneContent.getTabs().add(this.tabGiaoViens);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabGiaoViens);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabGiaoViens) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabGiaoViens);
                }
            }
        });
        this.togglebuttonHanhKiems.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabHanhKiems) == false) {
                    this.tabpaneContent.getTabs().add(this.tabHanhKiems);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabHanhKiems);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabHanhKiems) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabHanhKiems);
                }
            }
        });
        this.togglebuttonHocs.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabHocs) == false) {
                    this.tabpaneContent.getTabs().add(this.tabHocs);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabHocs);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabHocs) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabHocs);
                }
            }
        });
       
        this.togglebuttonHocKi_NamHocs.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabHocKi_NamHocs) == false) {
                    this.tabpaneContent.getTabs().add(this.tabHocKi_NamHocs);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabHocKi_NamHocs);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabHocKi_NamHocs) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabHocKi_NamHocs);
                }
            }
        });
        this.togglebuttonHocLucs.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabHocLucs) == false) {
                    this.tabpaneContent.getTabs().add(this.tabHocLucs);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabHocLucs);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabHocLucs) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabHocLucs);
                }
            }
        });
        this.togglebuttonKQs.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabKQs) == false) {
                    this.tabpaneContent.getTabs().add(this.tabKQs);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabKQs);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabKQs) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabKQs);
                }
            }
        });
        this.togglebuttonKTs.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabKTs) == false) {
                    this.tabpaneContent.getTabs().add(this.tabKTs);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabKTs);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabKTs) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabKTs);
                }
            }
        });
        this.togglebuttonLops.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabLops) == false) {
                    this.tabpaneContent.getTabs().add(this.tabLops);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabLops);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabLops) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabLops);
                }
            }
        });
        this.togglebuttonMonHocs.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabMonHocs) == false) {
                    this.tabpaneContent.getTabs().add(this.tabMonHocs);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabMonHocs);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabMonHocs) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabMonHocs);
                }
            }
        });
        this.togglebuttonThongKe.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabThongKe) == false) {
                    this.tabpaneContent.getTabs().add(this.tabThongKe);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabThongKe);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabThongKe) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabThongKe);
                }
            }
        });
        
        this.toggleButtonChuNhiem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabChuNhiems) == false) {
                    this.tabpaneContent.getTabs().add(this.tabChuNhiems);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabChuNhiems);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabChuNhiems) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabChuNhiems);
                }
            }
        });
        
         this.toggleButtonGiangDay.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().contains(this.tabGiangDays) == false) {
                    this.tabpaneContent.getTabs().add(this.tabGiangDays);
                    this.tabpaneContent.selectionModelProperty().getValue().select(this.tabGiangDays);
                }
            } else {
                if (this.tabpaneContent.getTabs().contains(this.tabGiangDays) == true) {
                    this.tabpaneContent.getTabs().remove(this.tabGiangDays);
                }
            }
        });
        

        ///Binding đóng tab, mở tắt button///
        this.tabAccountses.setOnCloseRequest((event) -> {
            this.togglebuttonAccountses.setSelected(false);
        });

        this.tabCOs.setOnCloseRequest((event) -> {
            this.togglebuttonCOs.setSelected(false);
        });

        this.tabGiaoViens.setOnCloseRequest((event) -> {
            this.togglebuttonGiaoViens.setSelected(false);
        });

        this.tabHanhKiems.setOnCloseRequest((event) -> {
            this.togglebuttonHanhKiems.setSelected(false);
        });

        this.tabHocs.setOnCloseRequest((event) -> {
            this.togglebuttonHocs.setSelected(false);
        });

        this.tabHocKi_NamHocs.setOnCloseRequest((event) -> {
            this.togglebuttonHocKi_NamHocs.setSelected(false);
        });

        this.tabHocLucs.setOnCloseRequest((event) -> {
            this.togglebuttonHocLucs.setSelected(false);
        });

        this.tabKQs.setOnCloseRequest((event) -> {
            this.togglebuttonKQs.setSelected(false);
        });

        this.tabKTs.setOnCloseRequest((event) -> {
            this.togglebuttonKTs.setSelected(false);
        });

        this.tabLops.setOnCloseRequest((event) -> {
            this.togglebuttonLops.setSelected(false);
        });

        this.tabMonHocs.setOnCloseRequest((event) -> {
            this.togglebuttonMonHocs.setSelected(false);
        });

        this.tabThongKe.setOnCloseRequest((event) -> {
            this.togglebuttonThongKe.setSelected(false);
        });
        
        this.tabChuNhiems.setOnCloseRequest((event) -> {
            this.toggleButtonChuNhiem.setSelected(false);
        });
        
        this.tabGiangDays.setOnCloseRequest((event) -> {
            this.toggleButtonGiangDay.setSelected(false);
        });
      

        ///Chức năng OpenAll
        this.togglebuttonOpenAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().size() < this.TABS_AVAIABLE) {
                    SetOpenAllTabAvaiable(true);
                }
            } else {
                if (this.tabpaneContent.getTabs().size() >= this.TABS_AVAIABLE) {
                    SetOpenAllTabAvaiable(false);
                }
            }

        });

        ///Chức năng CloseAll///
        this.togglebuttonCloseAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (this.tabpaneContent.getTabs().size() > 0) {
                    SetOpenAllTabAvaiable(false);
                    this.togglebuttonCloseAll.setSelected(false);
                }
            }
        });

        ///Chức năng back///
        this.togglebuttonBack.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.statusProperty.set(STATUS.BACK);
                this.togglebuttonBack.setSelected(false);
            }
        });

        ///Thay đổi status///
        this.statusProperty.set(STATUS.READY);
    }

}
