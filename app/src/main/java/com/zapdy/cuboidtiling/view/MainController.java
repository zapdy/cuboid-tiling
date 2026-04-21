package com.zapdy.cuboidtiling.view;

import java.util.ArrayList;
import java.util.Map;

import com.zapdy.cuboidtiling.model.Cube;
import com.zapdy.cuboidtiling.model.CuboidTilingSolver;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {
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
            task.setOnSucceeded(e -> updatePartitionListView());
            
            partitionListView.getItems().clear(); 
            new Thread(task).start();
            this.solver.solve(width, height, depth);
        });
    }
    private void updatePartitionListView() {
        for (Map.Entry<String, ArrayList<Cube>> entry : solver.getPartitions().entrySet()) {
            String key = entry.getKey();
            // ArrayList<Cube> cubes = entry.getValue();
            partitionListView.getItems().add(key);
        } 
    }
}

