//NOTE: The base data for the easy button image and the Genie moon is generated with Open AI's DALL-E and modified using GIMP and Photoroom
//Note: The settings icon on the control panel is a symbolic icon created by GNOME.
//Author: Grant Schilb
package org.example.theeasybutton;
//Imports here
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;

import java.io.File;
import java.io.IOException;


public class HelloApplication extends Application {
    //Hit counter
    Text hit_counter = new Text("Hit Counter: " + 0);

    public int hits = 0;

    //Bazinga image
    Image bazinga_image = new Image("bazinga button.png");
    Image bazinga_blue = new Image("bazinga blue.png");
    Image bazinga_green = new Image("bazinga green.png");
    Image current_bazinga = new Image("bazinga button.png");

    //Easy button Images
    public Image easy_button = new Image("easy (2).png");
    public Image easy_button_blue = new Image("blue.png");
    public Image easy_button_green = new Image("green.png");
    public Image current_easy = new Image("easy (2).png");

    //Skibidi button Images
    Image skibidiRed = new Image("Skibidi Red.png");
    Image skibidiBlue = new Image("Skibidi Blue.png");
    Image skibidiGreen = new Image("Skibidi Green.png");
    Image current_skibidi = new Image("Skibidi Red.png");

    //Genie button Images
    Image genieRed = new Image("genie_red.png");
    Image genieBlue = new Image("genie_blue.png");
    Image genieGreen = new Image("genie_green.png");
    Image madeYouLook = new Image("made_you_look.png");
    Image currentGenie = new Image("genie_red.png");

    //Secret Image
    Image secret = new Image("question block.png");

    //Imageview for easy button visible
    public ImageView easy_button_visible = new ImageView(easy_button);

    //Files for audio and video
    public File file = new File("assets/easy.mp3");
    public File bazingaMP3 = new File("assets/bazinga.mp4");
    public File skibidiMP3 = new File("assets/skibidi short.m4a");
    public File genieMP3 = new File("assets/genie.m4a");
    public File ResetMP3 = new File("assets/mario_death.mp3");
    public File removeMP3 = new File("assets/oof.mp3");
    public File openControl = new File("assets/error.mp3");
    public File tadaWAV = new File("assets/TADA.WAV");
    public File secretMP3 = new File("assets/secret_mode.m4a");
    public File GoodMP4 = new File("assets/good.mp4");
    public MediaPlayer bazinga_effect = new MediaPlayer(new Media (bazingaMP3.toURI().toString()));
    public MediaPlayer easy_effect = new MediaPlayer(new Media (file.toURI().toString()));
    public MediaPlayer skibidi_effect = new MediaPlayer(new Media (skibidiMP3.toURI().toString()));
    public MediaPlayer genie_effect = new MediaPlayer(new Media (genieMP3.toURI().toString()));
    public MediaPlayer reset_effect = new MediaPlayer(new Media(ResetMP3.toURI().toString()));
    public MediaPlayer remove_effect = new MediaPlayer(new Media(removeMP3.toURI().toString()));
    public MediaPlayer control_effect = new MediaPlayer(new Media(openControl.toURI().toString()));
    public MediaPlayer tada_effect = new MediaPlayer(new Media(tadaWAV.toURI().toString()));
    public MediaPlayer secret_effect = new MediaPlayer(new Media(secretMP3.toURI().toString()));
    public MediaPlayer good_effect = new MediaPlayer(new Media(GoodMP4.toURI().toString()));

    //Sets the Media player with the "that was easy" sound to easyPlayer
    public MediaPlayer easyPlayer = easy_effect;


    public MediaView video = new MediaView(bazinga_effect);
    public void start(Stage stage) throws IOException {


        //MediaView and Window for Bazinga Video
        BorderPane video_pane_thing = new BorderPane();
        video_pane_thing.setCenter(video);
        Scene bazinga_video = new Scene(video_pane_thing, 640,360);
        Stage bazinga_video_stage = new Stage();
        bazinga_video_stage.setTitle("Bazinga *laugh track*");
        bazinga_video_stage.setScene(bazinga_video);
        bazinga_video_stage.setResizable(false);
        bazinga_video_stage.setAlwaysOnTop(true);
        video.setFitHeight(360);
        video.setFitWidth(640);

        //RickRoll stage and mediaview
        Rectangle2D display = Screen.getPrimary().getBounds();
        BorderPane good_pane_thing = new BorderPane();
        MediaView good_view = new MediaView(good_effect);
        good_pane_thing.setCenter(good_view);
        Scene good_video = new Scene(good_pane_thing, display.getWidth(), display.getHeight());
        Stage good_video_stage = new Stage();
        good_video_stage.setScene(good_video);
        good_video_stage.setAlwaysOnTop(true);
        good_video_stage.setResizable(false);
        good_video_stage.setFullScreen(true);
        good_view.setFitWidth(display.getWidth());
        good_view.setFitHeight(display.getHeight());
        good_video_stage.setFullScreenExitHint("");

        //set dimensions of the easy button
        easy_button_visible.setFitHeight(350);
        easy_button_visible.setFitWidth(350);


        //Create the border pane for the main Window
        BorderPane pane = new BorderPane();
        //place the easy button into the center of the pane
        pane.setCenter(easy_button_visible);
        //Make hit counter visible
        pane.setTop(hit_counter);
        //pane stuff
        pane.setOpacity(1);
        //Main Window Things
        Scene scene = new Scene(pane, 380, 540);
        stage.setTitle("The Easy Button");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(easy_button);
        stage.setOpacity(1);

        //Event for playing the sound effect for all easy button variants
        easy_button_visible.setOnMouseClicked(event ->{
            //Plays the sound that is currently in memory.
            easyPlayer.play();
            easyPlayer.setOnEndOfMedia(easyPlayer::stop);
            //Debug that shows the duration of the currently playing media on click
            System.out.println("log: " + easyPlayer.getCurrentTime());
            //Does not increase the hit count except at the beginning of the sound effect
            if(easyPlayer.getCurrentTime().equals(new Duration(0.0)))
                hits++;
            //Renders the amount of times the easy button has been hit
            hit_counter.setText("Hit Counter: " + hits);
            //Checks memory location of bazinga sound effect. If that effect is loaded into memory, then the bazinga stage shows if button is clicked
            if (easyPlayer.equals(bazinga_effect)){
                bazinga_video_stage.show();


                //Event and Timeline are used to close the Bazinga video the moment that the video reaches its end.
                EventHandler<ActionEvent> bazinga_handler = e->{
                    bazinga_video_stage.close();
                };

                Timeline bazinga_stage_thing = new Timeline(new KeyFrame(bazinga_effect.getTotalDuration(),bazinga_handler));
                bazinga_stage_thing.setCycleCount(1);
                bazinga_stage_thing.play();


            }

            //If the rickroll is loaded into memory,then show its corresponding video stage.
            //EventHandler and Timeline are used to close out rickroll once it has reached its end.
            if(easyPlayer.equals(good_effect)){
                good_video_stage.show();

                EventHandler<ActionEvent> good_handler = e->{
                    good_video_stage.close();
                };

                Timeline bazinga_stage_thing = new Timeline(new KeyFrame(good_effect.getTotalDuration(),good_handler));
                bazinga_stage_thing.setCycleCount(1);
                bazinga_stage_thing.play();

            }


            //Animation code for Genie Mode that only run if the genie sound effect is assigned to easy player.
            if(easyPlayer.equals(genie_effect)){
                easy_button_visible.setImage(madeYouLook);
                EventHandler<ActionEvent> eventHandler = e ->{
                    if(easy_button_visible.getImage().equals(madeYouLook)){
                        easy_button_visible.setImage(currentGenie);
                    }
                };
                Timeline animation = new Timeline(new KeyFrame(Duration.millis(1150), eventHandler));
                animation.setCycleCount(1);
                animation.play();
            }
        });
        pane.setBottom(getBottomHbox());
    }

    public static void main(String[] args) {
        launch();
    }
    private HBox getBottomHbox(){

        //Label for 'Make it easy!' and event
        Text easy_text = new Text("Make it easy!");
        easy_text.setFont(Font.font("Comic Sans MS", 20));
        easy_text.setFill(Color.BLUE);
        easy_text.setOnMouseClicked(e->{
            easy_button_visible.setImage(secret);
            secret_effect.play();
            secret_effect.setOnEndOfMedia(secret_effect::stop);
            easyPlayer = good_effect;
        });

        //The Easy Panel Window and Gridpane
        GridPane controlPane = new GridPane();
        Scene controlPanel = new Scene(controlPane, 310, 300);
        Stage controlStage = new Stage();
        controlStage.setTitle("Nothing easier than this...");
        controlStage.setScene(controlPanel);
        controlStage.setResizable(false);
        controlStage.setAlwaysOnTop(true);
        controlStage.getIcons().add(new Image("settings.png"));

        //Button to launch easy panel
        Button easy_control_panel = new Button("The Easy Panel");

        //Event to launch easy panel
        easy_control_panel.setOnAction(e ->{
            controlStage.show();
            control_effect.play();
            control_effect.setOnEndOfMedia(control_effect::stop);
        });

        //Top text of Easy Panel
        Text welcome = new Text("Welcome!!!\nEasiness awaits!\n");
        welcome.setFont(Font.font("Comic Sans MS", 18));

        //Buttons that will be placed inside of The Easy Panel
        //Bazinga Mode and action
        Button bazinga = new Button("Bazinga mode");
        bazinga.setOnAction(event -> {
            if(easy_button_visible.getImage().equals(bazinga_image)){
                System.out.println("Error: Bazinga button already enabled");
            }
            else{System.out.println("log: Bazinga button enabled");}
            easy_button_visible.setImage(bazinga_image);
            easyPlayer = bazinga_effect;
        });

        //Easy mode and action
        Button easy = new Button("Easy mode");
        easy.setOnAction(event -> {
            easy_button_visible.setImage(easy_button);
            easyPlayer = easy_effect;
        });

        //Reset Button
        Button reset = new Button("Reset Counter");
        reset.setOnMouseClicked(e ->{
            if(hits == 0){
                System.out.println("Error: Cannot reset hits at 0");
            }
            else{
                System.out.println("log: The hits have been reset");
            }
            hits = 0;
            hit_counter.setText("Hit Counter: " + hits);
            reset_effect.play();
            reset_effect.setOnEndOfMedia(reset_effect::stop);
        });

        //Remove A Hit button and action
        Button removeAHit = new Button("Remove Hit");
        removeAHit.setOnMouseClicked(e ->{
            if(hits > 0){hits--;}
            hit_counter.setText("Hit Counter: " + hits);
            remove_effect.play();
            remove_effect.setOnEndOfMedia(remove_effect::stop);
        });
        //Button for Skibidi Mode and action
        Button skibidi = new Button("Skibidi mode");
        skibidi.setOnAction(event -> {
            easy_button_visible.setImage(skibidiRed);
            easyPlayer = skibidi_effect;
        });

        //Button for Genie Mode and action
        Button genie = new Button("Genie mode");
        genie.setOnAction(event -> {
            easy_button_visible.setImage(genieRed);
            easyPlayer = genie_effect;
            currentGenie = genieRed;
        });

        //Button, Action, and Window for the About this Project window
        Button aboutProject = new Button("About");
        //About this Project
        BorderPane aboutPane = new BorderPane();
        Scene aboutScene =  new Scene(aboutPane);
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About this button!!!");
        aboutStage.setScene(aboutScene);
        aboutStage.setResizable(false);

        Text about = new Text("About this project i guess...");
        Text aboutBody = (new Text("As a kid, I always enjoyed hitting the easy button.\nMany years later, I became sad and depressed\ndue to the lack of an easy button in my life.\nThus, the goal of this project is simple." +
                "\nBring smiles to the the faces of many,\nthrough the power of the easy button!!!\n\nAlso, I needed to make a GUI Project for Computer Science\nand this was the only thing I could think of.\n\nCheck out my other work!\n\nGithub: https://github.com/CoolGeek167" +
                "\nYouTube: https://www.youtube.com/@grantsgamesandtech312\n\nCopyright 2025...bazinga!"));
        Text copyright = new Text("Copyright 2025...bazinga!");
        GridPane aboutGrid = new GridPane();
        aboutGrid.setPadding(new Insets(4, 4, 13.5, 4));
        aboutGrid.setHalignment(copyright, HPos.RIGHT);

        aboutBody.setFont(Font.font("Comic Sans MS", 14));
        about.setFont(Font.font("Comic Sans MS", 20));
        aboutPane.setTop(about);
        aboutPane.setCenter(aboutBody);
        aboutPane.setBottom(aboutGrid);

        aboutProject.setOnAction(e -> {
            aboutStage.show();
            tada_effect.play();
            tada_effect.setOnEndOfMedia(tada_effect::stop);
        });

        //Red Radio Button and actions
        RadioButton red = new RadioButton("Red");
        red.setOnAction(event -> {
            if(easyPlayer.equals(easy_effect)){
                easy_button_visible.setImage(easy_button);
            }
            else if(easyPlayer.equals(bazinga_effect)){
                easy_button_visible.setImage(bazinga_image);
            }
            else if(easyPlayer.equals(skibidi_effect)){
                easy_button_visible.setImage(skibidiRed);
            }
            else if(easyPlayer.equals(genie_effect)){
                easy_button_visible.setImage(genieRed);
                currentGenie = genieRed;
            }
        });

        RadioButton green = new RadioButton("Green");
        green.setOnAction(event -> {
            if(easyPlayer.equals(easy_effect)){
                easy_button_visible.setImage(easy_button_green);
            }
            else if(easyPlayer.equals(bazinga_effect)){
                easy_button_visible.setImage(bazinga_green);
            }
            else if(easyPlayer.equals(skibidi_effect)){
                easy_button_visible.setImage(skibidiGreen);
            }
            else if(easyPlayer.equals(genie_effect)){
                easy_button_visible.setImage(genieGreen);
                currentGenie = genieGreen;
            }
        });

        //Radio Button Blue and actions
        RadioButton blue = new RadioButton("Blue");
        blue.setOnAction(event -> {
            if(easyPlayer.equals(easy_effect)) {
                easy_button_visible.setImage(easy_button_blue);
            }
            else if(easyPlayer.equals(bazinga_effect)){
                easy_button_visible.setImage(bazinga_blue);
            }
            else if(easyPlayer.equals(skibidi_effect)){
                easy_button_visible.setImage(skibidiBlue);
            }
            else if(easyPlayer.equals(genie_effect)){
                easy_button_visible.setImage(genieBlue);
                currentGenie = genieBlue;
            }
        });

        //Code to keep radio buttons together
        ToggleGroup radioButtonGroup = new ToggleGroup();
        red.setToggleGroup(radioButtonGroup);
        green.setToggleGroup(radioButtonGroup);
        blue.setToggleGroup(radioButtonGroup);

        //Code for Easy Control Panel UI Layout
        //Padding and Alignment
        controlPane.setPadding(new Insets(4, 4, 13.5, 4));
        controlPane.setHgap(2);
        controlPane.setVgap(3);
        controlPane.setAlignment(Pos.TOP_CENTER);
        //This code adds all the nodes onto places in the gridpane
        controlPane.add(welcome, 0, 0);
        controlPane.add(bazinga, 0, 3 );
        controlPane.add(easy, 1, 3 );
        controlPane.add(skibidi, 0,5);
        controlPane.add(genie, 1,5);
        controlPane.add(red, 0, 6);
        controlPane.add(blue, 0, 7);
        controlPane.add(green, 0, 8);
        controlPane.add(reset, 1, 6);
        controlPane.add(removeAHit, 1, 7);
        controlPane.add(aboutProject, 1, 8);

        //Horizontal Box for the title and button
        HBox bottomHBox = new HBox(2);
        bottomHBox.setPadding(new Insets(15, 15, 15, 15));
        bottomHBox.setSpacing(100);
        bottomHBox.getChildren().add(easy_text);
        bottomHBox.getChildren().add(easy_control_panel);


        hit_counter.setFont(Font.font("Comic Sans MS", 20));
        return bottomHBox;
    }
}