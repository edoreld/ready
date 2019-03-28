package com.github.edoreld.mowerapp;

import java.io.File;
import java.io.IOException;

import com.github.edoreld.mower.processing.MowerSolver;

public class Main {
	public static void main(String... args) throws IOException {
		File file = new File(args[0]);
		MowerSolver solver = new MowerSolver(file);
		System.out.println(solver.solve());
	}

}
