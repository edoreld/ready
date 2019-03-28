package com.github.edoreld.mowerapp.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.Test;

import com.github.edoreld.mower.processing.MowerSolver;
import com.github.edoreld.mowerapp.baseclasses.Board;
import com.github.edoreld.mowerapp.baseclasses.MowerProgram;
import com.github.mower.baseclasses.Mower;

class MowerTest {

	@Test
	void solve() throws IOException {
		//		URL fileURL = this.getClass().getResource("/input.txt");
		URL fileURL = this.getClass().getResource("/input.txt");
		File file = new File(fileURL.getFile());

		MowerSolver solver = new MowerSolver(file);

		assertEquals("1 3 N\n5 1 E", solver.solve());

	}

	/**
	 * Tests that the board is initialized with the right dimensions
	 * The input is 5 5, so the board size must be 6 6
	 * @throws IOException
	 */
	@Test
	void boardDimensions() throws IOException {
		URL fileURL = this.getClass().getResource("/input.txt");
		File file = new File(fileURL.getFile());

		MowerSolver solver = new MowerSolver(file);

		assertEquals(6, solver.getBoard().getWidth());
		assertEquals(6, solver.getBoard().getHeight());
	}

	/**
	 * Tests the orientation and position of a newly created mower
	 */
	@Test
	void mowerInitialization() {
		String mowerString = "5 7 N";
		Mower mower = new Mower(mowerString);
		assertEquals(5, mower.getxCoord());
		assertEquals(7, mower.getyCoord());
		assertEquals("N", mower.getOrientation());

		String mowerString2 = "17 3 E";
		Mower mower2 = new Mower(mowerString2);
		assertEquals(17, mower2.getxCoord());
		assertEquals(3, mower2.getyCoord());
		assertEquals("E", mower2.getOrientation());
	}

	/**
	 * Test that rotation works
	 */
	@Test
	void rotationTest() {
		String mowerData = "0 0 N";
		Mower mower = new Mower(mowerData);
		mower.turnRight();
		mower.turnRight();

		assertEquals("0 0 S", mower.getPosition());
		mower.turnLeft();
		mower.turnLeft();
		mower.turnLeft();
		assertEquals("0 0 W", mower.getPosition());
	}

	/**
	 * Test movements outside of the board
	 */
	@Test
	void testMoveWest() {
		Board board = new Board("5 5");
		Mower mower = new Mower("0 0 W", board);
		MowerProgram moves = new MowerProgram(mower, "A");
		board.add(moves);
		board.run();
		assertEquals(mower.getPosition(), "0 0 W");
	}

	@Test
	void testMoveEast() {
		Board board = new Board("5 5");
		Mower mower = new Mower("5 0 E", board);
		MowerProgram moves = new MowerProgram(mower, "A");
		board.add(moves);
		board.run();
		assertEquals(mower.getPosition(), "5 0 E");
	}

	@Test
	void testMoveNorth() {
		Board board = new Board("5 5");
		Mower mower = new Mower("0 5 N", board);
		MowerProgram moves = new MowerProgram(mower, "A");
		board.add(moves);
		board.run();
		assertEquals(mower.getPosition(), "0 5 N");
	}

	@Test
	void testMoveSouth() {
		Board board = new Board("5 5");
		Mower mower = new Mower("0 0 S", board);
		MowerProgram moves = new MowerProgram(mower, "A");
		board.add(moves);
		board.run();
		assertEquals(mower.getPosition(), "0 0 S");
	}

}
