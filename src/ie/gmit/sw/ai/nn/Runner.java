package ie.gmit.sw.ai.nn;

import java.io.File;
import java.io.IOException;

import org.encog.util.obj.SerializeObject;

import ie.gmit.sw.ai.nn.activator.Activator;

public class Runner {
	public static void main(String[] args) throws Exception {
		new Runner().go();
	}
	
	public void go() throws Exception {
		//1) Health (2 = Healthy, 1 = Minor Injuries, 0 = Critical)
		//2) Strength  (2 = Strong, 1 = Normal, 0 = weak)
		
		double[][] data = { //Health, Strength ,  
				{ 1, 2}, { 1, 1}, { 1, 0},
				{ 2, 2}, { 2, 1}, { 2, 0},
				{ 0, 2}, { 0, 1}, { 0, 0}};

		double[][] expected = { //Attack, Roam, Run
				{ 1.0, 0.0, 0.0}, { 1.0, 0.0, 0.0}, { 0.0, 1.0, 0.0}, 
				{ 1.0, 0.0, 0.0}, { 1.0, 0.0, 0.0}, { 1.0, 0.0, 0.0},
				{ 0.0, 0.0, 1.0}, { 0.0, 1.0, 0.0}, { 0.0, 0.0, 1.0}};
		
		NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 2, 2, 3);
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn); 
		trainer.train(data, expected, 0.01, 100000);
	
		try {
			SerializeObject.save(new File("EnemyNetwork_Neural"), nn);// SAVES NETWORK TO FILE
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double[] test = {0, 0};
		double[] result = nn.process(test);
		for (int i = 0; i < result.length; i++){ 
			System.out.println(result[i]);
		}
		System.out.println(Utils.getMaxIndex(result) + 1);

	}	
}
