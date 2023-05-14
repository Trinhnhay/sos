package Production_Code;

import java.util.Random;

public class SimpleMode extends Board {

	public Board newBoard = new Board();
	public String winner;

	public SimpleMode(Board board) {
		newBoard = board;
	}

	public SimpleMode(Board board, String blue, String red) {
		newBoard = board;
		newBoard.initialFile(blue, red);
	}

	public void simpleMove(int row, int col) {
		if (row >= 0 && row < newBoard.size && col >= 0 && col < newBoard.size && newBoard.grid[row][col] == ' '
				&& newBoard.element != ' ') {
			newBoard.grid[row][col] = newBoard.element;
			doesEndGame(row, col);
			newBoard.getReplayMove(row, col);
		}
	}

	private void doesEndGame(int row, int col) {
		if (newBoard.turn.equals("blue")) {
			if (newBoard.checkSOS(row, col) == 0) {
				recordGame(newBoard.turn, newBoard.element, row, col);
				if (newBoard.isFullBoard()) {
					winner = "draw";
					newBoard.endRecord(winner);
				} else
					newBoard.turn = "red";
			} else if (newBoard.checkSOS(row, col) != 0) {
				recordGame(newBoard.turn, newBoard.element, row, col);
				winner = "blue";
				newBoard.endRecord(winner);
			}
		} else if (newBoard.turn.equals("red")) {
			if (newBoard.checkSOS(row, col) == 0) {
				recordGame(newBoard.turn, newBoard.element, row, col);
				if (newBoard.isFullBoard()) {
					winner = "draw";
					newBoard.endRecord(winner);
				} else {
					newBoard.turn = "blue";
				}
			} else if (newBoard.checkSOS(row, col) != 0) {
				recordGame(newBoard.turn, newBoard.element, row, col);
				winner = "red";
				newBoard.endRecord(winner);
			}
		}
	}

	public void autoSimplePlay() {
		Random random = new Random();
		do {
			newBoard.row = random.nextInt(newBoard.size);
			newBoard.col = random.nextInt(newBoard.size);
		} while (newBoard.grid[newBoard.row][newBoard.col] != ' ');

		if (newBoard.row >= 0 && newBoard.row < newBoard.size && newBoard.col >= 0 && newBoard.col < newBoard.size
				&& newBoard.grid[newBoard.row][newBoard.col] == ' ') {
			newBoard.grid[newBoard.row][newBoard.col] = newBoard.chooseElement();
			doesEndGame(newBoard.row, newBoard.col);
			newBoard.getReplayMove(newBoard.row, newBoard.col);
		}
	}

	public void replayRecord() {
		String line = newBoard.lineList.elementAt(newBoard.lineList.size() - 1)
				+ String.format("%-20s %-20s", " ", "Removed");
		newBoard.lineList.remove(newBoard.lineList.size() - 1);
		newBoard.lineList.add(line);
	}

	private void recordGame(String turn, char element, int row, int col) {

		String line = String.format("\n%-10s %-10s %-20s %-20s", newBoard.step + ".", turn, element,
				"(" + row + "," + col + ")");
		while (!newBoard.SOSPosition.isEmpty()) {
			line = line + "(";
			for (int i = 0; i <= 2; i++) {
				line = line + "(" + newBoard.SOSPosition.elementAt(0) + ",";
				line = line + newBoard.SOSPosition.elementAt(1) + ")";
				newBoard.SOSPosition.remove(1);
				newBoard.SOSPosition.remove(0);
			}
			line = line + ")";
		}
		newBoard.lineList.add(line);
		newBoard.step++;
	}

	public void remove() {
		newBoard.removeCol = newBoard.removePosition.get(newBoard.removePosition.size() - 1);
		newBoard.removePosition.remove(newBoard.removePosition.size() - 1);
		newBoard.removeRow = newBoard.removePosition.get(newBoard.removePosition.size() - 1);
		newBoard.removePosition.remove(newBoard.removePosition.size() - 1);

		if (newBoard.removeRow >= 0 && newBoard.removeRow < newBoard.size && newBoard.removeCol >= 0
				&& newBoard.removeCol < newBoard.size) {
			newBoard.grid[newBoard.removeRow][newBoard.removeCol] = ' ';
			if (newBoard.turn.equals("blue")) {
				newBoard.turn = "red";
			} else if (newBoard.turn.equals("red")) {
				newBoard.turn = "blue";
			}
			replayRecord();
		}
	}

}
