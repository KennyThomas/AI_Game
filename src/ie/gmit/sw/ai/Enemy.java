package ie.gmit.sw.ai;

public class Enemy implements Command {

	 int healthStatus; 
	 int strengthStatus;
	 String Command; 
	 int health;
	 String strength;    
	 int strengthvalue;  

	public int getStrengthvalue() {
		return strengthvalue;
	}

	public void setStrengthvalue(int strengthvalue) {
		this.strengthvalue = strengthvalue;
	}

	public int getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(int healthStatus) {
		this.healthStatus = healthStatus;
	}

	public int getStrengthStatus() {
		return strengthStatus;
	}

	public void setStrengthStatus(int strengthStatus) {
		this.strengthStatus = strengthStatus;
	}

	public String getCommand() {
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public int gotHit(int damage) {

		setHealth(health - damage);
		return health;

	}

	public int EnemyRegen() {

		if (health <= 100) {
			health++;
		}

		return health;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
