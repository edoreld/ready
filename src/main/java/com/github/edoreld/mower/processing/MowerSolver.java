package com.github.edoreld.mower.processing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.github.edoreld.mowerapp.baseclasses.Board;
import com.github.edoreld.mowerapp.baseclasses.MowerProgram;
import com.github.mower.baseclasses.Mower;

/**
 * Class to solve the mower problem
 * The constructor initializes the board and the instructions
 * The {@link #solve} method solves the problem and outputs the final board state
 *
 * @author Javier Martínez
 *
 */
public class MowerSolver {

	Board board;
	List<MowerProgram> moves;

	/**
	 *
	 * @param file a file in the expected format
	 * @throws IOException
	 */
	public MowerSolver(File file) throws IOException {
		if (!file.isFile()) {
			throw new IOException();
		}

		LineIterator it = FileUtils.lineIterator(file);

		initializeBoard(it);
		storeMoves(it);

	}

	/**
	 * Initializes a board with a certain width and height
	 * @param it
	 */
	private void initializeBoard(LineIterator it) {
		this.board = new Board(it.nextLine());
	}

	/**
	 * Stores the moves from the content of the file into the {@link MowerProgram} array for later execution
	 * @param it
	 */
	private void storeMoves(LineIterator it) {
		moves = new ArrayList<>();
		while (it.hasNext()) {
			String mower = it.nextLine();
			String movements = it.nextLine();

			board.add(new MowerProgram(new Mower(mower, board), movements));
		}
	}

	/**
	 *	Solves the board and returns the final position of the mowers as a list of lines where each line is in the format:
	 * &lt;x-coord&gt; &lt;y-coord&gt; &lt;single-letter orientation&gt;
	 * @return
	 */
	public String solve() {
		board.run();
		return board.getBoard();
	}

	/**
	 *
	 * @return this board
	 */
	public Board getBoard() {
		return this.board;
	}
}
