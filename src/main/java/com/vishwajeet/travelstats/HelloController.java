package com.vishwajeet.travelstats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class HelloController {
    @FXML
    private Button checkBtn;
    @FXML
    private TextField flightNumberInput;
    @FXML
    private Label sourceLabel,destinationLabel,ArivalDate,departureDate,departureTime,arivalTime;
    @FXML
    private Label terminalLabel, terminalLabel2,gateLabel,gateLabel2,statusLabel,durationLabel;

    @FXML
    private HBox box1,box2,box3,box4,box5,box6,box7;



    @FXML
    void CheckStatus() throws IOException, ParseException {

        box1.setVisible(false);
        box2.setVisible(false);
        box3.setVisible(false);
        box4.setVisible(false);
        box5.setVisible(false);
        box6.setVisible(false);
        box7.setVisible(false);

        Calendar cal = Calendar.getInstance();
        String dateStr = new SimpleDateFormat("E, dd MMM Y").format(cal.getTime());
        String yr = new SimpleDateFormat("y").format(cal.getTime());
        String mo = new SimpleDateFormat("M").format(cal.getTime());
        String da = new SimpleDateFormat("d").format(cal.getTime());
        String urlPart = "year="+yr+"&month="+mo+"&date="+da;

        //flightNumberInput.setText("6E6354");

        String number = flightNumberInput.getText();
        String carrier = number.substring(0,2);
        String num = number.substring(2);

        org.jsoup.nodes.Document doc = Jsoup.connect("https://www.flightstats.com/v2/flight-tracker/"+carrier+"/"+num+"?"+urlPart).get();

       // Elements divs = doc.select("div.text-helper__TextHelper-sc-8bko4a-0");

       Elements divs = doc.select("div.route-with-plane__AirportCodeLabel-sc-154xj1h-2.VrFKg.text-helper__TextHelper-sc-8bko4a-0.egPzce");
        sourceLabel.setText(divs.get(0).text());
        destinationLabel.setText(divs.get(1).text());

        divs = doc.select("div.text-helper__TextHelper-sc-8bko4a-0.efwouT");
        departureDate.setText(divs.get(0).text()+" "+dateStr);
        ArivalDate.setText(divs.get(1).text()+" "+dateStr);

        divs = doc.select("div.text-helper__TextHelper-sc-8bko4a-0.kbHzdx");
        if (divs.get(1).text().equals("--") == false){
            departureTime.setText(divs.get(1).text());
        }
        else {
            departureTime.setText(divs.get(0).text());
        }

        if (divs.get(5).text().equals("--") == false){
            arivalTime.setText(divs.get(5).text());
        }
        else {
            arivalTime.setText(divs.get(4).text());
        }

        //Duration
        String time1 = divs.get(0).text().substring(0,5);
        String time2 = divs.get(4).text().substring(0,5);

        System.out.println("Time1= "+time1+"\n"+"time3= "+time2);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();

        if (difference>0)
        durationLabel.setText(org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(difference, "HH:mm"));
        else
            durationLabel.setText("-");




        divs = doc.select("div.ticket__TGBValue-sc-1rrbl5o-16.hUgYLc.text-helper__TextHelper-sc-8bko4a-0.kbHzdx");

        if (divs.get(0).text().equals("N/A")){
            terminalLabel.setText("-");
        }
        else {
            terminalLabel.setText(divs.get(0).text());
        }

        if (divs.get(2).text().equals("N/A")){
            terminalLabel2.setText("-");
        }
        else {
            terminalLabel2.setText(divs.get(2).text());
        }


        if (divs.get(1).text().equals("N/A")){
            gateLabel.setText("-");
        }
        else {
            gateLabel.setText(divs.get(1).text());
        }

        if (divs.get(3).text().equals("N/A")){
            gateLabel2.setText("-");
        }
        else {
            gateLabel2.setText(divs.get(3).text());
        }

        divs = doc.select("div.text-helper__TextHelper-sc-8bko4a-0");
        statusLabel.setText(divs.get(6).text());




        box1.setVisible(true);
        box2.setVisible(true);
        box3.setVisible(true);
        box4.setVisible(true);
        box5.setVisible(true);
        box6.setVisible(true);
        box7.setVisible(true);


        int i = 1;
        for(Element elem : divs){
            System.out.println(i+ "  ----------------------------------------------------------------------------");
            System.out.println(elem.text()); //get all elements inside div
            i++;
        }
    }

}