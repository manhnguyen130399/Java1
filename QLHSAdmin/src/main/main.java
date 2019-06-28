/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Computer
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ///Test main window///
        Parent root = FXMLLoader.load(getClass().getResource("/views/viewscontroller/MainWindow.fxml"));

        ///Test login form///
        //Parent root = FXMLLoader.load(getClass().getResource("/views/menutab/tabchunhiems/ChuNhiemsForm.fxml"));

        ///Test Loading screen///
//        FXMLLoader fxmll = new FXMLLoader(getClass().getResource("/views/loadingscreen/LoadingScreen.fxml"));
//        Parent root = fxmll.load();
//        ((LoadingScreenController) fxmll.getController()).setAccountsLogined(new Accounts("GV00000000", "GV00000000", "GV00000000", "user", "GV00000000"));
//        ((LoadingScreenController) fxmll.getController()).setHocKi_NamHoc(new HocKi_NamHoc(2019, 2));
//        ((LoadingScreenController) fxmll.getController()).RunProgress();

        ///Test MainMenu///
//        Parent root = FXMLLoader.load(getClass().getResource("/views/mainmenu/MainMenuForm.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setTitle("Quản lí học sinh");
        primaryStage.getIcons()
                .add(new Image(this.getClass().getResourceAsStream("/views/viewsicons/QLHSLogo.png")));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
