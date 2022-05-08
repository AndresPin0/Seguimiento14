
package application.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeZoneSelectedFX implements Initializable {

    private Boolean stop = false;

    @FXML
    private Label zoneHour;

    @FXML
    private Label selectedDate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actualTime();
        actualDate();
    }

    public void searchSelectedZone(String selectedZone){
        TimeZone tz = TimeZone.getTimeZone(selectedZone);
        System.out.println(tz.getID());
    }

    private void actualTime() {

        Thread thread = new Thread(()->{
            SimpleDateFormat sdt = new SimpleDateFormat("hh:mm:ss a");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String currentTime = sdt.format(new Date());
                Platform.runLater(()->{
                    zoneHour.setText(currentTime);
                });
            }
        });
        thread.start();
    }

    private void actualDate(){
        Thread thread = new Thread(()->{
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String currentDate = sdf.format(new Date());
                Platform.runLater(()->{
                    selectedDate.setText(currentDate);
                });

            }
        });
        thread.start();
    }
}




