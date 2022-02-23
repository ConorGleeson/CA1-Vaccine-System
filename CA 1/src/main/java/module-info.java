module com.example.ca1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires xstream;




    opens com.example.ca1 to javafx.fxml, xstream;
   exports com.example.ca1;
}