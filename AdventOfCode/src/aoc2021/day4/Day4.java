package aoc2021.day4;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day4 {

    public static void run() {
        int res = 0;

        System.out.println("Solutions day 4:");

        //ArrayList<Integer> input = InputLoader.toIntegerList("input/day4.txt");
        ArrayList<String> input = InputLoader.toStringList("input/day4.txt");
        ArrayList<BingoCard> bingoCards = new ArrayList<>();

        ArrayList<Integer> drawingOrder = new ArrayList<>();
        for (String s : input.get(0).split(",")) {
            drawingOrder.add(Integer.parseInt(s));
        }

        for (int i = 2; i<input.size(); i+=6) {
            ArrayList<Integer> board = new ArrayList<>();
            for (int j = 0; j <5; j++) {
                String strRow = input.get(i+j);
                strRow = strRow.trim();
                for (String point : strRow.split("\\s{1,2}")) {
                    board.add(Integer.parseInt(point));
                }
            }
            bingoCards.add(new BingoCard(board));
        }

        boolean gameover = false;
        ArrayList<BingoCard> winners = new ArrayList<>();
        for (int draw : drawingOrder) {
            for(BingoCard card : bingoCards) {
                if(card.nextRound(draw)) {
                    winners.add(card);
                }
            }
            bingoCards.removeAll(winners);
        }

        System.out.println(winners.get(0).getId());
        System.out.println(winners.get(winners.size()-1).getId());
    }
}
