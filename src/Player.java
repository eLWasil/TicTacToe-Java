public abstract class Player {
    protected boolean isComputer;
    protected char mark;


    public Player(char mark, boolean isComputer)
    {
        this.mark = mark;
        this.isComputer = isComputer;
    }

    public char getMark() {
        return mark;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public abstract int[] getMove(char[][] boardAsTable);



    protected class emptyField {
        public int x, y;
        public emptyField(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }


}
