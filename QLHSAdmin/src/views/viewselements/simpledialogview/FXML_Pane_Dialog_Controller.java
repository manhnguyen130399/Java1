/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.simpledialogview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class FXML_Pane_Dialog_Controller implements Initializable {

    @FXML
    private BorderPane borderPane_root;

    @FXML
    private Label label_title;

    @FXML
    private TextArea textArea_content;

    @FXML
    private void button_minimize(ActionEvent event) {
        ((Stage) (this.borderPane_root).getScene().getWindow()).setIconified(true);
    }

    @FXML
    private void button_exit(ActionEvent event) {
        ((Stage) (this.borderPane_root).getScene().getWindow()).close();
    }

    @FXML
    private void button_OK(ActionEvent event) {
        this.buttonSelected = ButtonType.OK;
        button_exit(event);
    }
    
    @FXML
    private void button_CANCEL(ActionEvent event){
        this.buttonSelected = ButtonType.CANCEL;
        button_exit(event);
    }
    
    private ButtonType buttonSelected;

    public ButtonType getButtonSelected() {
        return buttonSelected;
    }

    public void set_title(String title) {
        this.label_title.setText(title);
    }

    public void set_content(String content) {
        this.textArea_content.setText(content);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.buttonSelected = ButtonType.CANCEL;
    }

}
