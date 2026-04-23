package com.zapdy.cuboidtiling.view;

import java.util.ArrayList;

import com.zapdy.cuboidtiling.logic.Cuboid;

import javafx.fxml.FXML;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public class CuboidViewController {
    private ArrayList<Cuboid> cuboids;
    private double lastMouseX;
    private double lastMouseY;
    private int size = 200;
    private int inset = 10;
    private StackPane rootPane;
    private Parent mainView;
    private String partitionString;
    @FXML
    private Button backButton;
    @FXML
    private Label partitionLabel;
    @FXML
    private SubScene cuboidSubScene;
    private int containerWidth;
    private int containerHeight;
    private int containerDepth;

    @FXML
    private Label containerSizeLabel;

    @FXML
    private void initialize() {
        cuboidSubScene.setDepthTest(DepthTest.ENABLE);
        cuboidSubScene.widthProperty().bind(((Pane) cuboidSubScene.getParent()).widthProperty());
        cuboidSubScene.heightProperty().bind(((Pane) cuboidSubScene.getParent()).heightProperty());
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
        Group cuboidGroup = new Group();
        partitionLabel.setText(this.partitionString);
        containerSizeLabel.setText("Container " + this.containerWidth / size + "x" + this.containerHeight / size + "x" + this.containerDepth / size);
        PhongMaterial material = new PhongMaterial(); 
        material.setSelfIlluminationMap(null);
        material.setDiffuseColor(Color.web("#89CFF0", 0.75));
        material.setSpecularColor(null);
        for (Cuboid cuboid : cuboids) {
            int boxWidth = (cuboid.width() * this.size) - this.inset;
            int boxHeight = (cuboid.height() * this.size) - this.inset;
            int boxDepth = (cuboid.depth() * this.size) - this.inset;

            Box box = new Box(boxWidth, boxHeight, boxDepth);

            box.setTranslateX(((cuboid.x() + cuboid.width() / 2.0) * this.size) - (this.containerWidth + this.inset) / 2.0);
            box.setTranslateY(((cuboid.y() + cuboid.height() / 2.0) * this.size) - (this.containerHeight + this.inset) / 2.0); 
            box.setTranslateZ(((cuboid.z() + cuboid.depth() / 2.0) * this.size) - (this.containerDepth + this.inset) / 2.0); 
            box.setMaterial(material);
            cuboidGroup.getChildren().add(box);
        }
        cuboidGroup.translateXProperty().bind(((Pane) cuboidSubScene.getParent()).widthProperty().divide(2));
        cuboidGroup.translateYProperty().bind(((Pane) cuboidSubScene.getParent()).heightProperty().divide(2));

        this.setupCamera(cuboidGroup);
        cuboidSubScene.setRoot(cuboidGroup);
    }

    public void setRootPane(StackPane rootPane) {
        this.rootPane = rootPane;
    }

    private void setupCamera(Group cuboidGroup) {
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setTranslateZ(-1000);
        cuboidSubScene.setCamera(camera);

        cuboidSubScene.setOnScroll(event -> {
            double zoomSpeed = 2.0;
            double delta = event.getDeltaY();

            camera.setTranslateZ(camera.getTranslateZ() + delta * zoomSpeed);
        });

        cuboidGroup.setOnMousePressed(event -> {
            this.lastMouseX = event.getSceneX();
            this.lastMouseY = event.getSceneY();
        });

        cuboidGroup.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - lastMouseX;
            double deltaY = event.getSceneY() - lastMouseY;

            Rotate rotY = new Rotate(-deltaX * 1.0, Rotate.Y_AXIS);
            Rotate rotX = new Rotate(deltaY * 1.0, Rotate.X_AXIS);

            cuboidGroup.getTransforms().add(0, rotY);
            cuboidGroup.getTransforms().add(0, rotX);

            lastMouseX = event.getSceneX();
            lastMouseY = event.getSceneY();
        });
    }

    public void setContainerSize(int width, int height, int depth) {
        this.containerWidth = width * size;
        this.containerHeight = height * size;
        this.containerDepth = depth * size;
    }

    public void setPartitionString(String partition) {
        this.partitionString = partition;
    }
}

