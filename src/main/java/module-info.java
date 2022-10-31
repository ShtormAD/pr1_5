module com.example.pr1_5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pr1_5 to javafx.fxml;
    exports com.example.pr1_5;
}