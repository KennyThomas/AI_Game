package ie.gmit.sw.ai;

import java.io.File;
import java.io.IOException;

import org.encog.ml.data.MLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.util.obj.SerializeObject;

import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.Utils;
import javafx.application.Application;

public class Runner {
	
	public static void main(String[] args) throws Exception {	
		
		new LoadNetworks().LoadNeural();  //Load the Neural Network
		new LoadNetworks().LoadFuzzy();   //Load the Fuzzy Network
	
		
		/*
		 * Launch the JavaFX UI only when all the long-running AI 
		 * configuration tasks have been completed. Use the arrow 
		 * keys to move the player character and the 'Z' key to 
		 * toggle the zoom in / out.
		 */
		Application.launch(GameWindow.class, args);
	}
}