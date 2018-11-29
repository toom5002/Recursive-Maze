package recursive.maze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RecursiveMaze {  // make maze bigger

    public static int sX = 8, sY = 1;

    static String[] cmbTxt = {"MAZE 1", "MAZE 2", "MAZE 3"};
    static JComboBox cmbMsg = new JComboBox(cmbTxt);
    static JLabel lblcombo = new JLabel();

    public static ActionListener a = (new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cmbMsg) {
                JComboBox cmb2 = (JComboBox) e.getSource();
                String msg = (String) cmb2.getSelectedItem();
                switch (msg) {
                    case "MAZE 1":
                        lblcombo.setText("Maze 1");
                        maze = maze1.clone();
                        frame(sX, sY, testW);
                        //System.out.println("1");

                        break;
                    case "MAZE 2":
                        lblcombo.setText("Maze 2");
                        maze = maze2.clone();
                        //System.out.println("2");

                        break;
                    case "MAZE 3":
                        lblcombo.setText("Maze 3");
                        maze = maze3.clone();
                        //System.out.println("3");

                        break;
                    default:
                        lblcombo.setText("There seems to be an error in the program. Whoops!");
                }
            }

        }

    });
    public static JFrame testW = new JFrame();

    public static JLabel[][] lbl = new JLabel[10][10];
    public int counter = 0;
    public static String[][] maze = new String[10][10];
    public static String[][] maze1
            = {{"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", " ", " ", " ", "#", " ", "#", " ", " ", "#"},
            {"#", " ", " ", " ", "#", " ", "#", " ", "#", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", "#", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", "#", "#", "#", "#", "#"},
            {"#", " ", " ", " ", " ", " ", "#", " ", "#", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},};

    public static String[][] maze2
            = {{"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},};

    public static String[][] maze3
            = {{"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "#", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},};

    // Get the start location (x,y) and try to solve the maze
    public void solve(int x, int y) throws InterruptedException {
        maze[x][y] = "S";
        if (step(x, y)) {
            maze[x][y] = "S";

        }
    }

    // Backtracking method
    public boolean step(int x, int y) throws InterruptedException {
        //lbl[0][0].setBackground(Color.PINK);
        // Thread.sleep(10000);
        counter++;

        System.out.println(this.toString());
        Thread.sleep(250);

        /**
         * Accept case - we found the exit *
         */
        if (maze[x][y] == "X") {
            return true;
        }

        /**
         * Reject case - we hit a wall or our path *
         */
        if (maze[x][y] == "#" || maze[x][y] == "*") {
            return false;
        }

        /**
         * Backtracking Step *
         */
        // Mark this location as part of our path
        maze[x][y] = "*";
        lbl[x][y].setBackground(Color.GREEN);

        //frame();
        boolean result;

        // Try to go Right
        result = step(x, y + 1);
        if (result) {
            return true;
        }

        // Try to go Up
        result = step(x - 1, y);
        if (result) {
            return true;
        }

        // Try to go Left
        result = step(x, y - 1);
        if (result) {
            return true;
        }

        // Try to go Down
        result = step(x + 1, y);
        if (result) {
            return true;
        }

        /**
         * Dead end - this location can't be part of the solution *
         */
        // Unmark this location
        maze[x][y] = "+";
        lbl[x][y].setBackground(Color.RED);
        //frame();
        // Go back
        return false;
    }

    public String toString() {
        String output = "";
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                output += maze[x][y] + " ";
            }
            output += "\n";
        }
        return output;
    }

    public static void main(String[] args) throws InterruptedException {
        RecursiveMaze m = new RecursiveMaze();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Maze");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLayout(new GridLayout(10, 10));

        testW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testW.setTitle("Maze Setup");
        testW.setBounds(850, 0, 1080, 1080);
        testW.setLayout(new GridLayout(10, 10));
        testW.setVisible(true);

        userchoise();

        // Locate the exit 
        m.maze[1][1] = "X";
        frame(sX, sY, window);
        window.setVisible(true);
        // Start solving the maze from (8, 1)

        m.solve(sX, sY);
        System.out.println(m);
        // System.out.println("Total calls: " + m.counter);

        //moved framed to just after the starting gets placed in order to have it go up
    }

    public static void frame(int sX, int sY, JFrame window) {

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                //lbl[x][y] = new JLabel(maze[x][y], SwingConstants.CENTER);
                //lbl[x][y].setFont(new Font("Verdana", Font.BOLD, 60));
                lbl[x][y] = new JLabel("", SwingConstants.CENTER);
                lbl[x][y].setName(maze[x][y]);
                lbl[x][y].setOpaque(true);
                //lbl[x][y].setText(lbl[x][y].getName());
                if ((lbl[x][y].getName()).equals("#")) {
                    lbl[x][y].setBackground(Color.BLACK);

                } else if (lbl[x][y] == lbl[sX][sY]) {
                    lbl[x][y].setBackground(Color.GREEN);
                    lbl[x][y].setText("START");
                    lbl[x][y].setFont(new Font("Verdana", Font.BOLD, 40));

                } else if ((lbl[x][y].getName()).equals("X")) {
                    lbl[x][y].setBackground(new Color(212, 215, 0));
                    lbl[x][y].setText("END");
                    lbl[x][y].setFont(new Font("Verdana", Font.BOLD, 40));

                } else if ((lbl[x][y].getName()).equals("*")) {
                    lbl[x][y].setBackground(Color.GREEN);

                } else if ((lbl[x][y].getName()).equals("+")) {
                    lbl[x][y].setBackground(Color.RED);
                }

                lbl[x][y].setBorder(new LineBorder(Color.BLACK));
                window.add(lbl[x][y]);

            }
        }
    }

    public static void userchoise() {
        //tring[] buttons = {"MAZE 1", "MAZE 2", "MAZE 3"};

        cmbMsg.setSelectedIndex(0);
        cmbMsg.addActionListener(a);
        JOptionPane.showMessageDialog(null, cmbMsg, "Choose A Maze!", JOptionPane.QUESTION_MESSAGE);
        testW.setVisible(false);

    }
}
// have it pass a boolean that will check if it is running the entire code or just a test. dont want the whole thing solved just walls to show up 1/2
// for the preview part 2/2
// have it ask for starting and ending position
// setLocation of the JComboBox
// make a few default mazes and have people be able to choose which one they want solved
