package app;

import edu.princeton.cs.introcs.*;

public class Minesweeper {
    public static void main(String[] args) {
        int size = 9;
        boolean[][] mines = new boolean[size][size];

        int numberOfMines = 10;
        for (int i = 0; i < numberOfMines; i++) {
            while (true) {
                int x = (int) Math.floor(Math.random() * size);
                int y = (int) Math.floor(Math.random() * size);

                if (!mines[x][y]) {
                    mines[x][y] = true;
                    break;
                }
            }
        }

        int[][] counts = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (mines[x][y]) {
                    for (int x1 = Math.max(0, x - 1); x1 <= Math.min(size - 1, x + 1); x1++) {
                        for (int y1 = Math.max(0, y - 1); y1 <= Math.min(size - 1, y + 1); y1++) {
                            ++counts[x1][y1];
                        }
                    }
                }
            }
        }

        int xCursor = 0;
        int yCursor = 0;
        boolean[][] revealed = new boolean[size][size];
        while (true) {
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    System.out.print(xCursor == x && yCursor == y ? ">" : " ");
                    System.out.print(revealed[x][y] ? (counts[x][y] == 0 ? "_" : (counts[x][y])) : "X");
                }
                System.out.println();
            }
            int clearedmines = 0;
            for (int x = 0; x < size; x++) {
                for (int y = 0; y< size; y++) {
                    if (revealed[x][y] == true) {
                        ++clearedmines;
                    }
                }
            }
            System.out.print("Here's how many mines have been cleared: ");
            System.out.println(clearedmines);
            System.out.print("up(w), left(a), down(s), right(d), step(f) ");
            char ch = StdIn.readLine().charAt(0);
            System.out.println();
            if (ch == 'w') {
                --yCursor;
                if (yCursor<0) {
                    yCursor+=size;
                }
            } else if (ch == 's') {
                ++yCursor;
                if (yCursor>=size) {
                    yCursor-=size;
                }
            } else if (ch == 'a') {
                --xCursor;
                if (xCursor<0) {
                    xCursor+=size;
                }
            } else if (ch == 'd') {
                ++xCursor;
                if (xCursor>=size) {
                    xCursor-=size;
                }
            } else if (ch == 'f') {
                if (mines[xCursor][yCursor]) {
                    System.out.println("Sorry.. you stepped on a mine");
                    break;
                }
                revealed[xCursor][yCursor] = true;
            }
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(" ");
                System.out.print(mines[x][y] ? "*" : (counts[x][y] == 0 ? "_" : counts[x][y]));
            }
            System.out.println();
        }
    }
}