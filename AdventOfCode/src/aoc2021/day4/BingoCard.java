package aoc2021.day4;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.util.ArrayList;

public class BingoCard {

    private ArrayList<Pair<Integer, Boolean>> board = new ArrayList<>();
    private boolean winner = false;
    int finalDraw = -1;

    public BingoCard(ArrayList<Integer> board) {
        for(int point : board) {
                this.board.add(new Pair<Integer, Boolean>(point, false));
        }
    }

    public boolean nextRound(int draw) {
        int index = board.indexOf(new Pair<Integer, Boolean>(draw, false));
        if (index >= 0) {
            board.set(index, new Pair<>(draw, true));
        }
        checkBoard();
        if(winner) {
            finalDraw = draw;
        }
        return winner;
    }

    public int getId() {
        int res = 0;
        for(Pair<Integer, Boolean> point: board) {
            if(!point.getValue()) {
                res+=point.getKey();
            }
        }
        return res * finalDraw;
    }

    private void checkBoard() {
        checkColumns();
        checkRows();
    }

    private void checkRows() {
        for (int i = 0; i<5; i++) {
            boolean complete = true;
            for (int j = 0; j<5; j++) {
                if (!board.get(i+j).getValue()) {
                    complete = false; //This row is not complete, break out of loop and test next row
                    break;
                }
            }
            if(complete) {
                winner = true;
                break;
            }
        }
    }

    private void checkColumns(){
        for (int i = 0; i<5; i++) {
            boolean complete = true;
            for (int j = 0; j<5; j++) {
                if (!board.get((5*j)+i).getValue()) {
                    complete = false; //This column is not complete, break out of loop and test next column
                    break;
                }
            }
            if(complete) {
                winner = true;
                break;
            }
        }
    }

    public void print() {
        System.out.println(board);
    }
}
