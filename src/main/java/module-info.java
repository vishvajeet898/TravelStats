module com.vishwajeet.travelstats {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.jsoup;
    requires org.apache.commons.lang3;


    opens com.vishwajeet.travelstats to javafx.fxml;
    exports com.vishwajeet.travelstats;
}