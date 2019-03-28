package com.github.edoreld.mowerapp.baseclasses;

import com.github.mower.baseclasses.Mower;

/**
 * A class representing series of instructions for a specific mower
 * Example of moves: "AGADAGAD", "AGGAGGAGGA"
 *
 * @author Javier Martínez
 *
 */
public class MowerProgram {

	Mower mower;
	String moves;

	public MowerProgram(Mower mower, String moves) {
		this.mower = mower;
		this.moves = moves;
	}

	public Mower getMower() {
		return mower;
	}

	public void setMower(Mower mower) {
		this.mower = mower;
	}

	public String getMoves() {
		return moves;
	}

	public void setMoves(String moves) {
		this.moves = moves;
	}
}
