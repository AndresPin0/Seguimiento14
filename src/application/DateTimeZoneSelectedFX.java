
package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class DateTimeZoneSelectedFX implements Initializable {

    private Boolean stop = false;
    @FXML
    private Button back;

    @FXML
    private Label zoneHour;

    @FXML
    private Label selectedZone;

    @FXML
    private Label selectedDate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actualTime();
        actualDate();
        setSelectedDateZone();
    }

    public void setSelectedDateZone(){


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

    @FXML
    private void setBack(ActionEvent event){
        this.selectedZone = null;
        Stage stage = (Stage) this.selectedZone.getScene().getWindow();
        stage.close();
    }
}




