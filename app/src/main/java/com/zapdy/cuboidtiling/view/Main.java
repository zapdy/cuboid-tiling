package com.zapdy.cuboidtiling.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /*
    public static void main(String[] args) {
        CuboidTilingSolver solver = new CuboidTilingSolver(2, 2, 2);
        solver.solve();
        Map<String, ArrayList<Cube>> partitions = solver.getPartitions();
        IO.println("all possible sequences of cube placements: " + solver.counter);
        IO.println("partitions of possible sequences of cube placements: " + partitions.size());

        for (Map.Entry<String, ArrayList<Cube>> entry : partitions.entrySet()) {
            String key = entry.getKey();
            ArrayList<Cube> cubes = entry.getValue();
            IO.println(key + cubes.size());
        } 
    }
    */
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Cuboid Tiling Solver");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();}

    public static void main(String[] args) {
        launch(args);
    }
}
