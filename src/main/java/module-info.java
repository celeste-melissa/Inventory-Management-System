module com.ims.software {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.ims.software to javafx.fxml;
    exports com.ims.software;
    exports com.ims.software.Controller;
    exports com.ims.software.Model;

    opens com.ims.software.Controller;
    opens com.ims.software.Model;

}