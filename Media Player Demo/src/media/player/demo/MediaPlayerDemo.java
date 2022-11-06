/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media.player.demo;

import java.io.File;
import java.time.Duration;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Abel
 */
public class MediaPlayerDemo extends Application {
    private File file = new File("D:\\mediatest\\media.mp4");
    private final String MEDIA_URL = file.toURI().toString();
    @Override
    public void start(Stage primaryStage) {
        Media media= new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        
        //Add play button
        Button playButton = new Button(">");
        //register event handler
        playButton.setOnAction(e->{
            if(playButton.getText().equals(">")){
                mediaPlayer.play();
                playButton.setText("||");
            }
            else{
                mediaPlayer.pause();
                playButton.setText(">");
            }
        });
        
        //Add rewind button
        Button rewindButton = new Button("<<");
        //register event handler
        rewindButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mediaPlayer.seek(javafx.util.Duration.ZERO);
            }
        });
        
        //add volume slider
        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));
        
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playButton,rewindButton, new Label("Volume: "), slVolume);
        
        BorderPane root = new BorderPane();
        root.setCenter(mediaView);
        root.setBottom(hBox);
        
        Scene scene = new Scene(root, 800, 800);
        
        primaryStage.setTitle("Media Player Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
