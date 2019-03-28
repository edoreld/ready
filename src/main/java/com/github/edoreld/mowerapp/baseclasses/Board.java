package com.github.edoreld.mowerapp.baseclasses;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import com.github.mower.baseclasses.Mower;

/**
 * 2-d board representing the grass field where the lawnmowers do their work
 * @author Javier Martínez
 *
 */
public class Board extends AbstractList<MowerProgram> {

	List<MowerProgram> moves;

	int width;
	int height;

	public Board(String board) {
		String[] coords = board.split("\\s");
		this.width = Integer.parseInt(coords[0]) + 1;
		this.height = Integer.parseInt(coords[1]) + 1;
		moves = new ArrayList<>();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public MowerProgram get(int index) {
		return moves.get(index);
	}

	@Override
	public int size() {
		return moves.size();
	}

	@Override
	public boolean add(MowerProgram move) {
		return moves.add(move);
	}

	/**
	 * Executes the series of Mower Programs executed in the {@link MowerPogram} array
	 */
	public void run() {
		for (MowerProgram move : moves) {
			Mower mower = move.getMower();
			String mowerMoves = move.getMoves();
			for (int i = 0; i < mowerMoves.length(); i++) {
				String currentMove = mowerMoves.substring(i, i + 1);
				mower.executeInstruction(currentMove);
			}
		}
	}

	/**
	 * Prints a textual representation of the board as a list of lines. Each line has the format:
	 * &lt;x-coord&gt; &lt;y-coord&gt; &lt;single-letter orientation&gt;
	 * @return
	 */
	public String getBoard() {
		StringBuilder sb = new StringBuilder("");
		for (MowerProgram move : moves) {
			sb.append(move.getMower().getPosition() + "\n");
		}

		String boardStatus = sb.toString();
		return boardStatus.trim();
	}

	/**
	 * Prints a graphical representation of the board
	 */
	public void printBoardGraphically() {
		boolean match = false;
		for (int x = 0; x < width; x++) {
			System.out.println();
			for (int y = 0; y < height; y++) {
				for (MowerProgram move : moves) {
					if (move.getMower().getxCoord() == x && move.getMower().getxCoord() == y) {
						System.out.print(move.getMower().getOrientation());
						match = true;
					}
				}
				if (!match) {
					System.out.print("_");
				}
				match = false;
			}

		}

		System.out.println();
	}

}
