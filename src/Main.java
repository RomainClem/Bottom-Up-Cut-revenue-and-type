public class Main {

    public static void main(String[] args) {

        /* Given example of the value of the roll-piece
            Using an array since it has a better time complexity than Lists
            rollPrices: array of doubles with the values given in the example
            rodCut: array of int using example given in the lecture
         */
        double[] rollPieces = {1.2, 3, 5.8, 0, 10.1};
        double[] rodCut = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};


        // Creating instance of Bottom Up algorithm
        PaperRollCuttingBottomUp bestSolution = new PaperRollCuttingBottomUp();

        // Assignment example
        System.out.println("Example 1: Paper Roll Cutting\n------------------------------");
        bestSolution.bottomUpCut(rollPieces, rollPieces.length);

        // Lecture example
        System.out.println("Example 2: Rod Cutting Problem\n------------------------------");
        bestSolution.bottomUpCut(rodCut, rodCut.length);

        // Random example with a length from 10 to 30
        int randLength = (int) ((Math.random() * (30 - 10)) + 10);

        // Initializing random values array
        double[] randArray = new double[randLength];

        // Assigning random values
        for (double i = 1; i <= randLength; i++) randArray[(int)i-1] = Math.random() * ((2*i)-i)+i;

        // Printing on screen
        System.out.println("Example 3: Random dynamic values\n--------------------------------");
        bestSolution.bottomUpCut(randArray, randArray.length);

    }
}
