package com.vishwajeet.travelstats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 852, 502);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
       //
 /*       org.jsoup.nodes.Document doc = Jsoup.connect("https://www.flightstats.com/v2/flight-tracker/6E/6354?year=2022&month=6&date=27").get();

        Elements divs = doc.select("div.route-with-plane__AirportCodeLabel-sc-154xj1h-2");
        int i = 1;
        for(Element elem : divs){
            System.out.println(i+ "  ----------------------------------------------------------------------------");
            System.out.println(elem.text()); //get all elements inside div
            i++;
        }*/

        launch();


    }
}