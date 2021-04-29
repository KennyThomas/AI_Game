package ie.gmit.sw.ai;

import javafx.application.Platform;

public class GameController {
	static double range;

	public double playerRange(double x1, double x2, double y1, double y2) {
		range = Math.sqrt((Math.pow((x1 - x2), 2)) + (Math.pow((y1 - y2), 2))); // distance between ghosts and player;
		return range;

	}

	public void controlGame(Player player, Enemy enemy) {

		if (range <= 1 && enemy.getCommand() == "Attack") { // Enemy attack
			System.out.println("Enemy is attacking");
			player.gotHit(4);
			System.out.println("My health " + player.getHealth());
		}

		else if (enemy.getCommand() == "Roam") { // While roaming the enemy can regenerate health
			System.out.println("Enemy is roaming");
			enemy.EnemyRegen();

		} else if (enemy.getCommand() == "Run") {
			System.out.println("Enemy is Running");

		}

		if (range <= 1) { // enemy takes damage
			enemy.gotHit(8);
			if (enemy.getHealth() <= 0) {
				enemy.setHealth(0);
				enemy.setCommand("Defeated");
			}
			player.setAttackingEnemyHealth(enemy.getHealth()); // Display the health of the enemy your attacking
			player.setAttackingEnemyCommand(enemy.getCommand());// Display the command of the enemy your attacking

		}
	}

	public boolean IsEnemyAlive(Enemy enemy) {
		boolean alive;

		if (enemy.getHealth() <= 0) {
			alive = false;
		} else {
			alive = true;
		}
		return alive;
	}

	public void HasplayerWon(Player player) {
		if (player.getGhostsDefeated() == 5) {
			System.out.println("YOU WIN!!");
			Platform.exit(); // close application
		}

	}

	public void IsPlayerAlive(Player player) {

		if (player.getHealth() <= 0) {
			System.out.println("Player defeated!!");
			Platform.exit(); // close application
		}

	}
}
