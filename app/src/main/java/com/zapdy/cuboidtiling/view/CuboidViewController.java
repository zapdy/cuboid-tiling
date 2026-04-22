package com.zapdy.cuboidtiling.view;

import java.util.ArrayList;

import com.zapdy.cuboidtiling.model.Cuboid;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class CuboidViewController {
    private ArrayList<Cuboid> cuboids;
    private StackPane rootPane;
    Parent mainView;
    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        IO.println("Cuboid View Controller");

        backButton.setOnAction(event -> {
            this.rootPane.getChildren().setAll(mainView);
        });
    }

    public void setMainView(Parent view) {
        this.mainView = view; 
    }

	public void setCuboids(ArrayList<Cuboid> cuboids) {
        this.cuboids = cuboids;
	}

    public void showCuboid() {
        IO.println(cuboids.get(0).height());
    }

    public void setRootPane(StackPane rootPane) {
        this.rootPane = rootPane;
    }
}

