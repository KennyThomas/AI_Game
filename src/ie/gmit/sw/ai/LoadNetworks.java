package ie.gmit.sw.ai;

import java.io.File;
import java.io.IOException;

import org.encog.util.obj.SerializeObject;

import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.Utils;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class LoadNetworks {

	private static NeuralNetwork Neuralnet;
	private static FIS fuzzyNet;

	public void LoadNeural() throws ClassNotFoundException, IOException {
		try {
			Neuralnet = (NeuralNetwork) SerializeObject.load(new File("res/Neural/EnemyNetwork_Neural"));  //Load in the Neural network
			System.out.println(Neuralnet);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in the network
	}

	public int RunNeural(double[] data) throws Exception {
		double[] result = Neuralnet.process(data);  // Pass in enemy data to output a command
		int output;
		output = Utils.getMaxIndex(result) + 1;
		return output;

	}

	public void LoadFuzzy() throws ClassNotFoundException, IOException {
		fuzzyNet = FIS.load("res\\fcl\\FuzzyNet.fcl", true); // Load and parse the FCL
	}

	public double Runfuzzy(int health, int strength) throws Exception {

		FunctionBlock fb = fuzzyNet.getFunctionBlock("FuzzyNetEnemy");
		fuzzyNet.setVariable("Health", health); // Apply a value to a variable
		fuzzyNet.setVariable("Strength", strength); // Apply a value to a variable
		fuzzyNet.evaluate(); // Execute the fuzzy inference engine
		double ouput = fuzzyNet.getVariable("Command").getValue();
		return ouput; // return the result
	}

}
