package ie.gmit.sw.ai;

public class Player {

	private int health = 100;
	private static Player instance = null;
	private int GhostsDefeated;
	private double row, col;
	private int AttackingEnemyHealth;
	private String AttackingEnemyCommand;
	
	public String getAttackingEnemyCommand() {
		return AttackingEnemyCommand;
	}

	public void setAttackingEnemyCommand(String attackingEnemyCommand) {
		AttackingEnemyCommand = attackingEnemyCommand;
	}

	public int getAttackingEnemyHealth() {
		return AttackingEnemyHealth;
	}

	public void setAttackingEnemyHealth(int attackingEnemyHealth) {
		AttackingEnemyHealth = attackingEnemyHealth;
	}

	public double getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public double getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	private Player(int health) {
		super();
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public static Player getInstance() {

		if (instance == null) {
			instance = new Player(100);
		}

		return instance;
	}

	public int gotHit(int damage) {

		setHealth(health - damage);
		return health;

	}

	public int PlayerRegen() {

		if (health > 80) {
			setHealth(100);
		}
		else {
			setHealth(getHealth()+20);
		}

		return health;
	}

	public int getGhostsDefeated() {
		return GhostsDefeated;
	}

	public void setGhostsDefeated(int ghostsDefeated) {
		GhostsDefeated += ghostsDefeated;
	}
}