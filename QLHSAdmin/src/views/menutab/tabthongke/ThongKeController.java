/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.menutab.tabthongke;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class ThongKeController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private ScrollPane scrollpaneContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("TKContentForm.fxml"));
            this.scrollpaneContent.setContent(fxmll.load());
        } catch (IOException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    
}
