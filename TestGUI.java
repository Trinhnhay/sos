package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Production_Code.Board;
import Production_Code.GUI;

class TestGUI {
	Board board;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	// Acceptance criteria 1.1
	@Test
	public void TestValidSize() {
		new GUI(6, "simple");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 1.2
	@Test
	public void TestInvalidSize() {
		GUI gui = new GUI(-7, "simple");
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 2.1
	@Test
	public void TestValidMode() {
		new GUI(6, "simple");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 2.2
	@Test
	public void TestInvalidMode() {
		new GUI(3, "specific");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 3.1
	@Test
	public void TestStartGame() {
		GUI gui = new GUI(7, "simple");
		gui.simpleGameMakeMove(0, 2, 'S');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 3.2
	@Test
	public void TestGameNotStart() {
		GUI gui = new GUI(6, "general");
		gui.generalGameMakeMove(1, 2, ' ');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 4.1
	@Test
	public void TestSimpleValidMove() {
		GUI gui = new GUI(10, "simple");
		gui.simpleGameMakeMove(4, 8, 'S');
		gui.simpleGameMakeMove(2, 2, 'O');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 4.2
	@Test
	public void TestSimpleInvalidMove() {
		GUI gui = new GUI(8, "simple");
		gui.simpleGameMakeMove(4, 2, 'S');// blue turn
		gui.simpleGameMakeMove(4, 2, 'O');// red turn
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 4.3
	@Test
	public void TestSimpleInvalidMoveOutside() {
		GUI gui = new GUI(4, "simple");
		gui.simpleGameMakeMove(5, 2, 'S');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 6.1
	@Test
	public void TestGeneralValidMove() {
		GUI gui = new GUI(10, "general");
		gui.generalGameMakeMove(1, 1, 'O');
		gui.generalGameMakeMove(1, 4, 'O');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Acceptance criteria 6.2
	@Test
	public void TestGeneralInvalidMove() {
		GUI gui =new GUI (8, "general");
		gui.generalGameMakeMove(4,4, 'O');// blue turn
		gui.generalGameMakeMove(4,4, 'S');// red turn
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	// Acceptance criteria 6.3
	@Test
	public void TestGeneralInvalidMoveOutside() {
		GUI gui = new GUI(5, "general");
		gui.generalGameMakeMove(3, 3, 'S');
		gui.generalGameMakeMove(-1, 5, 'S');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 6.4
	@Test
	public void TestGeneralGameMakingSOS() {
		GUI gui = new GUI(5, "general");
		gui.generalGameMakeMove(1, 1, 'S');
		gui.generalGameMakeMove(2, 2, 'O');
		gui.generalGameMakeMove(3, 3, 'S');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 6.5
	@Test
	public void TestGeneralGameNotMakingSOS() {
		GUI gui = new GUI(6, "general");
		gui.generalGameMakeMove(1, 1, 'S');
		gui.generalGameMakeMove(2, 2, 'S');
		gui.generalGameMakeMove(3, 3, 'S');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 5.1
	@Test
	public void TestSimpleGameHavingWinner() {
		GUI gui = new GUI(4, "simple");
		gui.simpleGameMakeMove(1, 1, 'S');// blue turn
		gui.simpleGameMakeMove(0, 2, 'O');// red turn
		gui.simpleGameMakeMove(2, 2, 'O');// blue turn
		gui.simpleGameMakeMove(3, 3, 'S');// red turn, winner
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 5.2
	@Test
	public void TestSimpleGameContinue() {
		GUI gui = new GUI(4, "simple");
		gui.simpleGameMakeMove(1, 1, 'S');// blue turn
		gui.simpleGameMakeMove(0, 2, 'O');// red turn
		gui.simpleGameMakeMove(2, 3, 'S');// blue turn
		gui.simpleGameMakeMove(3, 3, 'S');// red turn
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 5.3
	@Test
	public void TestSimpleDrawGame() {
		GUI gui = new GUI(3, "simple");
		gui.simpleGameMakeMove(0, 0, 'S');// blue turn
		gui.simpleGameMakeMove(0, 1, 'S');// red turn
		gui.simpleGameMakeMove(0, 2, 'S');// blue turn
		gui.simpleGameMakeMove(1, 0, 'S');// red turn
		gui.simpleGameMakeMove(1, 1, 'S');// blue turn
		gui.simpleGameMakeMove(1, 2, 'S');// red turn
		gui.simpleGameMakeMove(2, 0, 'S');// blue turn
		gui.simpleGameMakeMove(2, 1, 'S');// blue turn
		gui.simpleGameMakeMove(2, 2, 'S');// red turn

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 7.1
	@Test
	public void TestGeneralGameHavingWinner() {
		GUI gui = new GUI(3, "general");
		gui.generalGameMakeMove(0, 0, 'S');
		gui.generalGameMakeMove(1, 1, 'O');
		gui.generalGameMakeMove(2, 2, 'S');
		gui.generalGameMakeMove(0, 2, 'S');
		gui.generalGameMakeMove(0, 1, 'O');
		gui.generalGameMakeMove(1, 2, 'O');
		gui.generalGameMakeMove(2, 0, 'S');
		gui.generalGameMakeMove(1, 0, 'O');
		gui.generalGameMakeMove(2, 1, 'O');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 7.2
	@Test
	public void TestGeneralDrawGame1() {
		GUI gui = new GUI(3, "general");
		gui.generalGameMakeMove(0, 0, 'O');
		gui.generalGameMakeMove(0, 1, 'O');
		gui.generalGameMakeMove(0, 2, 'O');
		gui.generalGameMakeMove(1, 0, 'O');
		gui.generalGameMakeMove(1, 1, 'O');
		gui.generalGameMakeMove(1, 2, 'O');
		gui.generalGameMakeMove(2, 0, 'O');
		gui.generalGameMakeMove(2, 1, 'O');
		gui.generalGameMakeMove(2, 2, 'O');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 7.2
	@Test
	public void TestGeneralDrawGame2() {
		GUI gui = new GUI(3, "general");
		gui.generalGameMakeMove(0, 0, 'S');
		gui.generalGameMakeMove(0, 1, 'O');
		gui.generalGameMakeMove(0, 2, 'S');
		gui.generalGameMakeMove(1, 0, 'O');
		gui.generalGameMakeMove(2, 0, 'S');
		gui.generalGameMakeMove(1, 1, 'O');
		gui.generalGameMakeMove(1, 2, 'S');
		gui.generalGameMakeMove(2, 2, 'S');
		gui.generalGameMakeMove(2, 1, 'S');
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 8.1
	@Test
	public void TestAutoSimpleMakeValidMove() {
		GUI gui = new GUI(3, "simple");

		gui.redPlayer = "computer";
		gui.bluePlayer = "human";
		
		gui.simpleGameMakeMove(1, 2, 'S');

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 8.2
	@Test
	public void TestAutoSimpleMakeInvalidMove() {
		GUI gui = new GUI(6, "simple");

		gui.redPlayer = "human";
		gui.bluePlayer = "computer";

		gui.simpleGameMakeMove(1, 2, 'S');
		gui.simpleGameMakeMove(1, 2, 'O');

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 8.3
	@Test
	public void TestAutoTwoPlayerSimple() {
		GUI gui = new GUI(7, "simple");

		gui.redPlayer = "computer";
		gui.bluePlayer = "computer";

		gui.autoSimpleStartGame();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 9.1
	@Test
	public void TestAutoGeneralMakeValidMove() {
		GUI gui = new GUI(5, "general");

		gui.redPlayer = "computer";
		gui.bluePlayer = "human";

		gui.generalGameMakeMove(3, 3, 'O');

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 9.2
	@Test
	public void TestAutoGeneralMakeInvalidMove() {
		GUI gui = new GUI(6, "general");

		gui.redPlayer = "human";
		gui.bluePlayer = "computer";

		gui.generalGameMakeMove(3, 2, 'S');
		gui.generalGameMakeMove(3, 2, 'O');

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Acceptance criteria 9.3
	@Test
	public void TestAutoTwoPlayerGeneral() {
		GUI gui = new GUI(7, "general");

		gui.redPlayer = "computer";
		gui.bluePlayer = "computer";

		gui.autoGeneralStartGame();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
