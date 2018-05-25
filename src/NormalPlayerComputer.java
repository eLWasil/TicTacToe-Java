import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalPlayerComputer extends Player {

    public NormalPlayerComputer(char mark) {
        super(mark, true);
    }

    @Override
    public int[] getMove(char[][] boardAsTable) {
        int[] move = new int[2];

        List<emptyField> emptyFields = new ArrayList<>();


        for (int row = 0; row < boardAsTable.length; row++)
        {
            for (int col = 0; col < boardAsTable.length; col++)
            {
                if (boardAsTable[row][col] == '0')
                {
                    emptyFields.add(new emptyField(row, col));
                }
            }
        }

        Random rand = new Random();
        int emptyIdx = rand.nextInt(emptyFields.size());

        move[0] = emptyFields.get(emptyIdx).x;
        move[1] = emptyFields.get(emptyIdx).y;

        return move;
    }
}
