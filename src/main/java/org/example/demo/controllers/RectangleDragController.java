package org.example.demo.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RectangleDragController {

    @FXML
    private SplitPane container;

    public void initialize() {
        Platform.runLater(() -> {
            if (container != null && container.getScene() != null) {
                Scene scene = container.getScene();
                Stage stage = (Stage) scene.getWindow();
                if (stage != null) {
                    stage.widthProperty().addListener((observable, oldValue, newValue) -> {
                        container.setPrefWidth(newValue.doubleValue());
                    });

                    stage.heightProperty().addListener((observable, oldValue, newValue) -> {
                        container.setPrefHeight(newValue.doubleValue());
                    });
                }
            }
        });
    }

    @FXML
    private Group groupOfRectangleAndText;
    @FXML
    private TextArea editControl1;
    @FXML
    private TextArea editControl2;

    private double initialX;
    private double initialY;
    private double newX;
    private double newY;


    public void setOnMousePressed(MouseEvent mouseEvent) {
        initialX = mouseEvent.getSceneX() - groupOfRectangleAndText.getLayoutX();
        initialY = mouseEvent.getSceneY() - groupOfRectangleAndText.getLayoutY();
    }

    public void setOnMouseDragged(MouseEvent mouseEvent) {
        newX = mouseEvent.getSceneX() - initialX;
        newY = mouseEvent.getSceneY() - initialY;
        groupOfRectangleAndText.setLayoutX(newX);
        groupOfRectangleAndText.setLayoutY(newY);

        editControl1.setText(String.valueOf(newX));
        editControl2.setText(String.valueOf(newY));
    }

    public void changeTheRectangleX(KeyEvent inputMethodEvent) {
        try {
            newX = Double.parseDouble(editControl1.getText());
            groupOfRectangleAndText.setLayoutX(newX);
        } catch (NumberFormatException exception) {
            editControl1.setText(String.valueOf(groupOfRectangleAndText.getLayoutX()));
        }
    }

    public void changeTheRectangleY(KeyEvent inputMethodEvent) {
        try {
            newY = Double.parseDouble(editControl2.getText());
            groupOfRectangleAndText.setLayoutX(newY);
        } catch (NumberFormatException exception) {
            editControl2.setText(String.valueOf(groupOfRectangleAndText.getLayoutY()));
        }
    }
}