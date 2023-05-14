package Test;

import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Production_Code.Board;

class TestBoard {
	private static Board board;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		board = new Board();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	// Acceptance criteria 1.1
	@Test
	public void TestValidSize() {
		board.getBoardSize(10);
		board.initializeBoard();
		for (int row = 0; row < board.size; row++) {
			for (int col = 0; col < board.size; col++) {
				assertEquals("", board.grid[row][col], ' ');
			}
		}
	}

	// Acceptance criteria 1.2
	@Test
	public void TestInvalidSize() {
		// size >20
		board.getBoardSize(23);
		assertEquals("", board.size, 0);

		// size <3
		board.getBoardSize(2);
		assertEquals("", board.size, 0);
	}

	// Acceptance criteria 2.1
	@Test
	public void TestValidMode() {
		board.getGameMode("simple");
		assertEquals("", board.gameMode, "simple");
	}

	// Acceptance criteria 2.2 (no game mode is chose)
	@Test
	public void TestInvalidMode() {

		board.getGameMode("");
		assertNull("", board.gameMode);
	}

	// Acceptance criteria 2.2 (not simple or general)
	@Test
	public void TestInvalidMode2() {

		board.getGameMode("special");
		assertNull("", board.gameMode);
	}

	// Acceptance criteria 3.1 (success start simple game)
	@Test
	public void TestStartGame() {
		board = new Board(7, "simple");
		board.getElement('S');
		board.simpleMakeMove(4, 3);

		assertEquals("", board.grid[4][3], 'S');
	}

	// Acceptance criteria 3.1 (success start general game)
	@Test
	public void TestStartGame2() {
		board = new Board(7, "general");
		board.getElement('O');
		board.generalMakeMove(6, 3);

		assertNotEquals("", board.grid[6][3], 'S');
	}

	// Acceptance criteria 3.2
	@Test
	public void TestGameNotStart() {
		board = new Board(7, "simple");
		board.getElement('Y');
		board.simpleMakeMove(2, 2);

		assertEquals("", board.grid[2][2], ' ');
	}

	// Acceptance criteria 3.2
	@Test
	public void TestGameNotStart2() {
		board = new Board(7, "simple");
		board.getElement(' ');
		board.simpleMakeMove(2, 6);

		assertEquals("", board.grid[2][6], ' ');
		assertEquals("", board.turn, "blue");
	}

	// Acceptance criteria 4.1
	@Test
	public void TestSimpleValidMove() {
		board = new Board(10, "simple");
		board.getElement('S'); // blue turn
		board.simpleMakeMove(4, 2);

		board.getElement('S'); // red turn
		board.simpleMakeMove(7, 1);

		assertNotEquals("", board.grid[4][2], 'O');
		assertEquals("", board.grid[7][1], 'S');
		assertEquals("", board.turn, "blue");

	}

	// Acceptance criteria 4.2
	@Test
	public void TestSimpleInValidMove() {
		board = new Board(10, "simple");
		board.getElement('S'); // blue turn
		board.simpleMakeMove(4, 2);

		board.getElement('O'); // red turn
		board.simpleMakeMove(4, 2);

		assertEquals("", board.grid[4][2], 'S');
		assertEquals("", board.turn, "red");

	}

	// Acceptance criteria 4.3
	@Test
	public void TestSimpleInvalidMoveOutside() {
		board = new Board(10, "simple");
		board.getElement('S'); // blue turn
		board.simpleMakeMove(10, 2);

		assertEquals("", board.turn, "blue");
	}

	// Acceptance criteria 4.3
	@Test
	public void TestSimpleInvalidMoveOutside2() {
		board = new Board(4, "simple");
		board.getElement('S'); // blue turn
		board.simpleMakeMove(-1, 2);

		assertEquals("", board.turn, "blue");
	}

	// Acceptance criteria 6.1
	@Test
	public void TestGeneralValidMove() {
		board = new Board(3, "general");
		board.getElement('O'); // blue turn
		board.generalMakeMove(0, 2);

		board.getElement('S');
		board.generalMakeMove(1, 1);

		assertEquals("", board.grid[0][2], 'O');
		assertEquals("", board.grid[1][1], 'S');
		assertEquals("", board.turn, "blue");
	}

	// Acceptance criteria 6.2
	@Test
	public void TestGeneralInValidMove() {
		board = new Board(4, "general");
		board.getElement('O'); // blue turn
		board.generalMakeMove(0, 2);

		board.getElement('S');// red turn
		board.generalMakeMove(0, 2);

		assertEquals("", board.grid[0][2], 'O');
		assertEquals("", board.turn, "red");
	}

	// Acceptance criteria 6.3
	@Test
	public void TestGeneralInvalidMoveOutside() {
		board = new Board(4, "general");
		board.getElement('O'); // blue turn
		board.generalMakeMove(0, 5);
		assertEquals("", board.turn, "blue");

		board.getElement('S');
		board.generalMakeMove(6, 2);
		assertEquals("", board.turn, "blue");
	}
}
