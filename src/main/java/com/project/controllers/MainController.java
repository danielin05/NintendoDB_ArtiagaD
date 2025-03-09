package com.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


import org.json.JSONArray;
import org.json.JSONObject;

public class MainController implements Initializable {
    
    @FXML
    private VBox listItems = new VBox();
    @FXML
    private VBox contentBox = new VBox();
    @FXML
    private ChoiceBox<String> choiceTypes = new ChoiceBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceTypes.getItems().addAll(new String[]{"Personatges","Jocs","Consoles"});
        choiceTypes.setValue("Personatges");

        choiceTypes.setOnAction((evt)->{
            putList(choiceTypes.getValue());
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/listLayout.fxml"));

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

    private void itemSelected(Parent item, JSONObject itemInfo){
        // Eliminem la selecció de tots els elements
        for(Node elem : listItems.getChildren()){
            elem.setStyle("-fx-background-color: white;");
        }

        // Seleccionem l'element
        item.setStyle("-fx-background-color: lightgray;");

        contentBox.getChildren().clear();

        try{
            if(choiceTypes.getValue().equals("Personatges")){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/charInfoLayout.fxml"));

                Parent charLayout = loader.load();
                objectController charController = loader.getController();

                charController.setContentCharacter(itemInfo);

                contentBox.getChildren().add(charLayout);
            }else if(choiceTypes.getValue().equals("Jocs")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/gameInfoLayout.fxml"));
                Parent gameLayout = loader.load();
                objectController gameController = loader.getController();
                gameController.setContentGame(itemInfo);

                contentBox.getChildren().add(gameLayout);
            }else if(choiceTypes.getValue().equals("Consoles")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/consoleInfoLayout.fxml"));
                Parent consoleLayout = loader.load();
                objectController consoleController = loader.getController();

                consoleController.setContentConsole(itemInfo);

                contentBox.getChildren().add(consoleLayout);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    
    


}
