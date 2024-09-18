package hw3;



import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A utility class for handling game file input and output operations.
 * @author neha2004
 *
 */
public class GameFileUtil {
	/**

	Saves the current state of the game instance to a file at the specified location.
	@param fileLocation the file location to save the game state to.
	@param gameInstance the game instance to save the state of.
	*/

	public static void save(String fileLocation, ConnectGame gameInstance) {
	    try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(fileLocation))) {
	        Grid gameGrid = gameInstance.getGrid();
	        bufferWriter.write(gameGrid.getWidth() + " " + gameGrid.getHeight() + " " + gameInstance.getMinTileLevel() + " " + gameInstance.getMaxTileLevel() + " " + gameInstance.getScore() + "\n");
	        for (int rowIndex = 0; rowIndex < gameGrid.getHeight(); rowIndex++) {
	            for (int columnIndex = 0; columnIndex < gameGrid.getWidth(); columnIndex++) {
	                bufferWriter.write(gameGrid.getTile(columnIndex, rowIndex).getLevel() + (columnIndex < gameGrid.getWidth() - 1 ? " " : ""));
	            }
	            if (rowIndex < gameGrid.getHeight() - 1) {
	                bufferWriter.write("\n");
	            }
	        }
	    } catch (IOException ioException) {
	        ioException.printStackTrace();
	    }
	}
	/**

	Loads a saved game state from a file at the specified path into the provided game instance.
	@param filePath the path of the file containing the saved game state.
	@param game the game instance to load the saved state into.
	*/


	public static void load(String filePath, ConnectGame game) {
	    try (Scanner scanner = new Scanner(new File(filePath))) {
	        int width = scanner.nextInt();
	        int height = scanner.nextInt();
	        int minTileLevel = scanner.nextInt();
	        int maxTileLevel = scanner.nextInt();
	        long score = scanner.nextLong();

	        Grid grid = new Grid(width, height);
	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                int level = scanner.nextInt();
	                api.Tile tile = new api.Tile(level);
	                grid.setTile(tile, x, y);
	            }
	        }

	        game.setGrid(grid);
	        game.setMinTileLevel(minTileLevel);
	        game.setMaxTileLevel(maxTileLevel);
	        game.setScore(score);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }


	}
}

