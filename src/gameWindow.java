import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class gameWindow  extends JFrame {

    public boolean isPlayerOneTurn = true;

    private List<JButton> fields;
    private boolean isPlayerFirst;
    private int gridSize;
    JPanel gridOfButtons;

    public gameWindow(int gridSize,boolean isPlayerFirst)
    {
        this.gridSize = gridSize;
        this.isPlayerFirst = isPlayerFirst;
        fields = new ArrayList<>();

        gridOfButtons = new JPanel();
    }

    public void build()
    {
        buildWindow();

        /*

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                buildWindow();
            }
        });

         */
    }

    private void buildWindow()
    {
        int size = 800;
        setSize(size + 50, size + 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setTitle("Kolko i Krzyzyk");

        //gridOfButtons.setLayout(new GridLayout(gridSize, gridSize));
        gridOfButtons.setLayout(null);

        int buttonSize = (int)Math.floor(size / gridSize);


        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                JButton button = new JButton("");
                button.setBounds(j * buttonSize, i * buttonSize, buttonSize, buttonSize);
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isPlayerOneTurn && button.getText().equals(""))
                        {
                            button.setText(isPlayerFirst ? "O" : "X");
                            isPlayerOneTurn = false;
                        }
                    }
                });
                fields.add(button);
                add(button);
            }
        }
        add(gridOfButtons);
    }

    public void endOfGame(String text)
    {
        JOptionPane.showMessageDialog(gridOfButtons, text);

    }

    public char getFieldMark(int row, int col)
    {
        char mark;

        if (row >= 0 && col >= 0)
        {
            try {
                if (fields.get((row * gridSize) + col).getText().equals(""))
                    mark = '0';
                else
                    mark = fields.get((row * gridSize) + col).getText().charAt(0);

            } catch (IndexOutOfBoundsException e)
            {
                mark = '1';
            }
        }else
            mark = '1'; // mean out of range



        return mark;

    }

    public char[][] getTable()
    {
        char Table[][] = new char[gridSize][gridSize];

        for (int row = 0; row < gridSize; row++)
        {
            for (int col = 0; col < gridSize; col++)
            {
                Table[row][col] = getFieldMark(row, col);
            }
        }

        return Table;
    }

    public void setCharToField(char c, int row, int col)
    {
        try {
            fields.get((row * gridSize) + col).setText(String.valueOf(c));
        } catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isBoardFull()
    {
        boolean boardFull = true;

        int k = 0;
        for (int i = 0; i < fields.size(); i++)
        {
            //System.out.println(k++ + " " + fields.get(i).getText());
            if (fields.get(i).getText().equals(""))
            {
                boardFull = false;
                break;
            }
        }

        return boardFull;
    }

    public String getBoardKey() {
        StringBuilder buildKey = new StringBuilder();

        for (JButton button : fields)
        {
            if (button.getText().equals(""))
            {
                buildKey.append(0);
            }
            else {
                buildKey.append(button.getText().charAt(0));
            }
        }

        return buildKey.toString();
    }


}
