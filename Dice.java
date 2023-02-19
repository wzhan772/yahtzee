/**
 * this class represents a six-sided die. Each die has a random corresponding value
 *
 */

public class Dice {
	
	//initializing value variable
	private int value;
	
	/**
	 * Dice constructor
	 */
	public Dice() {
		value = -1;
	}
	
	/**
	 * 
	 * @param number number given to each die
	 */
	public Dice(int number) {
		value = number;
	}
	
	/**
	 * rolls a random number for each value
	 */
	public void roll() {
		value = RandomNumber.getRandomNumber(1, 6);

	}
	
	/**
	 * 
	 * @return value of die
	 */
	public int getValue() {
		return value;
	}
}
