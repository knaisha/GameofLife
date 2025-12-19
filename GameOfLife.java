
public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;

    public GameOfLife(int x, int y)
    {
        board = new int[x][y];

    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    // Run the simulation for a number of turns
   public void run(int turns) {
    for (int i = 0; i < turns; i++) {
        step();
    }
}


  public void step()
{
    print();
    int[][] next = new int[board.length][board[0].length];

    for (int x = 0; x < board.length; x++) {
        for (int y = 0; y < board[0].length; y++) {

            int neighbors = countNeighbors(x, y);
            int cell = board[x][y];

            // Apply Conway's rules
            if (cell == 1) { // live cell
                if (neighbors < 2) next[x][y] = 0;       // dies (underpopulation)
                else if (neighbors <= 3) next[x][y] = 1; // lives
                else next[x][y] = 0;                     // dies (overpopulation)
            } else { // dead cell
                if (neighbors == 3) next[x][y] = 1;      // reproduction
                else next[x][y] = 0;
            }
        }
    }

    board = next;
}



 public int countNeighbors(int x, int y) {
    int count = 0;

    // Check all 8 neighbors
    for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {

            // skip the center cell
            if (dx == 0 && dy == 0) continue;

            // Use get(x,y) because it wraps around
            if (get(x + dx, y + dy) == 1) {
                count++;
            }
        }
    }

    return count;
}


    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit= board[0].length;
        return board[(x+xLimit)%xLimit][(y+yLimit)%yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get()
    {
        return board;
    }

    // Test helper to print the current state
    public void print(){
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y%10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x%10);
            for (int y=0; y<board[x].length; y++)
            {
                if (board[x][y] == 1)
                {
                    System.out.print("⬛");
                }
                else
                {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
