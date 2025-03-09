package com.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


import org.json.JSONArray;
import org.json.JSONObject;

import com.project.UtilsViews;

public class MobileController implements Initializable {
    
    @FXML
    private VBox listItems = new VBox();
    @FXML
    private VBox infoLayout = new VBox();
    @FXML
    private BorderPane rootPane = new BorderPane();
    @FXML
    private Button returnAction = new Button();
    @FXML
    private ChoiceBox<String> choiceTypes = new ChoiceBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceTypes.getItems().addAll(new String[]{"Personatges","Jocs","Consoles"});
        choiceTypes.setValue("Personatges");

        choiceTypes.setOnAction((evt)->{
            putList(choiceTypes.getValue());
        });

        returnAction.setOnAction((evt) -> {
            UtilsViews.setViewAnimating("Mobile");
        });

        putList(choiceTypes.getValue());
    }

    @FXML
    public void putList(String listToShow){
        JSONArray itemsJson;

        try{

            // Llegim el JSON en un String amb StringBuilder
            StringBuilder jsonTxt = new StringBuilder();

            Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(new File("./src/main/resources/assets/data/"+listToShow.toLowerCase()+".json"))));
            while(scan.hasNext()){
                jsonTxt.append(scan.nextLine());
            }

            // Convertim el JSON a un objecte JSONArray
            itemsJson = new JSONArray(jsonTxt.toString());

            listItems.getChildren().clear();

            for(Object itemObj : itemsJson){
                // Carreguem el FXML com a sub plantilla
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/phoneListLayout.fxml"));

                Parent itemTemplate = loader.load();

                ListController listController = loader.getController();

                // Afegim un listener a l'item que hem carregat per detectar la seva selecció
                itemTemplate.setOnMouseClicked((evt)->{
                    itemSelected(itemTemplate,(JSONObject)itemObj);
                });

                listController.setText(((JSONObject)itemObj).getString("nom"));
                URL characterImage = getClass().getResource("/assets/images/"+((JSONObject)itemObj).getString("imatge"));

                listController.setPhoto(characterImage);

                listItems.getChildren().add(itemTemplate);
            }

            scan.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    @FXML
    private void returnAction() {
        UtilsViews.setViewAnimating("Mobile");
    }    

    private void itemSelected(Parent item, JSONObject itemInfo){
        // Eliminem la selecció de tots els elements
        for(Node elem : listItems.getChildren()){
            elem.setStyle("-fx-background-color: white;");
        }

        // Seleccionem l'element
        item.setStyle("-fx-background-color: lightgray;");

        infoLayout.getChildren().clear();
        System.out.println("infoLayout visible? " + infoLayout.isVisible());
        System.out.println("rootPane visible? " + rootPane.isVisible());

        System.out.println("infoLayout Parent antes de agregar contenido: " + infoLayout.getParent());
        System.out.println("rootPane Center: " + rootPane.getCenter());

        if (rootPane.getCenter() == null || rootPane.getCenter() != infoLayout) {
            rootPane.setCenter(infoLayout);
        }
        
        System.out.println("infoLayout Parent despues de la reinsercion: " + infoLayout.getParent());
        System.out.println("rootPane Center: " + rootPane.getCenter());

        try{
            if(choiceTypes.getValue().equals("Personatges")){

                UtilsViews.setViewAnimating("MobileInfo");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/charInfoLayout.fxml"));
                Parent charLayout = loader.load();
                objectController charController = loader.getController();
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
                objectController gameController = loader.getController();
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
                objectController consoleController = loader.getController();
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
