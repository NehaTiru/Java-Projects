package hw3;

import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;
/**
 * The code is for the ConnectGame class, which is a game in which the player chooses a sequence of adjacent tiles in a grid, 
 * and the selected tiles are upgraded and then removed from the grid. 
 * @author neha2004
 *
 */


public class ConnectGame {


	/**

	A listener that updates the dialog display in the GUI.
	*/
	private ShowDialogListener dialog_updater;
	/**

	A listener that updates the score display in the GUI.
	*/
	private ScoreUpdateListener score_updater;
	/**

	The game's grid.
	*/
	private Grid grid;
	/**

	The minimum level of tiles allowed in the game.
	*/
	private int min_Tile_level;
	/**

	The maximum level of tiles allowed in the game.
	*/
	private int max_Tile_level;
	/**

	A random number generator.
	*/
	private Random new_rand;
	/**

	The player's current score.
	*/
	private long player_score;
	/**

	A list of the tiles that have been chosen by the player.
	*/
	private List<Tile> Tiles_chosen = new ArrayList<>();

	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) {
		this.grid = new Grid(width, height);
		this.min_Tile_level = min;
		this.max_Tile_level = max;
		this.new_rand = rand;
		this.player_score = 0;
		this.Tiles_chosen = new ArrayList<>();
		radomizeTiles();
	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		int range = max_Tile_level - min_Tile_level;
	    int random_Level = new_rand.nextInt(range) + min_Tile_level;
	    return new Tile(random_Level);
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
	    int deltaX = Math.abs(t1.getX() - t2.getX());
	    int deltaY = Math.abs(t1.getY() - t2.getY());

	    boolean isAdjacent = (deltaX <= 1 && deltaY <= 1) && !(deltaX == 0 && deltaY == 0);
	    return isAdjacent;
	}
	
	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		int width = grid.getWidth();
	    int height = grid.getHeight();

	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            Tile random_Tile = getRandomTile();
	            grid.setTile(random_Tile, x, y);
	        }
	    }
	}


	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		if (Tiles_chosen.size() == 0) {
		Tile selectedTile = grid.getTile(x, y);
		selectedTile.setSelect(true);
		Tiles_chosen.add(selectedTile);
		return true;
		}
		return false;
	}


	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		if(Tiles_chosen.size() == 0) {
			return false;
		}
		Tile lastTile = Tiles_chosen.get(Tiles_chosen.size() - 1);
		if (lastTile.getX() == x && lastTile.getY() == y) {
		    if (Tiles_chosen.size() == 1) {
		        unselect(x, y);
		        Tiles_chosen.clear();
		    } 
		    else {
		        long score = 0;
		        for (Tile tile : Tiles_chosen) {
		            score += (int) Math.pow(2, tile.getLevel());
		        }
		        upgradeLastSelectedTile();
		        player_score += score;
		        if (score_updater != null) {
		            score_updater.updateScore(player_score);
		        }
		        for (int i = 0; i < Tiles_chosen.size() - 1; i++) {
		            Tile tile = Tiles_chosen.get(i);
		            tile.setSelect(false);
		            unselect(tile.getX(), tile.getY());
		        }
		        dropSelected();
		        lastTile.setSelect(false);
		        Tiles_chosen.clear();
		    }
		    return true;
		}
		return false;
	}
	

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
	    if (!Tiles_chosen.isEmpty()) {
	        Tile lastTile = Tiles_chosen.get(Tiles_chosen.size() - 1);
	        int updatedLevel = lastTile.getLevel() + 1;
	        lastTile.setLevel(updatedLevel);
	        lastTile.setSelect(false);
	        Tiles_chosen.remove(Tiles_chosen.size() - 1);

	        if (updatedLevel > max_Tile_level) {
	            min_Tile_level++;
	            max_Tile_level++;
	            int newValue = (int) Math.pow(2, updatedLevel);
	            int removedValue = (int) Math.pow(2, min_Tile_level - 1);
	            dialog_updater.showDialog("A new block with value " + newValue + " has appeared. Removing blocks with value " + removedValue + ".");
	        }
	    }
	}

	
	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */

	public void tryContinueSelect(int x, int y) {
	    if (Tiles_chosen.isEmpty()) {
	        return;
	    }

	    Tile nextTile = grid.getTile(x, y);
	    Tile last_Selected_Tile = Tiles_chosen.get(Tiles_chosen.size() - 1);

	    if (isAdjacent(last_Selected_Tile, nextTile)) {
	        if (Tiles_chosen.size() == 1) {
	            if (are_Same_Level(last_Selected_Tile, nextTile)) {
	                select_Next_Tile(nextTile);
	            }
	        } else {
	            Tile secondLastSelectedTile = Tiles_chosen.get(Tiles_chosen.size() - 2);

	            if (isBacktracking(last_Selected_Tile, secondLastSelectedTile, nextTile)) {
	                unselect(last_Selected_Tile.getX(), last_Selected_Tile.getY());
	            } else if (isAllowedLevel(last_Selected_Tile, nextTile)) {
	                select_Next_Tile(nextTile);
	            }
	        }
	    }
	}
	
	/**

	Checks if two given tiles are on the same level.
	@param tile1 the first Tile object to compare
	@param tile2 the second Tile object to compare
	@return true if the two tiles are on the same level, false otherwise
	*/

	private boolean are_Same_Level(Tile tile1, Tile tile2) {
	    return tile1.getLevel() == tile2.getLevel();
	}
	
	/**

	Checks if the next tile selected in a sequence of moves represents a backtracking move.
	A backtracking move occurs when the player tries to move to a tile that was the previous
	tile in the sequence of moves.
	@param lastSelectedTile the last Tile object that was selected
	@param second_Last_SelectedTile the second to last Tile object that was selected
	@param nextTile the Tile object representing the next tile selected by the player
	@return true if the next tile selected represents a backtracking move, false otherwise
	*/

	private boolean isBacktracking(Tile lastSelectedTile, Tile second_Last_SelectedTile, Tile nextTile) {
	    return nextTile.getX() == second_Last_SelectedTile.getX() && nextTile.getY() == second_Last_SelectedTile.getY();
	}
	
	/**

	Checks if the level difference between the last selected tile and the next tile is allowed.
	The level difference between two tiles is allowed if it is either 0 or 1.
	@param last_Selected_Tile the last Tile object that was selected
	@param nextTile the Tile object representing the next tile selected by the player
	@return true if the level difference between the last selected tile and the next tile is allowed, false otherwise
	*/


	private boolean isAllowedLevel(Tile last_Selected_Tile, Tile nextTile) {
	    int level_Diff = nextTile.getLevel() - last_Selected_Tile.getLevel();
	    return level_Diff == 0 || level_Diff == 1;
	}
	
	/**

	Selects the next tile and updates the Tiles_chosen collection to include the new tile.
	@param nextTile the Tile object representing the next tile selected by the player
	*/


	private void select_Next_Tile(Tile nextTile) {
	    Tiles_chosen.add(nextTile);
	    nextTile.setSelect(true);
	}



	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {
	    Tile[] selected_Tiles_Array = Tiles_chosen.toArray(new Tile[0]);
	    return selected_Tiles_Array;
	}


	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {
		   Tile[] selectedTilesList = getSelectedAsArray();
		    Set<Integer> affectedColumns = new HashSet<>();

		    
		    for (Tile selectedTile : selectedTilesList) {
		        int column = selectedTile.getX();
		        int row = selectedTile.getY();
		        grid.setTile(null, column, row);
		        affectedColumns.add(column);
		    }

		    
		    for (int column : affectedColumns) {
		        int newRow = grid.getHeight() - 1;

		        
		        for (int row = grid.getHeight() - 1; row >= 0; row--) {
		            Tile tile = grid.getTile(column, row);
		            if (tile != null) {
		                grid.setTile(tile, column, newRow);
		                newRow--;
		            }
		        }

		        
		        for (int row = newRow; row >= 0; row--) {
		            Tile generatedTile = getRandomTile();
		            grid.setTile(generatedTile, column, row);
		        }
		    }
	}
	
	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int targetLevel) {
		int gridWidth = grid.getWidth();
	    int gridHeight = grid.getHeight();

	    for (int column = 0; column < gridWidth; column++) {
	        int newRow = gridHeight - 1;

	        
	        for (int row = gridHeight - 1; row >= 0; row--) {
	            Tile tile = grid.getTile(column, row);
	            if (tile.getLevel() != targetLevel) {
	                grid.setTile(tile, column, newRow);
	                newRow--;
	            }
	        }

	        
	        for (int row = newRow; row >= 0; row--) {
	            Tile generatedTile = getRandomTile();
	            grid.setTile(generatedTile, column, row);
	        }
	    }
	}


	public void unselect(int x, int y) {
		Tile targetTile = grid.getTile(x, y);
	    targetTile.setSelect(false);
	    Tiles_chosen.remove(targetTile);
	}

	public Grid getGrid() {
		return grid;
	}

	public long getScore() {
		return player_score;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		return min_Tile_level;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		this.player_score = score;
	}
	
	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		return max_Tile_level;
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		this.max_Tile_level = maxTileLevel;
	}
	
	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		this.min_Tile_level = minTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialog_updater = dialogListener;
		this.score_updater = scoreListener;
	}
	
	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}
}