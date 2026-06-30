module org.example.spacewar3d {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens org.example.spacewar3d to javafx.fxml;
    exports org.example.spacewar3d;
}