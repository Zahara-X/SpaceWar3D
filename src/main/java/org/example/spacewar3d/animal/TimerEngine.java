package org.example.spacewar3d.animal;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimerEngine {

      public void start_timer() {
          Timeline line = new Timeline(new KeyFrame(Duration.millis(16), e -> { // 60fps

          }));
          line.setCycleCount(Timeline.INDEFINITE);
          line.play();
      }
}