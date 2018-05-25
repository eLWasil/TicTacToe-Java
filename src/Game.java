public class Game {
    public final int gridSize;

    private gameWindow window;
    private Player player1;
    private Player player2;


    public Game(final int gridSize, Player p1, Player p2)
    {
        this.gridSize = gridSize;
        window = new gameWindow(gridSize, true);

        player1 = p1;
        player2 = p2;
    }

    public void mainLoop()
    {
        String[] endTexts = { "", "Player 1 won!", "Player 2 won!", "REMIS" };

        window.build();

        short currentPlayer = 0;
        while (winConditions() == 0)
        {
            if (currentPlayer == 0)
            {
                if (player1.isComputer())
                {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        int[] move = player1.getMove(window.getTable());
                        window.setCharToField(player1.getMark(), move[0], move[1]);
                        window.isPlayerOneTurn = false;
                    }
                }
                else {
                    try {
                        while (true) {
                            Thread.sleep(500);
                            if (!window.isPlayerOneTurn)
                                break;
                            else
                                window.repaint();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else if (currentPlayer == 1)
            {
                if (player2.isComputer())
                {
                    int[] move = player2.getMove(window.getTable());
                    window.setCharToField(player2.getMark(), move[0], move[1]);
                    window.isPlayerOneTurn = true;
                }
                else {
                    try {
                        while (true) {
                            Thread.sleep(500);
                            if (window.isPlayerOneTurn)
                                break;
                            else
                                window.repaint();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            window.repaint();
            currentPlayer = (short)((++currentPlayer) % 2);
        }

        window.endOfGame(endTexts[winConditions()]);
    }

    private int winConditions()
    {
        int winner;

        if (isPlayerWon(player1.getMark()))
            winner = 1;
        else if (isPlayerWon(player2.getMark()))
            winner = 2;
        else if (window.isBoardFull())
            winner = 3;
        else
            winner = 0;

        return winner;
    }

    private boolean isPlayerWon(char playerMark)
    {
        boolean isTheWinner = false;

        for (int row = 0; row < gridSize; row++)
        {
            for (int col = 0; col < gridSize; col++)
            {
                if (window.getFieldMark(row, col) == playerMark)
                {

                    if (rowWinCondition(playerMark, row, col))
                        isTheWinner = true;
                    else if (colWinCondition(playerMark, row, col))
                        isTheWinner = true;
                    else if (lDiagWinCondition(playerMark, row, col))
                        isTheWinner = true;
                    else if (rDiagWinCondition(playerMark, row, col))
                        isTheWinner = true;

                    if (isTheWinner)
                        break;

                }
            }
        }

        return isTheWinner;
    }

    private boolean rowWinCondition(char mark, int row, int col)
    {
        int counter = 0;
        for (int i = col; i < gridSize; i++)
        {
            if (window.getFieldMark(row, i) == mark)
                counter++;
            else
                break;
        }

        if (counter >= 3)
            return true;
        else
            return false;
    }

    private boolean colWinCondition(char mark, int row, int col)
    {
        int counter = 0;
        for (int i = row; i < gridSize; i++)
        {
            if (window.getFieldMark(i, col) == mark)
                counter++;
            else
                break;
        }

        if (counter >= 3)
            return true;
        else
            return false;
    }

    private boolean lDiagWinCondition(char mark, int row, int col)
    {
        int counter = 0;

        int cCol = col;
        for (int i = row; i < gridSize; i++)
        {
            if (window.getFieldMark(i, cCol--) == mark)
                counter++;
            else
                break;
        }

        if (counter >= 3)
            return true;
        else
            return false;
    }

    private boolean rDiagWinCondition(char mark, int row, int col)
    {
        int counter = 0;

        int cRow = row;
        for (int i = col; i < gridSize; i++)
        {
            if (window.getFieldMark(cRow++, i) == mark)
                counter++;
            else {
                break;
            }
        }

        if (counter >= 3)
            return true;
        else
            return false;
    }
}
