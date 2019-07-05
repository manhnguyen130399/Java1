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
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrintResolution;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

public class ThongKeController implements Initializable {

    @FXML
    private BorderPane borderpaneRoot;
    @FXML
    private ScrollPane scrollpaneContent;
    @FXML
    private  Button buttonUp;
    @FXML
    private Button buttonPrint;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("TKContentForm.fxml"));
            this.scrollpaneContent.setContent(fxmll.load());
             this.buttonUp.setOnAction((event) -> {
                this.scrollpaneContent.setVvalue(0);
                this.scrollpaneContent.setHvalue(0);
            });

            this.buttonPrint.setOnAction((event) -> {
                VBox vBoxContent = (VBox) this.scrollpaneContent.getContent();

                PrinterJob job = PrinterJob.createPrinterJob();

                if (job != null && job.showPrintDialog(vBoxContent.getScene().getWindow())) {
                    Printer printer = job.getPrinter();
                    PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);

                    double width = vBoxContent.getWidth();
                    double height = vBoxContent.getHeight();

                    PrintResolution resolution = job.getJobSettings().getPrintResolution();

                    width /= resolution.getFeedResolution();

                    height /= resolution.getCrossFeedResolution();

                    double scaleX = pageLayout.getPrintableWidth() / width / 600;
                    double scaleY = pageLayout.getPrintableHeight() / height / 600;

                    Scale scale = new Scale(scaleX, scaleY);

                    vBoxContent.getTransforms().add(scale);

                    boolean success = job.printPage(pageLayout, vBoxContent);
                    if (success) {
                        job.endJob();
                    }
                    vBoxContent.getTransforms().remove(scale);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    
}
