public class EasyComputerPlayer extends Player {

    public EasyComputerPlayer(char mark)
    {
        super(mark, true);
    }


    @Override
    public int[] getMove(char[][] boardAsTable) {
        int[] move = new int[2];

        outerloop:
        for (int row = 0; row < boardAsTable.length; row++)
        {
            for (int col = 0; col < boardAsTable.length; col++)
            {
                if (boardAsTable[row][col] == '0')
                {
                    move[0] = row;
                    move[1] = col;
                    break outerloop;
                }
            }
        }

        return move;
    }
}
