package Production_Code;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends JFrame {

	private Board board;
	private static final String LOGO = "sos.jpg";

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 500;
	private static final int CELL_SIZE = 35;

	private JButton[][] cell;
	private JTextField size;

	private JPanel panelMid = new JPanel();
	private JPanel panelBottom = new JPanel();

	private char element = ' ';

	private SimpleMode simpleGame;
	private GeneralMode generalGame;

	private JRadioButton SRed = new JRadioButton("S");
	private JRadioButton SBlue = new JRadioButton("S");
	private JRadioButton ORed = new JRadioButton("O");
	private JRadioButton OBlue = new JRadioButton("O");
	private ButtonGroup group = new ButtonGroup();

	private JRadioButton blueHuman = new JRadioButton("Human");
	private JRadioButton redHuman = new JRadioButton("Human");
	private JRadioButton blueComputer = new JRadioButton("Computer");
	private JRadioButton redComputer = new JRadioButton("Computer");

	public String bluePlayer;
	public String redPlayer;

	private Boolean replay = true;

	private JCheckBox record = new JCheckBox("Record game");

	public GUI(Board board) {

		this.board = board;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("SOS");
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		createPanel();
		pack();
	}

	public GUI(int size, String mode) {

		this.board = new Board(size, mode);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("SOS");
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		createPanel();
		displayBoard();
		pack();
	}

	private void createPanel() {
		JPanel panelTop = new JPanel();
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();

		panelLeft.setBackground(Color.WHITE);
		panelRight.setBackground(Color.WHITE);
		panelTop.setBackground(Color.WHITE);
		panelMid.setBackground(Color.WHITE);
		panelBottom.setBackground(Color.WHITE);

		panelTop.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 7));
		panelLeft.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT - FRAME_WIDTH / 7));
		panelRight.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT - FRAME_WIDTH / 7));
		panelMid.setPreferredSize(new Dimension(480, 430));
		panelMid.setLayout(new BorderLayout());
		panelBottom.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 10));
		panelBottom.setLayout(new BorderLayout());

		this.add(panelLeft, BorderLayout.WEST);
		this.add(panelRight, BorderLayout.EAST);
		this.add(panelTop, BorderLayout.NORTH);
		this.add(panelMid, BorderLayout.CENTER);
		this.add(panelBottom, BorderLayout.SOUTH);

		panelTop.setLayout(new BorderLayout());
		panelTop.add(new JLabel(iconImage()), BorderLayout.WEST);
		panelTop.add(modeAndSizePanel(), BorderLayout.CENTER);

		panelLeft.setLayout(new BorderLayout());
		panelLeft.add(bluePlayer(), BorderLayout.CENTER);
		panelLeft.add(recordGame(), BorderLayout.SOUTH);

		panelRight.setLayout(new BorderLayout());
		panelRight.add(startAndReplayPanel(), BorderLayout.SOUTH);
		panelRight.add(redPlayer(), BorderLayout.CENTER);

	}

	private JPanel recordGame() {
		JPanel recordPanel = new JPanel();
		recordPanel.setBackground(Color.WHITE);

		record.setBackground(Color.WHITE);
		record.doClick();
		recordPanel.add(record);

		return recordPanel;
	}

	private ImageIcon iconImage() {
		return new ImageIcon(getClass().getResource(LOGO));
	}

	private void playerPanel(JPanel subPanel, String player, JRadioButton human, JRadioButton S, JRadioButton O,
			JRadioButton computer) {

		subPanel.setBackground(Color.WHITE);
		subPanel.setPreferredSize(new Dimension(100, 100));
		subPanel.setLayout(null);

		JLabel playerLabel = new JLabel(player);
		playerLabel.setBounds(30, 70, 70, 20);
		playerLabel.setForeground(Color.BLACK);
		playerLabel.setPreferredSize(new Dimension(100, 50));
		subPanel.add(playerLabel);

		human.setBounds(20, 110, 70, 20);
		human.setBackground(Color.WHITE);
		human.setForeground(Color.BLACK);
		human.addActionListener(new playerTypeListener());
		human.doClick();
		subPanel.add(human);

		S.setBounds(40, 130, 50, 20);
		S.setBackground(Color.WHITE);
		S.setForeground(Color.BLACK);
		S.addActionListener(new chessListener());
		S.doClick();
		subPanel.add(S);

		O.setBounds(40, 150, 50, 20);
		O.setBackground(Color.WHITE);
		O.setForeground(Color.BLACK);
		O.addActionListener(new chessListener());
		subPanel.add(O);

		computer.setBounds(20, 170, 100, 20);
		computer.setBackground(Color.WHITE);
		computer.setForeground(Color.BLACK);
		computer.addActionListener(new playerTypeListener());
		subPanel.add(computer);

		ButtonGroup group = new ButtonGroup();
		group.add(computer);
		group.add(human);

	}

	private JPanel bluePlayer() {

		JPanel subPanel = new JPanel();
		playerPanel(subPanel, "Blue player", blueHuman, SBlue, OBlue, blueComputer);

		group.add(OBlue);
		group.add(SBlue);

		return subPanel;
	}

	private JPanel redPlayer() {
		JPanel subPanel = new JPanel();
		playerPanel(subPanel, "Red player", redHuman, SRed, ORed, redComputer);

		group.add(ORed);
		group.add(SRed);

		return subPanel;
	}

	private JPanel modeAndSizePanel() {

		JPanel subTopPanel = new JPanel();
		subTopPanel.setBackground(Color.WHITE);
		subTopPanel.setLayout(new BorderLayout());

		JPanel subLeftPanel = new JPanel();
		subLeftPanel.setBackground(Color.WHITE);
		subLeftPanel.setLayout(new BorderLayout());

		JRadioButton simpleButton = new JRadioButton("Simple game");
		simpleButton.setBackground(Color.WHITE);
		simpleButton.setForeground(Color.BLACK);
		subLeftPanel.add(simpleButton, BorderLayout.WEST);
		simpleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.getGameMode("simple");
			}
		});

		JRadioButton generalButton = new JRadioButton("General game");
		generalButton.setBackground(Color.WHITE);
		generalButton.setForeground(Color.BLACK);
		;
		subLeftPanel.add(generalButton, BorderLayout.EAST);
		generalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.getGameMode("general");
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(simpleButton);
		group.add(generalButton);

		subTopPanel.add(subLeftPanel, BorderLayout.WEST);

		JPanel subRightPanel = new JPanel();
		subRightPanel.setBackground(Color.WHITE);
		subRightPanel.setPreferredSize(new Dimension(300, 70));
		subRightPanel.setLayout(null);

		JLabel boardSize = new JLabel("Board size: ");
		boardSize.setBounds(5, 15, 80, 50);
		boardSize.setForeground(Color.BLACK);
		subRightPanel.add(boardSize, BorderLayout.WEST);

		size = new JTextField();
		size.setBounds(80, 25, 100, 30);
		subRightPanel.add(size);

		subTopPanel.add(subRightPanel, BorderLayout.EAST);

		return subTopPanel;
	}

	private JPanel startAndReplayPanel() {

		JPanel subRightPanel = new JPanel();
		subRightPanel.setBackground(Color.white);
		subRightPanel.setPreferredSize(new Dimension(150, 80));

		JButton ReplayButton = new JButton("Replay");
		ReplayButton.setBackground(new Color(255, 250, 250));
		ReplayButton.setPreferredSize(new Dimension(100, 30));
		ReplayButton.addActionListener(new ButtonListener());

		JButton StartButton = new JButton("Start");
		StartButton.setBackground(new Color(255, 250, 250));
		StartButton.setPreferredSize(new Dimension(100, 30));
		StartButton.addActionListener(new ButtonListener());
		StartButton.setFocusable(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		subRightPanel.add(StartButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		subRightPanel.add(ReplayButton, gbc);

		return subRightPanel;
	}

	private void addBoardPanel(Board board) {

		JPanel boardPanel = new JPanel();
		boardPanel.setBackground(Color.BLACK);
		boardPanel.setPreferredSize(new Dimension(board.size * CELL_SIZE, board.size * CELL_SIZE));
		boardPanel.setLayout(new GridLayout(board.size, board.size));

		cell = new JButton[board.size][board.size];
		for (int i = 0; i < board.size; i++) {
			for (int j = 0; j < board.size; j++) {
				cell[i][j] = new JButton();
				boardPanel.add(cell[i][j]);
				cell[i][j].setPreferredSize(new Dimension(35, 35));
				cell[i][j].addActionListener(new CellListener());
				cell[i][j].setBackground(new Color(255, 250, 250));
			}
		}

		panelMid.add(boardPanel, BorderLayout.CENTER);
		JLabel message = new JLabel("Playing " + board.gameMode + " game....\n");
		panelMid.add(message, BorderLayout.NORTH);
		panelMid.setVisible(true);
	}

	private void displayBoard() {

		panelMid.setVisible(false);
		if (board.size != 0 && board.gameMode != null) {
			board = new Board(board.size, board.gameMode);
			addBoardPanel(board);
			if (board.gameMode == "simple") {
				simpleGame = new SimpleMode(board, bluePlayer, redPlayer);
				autoSimpleStartGame();
			} else if (board.gameMode == "general") {
				generalGame = new GeneralMode(board, bluePlayer, redPlayer);
				autoGeneralStartGame();
			}
		}
	}

	private void displayTurn(String turn) {

		JLabel message = new JLabel("It's currently " + turn + "'s turn ");

		JPanel turnPanel = new JPanel();
		turnPanel.setBackground(Color.WHITE);
		turnPanel.add(message);

		panelBottom.add(turnPanel, BorderLayout.CENTER);
		panelBottom.setVisible(true);
	}

	public void simpleGameMakeMove(int row, int col, char element) {
		replay = true;
		if (row >= 0 && row < simpleGame.newBoard.size && col >= 0 && col < simpleGame.newBoard.size
				&& simpleGame.newBoard.grid[row][col] == ' ') {
			if (simpleGame.newBoard.turn == "blue")
				cell[row][col].setForeground(Color.BLUE);
			if (simpleGame.newBoard.turn == "red")
				cell[row][col].setForeground(Color.RED);

			simpleGame.newBoard.getElement(element);
			simpleGame.simpleMove(row, col);

			cell[row][col].setText(String.valueOf(simpleGame.newBoard.grid[row][col]));
			simpleGameEnd(row, col, simpleGame);

			if ((redPlayer.equals("computer") || bluePlayer.equals("computer"))
					&& simpleGame.newBoard.checkSOS(row, col) == 0)
				displayAutoSimpleMove();
		}
	}

	public void autoSimpleStartGame() {

		if (bluePlayer.equals("computer")) {
			displayAutoSimpleMove();
		}
		if (redPlayer.equals("computer") && bluePlayer.equals("computer")) {
			do {
				displayAutoSimpleMove();
			} while (simpleGame.winner == null);
		}
	}

	private void simpleGameEnd(int row, int col, SimpleMode game) {

		if (game.newBoard.checkSOS(row, col) != 0) {
			displaySimpleSOS();
			JOptionPane.showMessageDialog(null, game.winner + " is the winner !!!");
			replay = false;
		} else if (game.newBoard.isFullBoard()) {
			JOptionPane.showMessageDialog(null, "The game is draw");
			replay = false;
		} else {
			displayTurn(game.newBoard.turn);
		}
	}

	private void displaySimpleSOS() {
		int row, col;
		while (!simpleGame.newBoard.SOSPosition.isEmpty()) {
			row = simpleGame.newBoard.SOSPosition.elementAt(0);
			col = simpleGame.newBoard.SOSPosition.elementAt(1);
			simpleGame.newBoard.SOSPosition.remove(1);
			simpleGame.newBoard.SOSPosition.remove(0);
			if (simpleGame.winner.equals("red"))
				cell[row][col].setBackground(new Color(255, 204, 204));
			else
				cell[row][col].setBackground(new Color(204, 255, 255));
		}
	}

	private void displayGeneralSOS() {
		int row, col;
		while (!generalGame.newBoard.SOSPosition.isEmpty()) {
			row = generalGame.newBoard.SOSPosition.elementAt(0);
			col = generalGame.newBoard.SOSPosition.elementAt(1);
			generalGame.newBoard.SOSPosition.remove(1);
			generalGame.newBoard.SOSPosition.remove(0);
			if (generalGame.newBoard.turn.equals("blue"))
				cell[row][col].setBackground(new Color(204, 255, 255));
			else
				cell[row][col].setBackground(new Color(255, 204, 204));
		}
	}

	private void autoSimpleMakeMove(SimpleMode game, Color color) {
		game.autoSimplePlay();
		if (game.newBoard.row >= 0 && game.newBoard.row < game.newBoard.size && game.newBoard.col >= 0
				&& game.newBoard.col < game.newBoard.size) {
			cell[game.newBoard.row][game.newBoard.col].setForeground(color);
			cell[game.newBoard.row][game.newBoard.col]
					.setText(String.valueOf(game.newBoard.grid[game.newBoard.row][game.newBoard.col]));
		}
	}

	private void displayAutoSimpleMove() {
		if (simpleGame.newBoard.turn == "red")
			autoSimpleMakeMove(simpleGame, Color.RED);
		else if (simpleGame.newBoard.turn == "blue")
			autoSimpleMakeMove(simpleGame, Color.BLUE);
		simpleGameEnd(simpleGame.newBoard.row, simpleGame.newBoard.col, simpleGame);
	}

	public void generalGameMakeMove(int row, int col, char element) {
		replay = true;
		generalGame.newBoard.getElement(element);
		if (row >= 0 && row < generalGame.newBoard.size && col >= 0 && col < generalGame.newBoard.size
				&& generalGame.newBoard.grid[row][col] == ' ' && generalGame.newBoard.element != ' ') {
			if (generalGame.newBoard.turn == "blue")
				cell[row][col].setForeground(Color.BLUE);
			if (generalGame.newBoard.turn == "red")
				cell[row][col].setForeground(Color.RED);

			generalGame.generalMove(row, col);

			cell[row][col].setText(String.valueOf(generalGame.newBoard.grid[row][col]));
			displayGeneralScore(generalGame);
			displayGeneralSOS();
			generalGame.autoMoveNumber = 1;
			generalGameEnd(generalGame);

			if ((redPlayer.equals("computer") || bluePlayer.equals("computer"))
					&& generalGame.newBoard.checkSOS(row, col) == 0) {
				do {
					generalGame.autoMoveNumber++;
					autoPlayingGeneral();
				} while (generalGame.newBoard.checkSOS(generalGame.newBoard.row, generalGame.newBoard.col) != 0
						&& !generalGame.newBoard.isFullBoard());
			}
		}
	}

	public void autoGeneralStartGame() {

		if (bluePlayer.equals("computer")) {
			autoPlayingGeneral();
		}
		if (redPlayer.equals("computer") && bluePlayer.equals("computer")) {
			do {
				autoPlayingGeneral();
			} while (!generalGame.newBoard.isFullBoard());
		}
	}

	private void displayGeneralScore(GeneralMode game) {

		JLabel redScore = new JLabel();
		JLabel blueScore = new JLabel();

		JPanel bluePanel = new JPanel();
		JPanel redPanel = new JPanel();

		redPanel.setBackground(Color.WHITE);
		bluePanel.setBackground(Color.WHITE);

		redPanel.add(redScore);
		bluePanel.add(blueScore);

		redScore.setText("Red's socre " + game.redScore);
		blueScore.setText("Blue's socre " + game.blueScore);

		panelBottom.add(bluePanel, BorderLayout.WEST);
		panelBottom.add(redPanel, BorderLayout.EAST);
		displayTurn(game.newBoard.turn);
	}

	private void generalGameEnd(GeneralMode game) {
		game.isHavingWinner();
		if (game.result != null) {
			replay = false;
			if (game.result.equals("draw"))
				JOptionPane.showMessageDialog(null, "The game is draw");
			if (game.result.equals("red"))
				JOptionPane.showMessageDialog(null, "Red is winner");
			if (game.result.equals("blue"))
				JOptionPane.showMessageDialog(null, "Blue is winner");
		}
	}

	private void autoGeneralMakeMode(GeneralMode game, Color color) {
		game.AutoGeneralPlay();
		if (game.newBoard.row >= 0 && game.newBoard.row < game.newBoard.size && game.newBoard.col >= 0
				&& game.newBoard.col < game.newBoard.size) {
			cell[game.newBoard.row][game.newBoard.col].setForeground(color);
			cell[game.newBoard.row][game.newBoard.col]
					.setText(String.valueOf(game.newBoard.grid[game.newBoard.row][game.newBoard.col]));
		}
	}

	private void autoPlayingGeneral() {
		if (generalGame.newBoard.turn == "red") {
			autoGeneralMakeMode(generalGame, Color.RED);
		} else if (generalGame.newBoard.turn == "blue") {
			autoGeneralMakeMode(generalGame, Color.BLUE);
		}
		displayGeneralScore(generalGame);
		displayGeneralSOS();
		generalGameEnd(generalGame);
	}

	private Boolean isValidSetUp() {
		try {
			board.getBoardSize(Integer.parseInt(size.getText()));
			if (board.size == 0) {
				JOptionPane.showMessageDialog(null, "Please, choose the board size in range [3,20]\n");
				return false;
			}
			if (board.size >= 11)
				setExtendedState(JFrame.MAXIMIZED_BOTH);
			if (board.gameMode == null) {
				JOptionPane.showMessageDialog(null, "Please,choose the game mode");
				return false;
			}
			return true;
		} catch (Exception E) {
			JOptionPane.showMessageDialog(null, "Please, choose the board size in range [3,20]\n");
			return false;
		}
	}

	private class CellListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < board.size; i++) {
				for (int j = 0; j < board.size; j++) {
					if (cell[i][j] == e.getSource()) {
						if (board.gameMode == "simple") {
							simpleGame.newBoard.getReplayMove(i, j);
							simpleGameMakeMove(i, j, element);
						} else {
							generalGame.newBoard.getReplayMove(i, j);
							generalGameMakeMove(i, j, element);
						}
					}
				}
			}
		}
	}

	private void removeCell() {
		simpleGame.remove();
		cell[simpleGame.newBoard.removeRow][simpleGame.newBoard.removeCol].setText("");
		displayTurn(simpleGame.newBoard.turn);

	}

	private void removeSimpleCell() {
		removeCell();
		if (redPlayer.equals("computer") || bluePlayer.equals("computer")) {
			removeCell();
		}
		replay = false;
	}

	private void removeSOS() {

		int row, col;
		while (!generalGame.newBoard.SOSPosition.isEmpty()) {
			row = generalGame.newBoard.SOSPosition.elementAt(0);
			col = generalGame.newBoard.SOSPosition.elementAt(1);
			generalGame.newBoard.SOSPosition.remove(1);
			generalGame.newBoard.SOSPosition.remove(0);
			cell[row][col].setBackground(new Color(255, 250, 250));
		}
	}

	private void removeGeneralCell() {
		generalGame.replayRecord();
		for (int i = 0; i < generalGame.autoMoveNumber; i++) {
			generalGame.remove();
			cell[generalGame.newBoard.removeRow][generalGame.newBoard.removeCol].setText("");
			displayGeneralScore(generalGame);
			removeSOS();
		}
		replay = false;
	}

	private class playerTypeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == blueHuman)
				bluePlayer = "human";
			else if (e.getSource() == blueComputer) {
				bluePlayer = "computer";
			}
			if (e.getSource() == redHuman)
				redPlayer = "human";
			else if (e.getSource() == redComputer) {
				redPlayer = "computer";
			}
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Start")) {
				if (isValidSetUp()) {
					displayBoard();
				}
			} else if (e.getActionCommand().equals("Replay")) {
				if (replay) {
					if (board.gameMode.equals("simple"))
						removeSimpleCell();
					else
						removeGeneralCell();
				}
			}
		}
	}

	private class chessListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == SRed || e.getSource() == SBlue) {
				element = 'S';
			} else if (e.getSource() == ORed || e.getSource() == OBlue) {
				element = 'O';
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI(new Board());
			}
		});
	}
}
