module org.example.spacewar3d {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires io.netty.transport;
    requires io.netty.codec;
    requires io.netty.buffer;
    requires org.slf4j;
    requires io.netty.common;

    opens org.example.spacewar3d to javafx.fxml;
    exports org.example.spacewar3d;
    exports org.example.spacewar3d.group;
    opens org.example.spacewar3d.group to javafx.fxml;
}