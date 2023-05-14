package Production_Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.io.IOException;

public class GeneralMode extends Board {

	public Board newBoard = new Board();
	public int blueScore = 0;
	public int redScore = 0;
	public String result;

	public List<Integer> removePosition = new ArrayList<>();
	public int autoMoveNumber = 1;

	public GeneralMode(Board board) {
		newBoard = board;
	}

	public GeneralMode(Board board, String blue, String red) {
		newBoard = board;
		newBoard.initialFile(blue, red);
	}

	public void generalMove(int row, int col) {
		if (row >= 0 && row < newBoard.size && col >= 0 && col < newBoard.size && newBoard.grid[row][col] == ' '
				&& newBoard.element != ' ') {
			newBoard.grid[row][col] = newBoard.element;
			isEndGame(row, col);
			newBoard.getReplayMove(row, col);
		}
	}

	private void isEndGame(int row, int col) {
		if (newBoard.checkSOS(row, col) != 0) {
			if (newBoard.turn.equals("blue"))
				blueScore += newBoard.checkSOS(row, col);
			else if (newBoard.turn.equals("red"))
				redScore += newBoard.checkSOS(row, col);
			recordGame(newBoard.turn, newBoard.element, row, col);
		} else if (newBoard.checkSOS(row, col) == 0) {
			recordGame(newBoard.turn, newBoard.element, row, col);
			if (newBoard.turn.equals("blue")) {
				newBoard.turn = "red";
			} else if (newBoard.turn.equals("red")) {
				newBoard.turn = "blue";
			}
		}
		isHavingWinner();
	}

	public void isHavingWinner() {
		if (newBoard.isFullBoard()) {
			if (blueScore == redScore)
				result = "draw";
			else if (blueScore > redScore)
				result = "blue";
			else if (redScore > blueScore)
				result = "red";
			newBoard.endRecord(result);
		}
	}

	public void AutoGeneralPlay() {
		Random random = new Random();
		do {
			newBoard.row = random.nextInt(newBoard.size);
			newBoard.col = random.nextInt(newBoard.size);
		} while (newBoard.grid[newBoard.row][newBoard.col] != ' ');

		if (newBoard.row >= 0 && newBoard.row < newBoard.size && newBoard.col >= 0 && newBoard.col < newBoard.size
				&& newBoard.grid[newBoard.row][newBoard.col] == ' ') {
			newBoard.grid[newBoard.row][newBoard.col] = newBoard.chooseElement();

			isEndGame(newBoard.row, newBoard.col);
			newBoard.getReplayMove(newBoard.row, newBoard.col);
		}
	}

	public void replayRecord() {
		for (int i = autoMoveNumber; i > 0; i--) {
			String line = newBoard.lineList.elementAt(newBoard.lineList.size() - autoMoveNumber)
					+ String.format("%-20s %-10s", "", "Removed");
			newBoard.lineList.remove(newBoard.lineList.size() - autoMoveNumber);
			newBoard.lineList.add(line);
		}
	}

	private void recordGame(String turn, char element, int row, int col) {
		Vector<Integer> position = newBoard.SOSPosition;
		String line = String.format("\n%-10s %-10s %-20s %-20s %-20s", newBoard.step, turn, element,
				"(" + row + "," + col + ")", blueScore + ":" + redScore);
		if (!position.isEmpty()) {

			for (int i = 0; i < position.size(); i = i + 6) {
				line = line + "(";
				for (int j = 0; j < 6; j = j + 2) {
					line = line + "(" + position.elementAt(i + j) + ",";
					line = line + position.elementAt(i + j + 1) + ")";
				}
				line = line + ") ";
			}
		} else
			line = line + String.format("%-18s", "");
		newBoard.step++;
		newBoard.lineList.add(line);
	}

	public void remove() {

		newBoard.removeCol = newBoard.removePosition.get(newBoard.removePosition.size() - 1);
		newBoard.removePosition.remove(newBoard.removePosition.size() - 1);
		newBoard.removeRow = newBoard.removePosition.get(newBoard.removePosition.size() - 1);
		newBoard.removePosition.remove(newBoard.removePosition.size() - 1);

		if (newBoard.checkSOS(newBoard.removeRow, newBoard.removeCol) != 0) {
			if (newBoard.turn.equals("blue"))
				blueScore -= newBoard.checkSOS(newBoard.removeRow, newBoard.removeCol);
			else if (newBoard.turn.equals("red"))
				redScore -= newBoard.checkSOS(newBoard.removeRow, newBoard.removeCol);
		} else if (newBoard.checkSOS(newBoard.removeRow, newBoard.removeCol) == 0) {
			if (newBoard.turn.equals("blue")) {
				newBoard.turn = "red";
			} else if (newBoard.turn.equals("red")) {
				newBoard.turn = "blue";
			}
		}
		newBoard.grid[newBoard.removeRow][newBoard.removeCol] = ' ';
	}
}
