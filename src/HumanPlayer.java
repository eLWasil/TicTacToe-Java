public class HumanPlayer extends Player {

    public HumanPlayer(char mark)
    {
        super(mark, false);
    }

    @Override
    public int[] getMove(char[][] boardAsTable) {
        int[] move = new int[2];
        return move;
    }
}
