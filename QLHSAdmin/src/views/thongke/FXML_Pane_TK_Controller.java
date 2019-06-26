/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.thongke;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyDoubleProperty;
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

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class FXML_Pane_TK_Controller implements Initializable {

    @FXML
    private BorderPane borderPane_root;

    @FXML
    private ScrollPane scrollPane_content;

    @FXML
    private Button buttonPrint;

    @FXML
    private Button buttonUp;

    private FXML_Pane_TK_Content_Controller scrollPane_TK_Content_Controller;

    public void set_fit_heightProperty(ReadOnlyDoubleProperty heightProperty) {
        if (heightProperty != null) {
            heightProperty.addListener((observable, oldValue, newValue) -> {
                this.borderPane_root.setMaxHeight((double) newValue);
                this.borderPane_root.setPrefHeight((double) newValue);
                this.borderPane_root.setMinHeight((double) newValue);
            });
        }
    }

    public void set_fit_widthProperty(ReadOnlyDoubleProperty widthProperty) {
        if (widthProperty != null) {
            widthProperty.addListener((observable, oldValue, newValue) -> {
                this.borderPane_root.setMaxWidth((double) newValue);
                this.borderPane_root.setPrefWidth((double) newValue);
                this.borderPane_root.setMinWidth((double) newValue);
            });
        }
    }

    public void initialize_fit_windows() {
        //Todo
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader fxmll;

            fxmll = new FXMLLoader(this.getClass().getResource("FXML_Pane_TK_Content.fxml"));
            scrollPane_content.setContent(fxmll.load());
            scrollPane_TK_Content_Controller = fxmll.getController();

            this.buttonUp.setOnAction((event) -> {
                this.scrollPane_content.setVvalue(0);
                this.scrollPane_content.setHvalue(0);
            });

            this.buttonPrint.setOnAction((event) -> {
                VBox vBoxContent = (VBox) this.scrollPane_content.getContent();

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
        } catch (Exception e) {
            System.out.println("Error at initialize TK, e: " + e.toString());
            e.printStackTrace();
        }
    }
}
