/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package makarimplayer;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author aldimakarim
 */
public class FXMLMakarimPlayerController implements Initializable {
    private MediaPlayer mediaplayer;
    @FXML
    private JFXButton OpenFile;
    @FXML
    private JFXButton Play;
    @FXML
    private JFXButton Pause;
    @FXML
    private JFXButton Stop;
    @FXML
    private JFXButton Slowest;
    @FXML
    private JFXButton Slower;
    @FXML
    private JFXButton faster;
    @FXML
    private JFXButton Fastest;
    @FXML
    private JFXButton fullscreen;
    @FXML
    private MediaView mediaview;
    @FXML
    private Slider slider;
    @FXML
    private Slider slidervideo;
    /**
     * Initializes the controller class.
     */
   
    private String filepath;    
    @FXML
    private void Openvideo(ActionEvent event) {
            FileChooser filechooser = new FileChooser();
            FileChooser.ExtensionFilter filter = new  FileChooser.ExtensionFilter("Select a file (*.mp4)", "*.mp4");
            filechooser.getExtensionFilters().add(filter);
            File file = filechooser.showOpenDialog(null);
            filepath = file.toURI().toString();
            
            if(filepath != null){
                Media media = new Media(filepath);
                mediaplayer = new MediaPlayer(media);    
                mediaview.setMediaPlayer(mediaplayer);
                    DoubleProperty width = mediaview.fitWidthProperty();
                    DoubleProperty height = mediaview.fitHeightProperty();
                        
                    width.bind(Bindings.selectDouble(mediaview.sceneProperty(), "width"));
                    height.bind(Bindings.selectDouble(mediaview.sceneProperty(), "width"));
                    
                    slider.setValue(mediaplayer.getVolume() * 100);
                    slider.valueProperty().addListener(new InvalidationListener(){
                        @Override
                        public void invalidated(Observable observable) {
                            mediaplayer.setVolume(slider.getValue()/100);
                        }
                    });
                    
                    mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                            slidervideo.setValue(newValue.toSeconds());
                    }
                });
                    
                    slidervideo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mediaplayer.seek(Duration.seconds(slidervideo.getValue()));
                    }
                });
                    
                mediaplayer.play();
    }
    }

    @FXML
    private void Playvideo(ActionEvent event) {
        mediaplayer.play();
        mediaplayer.setRate(1);
    }

    @FXML
    private void Pausevideo(ActionEvent event) {
        mediaplayer.pause();
    }

    @FXML
    private void Stopvideo(ActionEvent event) {
        mediaplayer.stop();
    }

    @FXML
    private void Slowestvideo(ActionEvent event) {
        mediaplayer.setRate(0.5);
    }

    @FXML
    private void Slowervideo(ActionEvent event) {
        mediaplayer.setRate(0.75);
    }

    @FXML
    private void fastervideo(ActionEvent event) {
        mediaplayer.setRate(1.25);
    }

    @FXML
    private void Fastestvideo(ActionEvent event) {
        mediaplayer.setRate(5);
    }

    @FXML
    private void fullscreenvideo(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void about(ActionEvent event){
        Desktop desktop = Desktop.getDesktop();        
            File dirToOpen = null;
            try {        
                String path = "D:\\kelas2\\PBO\\netbean\\MakarimPlayer\\src\\gambar\\makarimplayer.pdf";        
                Runtime runtime = Runtime.getRuntime();        
                runtime.exec("explorer.exe "+path);                
            } catch (Exception E){
            }
 }

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}


