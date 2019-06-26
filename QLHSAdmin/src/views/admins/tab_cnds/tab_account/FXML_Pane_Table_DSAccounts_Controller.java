/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.admins.tab_cnds.tab_account;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class FXML_Pane_Table_DSAccounts_Controller implements Initializable {

    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> tableColumn_Username;
    @FXML
    private TableColumn<?, ?> tableColumn_Password;
    @FXML
    private TableColumn<?, ?> tableColumn_Displayname;
    @FXML
    private TableColumn<?, ?> tableColumn_Type;
    @FXML
    private TableColumn<?, ?> tableColumn_MaGV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
