public class Main {

    public static void main(String[] args) {

        /* Given example of the value of the roll-piece
            Using an array of double since it has a better time complexity than Lists
            rollPrices: array of doubles since values are decimal
         */
        double[] rollPieces = {1.2, 3, 5.8, 0, 10.1};
        double[] rodCut = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int rollLength = rollPieces.length;

        PaperRollCuttingBottomUp bestSolution = new PaperRollCuttingBottomUp();

        bestSolution.bottomUpCut(rollPieces, 5);

    }
}
