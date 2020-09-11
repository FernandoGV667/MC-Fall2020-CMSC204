package com.fgonzalesv.assignment1;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PasswordDriverFX extends Application{
	
   
   public static void main(String[] args){
	   launch(args);
   }
   
   public void start(Stage stage)
   {

	   //call the main scene which is a BorderPane
	   PasswordMain mainPane = new PasswordMain();
	   //PasswordMain root = mainPane.getTopContainer();
	   Scene scene = new Scene(mainPane, 550, 350);
	   stage.setScene(scene);
	   stage.setTitle("Password Checker");
	   stage.show();
   }
   
}
