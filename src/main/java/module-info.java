module com.vishwajeet.travelstats {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.vishwajeet.travelstats to javafx.fxml;
    exports com.vishwajeet.travelstats;
}