package recursive.maze;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RecursiveMaze {  // make maze bigger

    public static JFrame window = new JFrame();
    public static JLabel[][] lbl = new JLabel[10][10];
    public int counter = 0;

    public static String[][] maze
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

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Maze");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLayout(new GridLayout(10, 10));

        userchoise();
           int sX = 8;
        int sY = 1;
        // Locate the exit 
        m.maze[1][1] = "X";
        frame(sX,sY);
        window.setVisible(true);
        // Start solving the maze from (8, 1)
     
        m.solve(sX, sY);
         System.out.println(m);
        // System.out.println("Total calls: " + m.counter);

        //moved framed to just after the starting gets placed in order to have it go up
    }

    public static void frame(int sX, int sY) {

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
                    
                } else if (lbl[x][y] == lbl[sX][sY]){
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
    String[] buttons = {"MAZE 1", "MAZE 2", "Maze 3"};
    
        int b = JOptionPane.showOptionDialog(null, "Which maze do you want solved?", "Maze solver!",
                    JOptionPane.INFORMATION_MESSAGE, 1, null, buttons, buttons[0]);
    
    
}
} //penissssssss
// Have the starting position show up on the frame with words saying start.
// have it ask for starting and ending position
// make a few default mazes and have people be able to choose which one they want solved
