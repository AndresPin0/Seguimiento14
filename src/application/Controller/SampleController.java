package application.Controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

public class SampleController implements Initializable {
    private DateTimeZoneSelectedFX sl;
    @FXML
    private Label tmp;

    @FXML
    private Label hour_minutes;

    @FXML
    private Label Date;

    @FXML
    private Button initStopwatch;

    @FXML
    private ListView<String> listZone;
    private ArrayList<String> zones = new ArrayList<String>();
    private String selectedZone;
    private boolean stop = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actualTime();
        actualDate();
        setListZone();
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
                   Date.setText(currentDate);
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
                   hour_minutes.setText(currentTime);
               });
           }
        });


        thread.start();
    }

    @FXML
    private void setListZone(){
        Thread thread = new Thread(()->{
            String[] id = TimeZone.getAvailableIDs();
            zones.addAll(Arrays.asList(id));
            listZone.getItems().addAll(zones);
        });
        thread.start();
    }

    @FXML
    private void seeSelectedHour(MouseEvent event) throws IOException {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/DateTimeZoneSelected.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void selectedZone(MouseEvent event){
        try{
            System.out.println(event.getPickResult().getIntersectedNode().accessibleTextProperty());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/DateTimeZoneSelected.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }

    }

    @FXML
    private void initializeSW(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Stopwatch.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
