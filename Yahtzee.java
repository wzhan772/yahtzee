/**
 * this class represents the Yahtzee game. It holds the methods that calculate the scores of each possible outcome of the game
 *
 */

public class Yahtzee {
	
	//this is the constructor for each die roll
	private Dice[] dice;
	
	/**
	 * this method creates a new die array and rolls 5 random values
	 */
	public Yahtzee() {
		dice = new Dice[5];
		for (int i = 0; i < dice.length; i++) {
			dice[i] = new Dice();
			dice[i].roll();
		}
	}
	
	/**
	 * 
	 * @param diceNum initializes number of die
	 */
	public Yahtzee (Dice[] diceNum) {
		dice = diceNum;
	}
	
	/**
	 * 
	 * @return the value of the array
	 */
	public int[] getValueCount() {
		int [] arrayCount = new int[6];
		//set the value of each die roll
		for (int i = 0; i < dice.length; i++) {
			for (int j = 0; j < 6; j++) {
				if (dice[i].getValue() == j+1) {
					arrayCount[j]++;
				}
			}	
		}
		return arrayCount;
	}
	
	/**
	 * 
	 * @return the total points that the player scores
	 */
	public int[] getScoreOptions() {
		int counter = 0;
		int[] points = new int[13];
		int[] diceNum = getValueCount();
		
		//set each position in the points array to each corresponding value
        points[0] = diceNum[0]*1;
        
        points[1] = diceNum[1]*2;
        
        points[2] = diceNum[2]*3;
        
        points[3] = diceNum[3]*4;
        
        points[4] = diceNum[4]*5;
        
        points[5] = diceNum[5]*6;
		
		points[6] = checkKindsThree(diceNum, 3);
        
		points[7] = checkKindsFour(diceNum, 4);
        
        points[8]= checkHouse(diceNum);

        points[9] = checkStraightFour(diceNum, 4);
        
        points[10] = checkStraightFive(diceNum, 5);
        
        points[11] = checkKindsFive(diceNum, 5);
        
        points[12] = total(diceNum);  
        
        return points;    
    }
	
	/**
	 * 
	 * @param scoreArray is the position in the final scores array
	 * @param numKinds is three of a kind
	 * @return the total of the kinds or 0 if it does not apply
	 */
    private int checkKindsThree(int[] scoreArray, int numKinds) {
    	int finalScore = 0;
    	for (int i = 0; i < scoreArray.length; i++) {
        	finalScore += scoreArray[i] * (i + 1);
        }
        for (int i = 0; i < scoreArray.length; i++) {
            if (scoreArray[i] >= numKinds) {
                return finalScore;
            }
        }
        return 0;
    }    
    
    /**
     * 
	 * @param scoreArray is the position in the final scores array
	 * @param numKinds is four of a kind
	 * @return the total of the kinds or 0 if it does not apply
     */
    private int checkKindsFour(int[] scoreArray, int numKinds) {
    	int finalScore = 0;
    	for (int i = 0; i < scoreArray.length; i++) {
        	finalScore += scoreArray[i] * (i + 1);
        }
        for (int i = 0; i < scoreArray.length; i++) {
            if (scoreArray[i] >= numKinds) {
                return finalScore;
            }
        }
        return 0;
    }    
    
    /**
     * 
	 * @param scoreArray is the position in the final scores array
	 * @param numKinds is five of a kind
	 * @return a score of 50 if the player rolls 5 of a kind or 0 if it does not apply
     */
    private int checkKindsFive(int[] scoreArray, int numKinds) {
        for (int i = 0; i < scoreArray.length; i++) {
            if (scoreArray[i] >= numKinds) {
                return 50;
            }
        }
        return 0;
    }    
    
    /**
     * 
     * @param scoreArray is the position in the final scores array
     * @return a score of 25 if the player rolls a full house or 0 if it does not apply
     */
    private int checkHouse(int[] scoreArray) {
    	int i = 0;
        boolean checkPair = false;
        boolean checkTriple = false;
        //check if there is a pair or a triple 
        while (i < scoreArray.length) {
            if (scoreArray[i] == 2) {
            	checkPair = true;
            }
            else if (scoreArray[i] == 3) {
            	checkTriple = true;
            }
            i++;
        }
        //if both apply, return the corresponding score
        if (checkTriple == true && checkPair == true) {
            return 25;
        }
        return 0;
    }
    
    /**
     * 
     * @param scoreArray is the position in the final scores array
     * @param numStraight is a straight of 4
     * @return a score of 30 if the player rolls a four straight or 0 if it does not apply
     */
    private int checkStraightFour(int[] scoreArray, int numStraight) {
    	int i = 0;
    	while (i < scoreArray.length) {
        	int straightCounter = 0;
        	//checking if there is a straight of 4 
            if (scoreArray[i] != 0 && straightCounter < numStraight) {
                for (int k = i; k < scoreArray.length; k++) {    
                    if (scoreArray[k] == 0) {
                    	return 0;
                    }
                    else {
                    	//if there is a straight return a point score of 30 
                    	straightCounter++;
                        if (straightCounter == numStraight) {
                        	return 30;
                        }
                    }
                }
            }
            //continue to loop
            i++;
        }
        return 0;
    }  
    
    /**
     * 
     * @param scoreArray is the position in the final scores array
     * @param numStraight is a straight of 5 
     * @return a score of 40 if the player rolls a five straight or 0 if it does not apply
     */
    private int checkStraightFive(int[] scoreArray, int numStraight) {
    	int i = 0;
    	while (i < scoreArray.length) {
        	int straightCounter = 0;
        	//checking if there is a straight of 5
            if (scoreArray[i] != 0 && straightCounter < numStraight) {
                for (int k = i; k < scoreArray.length; k++) {    
                    if (scoreArray[k] == 0) {
                    	return 0;
                    }
                    else {
                    	//if there is a straight return a point score of 40
                    	straightCounter++;
                        if (straightCounter == numStraight) {
                        	return 40;
                        }
                    }
                }
            }
            //continue to loop
            i++;
        }
        return 0;
    }   
    
    /**
     * 
     * @param scoreArray is the position in the final scores array
     * @return the final score after adding up all the scores
     */
    private int total(int[] scoreArray) {
        int finalScore = 0;
        for (int i = 0; i < scoreArray.length; i++) {
        	finalScore += scoreArray[i] * (i + 1);
        }
        return finalScore;
    }
    
    /**
     * this method calls getScoreOptions() and finds the maximum value from the array of possible scores along with its lowest index position
     * @return an integer array containing 2 values: the highest value and the lowest index
     */
    public int[] score() {
        int[] totalScores = getScoreOptions();
        int currentLargest = totalScores[0];
        int position = 0;
        //if the score at the current position is larger than the current largest integer, replace it
        for (int i = 0; i < totalScores.length; i++) {
            if (totalScores[i] > currentLargest) {
            	currentLargest = totalScores[i];
            	//set the index to the current score position if it is larger than the current largest integer
                position = i;
            }
    }
        //return a 2 value array containing the current largest value and its index
        int[] maxPosition = {currentLargest, position};
        return maxPosition;
    }
	
    /**
     * 
     * @param other compares the current array to the other array
     * @return true or false depending on whether or not both arrays contain the same values or not
     */
	public boolean equals(Yahtzee other) {
        int[] currentArray = this.getValueCount();
        int[] otherArray = other.getValueCount();
        //check each value in each position of both arrays
        for (int i = 0; i < currentArray.length; i++) {
            if (currentArray[i] != otherArray[i]) {
                return false;
            }
        }
        return true;
    }
	
	/**
	 * method returns a string of the dice values in the specified format
	 */
	public String toString() {
		return "Dice: {" + dice[0].getValue() + ", " + dice[1].getValue() + ", " + dice[2].getValue() + ", " + dice[3].getValue() + ", " + dice[4].getValue() + "}";
	}
}
