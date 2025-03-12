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

public class MobileInfoController implements Initializable {
    
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
        UtilsViews.setViewAnimating("MobileInfo");
    }
}
