package com.project.controllers;

import java.net.URL;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class objectController {
    @FXML
    private ImageView image = new ImageView();
    @FXML 
    private Text title = new Text();
    @FXML
    private Label date = new Label();
    @FXML
    private Rectangle color = new Rectangle();
    @FXML
    private Label processor = new Label();
    @FXML
    private Label soldUnits = new Label(); 
    @FXML 
    private Label game = new Label();
    @FXML
    private Label year = new Label();
    @FXML
    private Label type = new Label();
    @FXML
    private Label description = new Label();

    public void setContentConsole(JSONObject console){
        URL imageURL = getClass().getResource("/assets/images/"+console.getString("imatge"));
        image.setImage(new Image(imageURL.toString()));
    
        title.setText(console.getString("nom"));
        date.setText(console.getString("data"));
        color.setStyle("-fx-fill: "+console.getString("color"));
        processor.setText(console.getString("procesador"));
        soldUnits.setText(String.valueOf(console.getInt("venudes")));
    }

    public void setContentGame(JSONObject game){
        try{
            URL imageURL = getClass().getResource("/assets/images/"+game.getString("imatge"));
            image.setImage(new Image(imageURL.toString()));
            title.setText(game.getString("nom"));
            year.setText(String.valueOf(game.getInt("any")));
            type.setText(game.getString("tipus"));
            description.setText(game.getString("descripcio"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setContentCharacter(JSONObject info){
        URL imageURL = getClass().getResource("/assets/images/"+info.getString("imatge"));
        this.image.setImage(new Image(imageURL.toString()));

        this.title.setText(info.getString("nom"));

        this.color.setStyle("-fx-fill: "+info.getString("color"));;

        this.game.setText(info.getString("nom_del_videojoc"));
    }
}
