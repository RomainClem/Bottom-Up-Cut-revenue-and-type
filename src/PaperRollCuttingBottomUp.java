public class PaperRollCuttingBottomUp {
    // static ANSI color scheme
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";




    void bottomUpCut(double[] rollPieces, int rollLength){

        // Checking if given length is incorrect and stopping the completion by using return
        if (rollLength > rollPieces.length || rollLength < 1) {
            System.out.println(RED + """
                    Error: given length is incorrect.
                    (Check if the length is greater than the array or smaller than 1)
                    """ + RESET);
            return;
        }

        /* Array to memorize dynamically the sub-problems
            @subRolls store the best price per length
            @firstCut store the first length to cut the rod/roll to reach the best value. Using the remaining length
                   - firstCut, you check again what is the first cut with that new length to achieve the best price.
         */
        double[] subRolls = new double[rollLength+1];
        int[] firstCut = new int[rollLength+1];

        // Max paper roll value for each iteration of subRolls[i]
        for (int i = 1; i <= rollLength; i++) {
            // defined lowest integer at every iteration to find the biggest value at each index
            double maxRollValue = Integer.MIN_VALUE;

            // For each sub value - checking if it's higher
            for (int j = 0; j < i; j++){
                // If it's higher than the previous highest value, replace it with the new value
                // Update the first cut needed to reach that value
                if (maxRollValue < rollPieces[j] + subRolls[i-j-1]){
                    maxRollValue = rollPieces[j] + subRolls[i-j-1];
                    firstCut[i] = j+1;
                }
            }

            // Assigning the maxvalue previously found
            subRolls[i] = maxRollValue;
        }

        // Printing information to form a sort of table
        printInfo(rollPieces, rollLength, subRolls, firstCut);

        // Printing the best cuts
        printBestCuts(rollLength, firstCut);

    }

    void printInfo(double[] rollPieces, int rollLength, double[] subRolls, int[] firstCut){
        //Printing the lengths
        System.out.printf("|%-7s|", "Length");
        for (int i = 1; i <= rollLength; i++) {
            System.out.printf("%6d", i);
        }

        //Printing the prices given
        System.out.printf("\n|%-7s|", "Price");
        for (int i = 0; i < rollLength; i++) {
            System.out.printf("%6.1f", rollPieces[i]);
        }

        //Printing the best prices possible
        System.out.printf("\n|%-7s|", "Best €");
        for (int i = 0; i < rollLength; i++) {
            System.out.printf("%6.1f", subRolls[i+1]);
        }

        // Printing the 1st rod to cut to achieve that best price
        // Which gives us the remaining rod and the next possible value to cut
        System.out.printf("\n|%-7s|", "1st Cut");
        for (int i = 0; i < rollLength; i++) {
            System.out.printf("%6d", firstCut[i+1]);
        }
    }

    void printBestCuts(int rollLength,int[] firstCut){
        // Printing roll cut and their type
        System.out.println("\n\nCut for each rolls/rods:");

        // For each possible length, printing each cuts starting with the first one
        for (int i = 0; i < rollLength; i++) {
            System.out.print("- length " + (i+1) + " = ");

            // Starting from the length 1 to n
            int checkRoll = i+1;
            while (checkRoll > 0){
                // We first check the first cut to use, which was the last found
                System.out.print(firstCut[checkRoll] + " " + YELLOW);
                // Printing dash to look nice , but could be better or different
                for (int j = 0; j < firstCut[checkRoll]; j++) {
                    System.out.print("-");
                }
                //Reset the color
                System.out.print(RESET);
                // Now we reduce the length of the roll using the last roll we cut
                checkRoll = checkRoll - firstCut[checkRoll];

                // If the roll still can't be cut, it will loop and let's print a + to make it nicer
                if (checkRoll> 0) System.out.print(" + ");
            }
            // Spacing
            System.out.println();
        }
    }

    void test(){
         /* Given example of the value of the roll-piece
            Using an array since it has a better time complexity than Lists
            rollPrices: array of doubles with the values given in the example
            rodCut: array of int using example given in the lecture
         */
        double[] rollPieces = {1.2, 3, 5.8, 0, 10.1};
        double[] rodCut = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};


        // Assignment example
        System.out.println("Example 1: Paper Roll Cutting\n------------------------------");
        bottomUpCut(rollPieces, rollPieces.length);

        // Lecture example
        System.out.println("\nExample 2: Rod Cutting Problem\n------------------------------");
        bottomUpCut(rodCut, rodCut.length);

        // Random example with a length from 10 to 30
        int randLength = (int) ((Math.random() * (30 - 10)) + 10);

        // Initializing random values array
        double[] randArray = new double[randLength];

        // Assigning random values
        for (double i = 1; i <= randLength; i++) randArray[(int)i-1] = Math.random() * ((2*i)-i)+i;

        // Printing on screen
        System.out.println("\nExample 3: Random dynamic values\n--------------------------------");
        bottomUpCut(randArray, randArray.length);
    }

}
