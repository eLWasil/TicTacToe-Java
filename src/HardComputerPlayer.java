import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HardComputerPlayer extends Player {
    char opponentMark;
    char[][] currentTable;


    public HardComputerPlayer(char mark, char opponentMark) {
        super(mark, true);
        this.opponentMark = opponentMark;
    }

    @Override
    public int[] getMove(char[][] boardAsTable) {
        currentTable = boardAsTable;
        int[] move = { -1, -1 };
        int[] moveToWin = { -1, -1 };
        int[] moveToBlock = { -1, -1 };

        outerloop:
        for (int row = 0; row < boardAsTable.length; row++) {
            for (int col = 0; col < boardAsTable.length; col++) {

                if (boardAsTable[row][col] == mark || boardAsTable[row][col] == '0') {
                    move = checkField(row, col, mark);

                    if (move[0] >= 0 && move[1] >= 0) {
                        moveToWin = move;
                    }
                }

                if (boardAsTable[row][col] == opponentMark || boardAsTable[row][col] == '0')
                {
                    move = checkField(row, col, opponentMark);

                    if (move[0] >= 0 && move[1] >= 0){
                        moveToBlock = move;
                    }
                }
            }
        }


        if (move[0] < 0 && move[1] < 0)
            move = getRandomCorner();


        if (moveToWin[0] >= 0 && moveToWin[1] >= 0)
            return moveToWin;
        else if (moveToBlock[0] >= 0 && moveToBlock[1] >= 0)
            return moveToBlock;
        else
            return move;
    }

    private int[] getRandomCorner()
    {
        int[] move = new int[2];

        List<NormalPlayerComputer.emptyField> emptyFields = new ArrayList<>();

        if (currentTable[0][0] == '0')
            emptyFields.add(new emptyField(0,0));

        if (currentTable[0][currentTable[0].length - 1] == '0')
            emptyFields.add(new emptyField(0,currentTable[0].length - 1));

        if (currentTable[currentTable.length - 1][0] == '0')
            emptyFields.add(new emptyField(currentTable.length - 1,0));

        if (currentTable[currentTable.length - 1][currentTable[0].length - 1] == '0')
            emptyFields.add(new emptyField(currentTable.length - 1, currentTable[0].length - 1));

        Random rand = new Random();
        int emptyIdx = rand.nextInt(emptyFields.size());

        move[0] = emptyFields.get(emptyIdx).x;
        move[1] = emptyFields.get(emptyIdx).y;

        return move;
    }

    private char getMarkFromTable(int row, int col)
    {
        char mark; // wiem, tak, to nie powinno działać,

        try {
            mark = currentTable[row][col];
        } catch (ArrayIndexOutOfBoundsException e)
        {
            // działaj dalej
            mark = '1';
        }

        return mark;
    }

    private int[] checkField(int row, int col, char cMark)
    {
        int[] move = {-1,-1};

        move = checkRow(row, col, cMark);

        if (move[0] < 0)
            move = checkColumn(row, col, cMark);

        if (move[0] < 0)
            move = checkLDiag(row, col, cMark);

        if (move[0] < 0)
            move = checkRDiag(row, col, cMark);

        return move;
    }

    private int[] checkRow(int row, int col, char cMark)
    {
        int[] movePosition = { -1, -1 };

        if (getMarkFromTable(row, col) == '0')
        {
            if (getMarkFromTable(row, col - 1) == cMark && getMarkFromTable(row, col + 1) == cMark)
            {
                movePosition = new int[] { row, col };
            }
        }else {
            if (getMarkFromTable(row, col - 1) == cMark)
            {
                if (getMarkFromTable(row, col + 1) == '0')
                {
                    movePosition = new int[] { row, col + 1 };
                }

            }else if (getMarkFromTable(row, col + 1) == cMark)
            {
                if (getMarkFromTable(row, col - 1) == '0')
                {
                    movePosition = new int[] { row, col - 1 };
                }
            }
        }

        return movePosition;
    }

    private int[] checkColumn(int row, int col, char cMark)
    {
        int[] movePosition = { -1, -1 };

        if (getMarkFromTable(row, col) == '0')
        {
            if (getMarkFromTable(row - 1, col) == cMark && getMarkFromTable(row + 1, col) == cMark)
            {
                movePosition = new int[] { row, col };
            }
        }else {
            if (getMarkFromTable(row - 1, col) == cMark)
            {
                if (getMarkFromTable(row + 1, col) == '0')
                {
                    movePosition = new int[] { row + 1, col };
                }

            }else if (getMarkFromTable(row + 1, col) == cMark)
            {
                if (getMarkFromTable(row - 1, col) == '0') {
                    movePosition = new int[] { row - 1, col };
                }

            }
        }

        return movePosition;
    }

    private int[] checkLDiag(int row, int col, char cMark)
    {
        int[] movePosition = { -1, -1 };

        if (getMarkFromTable(row, col) == '0')
        {
            if (getMarkFromTable(row - 1, col + 1) == cMark && getMarkFromTable(row + 1, col - 1) == cMark)
            {
                movePosition = new int[] { row, col };
            }
        }else {
            if (getMarkFromTable(row-1, col-1) == cMark)
            {
                if (getMarkFromTable(row+1, col+1) == '0')
                {
                    movePosition = new int[] { row + 1, col + 1 };
                }

            }else if (getMarkFromTable(row+1, col+1) == cMark)
            {
                if (getMarkFromTable(row-1, col-1) == '0')
                {
                    movePosition = new int[] { row - 1, col -1 };
                }
            }
        }

        return movePosition;
    }

    private int[] checkRDiag(int row, int col, char cMark)
    {
        int[] movePosition = { -1, -1 };

        if (getMarkFromTable(row, col) == '0')
        {
            if (getMarkFromTable(row + 1, col - 1) == cMark && getMarkFromTable(row - 1, col + 1) == cMark)
            {
                movePosition = new int[] { row, col };
            }
        }else {
            if (getMarkFromTable(row-1, col+1) == cMark) // TOP
            {
                if (getMarkFromTable(row+1, col-1) == '0')
                {
                    movePosition = new int[] { row + 1, col - 1 };
                }

            }else if (getMarkFromTable(row+1, col-1) == cMark) // DOWN
            {
                if (getMarkFromTable(row-1, col+1) == '0')
                {
                    movePosition = new int[] { row - 1, col + 1 };
                }

            }
        }

        return movePosition;
    }

}
