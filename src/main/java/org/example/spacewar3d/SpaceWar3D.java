package org.example.spacewar3d;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import netty.NettyClient;
import org.example.spacewar3d.animal.TimerEngine;
import org.example.spacewar3d.group.WindowService;
import org.example.spacewar3d.group.camera.WindowCamera;
import org.example.spacewar3d.manager.ManagerNetty;

public class SpaceWar3D extends Application {
    private final ManagerNetty managerNetty = new ManagerNetty();
    private final NettyClient nettyClient = new NettyClient();
    private final WindowService windowService = new WindowService(nettyClient, managerNetty);
    private final WindowCamera windowCamera = new WindowCamera();
    private final TimerEngine timerEngine = new TimerEngine();
    @Override
    public void start(Stage primaryStage) throws Exception {
        windowService.addButtons();
        windowService.actionsButtons();
        windowService.buttonsEntered();
        windowService.buttonsExited();
        timerEngine.start_timer();
        Scene scene = new Scene(windowService, 1280, 720, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);
        scene.setCamera(windowCamera);
        primaryStage.setTitle("SpaceWar3D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}