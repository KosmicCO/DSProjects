
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class Board {

    private int size;
    private int[][] board;

    public Board(int size) {
        this.size = size;
        board = new int[size][size];
    }

    private Board(int[][] board) { //unsafe
        size = board.length;
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            this.board[i] = Arrays.copyOf(board[i], size);
        }
    }

    public boolean validMove(int dir) {
        int prev;
        int cur;
        switch (dir) {
            case 0:
                for (int i = 0; i < size; i++) {
                    prev = -2;
                    for (int j = 0; j < size; j++) {
                        cur = board[i][j];
                        if (prev == cur) {
                            return true;
                        }
                        if (prev == 0 && cur > 0) {
                            return true;
                        }
                        prev = cur;
                    }
                }
            case 1:
                for (int i = 0; i < size; i++) {
                    prev = -2;
                    for (int j = size - 1; j >= 0; j--) {
                        cur = board[j][i];
                        if (prev == cur) {
                            return true;
                        }
                        if (prev == 0 && cur > 0) {
                            return true;
                        }
                        prev = cur;
                    }
                }
            case 2:
                for (int i = size - 1; i >= 0; i--) {
                    prev = -2;
                    for (int j = 0; j < size; j++) {
                        cur = board[i][j];
                        if (prev == cur) {
                            return true;
                        }
                        if (prev == 0 && cur > 0) {
                            return true;
                        }
                        prev = cur;
                    }
                }
            case 3:
                for (int i = 0; i < size; i++) {
                    prev = -2;
                    for (int j = 0; j < size; j++) {
                        cur = board[j][i];
                        if (prev == cur) {
                            return true;
                        }
                        if (prev == 0 && cur > 0) {
                            return true;
                        }
                        prev = cur;
                    }
                }
        }
        return false;
    }

    public Board move(int dir) {
        Board moved = new Board(size);
        switch(dir){
            case 0:
                
        }
    }
}
