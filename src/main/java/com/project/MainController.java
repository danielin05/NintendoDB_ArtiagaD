package com.project;

import java.util.ArrayList;
import java.util.List;

import com.project.objects.Personatges;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import jakarta.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.project.objects.Personatges;

public class MainController extends Application {

    private static final double MOBILE_THRESHOLD_WIDTH = 600; // Ancho para cambiar a la vista móvil
    private Scene scene;
    private Parent desktopView;
    private Parent mobileView;
    private final File dataFile;    
    
    /**
    * Constructor de la classe PR14GestioLlibreriaJSONPMain.
    *
    * @param dataFile Fitxer on es troben els llibres.
    */
   public MainController(File dataFile) {
       this.dataFile = dataFile;
   }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar las dos vistas FXML (layout.fxml y mobile_layout.fxml)
        desktopView = FXMLLoader.load(getClass().getResource("/assets/layout.fxml"));
        mobileView = FXMLLoader.load(getClass().getResource("/assets/phoneLayout.fxml"));

        // Usar la vista de escritorio como vista predeterminada
        scene = new Scene(desktopView, 800, 600); // Configura la vista inicial para escritorio
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX App");
        primaryStage.show();

        // Listener para detectar cambios de tamaño de la ventana
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // Cambiar a la vista móvil si el ancho es menor al umbral
                if (newValue.doubleValue() < MOBILE_THRESHOLD_WIDTH) {
                    scene.setRoot(mobileView);
                } else {
                    // Volver a la vista de escritorio si el ancho es mayor
                    scene.setRoot(desktopView);
                }
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Carrega els personatges des del fitxer JSON.
     *
     * @return Llista de personatges o null si hi ha hagut un error en la lectura.
     */
    public List<Personatges> carregarPersonatges() {
        List<Personatges> personatges = new ArrayList<>();
        try (JsonReader jsonReader = Json.createReader(new FileReader(dataFile))) {
            JsonArray jsonArray = jsonReader.readArray();
            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                String nom = jsonObject.getString("titol");
                String imatge = jsonObject.getString("imatge");
                String color = jsonObject.getString("color");
                String nom_del_videojoc = jsonObject.getString("nom_del_videojoc");
                personatges.add(new Personatges(nom, imatge, color, nom_del_videojoc));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personatges;
    }


}
