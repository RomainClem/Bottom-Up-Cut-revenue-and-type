public class PaperRollCuttingBottomUp {

    void bottomUpCut(double[] rollPieces, int rollLength){

        // Array to memorize dynamically the sub-problems
        double[] subRolls = new double[rollLength+1];

        // Initial sub value of length 0 is 0
        subRolls[0] = 0;

        // Max paper roll value for each iteration of subRolls[i]
        for (int i = 1; i <= rollLength; i++) {
            // defined lowest integer at every iteration to find the biggest value each time comparing it
            double maxRollValue = Integer.MIN_VALUE;

            // For each sub value - checking if it's higher
            for (int j = 0; j < i; j++) maxRollValue = Math.max(maxRollValue, rollPieces[j] + subRolls[i-j-1]);

            // Assigning the maxvalue previously found
            subRolls[i] = maxRollValue;
        }

        // Returning the requested best value for the roll length
        System.out.println("The best price for length " + rollLength + " is: " + subRolls[rollLength]);
    }


}
