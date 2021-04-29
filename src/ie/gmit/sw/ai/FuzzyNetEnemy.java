package ie.gmit.sw.ai;

public class FuzzyNetEnemy extends Enemy implements Command {

	public FuzzyNetEnemy(int Health, int StrengthValue) throws Exception {
		this.health = Health;
		this.strengthvalue = StrengthValue;
		// Health
	}
	
	
	static int RandStrength(){
        int strength = (int) ((Math.random()*(100 - 50))) + 50;  // each fuzzy enemy will get a random strength between 50-100
        return strength;
    }

	@Override
	public void execute() {
		double output = 0;
		try {
			output = new LoadNetworks().Runfuzzy(health, strengthvalue);  // pass in data to the fuzzy network
			if (output >= 65) {
				Command = "Attack";

			} else if (output >= 35 && output < 65) {
				Command = "Roam";
			} else {
				Command = "Run";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 System.out.println("This fuzzy enemy has Health: "+health+" Strength:"
		 +strengthvalue+" health status = "+healthStatus +" strength status ="
		 +strengthStatus+" command= "+Command);
	}

}
