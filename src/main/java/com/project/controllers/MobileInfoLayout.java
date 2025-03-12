package com.project.controllers;

import java.io.IOException;
import java.net.URL;

import org.json.JSONObject;

import com.project.UtilsViews;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MobileInfoLayout {
    @FXML
    private VBox infoLayout = new VBox();

    @FXML
    private ImageView image = new ImageView();
    @FXML 
    private Text title = new Text();
    @FXML
    private Label titleGame = new Label();
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

    @FXML
    private void returnAction() {
        UtilsViews.setViewAnimating("Mobile");
    } 

    public void setContentConsole(JSONObject console){
        URL imageURL = getClass().getResource("/assets/images/"+console.getString("imatge"));
        image.setImage(new Image(imageURL.toString()));
    
        title.setText(console.getString("nom"));
        date.setText(console.getString("data"));
        color.setFill(Color.web(console.getString("color")));
        processor.setText(console.getString("procesador"));
        soldUnits.setText(String.valueOf(console.getInt("venudes")));
    }

    public void setContentGame(JSONObject game){
        try{
            URL imageURL = getClass().getResource("/assets/images/"+game.getString("imatge"));
            image.setImage(new Image(imageURL.toString()));
            titleGame.setText(game.getString("nom"));
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

        color.setFill(Color.web(info.getString("color")));

        this.game.setText(info.getString("nom_del_videojoc"));
    }

    public void initInfo(ChoiceBox<String> choiceTypes, JSONObject itemInfo) {
        
        infoLayout.getChildren().clear();
        System.out.println("infoLayout visible? " + infoLayout.isVisible());

        System.out.println("infoLayout Parent antes de agregar contenido: " + infoLayout.getParent());


        try{
            if(choiceTypes.getValue().equals("Personatges")){
                System.out.println("HOLA MOB");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/charInfoLayout.fxml"));
                Parent charLayout = loader.load();
                DesktopInfoLayout charController = loader.getController();
                charController.setContentCharacter(itemInfo);
                
                System.out.println("infoLayout visible? " + infoLayout.isVisible());
                System.out.println("Antes de agregar: " + infoLayout.getChildren().size());
                infoLayout.getChildren().add(charLayout);

                infoLayout.setVisible(true);
                infoLayout.setManaged(true);
                infoLayout.requestLayout();
                
                System.out.println("infoLayout Parent: " + infoLayout.getParent());

                // infoLayout.getParent().requestLayout(); // Si el VBox está en otro contenedor, también lo refrescamos
                System.out.println("Contenido añadido: " + infoLayout.getChildren().get(0));
                System.out.println("infoLayout Parent: " + infoLayout.getParent());

                System.out.println("Después de agregar: " + infoLayout.getChildren().size());
                System.out.println("infoLayout visible? " + infoLayout.isVisible());


            }else if(choiceTypes.getValue().equals("Jocs")){

                UtilsViews.setViewAnimating("MobileInfo");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/gameInfoLayout.fxml"));
                Parent gameLayout = loader.load();
                DesktopInfoLayout gameController = loader.getController();
                gameController.setContentGame(itemInfo);
                
                System.out.println("infoLayout visible? " + infoLayout.isVisible());
                System.out.println("Antes de agregar: " + infoLayout.getChildren().size());
                infoLayout.getChildren().add(gameLayout);

                infoLayout.setVisible(true);
                infoLayout.setManaged(true);
                infoLayout.requestLayout();
                
                System.out.println("infoLayout Parent: " + infoLayout.getParent());

                // infoLayout.getParent().requestLayout(); // Si el VBox está en otro contenedor, también lo refrescamos
                System.out.println("Contenido añadido: " + infoLayout.getChildren().get(0));
                System.out.println("infoLayout Parent: " + infoLayout.getParent());

                System.out.println("Después de agregar: " + infoLayout.getChildren().size());
                System.out.println("infoLayout visible? " + infoLayout.isVisible());

            }else if(choiceTypes.getValue().equals("Consoles")){

                UtilsViews.setViewAnimating("MobileInfo");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/consoleInfoLayout.fxml"));
                Parent consoleLayout = loader.load();
                DesktopInfoLayout consoleController = loader.getController();
                consoleController.setContentConsole(itemInfo);
                
                System.out.println("infoLayout visible? " + infoLayout.isVisible());
                System.out.println("Antes de agregar: " + infoLayout.getChildren().size());
                infoLayout.getChildren().add(consoleLayout);
                
                infoLayout.setVisible(true);
                infoLayout.setManaged(true);
                infoLayout.requestLayout();
                
                System.out.println("infoLayout Parent: " + infoLayout.getParent());
                
                // infoLayout.getParent().requestLayout(); // Si el VBox está en otro contenedor, también lo refrescamos
                System.out.println("Contenido añadido: " + infoLayout.getChildren().get(0));
                System.out.println("infoLayout Parent: " + infoLayout.getParent());

                System.out.println("Después de agregar: " + infoLayout.getChildren().size());
                System.out.println("infoLayout visible? " + infoLayout.isVisible());

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
