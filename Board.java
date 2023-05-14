package Production_Code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Board {

	public char element = ' ';
	public char[][] grid;
	public int size;
	public String gameMode = null;
	public String turn;

	public int row;
	public int col;

	public Vector<Integer> SOSPosition;
	public BufferedWriter writer;
	public File file;
	public int step = 1;

	public int removeRow;
	public int removeCol;
	public List<Integer> removePosition = new ArrayList<>();

	public Vector<String> lineList = new Vector();

	public Board() {

		initializeBoard();
		turn = "blue";

	}

	public Board(int boardSize, String mode) {
		getBoardSize(boardSize);
		getGameMode(mode);
		turn = "blue";
		initializeBoard();

	}

	public void getBoardSize(int boardSize) {
		if (boardSize >= 3 && boardSize <= 20) {
			size = boardSize;
		} else
			size = 0;
	}

	public void getGameMode(String mode) {
		if (mode.equals("simple") || mode.equals("general"))
			gameMode = mode;
		else
			gameMode = null;
	}

	public void initializeBoard() {
		grid = new char[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				grid[row][col] = ' ';
			}
		}
	}

	public void getElement(char chess) {
		if (chess == 'S' || chess == 'O')
			element = chess;
		else
			element = ' ';
	}

	public void simpleMakeMove(int row, int col) {
		if (row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == ' ' && element != ' ') {
			grid[row][col] = element;
			if (turn.equals("blue")) {
				turn = "red";
			} else if (turn.equals("red")) {
				turn = "blue";
			}
		}
	}

	public void generalMakeMove(int row, int col) {
		if (row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == ' ' && element != ' ') {
			grid[row][col] = element;
			if (checkSOS(row, col) == 0 && turn.equals("blue")) {
				turn = "red";
			} else if (checkSOS(row, col) == 0 && turn.equals("red")) {
				turn = "blue";
			}
		}
	}

	public int checkSOS(int row, int col) {
		int count = 0;
		SOSPosition = new Vector<Integer>(24);
		if (grid[row][col] == 'O') {
			try {
				if (grid[row][col + 1] == 'S' && grid[row][col - 1] == 'S') {
					count++;
					SOSPosition.add(row);
					SOSPosition.add(col + 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row);
					SOSPosition.add(col - 1);
				}
			} catch (Exception e) {
			}

			try {
				if (grid[row + 1][col] == 'S' && grid[row - 1][col] == 'S') {
					count++;
					SOSPosition.add(row + 1);
					SOSPosition.add(col);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row - 1);
					SOSPosition.add(col);
				}
			} catch (Exception e) {
			}

			try {
				if (grid[row - 1][col - 1] == 'S' && grid[row + 1][col + 1] == 'S') {
					count++;
					SOSPosition.add(row - 1);
					SOSPosition.add(col - 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row + 1);
					SOSPosition.add(col + 1);
				}
			} catch (Exception e) {
			}
			try {
				if (grid[row + 1][col - 1] == 'S' && grid[row - 1][col + 1] == 'S') {
					count++;
					SOSPosition.add(row + 1);
					SOSPosition.add(col - 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row - 1);
					SOSPosition.add(col + 1);
				}
			} catch (Exception e) {
			}
		} else if (grid[row][col] == 'S') {
			try {
				if (grid[row][col + 1] == 'O' && grid[row][col + 2] == 'S') {
					count++;
					SOSPosition.add(row);
					SOSPosition.add(col + 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row);
					SOSPosition.add(col + 2);
				}
			} catch (Exception e) {
			}

			try {
				if (grid[row][col - 1] == 'O' && grid[row][col - 2] == 'S') {
					count++;
					SOSPosition.add(row);
					SOSPosition.add(col - 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row);
					SOSPosition.add(col - 2);
				}
			} catch (Exception e) {
			}
			try {
				if (grid[row - 1][col] == 'O' && grid[row - 2][col] == 'S') {
					count++;
					SOSPosition.add(row - 1);
					SOSPosition.add(col);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row - 2);
					SOSPosition.add(col);
				}
			} catch (Exception e) {
			}
			try {
				if (grid[row + 1][col] == 'O' && grid[row + 2][col] == 'S') {
					count++;
					SOSPosition.add(row + 1);
					SOSPosition.add(col);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row + 2);
					SOSPosition.add(col);
				}
			} catch (Exception e) {
			}
			try {
				if (grid[row + 1][col + 1] == 'O' && grid[row + 2][col + 2] == 'S') {
					count++;
					SOSPosition.add(row + 1);
					SOSPosition.add(col + 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row + 2);
					SOSPosition.add(col + 2);
				}
			} catch (Exception e) {
			}
			try {
				if (grid[row - 1][col - 1] == 'O' && grid[row - 2][col - 2] == 'S') {
					count++;
					SOSPosition.add(row - 1);
					SOSPosition.add(col - 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row - 2);
					SOSPosition.add(col - 2);
				}
			} catch (Exception e) {
			}
			try {
				if (grid[row - 1][col + 1] == 'O' && grid[row - 2][col + 2] == 'S') {
					count++;
					SOSPosition.add(row - 1);
					SOSPosition.add(col + 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row - 2);
					SOSPosition.add(col + 2);
				}
			} catch (Exception e) {
			}
			try {
				if (grid[row + 1][col - 1] == 'O' && grid[row + 2][col - 2] == 'S') {
					count++;
					SOSPosition.add(row + 1);
					SOSPosition.add(col - 1);
					SOSPosition.add(row);
					SOSPosition.add(col);
					SOSPosition.add(row + 2);
					SOSPosition.add(col - 2);
				}
			} catch (Exception e) {
			}

		}
		return count;
	}

	public boolean isFullBoard() {
		boolean flag = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (grid[i][j] == ' ') {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	public char chooseElement() {
		Random random = new Random();
		int num = random.nextInt(100);
		if (num % 2 == 0)
			element = 'O';
		else
			element = 'S';
		return element;
	}

	public void initialFile(String blue, String red) {
		try {
			file = new File("game record.txt");
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("Game mode: " + gameMode + "\nSize: " + size);
			writer.write("\nBlue player is " + blue);
			writer.write("\nRed player is " + red);
			if (gameMode.equals("simple"))
				writer.write(
						String.format("\n\n%-10s %-10s %-20s %-20s %-20s", "Move", "Turn", "S/O", "Position", "SOS"));
			else
				writer.write(String.format("\n\n%-10s %-10s %-20s %-20s %-20s %-20s", "Move", "Turn", "S/O", "Position",
						"Blue:Red(score)", "SOS"));

		} catch (IOException e) {
		}
	}

	public void endRecord(String winner) {
		try {
			for (int i = 0; i < lineList.size(); i++) {
				writer.write(lineList.elementAt(i));
			}
			if (winner.equals("draw"))
				writer.write("\n\nResult: The game is draw");
			else
				writer.write("\n\nResult: The winner is " + winner);
			writer.close();
		} catch (IOException e) {
		}
	}

	public void getReplayMove(int row, int col) {
		removePosition.add(row);
		removePosition.add(col);
	}

}
