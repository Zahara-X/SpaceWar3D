package org.example.spacewar3d.group;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import netty.NettyClient;
import org.example.spacewar3d.enumarated.Value;
import org.example.spacewar3d.manager.ManagerNetty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowService extends Group {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String[] texts = {"CONNECT", "SETTINGS", "EXIT"};
    private final NettyClient nettyClient;
    private final ManagerNetty managerNetty;
    private Button[] buttons;
    public WindowService(NettyClient nettyClient,  ManagerNetty managerNetty) {
        this.nettyClient = nettyClient;
        this.managerNetty = managerNetty;
    }

    public void addButtons() {
        buttons = new Button[3];
        for(int i = 0; i < buttons.length; i++) {
           buttonsConfigurations(i);
        }
    }
    // Connected server
    public void actionsButtons() {
        buttons[0].setOnAction(event -> {
            Object host = Value.HOST.getHost(), port = Value.PORT.getPort();
            if(host == null || port == null) {
                logger.warn("Host or port is null");
                return;
            }
            connect(host, port);
        });
        buttons[1].setOnAction(event -> {

        });
        buttons[2].setOnAction(event -> {

        });
    }
    public void buttonsEntered() {
        for(int i = 0; i < buttons.length; i++) {
            int count = i;
            buttons[count].setOnMouseEntered(ent -> {
                buttons[count].setBackground(Background.fill(Color.web("#ffffff", 1.)));
                buttons[count].setTextFill(Color.web("#050505", 1.));
            });
        }
    }
    public void buttonsExited() {
        for(int i = 0; i < buttons.length; i++) {
           int count = i;
            buttons[count].setOnMouseExited(event -> {
                buttons[count].setBackground(Background.fill(Color.web("#141414", .0)));
                buttons[count].setTextFill(Color.web("#ffffff", 1.));
            });
        }
    }
    public void connect(Object host, Object port) {
        ChannelFuture channelFuture = this.nettyClient.bootstrap.connect((String) host, (Integer) port);
        channelFuture.addListener((ChannelFutureListener) f -> {
            if(f.isSuccess()) {
                managerNetty.setChannel(f.channel());
                logger.info("Connected to server: {}", f.channel().remoteAddress());
            } else {
                this.nettyClient.group.shutdownGracefully();
                logger.error("Disconnected from server: {}", f.channel().remoteAddress());
            }
        });
    }
    public void buttonsConfigurations(int count) {
        buttons[count] = new Button(texts[count]);
        buttons[count].setPrefWidth(250);
        buttons[count].setPrefHeight(100);
        buttons[count].setTranslateX(100);
        int value = (count * 150) + 150;
        buttons[count].setTranslateY(value);
        buttons[count].setFont(new Font("Arial", 20));
        buttons[count].setBackground(Background.fill(Color.web("#141414", .0)));
        buttons[count].setTextFill(Color.web("#ffffff", 1.));
        buttons[count].setFocusTraversable(false);
        buttons[count].setBorder(new Border(new BorderStroke(
                Color.web("#ffffff", 1.),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(1)
        )));
        this.getChildren().add(buttons[count]);
    }
}