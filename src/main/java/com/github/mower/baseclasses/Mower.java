package com.github.mower.baseclasses;

import java.rmi.UnexpectedException;

import com.github.edoreld.mowerapp.baseclasses.Board;

/**
 * Class representing a Mower. It has position represented by x and y coordinates and orientation
 * represented by an enum with four values: NORTH, EAST, SOUTH, WEST
 * @author Javier Martínez
 *
 */
public class Mower {
	public Integer xCoord;
	public Integer yCoord;
	Orientation orientation;
	Board board;

	/**
	 * Initializes a lawnmower
	 * @param xCoord
	 * @param yCoord
	 * @param orientation
	 */

	public Mower(String mower) {
		String[] mowerData = mower.split("\\s");
		xCoord = Integer.parseInt(mowerData[0]);
		yCoord = Integer.parseInt(mowerData[1]);
		orientation = parseOrientation(mowerData[2]);
	}

	public Mower(String mower, Board board) {
		this(mower);
		this.board = board;

	}

	/**
	 * Converts an orientation in String format to an orientation in {@link Orientation} format
	 * @param string
	 * @return
	 */
	private Orientation parseOrientation(String string) {
		Orientation ori = null;
		switch (string) {
		case "N":
			ori = Orientation.NORTH;
			break;
		case "E":
			ori = Orientation.EAST;
			break;
		case "S":
			ori = Orientation.SOUTH;
			break;
		case "W":
			ori = Orientation.WEST;
			break;
		}
		return ori;
	}

	/**
	 * Orientation enum. Possible values: NORTH, EAST, SOUTH, WEST
	 * @author Javier Martínez
	 *
	 */
	public enum Orientation {
		NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

		public String oriLetter; // N, E, S, W

		/**
		 * Rotate the enum 90 degrees clockwise.
		 * Example: Orientation.NORTH turns to Orientation.EAST
		 * @return
		 */
		public Orientation rotate90clock() {
			return values()[Math.floorMod(ordinal() + 1, 4)];
		}

		/**
		 * Rotate the enum 90 degrees counterclockwise.
		 * Example: Orientation.NORTH turns to Orientation.WEST
		 * @return
		 */
		public Orientation rotate90counter() {

			return values()[Math.floorMod(ordinal() - 1, 4)];
		}

		private Orientation(String oriLetter) {
			this.oriLetter = oriLetter;
		}

		/**
		 * Returns the single letter associated with this orientation. Options are N, E, S, W
		 * @return
		 */
		public String getOriLetter() {
			return this.oriLetter;
		}

	}

	/**
	 * Returns the position and orientationin the following format:
	 * &lt;x-coord&gt; &lt;y-coord&gt; &lt;single-letter orientation&gt;
	 * @return
	 */
	public String getPosition() {
		return Integer.toString(xCoord) + " " + Integer.toString(yCoord) + " " + orientation.getOriLetter();
	}

	/**
	 * Prints the position in the same format as {@link #getPosition}
	 */
	public void printPosition() {
		System.out.println(getPosition());
	}

	/**
	 * Returns the single-letter orientation of this mower
	 * @return
	 */
	public String getOrientation() {
		return orientation.getOriLetter();
	}

	/**
	 * Sets the orientation of this mower
	 * @param orientation
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public Integer getxCoord() {
		return xCoord;
	}

	public void setxCoord(Integer xCoord) {
		this.xCoord = xCoord;
	}

	public Integer getyCoord() {
		return yCoord;
	}

	public void setyCoord(Integer yCoord) {
		this.yCoord = yCoord;
	}

	/**
	 * Rotates this mower 90 degrees clockwise
	 */
	public void turnRight() {
		orientation = orientation.rotate90clock();
	}

	/**
	 * Rotates this mower 90 degrees counterclockwise
	 */
	public void turnLeft() {
		orientation = orientation.rotate90counter();
	}

	/**
	 * Executes a single instruction. Possible instructions: G, D, A
	 */
	public void executeInstruction(String currentMove) {

		switch (currentMove) {
		case "G":
			this.turnLeft();
			break;
		case "D":
			this.turnRight();
			break;
		case "A":
			moveForward();
			break;
		}
	}

	/**
	 * Moves the mower forward if by doing so it won't be in an illegal position (outside the board)
	 * @throws UnexpectedException
	 */
	private void moveForward() throws IllegalArgumentException {
		switch (orientation) {
		case NORTH:
			if (this.yCoord + 1 < board.getHeight()) {
				this.yCoord++;
			}
			break;
		case EAST:
			if (this.xCoord + 1 < board.getWidth()) {
				this.xCoord++;
			}
			break;
		case SOUTH:
			if (this.yCoord - 1 >= 0) {
				this.yCoord--;
			}
			break;
		case WEST:
			if (this.xCoord - 1 >= 0) {
				this.xCoord--;
			}
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
}
