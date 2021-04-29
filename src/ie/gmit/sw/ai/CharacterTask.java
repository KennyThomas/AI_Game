package ie.gmit.sw.ai;

import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class CharacterTask extends Task<Void> {
	private static final int SLEEP_TIME = 300; // Sleep for 300 ms
	private static ThreadLocalRandom rand = ThreadLocalRandom.current();
	private boolean alive = true;
	private GameModel model;
	private GameController GC = new GameController();
	private char enemyID;
	private int row;
	private int col;
	int counter = 0;
	private Enemy enemy;

	private Player player = Player.getInstance(); // get the player instance

	public CharacterTask(GameModel model, char enemyID, int row, int col, Enemy enemy) {
		this.model = model;
		this.enemyID = enemyID;
		this.row = row;
		this.col = col;
		this.enemy = enemy;
	}

	@Override
	public Void call() throws Exception {

		while (alive) {
			Thread.sleep(SLEEP_TIME);
			synchronized (model) {
				GC.playerRange(player.getRow(), row, player.getCol(), col); // passes player position and enemy position
																			// to calculate distance
				GC.IsEnemyAlive(enemy); // Checks if enemy is alive
				GC.controlGame(player, enemy); // Checks if enemy/player can attack
				GC.IsPlayerAlive(player); // checks if the player is alive

				if (GC.IsEnemyAlive(enemy) == false) {
					player.setGhostsDefeated(1); // if the player defeats a ghost update score
					player.PlayerRegen(); // When the player defeats a ghos their health is regenerated
					System.out.println("Ghost " + enemyID + " defeated!!");
					alive = false;
					GC.HasplayerWon(player); // Checks if the player has defeated all ghosts
				}

				// Randomly pick a direction up, down, left or right
				int temp_row = row, temp_col = col;
				if (rand.nextBoolean()) {
					temp_row += rand.nextBoolean() ? 1 : -1;
				} else {
					temp_col += rand.nextBoolean() ? 1 : -1;
				}

				if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
					model.set(temp_row, temp_col, enemyID);
					model.set(row, col, '\u0020');
					row = temp_row;
					col = temp_col;
				} else {

					if (model.get(temp_col, temp_col) == '1') {
						System.out.println("My health " + player.getHealth());
						System.out.println(enemy.getCommand());
					}
					enemy.execute();
				}
			}
		}
		return null;
	}
}