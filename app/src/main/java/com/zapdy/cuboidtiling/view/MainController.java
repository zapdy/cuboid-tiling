package com.zapdy.cuboidtiling.view;

import java.util.ArrayList;
import java.util.Map;

import com.zapdy.cuboidtiling.model.Cuboid;
import com.zapdy.cuboidtiling.model.CuboidTilingSolver;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
    private StackPane rootPane;
    @FXML
    private ListView<String> partitionListView;
    @FXML
    private Button runButton;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField depthTextField;

    @FXML
    private Label statusLabel;

    private CuboidTilingSolver solver = new CuboidTilingSolver();

    @FXML
    private void initialize() {
        runButton.setOnAction(event -> {
            int width = Integer.valueOf(widthTextField.getText());
            int height = Integer.valueOf(heightTextField.getText());
            int depth = Integer.valueOf(depthTextField.getText());
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() {
                    solver.solve(width, height, depth);
                    return null;
                }
            };           
            task.setOnSucceeded(e -> this.updatePartitionListView());
            
            this.partitionListView.getItems().clear(); 
            this.statusLabel.setText("Solving...");
            new Thread(task).start();
        });

        partitionListView.setOnMouseClicked(event -> {
            String selected = partitionListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                return;
            }
            partitionListView.getSelectionModel().clearSelection();
            openCuboidView(selected);
        });
    }

    private void updatePartitionListView() {
        statusLabel.setText("Count: " + solver.getPartitions().size());
        for (Map.Entry<String, ArrayList<Cuboid>> entry : solver.getPartitions().entrySet()) {
            String key = entry.getKey();
            partitionListView.getItems().add(key);
        } 
    }

    private void openCuboidView(String partition) {
        try {
            ArrayList<Cuboid> cuboids = this.solver.getPartitions().get(partition);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cuboid-view.fxml"));
            Parent view = loader.load();
            Parent mainView = (Parent) rootPane.getChildren().get(0);
            CuboidViewController cuboidViewController = loader.getController();
            cuboidViewController.setCuboids(cuboids);

            int width = Integer.valueOf(widthTextField.getText());
            int height = Integer.valueOf(heightTextField.getText());
            int depth = Integer.valueOf(depthTextField.getText());

            cuboidViewController.setContainerSize(width, height, depth);
            cuboidViewController.setPartitionString(partition);
            cuboidViewController.showCuboid();
            cuboidViewController.setRootPane(rootPane);
            cuboidViewController.setMainView(mainView);
            this.rootPane.getChildren().setAll(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

