package fracCalc;
import java.util.*;
/* Outline!!! -- started off like this, then slowly veered off to another track after i figured out more and more
 * Input value -- 
 * 	- One string
 *  - New values separated by a single space (Look for spaces!!)
 * Organization
 *  - (note to self -- call everything within produce answer to keep values within scope)
 *  - method produceAnswer handles all calculations
 *  - main method handles all the output and user interface -- handles quit function as well
 *  - Helper methods as needed for organization -- Listed here:
 * Main method!
 *  - Create scanner userBoi
 *  - while loop to check if userBoi = "quit"
 *  	- "quit" exits the loop
 *  - userBoi != "quit", go on DONE
 * produceAnswer method!
 * 	- INPUT
 *  - SPECIAL CONDITIONS TO REMEMEBORIO 
 *  	- Divide by 0 (if statement)
 *  	- Multiply by 0 (if statement)
 *  - stringBreak portion (breaks up string into the 3 or more components of operands and operators)
 *  	- INPUT string from input
 *  	- operand and operators broken by spaces
 *  	- break string at first space
 *  	- set new string (operand1) of everything before the space and old string everything after the space
 *  	- repeat to find operator (operator1)
 *  	- repeat to find second number (operand2)
 *  	- output "checkpoint" -- operand1, operator1, operand2
 * 		- check if string contains underscore or not -- repeat for both operands or all operands -- DO FOR ALL OPERANDS --
 *  	- IF yes
 *  		- break string into fraction and wholeNumber
 *  		- call stringToInt
 *  		- split numerator and denominator of second broken string -- store
 *  		- set corresponding boolean isRational to true
 *  	- IF no
 *  		- call stringToInt -- store values
 *  		- set corresponding boolean isRational to false
 *  	- TURN fraction and number sets into improper fractions for math
 *  		- call impropFrac for each operand
 *  	- find corresponding operator and sort to each function
 *
 * Helper Methods:
 *  - stringToInt -- INPUT -- string OUTPUT integer
 *  	- scan string and store length -- length proportional to the highest place value -- i.e. 432, length = 2, hundred's place is 10^2.
 *  	- findNum(charAt 0,) multiply by Math.pow(given number, lengthOfString)
 *  		- loop -- charAt++ and length of string-- until charAt returns -1
 *  	- OUTPUT finished built number
 *  - findNum
 *  	- INPUT char value of a number
 *  	- changes char to integer getNumericValue
 *  	- OUTPUT returns a single integer value
 *  - impropFrac
 *  - operator sort -- merge into produceAnswer
 *  - multiply
 *  - divide
 */

public class FracCalc {

    public static void main(String[] args) 
    {
 		 Scanner userBoi = new Scanner(System.in);
         System.out.println("Type calculation in the space below in the pattern of <number> <operator> <number> respective to spacing");
         System.out.println("(If the number is a fraction, type in the form of <wholenumber>_<numerator>/<denominator>)");
         System.out.println("Type \"quit\" (without quations) to exit");
         String equation = userBoi.nextLine();
         equation = equation.toLowerCase(); //makes sure quit is not case sensitive
         while (!(equation.equals("quit"))) { //checks if equation string does not equate to "quit"
         	System.out.println(produceAnswer(equation));
         	equation = userBoi.nextLine();
         	equation = equation.toLowerCase();
         }
         System.out.println("Finished");
         userBoi.close();
    }

    public static String produceAnswer(String input)
    { 
    	//check if operation is being divided by zero
    	if (input.endsWith("/ 0") || input.endsWith("/ 0_0/0") || input.endsWith("/ 0/0")) {
    		return "Error! -- You tried to divide by Zero! No!!";
    	}
    	//check if operation is being multiplied by zero
    	if (input.endsWith("* 0") || input.endsWith("* 0_0/0") || input.endsWith("* 0/0") || input.startsWith("0 *")) {
    		return "0";
    	}
        //break up input string into operands and operators
    	//look for spaces
    	int spaceLocation = input.indexOf(' ');
    	int secondSpaceLocation = input.lastIndexOf(' ');
    	if (input.indexOf(' ') == -1) {
    		return "error";
    	}
    	//operOneStr = Operand One String version
    	String operOneStr = input.substring(0, spaceLocation);
    	//System.out.println(operOneStr);
    	//operTwoStr = Operand two String Version
    	String operTwoStr = input.substring(secondSpaceLocation + 1, input.length());
    	//System.out.println(operTwoStr);
    	char operator = input.charAt(spaceLocation + 1);
    	// look if fraction -- booleans
    	boolean opOneIsFrac = isMixedFraction(operOneStr);
    	boolean opTwoIsFrac = isMixedFraction(operTwoStr);
    	boolean opOneIsOnlyFrac = isOnlyFraction(operOneStr);
    	boolean opTwoIsOnlyFrac = isOnlyFraction(operTwoStr);
    	    	
    	//initialize all math variables
    	int wholeOne = 0;
    	int numOne = 0;
    	int denoOne = 1; //denominator equals one no matter what even if a whole number i.e. 20 = 20/1
    	int wholeTwo = 0;
    	int numTwo = 0;
    	int denoTwo = 1;
    	// if values are only fractions
    	if (opOneIsOnlyFrac == true && opOneIsFrac == false) {
    		numOne = stringToInt(operOneStr.substring(0, operOneStr.indexOf('/')));
    		denoOne = stringToInt(operOneStr.substring(operOneStr.indexOf('/') + 1, operOneStr.length()));
    	} else if (opOneIsOnlyFrac == false && opOneIsFrac == false) { // if values are only whole numbers
    		wholeOne = stringToInt(operOneStr);
    	}
    	// if values are only fractions
    	if (opTwoIsOnlyFrac == true && opTwoIsFrac == false) {
    		numTwo = stringToInt(operTwoStr.substring(0, operTwoStr.indexOf('/')));
    		denoTwo = stringToInt(operTwoStr.substring(operTwoStr.indexOf('/') + 1, operTwoStr.length()));
    	} else if (opTwoIsOnlyFrac == false && opTwoIsFrac == false) { //if values are only whole numbers
    		wholeTwo = stringToInt(operTwoStr);
    	}
    	// splits fractions into integers if any 
    	if (opOneIsFrac == true) {
    		wholeOne = stringToInt(operOneStr.substring(0, operOneStr.indexOf('_')));
    		numOne = stringToInt(operOneStr.substring(operOneStr.indexOf('_') + 1, operOneStr.indexOf('/')));
    		denoOne = stringToInt(operOneStr.substring(operOneStr.indexOf('/') + 1, operOneStr.length()));
    	}
    	if (opTwoIsFrac == true) {
    		wholeTwo = stringToInt(operTwoStr.substring(0, operTwoStr.indexOf('_')));
    		numTwo = stringToInt(operTwoStr.substring(operTwoStr.indexOf('_') + 1, operTwoStr.indexOf('/')));
    		denoTwo = stringToInt(operTwoStr.substring(operTwoStr.indexOf('/') + 1, operTwoStr.length()));
    	}
    	//changes numerator to negative value if whole number is negative
    	if (wholeOne < 0) {
    		numOne *= -1;
    	}
    	if (wholeTwo < 0) {
    		numTwo *= -1;
    	}
    	//Turn fractions into improper fractions if there are any fractions -- whole numbers to improper fractions as well -- TESTED WORKS
    	if (opOneIsFrac == true || numOne == 0) {
    		numOne = getImpropFrac(wholeOne, numOne, denoOne);
    		wholeOne = 0;
    	}
    	if (opTwoIsFrac == true || numTwo == 0) {
    		numTwo = getImpropFrac(wholeTwo, numTwo, denoTwo);
    		wholeTwo = 0;
    	}
    	//transfer to respective operators
    	if (operator == '+') {
    		//Addition -- ignore lowest common factor, just multiply denominators to find common denominator, simplify later using simplify method
    		int comDeno = denoOne * denoTwo;
    		int newNumOne = numOne * denoTwo;
    		int newNumTwo = numTwo * denoOne;
    		int finalNum = newNumOne + newNumTwo;
    		return simplify(finalNum, comDeno);
    		
    	} else if (operator == '-') {
    		//Subtraction -- ignore lowest common factor, just multiply denominators to find common denominator, simplify later using simplify method
    		int comDeno = denoOne * denoTwo;
    		int newNumOne = numOne * denoTwo;
    		int newNumTwo = numTwo * denoOne;
    		int finalNum = newNumOne - newNumTwo;
    		return simplify(finalNum, comDeno);
    		
    	} else if (operator == '/') {
    		return multiply(numOne, denoOne, denoTwo, numTwo);
    		
    	} else if (operator == '*') {
    		return multiply(numOne, denoOne, numTwo, denoTwo);
    		
    	} else if (operator == '=') { //just a little something i added because i was bored
    		//TODO FIX improper fraction check -- run through simplify method first, then check
    		String operOne = numOne + "/" + denoOne;
    		String operTwo = numTwo + "/" + denoTwo;
    		if (operOne.contentEquals(operTwo)) {
    			return "equivalent";
    		} else {
    			return "not equivalent";
    		}
    	} else {
    		return "error! -- Non-identifiable operator";
    	}
    }
    
    /* CUSTOM METHODS */
    
    //simple check if fraction
    public static boolean isMixedFraction (String input) {
    	if (input.contains("_") ) {
    		return true;
    	} else {
    		return false;
    	}
    }
    //simple check if fraction is only a fraction
    public static boolean isOnlyFraction (String input) {
    	if (input.contains("/") && !(input.contains("_"))) {
    		return true;
    	} else {
    		return false;
    	}
    }
    //String to integer DONE
    //Mark told me I could use parseInt AFTER I wrote all this out!!! :(
    //So this is my version of parseInt -- Enjoy!!
    public static int stringToInt(String input) {
    	int number = 0;
    	int places = input.length();
    	int curPlaceValue = places -1; //place value for exponent of 10
    	if (input.charAt(0) == '-') { //checks if string is negative
    		curPlaceValue--; //adjusts top limit accordingly to make up for the negative sign
    		for (int i = 1; i < places; i++) { //loops for necessary amount of place values
        		number += Character.getNumericValue(input.charAt(i)) * Math.pow(10, curPlaceValue); //adds given place value to total number (char to integer)
        		curPlaceValue --; //adjusts place value for next digit
        	}
    		return number * -1;
    	} else { //string is positive
    		for (int i = 0; i < places; i++) {
    			number += Character.getNumericValue(input.charAt(i)) * Math.pow(10, curPlaceValue);
    			curPlaceValue --; //adjusts place value for next digit
        	}
    		return number;
    	}
	}
    
    //impropFraction -- turns whole number and fraction into an improper fraction to do math on
    //input whole number, denominator, and numerator -- spits out new numerator as denominator will stay the same and whole will be set to 0
    public static int getImpropFrac (int whole,  int numerator, int denominator) {
    	int newNumerator = whole * denominator;
    	newNumerator += numerator;
    	return newNumerator;
    }

    //multiply -- basic fraction math, returns value as string -- needs simplifying
    public static String multiply (int numOne, int denoOne, int numTwo, int denoTwo) {
    	int numFinal = numOne * numTwo; //new numerator
    	int denoFinal = denoOne * denoTwo; //new denominator
    	return simplify(numFinal, denoFinal);
    }
    
    //simplify -- simplifies the fraction
    public static String simplify(int numerator, int denominator) {
    	int finalNum = numerator; //in case fraction is prime
    	int finalDen = denominator;
    	int finalWhole = 0;
    	boolean numIsNeg = false, denIsNeg = false; //simplifying loop only works for positive values -- change numerator/den positive temporarily and store value in booleans
    	//negative check
    	if (numerator < 0) {
    		numIsNeg = true;
    		numerator = Math.abs(numerator);
    	}
    	if (denominator < 0) {
    		denIsNeg = true;
    		denominator = Math.abs(denominator);
    	}
    	if (numerator == 0 && denominator == 0) { // if it ends up to be 0, it's 0
    		return "0";
    	}
    	//runs through every possibility for simplifying -- starts counting from top to ensure largest common factor
    	for (int i = numerator; i > 1; i--) {
    		if ((numerator % i == 0) && (denominator % i == 0)) {
    			finalNum = numerator / i;
    			finalDen = denominator / i;
    			break; //exit loop at first occurrence 
    		}
    	}
    	//if > 1; check for whole numbers
    	if (finalNum > finalDen) {
    		finalWhole = finalNum / finalDen;
    		finalNum = finalNum % finalDen;
    	}
    	//check if leftover fraction is 0 or not
    	if (finalNum == 0) {
    		if (numIsNeg == denIsNeg) {
    			return Integer.toString(finalWhole);
    		} else {
    			return "-" + finalWhole;
    		}
    	}
    	
    	//reassign negatives
    	finalNum = Math.abs(finalNum); //for some reason this fixes it from doing a double negative sign on front??
    	if (finalDen == 1) {
    		if (numIsNeg == denIsNeg) {
    			return Integer.toString(finalNum);
    		} else {
    			return "-" + finalNum;
    		}
    	}
    	if (numIsNeg == denIsNeg) {
    		if (finalWhole != 0) { //if a value other than 0 is stored in whole, add the "whole" value
    			return finalWhole + "_" + finalNum + "/" + finalDen;
    		} else { //if not, don't
    			return finalNum + "/" + finalDen;
    		}
    	} else {
    		if (finalWhole != 0) {
    			return "-" + finalWhole + "_" + finalNum + "/" + finalDen;
    		} else {
    			return "-" + finalNum + "/" + finalDen;
    		}
    	}

    }
    
}
