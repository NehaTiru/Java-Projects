package hw3;

import api.Tile;

/**
 * Represents the game's grid.
 * @author neha2004
 *
 */
public class Grid {
	private Tile[][] tiles;
	private int width;
	private int height;
	
	/**
	 * Creates a new grid.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		
		
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		
		return width;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		
		return height;
	}

	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public Tile getTile(int x, int y) {
	    if (isPositionWithinBounds(x, y)) {
	        return tiles[x][y];
	    }
	    return null;
	}
	
	/**

	Checks whether a given position (x,y) is within the bounds of the game board.
	@param x 
	@param y 
	@return true if the position is within the bounds of the game board, false otherwise.
	*/
	private boolean isPositionWithinBounds(int x, int y) {
	    return x >= 0 && x < width && y >= 0 && y < height;
	}


	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(Tile tile, int x, int y) {
	    if (isValidPosition(x, y)) {
	        updateTileAtPosition(tile, x, y);
	    }
	}
	/**

	Checks whether the specified position is a valid coordinate within the game board.
	@param x 
	@param y 
	@return true if the position is within the bounds of the game board, false otherwise.
	*/

	private boolean isValidPosition(int x, int y) {
	    return x >= 0 && x < width && y >= 0 && y < height;
	}

	/**

	Updates the tile at the specified position with the given tile, and updates the tile's location if the tile is not null.
	@param tile 
	@param x 
	@param y 
	*/
	private void updateTileAtPosition(Tile tile, int x, int y) {
	    tiles[x][y] = tile;
	    if (tile != null) {
	        tile.setLocation(x, y);
	    }
	}


	
	@Override
	public String toString() {
		String str = "";
		for (int y=0; y<getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			str += "[";
			for (int x=0; x<getWidth(); x++) {
				if (x > 0) {
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
}