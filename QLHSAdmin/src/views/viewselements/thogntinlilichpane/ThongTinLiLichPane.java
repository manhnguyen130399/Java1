/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.thogntinlilichpane;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Computer
 */
public final class ThongTinLiLichPane {

    private static ThongTinLiLichPane instance;

    public synchronized static ThongTinLiLichPane getInstance() {
        if (instance == null) {
            instance = new ThongTinLiLichPane();
        }
        return instance;
    }

    private ThongTinLiLichPane() {
        this.fxmll = new FXMLLoader(this.getClass().getResource("ThongTinLiLichFrom.fxml"));
    }

    private final FXMLLoader fxmll;
    private BorderPane borderpaneRoot;
    private ThongTinLiLichFormController borderpaneRootController;

    private void LoadData() throws IOException {
        borderpaneRoot = fxmll.load();
        borderpaneRootController = fxmll.getController();
    }

    public BorderPane getForm() throws IOException {
        if (borderpaneRoot == null || borderpaneRootController == null) {
            LoadData();
        }
        return this.borderpaneRoot;
    }

    public ThongTinLiLichFormController getController() throws IOException {
        if (borderpaneRoot == null || borderpaneRootController == null) {
            LoadData();
        }
        return this.borderpaneRootController;
    }
}
