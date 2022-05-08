package application;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.text.SimpleDateFormat;

public class SampleController implements Initializable {
    private DateTimeZoneSelectedFX sl;

    @FXML
    private Label hour_minutes;

    @FXML
    private Label Date;

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
        setZone();
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

    private void setZone(){
    }

    private void getDateZone(ActionEvent event){

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
        listZone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedZone = listZone.getSelectionModel().getSelectedItem();
                System.out.println(selectedZone);
            }
        });
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DateTimeZoneSelected.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
