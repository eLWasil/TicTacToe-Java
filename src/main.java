public class main {

    public static void main(String args[])
    {
        Player p1 = new HumanPlayer('O');
        Player p2 = new EasyComputerPlayer('V');
        Player p3 = new NormalPlayerComputer('C');
        Player p4 = new HardComputerPlayer('X', 'O');

        Game game = new Game(3, p1, p4);
        game.mainLoop();
    }
}
