/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.viewselements.simpledialogview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Computer
 */
public final class SimpleDialogViews {

    private SimpleDialogFormController controller;
    private Stage stage;
    private Scene scene;
    private Parent parent;

    private double x_offset;
    private double y_offset;

    public SimpleDialogViews() throws Exception {
        this.CreateSimpleDialogViews();
    }

    public SimpleDialogViews(String title, String content) throws Exception {
        this.CreateSimpleDialogViews();
        this.setTitle(title);
        this.setContent(content);
    }
    
    public void CreateSimpleDialogViews() throws Exception{
        FXMLLoader fxmll = new FXMLLoader(this.getClass().getResource("SimpleDialogForm.fxml"));

        parent = fxmll.load();

        controller = fxmll.getController();

        scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        scene.setOnMousePressed((event) -> {
            x_offset = event.getSceneX();
            y_offset = event.getSceneY();
        });
        scene.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() - x_offset);
            stage.setY(event.getScreenY() - y_offset);
        });

        stage = new Stage();
        stage.setScene(scene);

        stage.initStyle(StageStyle.TRANSPARENT);
    }

    public SimpleDialogFormController getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Parent getParent() {
        return parent;
    }

    public void setContent(String content) {
        this.controller.set_content(content);
    }

    public void setTitle(String title) {
        this.controller.set_title(title);
    }

    public void show() {
        this.stage.show();
    }

    public void showAndWait() {
        this.stage.showAndWait();
    }
    
    public ButtonType getButtonSelected(){
        return this.controller.getButtonSelected();
    }
}
