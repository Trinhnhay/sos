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

public class TestGeneralMode {

	public static Board board;
	public static GeneralMode game;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		board = new Board();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	// Acceptance criteria 6.4
	@Test
	public void TestMakingSOS() {
		board = new Board(4, "general");
		game = new GeneralMode(board, "human", "human");

		// start with zero score for both
		assertEquals("", game.blueScore, 0);
		assertEquals("", game.redScore, 0);

		// when blue get a score and the turn is not changed
		game.newBoard.getElement('S');// blue turn
		game.generalMove(1, 1);
		game.newBoard.getElement('O');// red turn
		game.generalMove(1, 2);
		game.newBoard.getElement('S'); // blue gets score
		game.generalMove(1, 3);

		assertEquals("", game.blueScore, 1);
		assertEquals("", game.redScore, 0);
		assertEquals("", game.newBoard.turn, "blue");

	}

	// Acceptance criteria 6.5
	@Test
	public void TestNotMakingSOS() {
		board = new Board(6, "general");
		game = new GeneralMode(board, "human", "human");

		assertEquals("", game.blueScore, 0);
		assertEquals("", game.redScore, 0);

		// make move
		assertEquals("", game.newBoard.turn, "blue");
		game.newBoard.getElement('S');// blue turn
		game.generalMove(0, 1);

		assertEquals("", game.newBoard.turn, "red");
		game.newBoard.getElement('O');// red turn
		game.generalMove(1, 2);

		assertEquals("", game.newBoard.turn, "blue");
		game.newBoard.getElement('S'); // blue gets score
		game.generalMove(5, 5);

		// no score is made
		assertEquals("", game.blueScore, 0);
		assertEquals("", game.redScore, 0);
		assertEquals("", game.newBoard.turn, "red");

	}

	// Acceptance criteria 7.1
	@Test
	public void TestHavingWinner() {

		board = new Board(3, "general");
		game = new GeneralMode(board, "human", "human");

		assertEquals("", game.blueScore, 0);
		assertEquals("", game.redScore, 0);

		// make move
		game.newBoard.getElement('S');// blue turn
		game.generalMove(0, 0);

		game.newBoard.getElement('O');// red turn
		game.generalMove(1, 1);

		game.newBoard.getElement('S'); // blue turn, set score and continue
		game.generalMove(2, 2);

		assertEquals("", game.blueScore, 1);

		game.newBoard.getElement('S'); // blue turn
		game.generalMove(0, 2);

		game.newBoard.getElement('O');// red turn, get score
		game.generalMove(0, 1);

		game.newBoard.getElement('O');// red turn, get score
		game.generalMove(1, 2);

		game.newBoard.getElement('S');// red turn, get Score
		game.generalMove(2, 0);

		game.newBoard.getElement('O');// red turn, get Score
		game.generalMove(1, 0);

		game.newBoard.getElement('O');// red turn, get Score
		game.generalMove(2, 1);

		game.isHavingWinner();

		assertEquals("", game.blueScore, 1);
		assertEquals("", game.redScore, 5);
		assertEquals("", game.result, "red");

	}

	// Acceptance criteria 7.2 (no one have score)
	@Test
	public void TestDrawGame() {

		board = new Board(3, "general");
		game = new GeneralMode(board, "human", "human");

		assertEquals("", game.blueScore, 0);
		assertEquals("", game.redScore, 0);

		// make "S" on all cells
		game.newBoard.getElement('S');// blue turn
		game.generalMove(0, 0);

		game.newBoard.getElement('S');// red turn
		game.generalMove(0, 1);

		game.newBoard.getElement('S');// blue turn
		game.generalMove(0, 2);

		game.newBoard.getElement('S');// red turn
		game.generalMove(1, 0);

		game.newBoard.getElement('S');// blue turn
		game.generalMove(1, 1);

		game.newBoard.getElement('S');// red turn
		game.generalMove(1, 2);

		game.newBoard.getElement('S');// blue turn
		game.generalMove(2, 0);
		game.newBoard.getElement('S');// red turn
		game.generalMove(2, 1);
		game.newBoard.getElement('S');// blue turn
		game.generalMove(2, 2);

		game.isHavingWinner();

		assertEquals("", game.blueScore, 0);
		assertEquals("", game.redScore, 0);
		assertEquals("", game.result, "draw");

	}

	// Acceptance criteria 7.2 (both have same score)
	@Test
	public void TestDrawGame2() {

		board = new Board(3, "general");
		game = new GeneralMode(board, "human", "human");

		assertEquals("", game.blueScore, 0);
		assertEquals("", game.redScore, 0);

		game.newBoard.getElement('S');// blue turn
		game.generalMove(0, 0);

		game.newBoard.getElement('O');// red turn
		game.generalMove(0, 1);

		game.newBoard.getElement('S');// blue turn, get score
		game.generalMove(0, 2);

		game.newBoard.getElement('O');// blue turn
		game.generalMove(1, 0);

		game.newBoard.getElement('S');// red turn, set score
		game.generalMove(2, 0);

		game.newBoard.getElement('O');// red turn, get score
		game.generalMove(1, 1);

		game.newBoard.getElement('S');// red turn
		game.generalMove(1, 2);

		game.newBoard.getElement('S');// blue turn, get score
		game.generalMove(2, 2);

		game.newBoard.getElement('S');// blue turn
		game.generalMove(2, 1);

		game.isHavingWinner();

		assertEquals("", game.blueScore, 2);
		assertEquals("", game.redScore, 2);
		assertEquals("", game.result, "draw");

	}

	// Acceptance criteria 9.1
	@Test
	public void TestAutoGeneralMakeValidMove() {
		board = new Board(5, "general");
		game = new GeneralMode(board, "human", "human");

		game.newBoard.getElement('S');
		game.generalMove(3, 0);

		assertEquals("", game.newBoard.turn, "red");
		game.AutoGeneralPlay();

		assertEquals("", game.newBoard.turn, "blue");
	}

	// Acceptance criteria 9.2
	@Test
	public void TestAutoGeneralMakeInvalidMove()  throws IOException {
		board = new Board(4, "general");
		game = new GeneralMode(board, "human", "human");
		
		game.newBoard.getElement('S');
		game.generalMove(3, 3);// blue turn

		game.AutoGeneralPlay();// red turn

		game.newBoard.getElement('O');
		game.generalMove(3, 3);// blue turn

		if (game.newBoard.turn.equals("red"))
			game.AutoGeneralPlay();
		
		assertEquals("", game.newBoard.turn, "blue");

	}

	// Acceptance criteria 9.3
	@Test
	public void TestAutoGeneralakeMove() {
		board = new Board(7, "general");
		game = new GeneralMode(board, "human", "human");

		while (!game.newBoard.isFullBoard())
			game.AutoGeneralPlay();

		game.isHavingWinner();

		assertNotNull("", game.result);

	}



}
