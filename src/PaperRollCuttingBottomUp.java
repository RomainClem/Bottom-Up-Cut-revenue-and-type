public class PaperRollCuttingBottomUp {

    void bottomUpCut(double[] rollPieces, int rollLength){

        if (rollLength > rollPieces.length || rollLength < 1) {
            System.out.println("\u001B[31m" + """
                    Error: given length is incorrect.
                    (Check if the length is greater than the array or smaller than 1)
                    """ + "\u001B[0m");
            return;
        }
        // Array to memorize dynamically the sub-problems
        double[] subRolls = new double[rollLength+1];
        int[] firstCut = new int[rollLength+1];

        // Initial sub value of length 0 is 0
        subRolls[0] = 0;

        // Max paper roll value for each iteration of subRolls[i]
        for (int i = 1; i <= rollLength; i++) {
            // defined lowest integer at every iteration to find the biggest value each time comparing it
            double maxRollValue = Integer.MIN_VALUE;

            // For each sub value - checking if it's higher
            for (int j = 0; j < i; j++){
                // If it's higher than the previous highest value, replace it with the new value
                // Indicate what's the 1st best cut to use
                if (maxRollValue < rollPieces[j] + subRolls[i-j-1]){
                    maxRollValue = rollPieces[j] + subRolls[i-j-1]; firstCut[i] = j+1;
                }
            }

            // Assigning the maxvalue previously found
            subRolls[i] = maxRollValue;
        }

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

        // Printing roll cut and their type
        System.out.println("\n\nCut for each rolls/rods:");

        // For each possible length, printing each cuts starting with the first one
        for (int i = 0; i < rollLength; i++) {
            System.out.print("- length " + (i+1) + " = ");

            // Starting from the length 1 to n
            int checkRoll = i+1;
            while (checkRoll > 0){
                // We first check the first cut to use, which was the last found
                System.out.print(firstCut[checkRoll] + " ");
                // Printing dash to look nice , but could be better or different
                for (int j = 0; j < firstCut[checkRoll]; j++) {
                    System.out.print("-");
                }
                // Now we reduce the length of the roll using the last roll we cut
                checkRoll = checkRoll - firstCut[checkRoll];

                // If the roll still can't be cut, it will loop and let's print a + to make it nicer
                if (checkRoll> 0) System.out.print(" + ");
            }
            // Spacing
            System.out.println();
        }



    }


}
