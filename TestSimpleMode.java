package Test;

import org.junit.Before;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Production_Code.Board;
import Production_Code.GeneralMode;
import Production_Code.SimpleMode;

class TestSimpleMode {

	private static Board board;
	private static SimpleMode game;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		board = new Board();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	// Acceptance criteria 5.1
	@Test
	public void TestWinGame() {
		board = new Board(7, "simple");
		game = new SimpleMode(board, "human", "human");

		// ongoing game
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(3, 4);
		game.newBoard.getElement('O');// red turn
		game.simpleMove(3, 3);

		// blue turn make a win
		game.newBoard.getElement('S');
		game.simpleMove(3, 2);

		assertEquals("", game.winner, "blue");

	}

	// Acceptance criteria 5.1
	@Test
	public void TestWinGame2() {
		board = new Board(5, "simple");
		game = new SimpleMode(board, "human", "human");

		// ongoing game
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(0, 0);
		game.newBoard.getElement('S');// red turn
		game.simpleMove(3, 3);
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(2, 2);

		// red turn make a win
		game.newBoard.getElement('O');
		game.simpleMove(1, 1);

		assertEquals("", game.winner, "red");

	}

	// Acceptance criteria 5.2
	@Test
	public void TestContinueGame() {
		board = new Board(6, "simple");
		game = new SimpleMode(board);

		// ongoing game
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(1, 0);
		game.newBoard.getElement('S');// red turn
		game.simpleMove(3, 4);
		game.newBoard.getElement('O');// blue turn
		game.simpleMove(2, 2);

		assertNull("", game.winner);

	}

	// Acceptance criteria 5.3
	@Test
	public void TestDrawGame() {
		board = new Board(3, "simple");
		game = new SimpleMode(board, "human", "human");

		// fill out the cell with all S
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(0, 0);
		game.newBoard.getElement('S');// red turn
		game.simpleMove(0, 1);
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(0, 2);
		game.newBoard.getElement('S');// red turn
		game.simpleMove(1, 0);
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(1, 1);
		game.newBoard.getElement('S');// red turn
		game.simpleMove(1, 2);
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(2, 0);
		game.newBoard.getElement('S');// red turn
		game.simpleMove(2, 1);
		game.newBoard.getElement('S');// blue turn
		game.simpleMove(2, 2);

		assertEquals("", game.winner, "draw");

	}

	// Acceptance criteria 8.1
	@Test
	public void TestAutoSimpleMakeValidMove() {
		board = new Board(5, "simple");
		game = new SimpleMode(board, "human", "human");

		game.newBoard.getElement('S');
		game.simpleMove(3, 3);

		assertEquals("", game.newBoard.turn, "red");
		game.autoSimplePlay();

		assertEquals("", game.newBoard.turn, "blue");
	}

	// Acceptance criteria 8.2
	@Test
	public void TestAutoSimpleMakeInvalidMove() {
		board = new Board(4, "simple");
		game = new SimpleMode(board, "human", "human");

		game.newBoard.getElement('S');
		game.simpleMove(3, 3);// blue turn

		game.autoSimplePlay();// red turn

		game.newBoard.getElement('O');
		game.simpleMove(3, 3);// blue turn

		if (game.newBoard.turn.equals("red"))
			game.autoSimplePlay();

		assertEquals("", game.newBoard.turn, "blue");

	}

	// Acceptance criteria 8.3
	@Test
	public void TestTwoAutoSimplePlayer() {
		board = new Board(7, "simple");
		game = new SimpleMode(board,"human", "human");

		assertNull("", game.winner);

		while (!game.newBoard.isFullBoard())
			game.autoSimplePlay();

		assertNotNull("", game.winner);
	}


}
