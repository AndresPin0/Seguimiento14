package application.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class StopWatchFX implements Initializable {

    private final ArrayList<String> laps = new ArrayList<String>();

    @FXML
    private ListView<String> listLaps;

    @FXML
    private Label stopwatch;

    @FXML
    private Button lapButton;

    @FXML
    private Button startButton;

    @FXML
    private Button ResetButton;

    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private boolean initThread = true;
    private boolean running = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void initializeStopWatch(ActionEvent e){
        startButton.setText("Stop");
        if (!running){
            initThread = true;
            running = true;
            startButtonThread();
        }else if (startButton.getText().equals("Stop")){ //Preguntar al profesor por qué al momento de darle parar, se demora un segundo...
            running = false;
            initThread = false;
            System.out.println("Stop thread");
            System.out.println(running);
            startButton.setText("Start");
        }
    }

    private void startButtonThread() {
        if (initThread){
            System.out.println("Start thread");
            Thread thread = new Thread(()->{
                try{
                    while (initThread){
                        Thread.sleep(1000);
                        seconds ++;
                        if (seconds>59){
                            seconds = 0;
                            minutes++;
                        }
                        if (minutes>59){
                            minutes = 0;
                            hours++;
                        }
                        System.out.println(seconds + "-" + Thread.currentThread());
                        String txt = (hours<=9?"0":"") + hours + ":" + (minutes<=9?"0":"") + minutes +":"+ (seconds<=9?"0":"") + seconds;
                        System.out.println(txt);
                        Platform.runLater(()->{
                            stopwatch.setText(txt);
                        });
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            });
            thread.start();
        }
    }

    @FXML
    public void resetStopwatch(ActionEvent e){
        //System.out.println(running);
        if (!running){
            listLaps.getItems().removeAll();
            stopwatch.setText("00:00:00");
            seconds = 0;
            minutes = 0;
            hours = 0;
        }
        ResetButton.setText("Reset");
    }

    @FXML
    private void addLap(ActionEvent e){
        Thread thread = new Thread(()->{
            String tmp = stopwatch.getText();
            listLaps.getItems().add(tmp);
            System.out.println(stopwatch.getText());
        });
        thread.start();

    }
}
