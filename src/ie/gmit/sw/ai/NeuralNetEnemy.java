package ie.gmit.sw.ai;

import java.io.File;
import java.io.IOException;

import org.encog.util.obj.SerializeObject;

import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.Utils;

public class NeuralNetEnemy extends Enemy implements Command {

	public NeuralNetEnemy(int Health, String Strength) throws Exception {
		this.health = Health;
		this.strength = Strength;
		// Health
	}
	
	
	static String RandStrength(){
        int r = (int) (Math.random()*3);
        String strength = new String [] {"Strong","Normal","Weak"}[r];  //Each Neural net ghost will get a random strength
        return strength;
    }

	@Override
	public void execute() {      // Decide the health and strength status of each ghost to pass into the neural network
		if (health >= 75) {
			healthStatus = 2;
		} else if (health >= 45 && health < 75) {
			healthStatus = 1;
		} else {
			healthStatus = 0;
		}

		if (strength == "Strong") {
			strengthStatus = 2;
		} else if (strength == "Normal") {
			strengthStatus = 1;
		} else {
			strengthStatus = 0;
		}

		// Attack, Hide, Run
		double[] data = { healthStatus, strengthStatus };
		int output = 0;
		try {
			output = new LoadNetworks().RunNeural(data); // pass in data to the neural network
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (output == 1) {  
			Command = "Attack";

		} else if (output == 2) {
			Command = "Roam";
		} else {
			Command = "Run";
		}

		 System.out.println("This Neural enemy has Health: "+health+" Strength:"
		 +strength+" health status = "+healthStatus +" strength status ="
		 +strengthStatus+" command= "+Command);
	}
}
