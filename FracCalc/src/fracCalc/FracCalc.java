package fracCalc;
import java.util.*;
/* Outline!!!
 * Input value -- 
 * 	- One string
 *  - New values separated by a single space (Look for spaces!!)
 * Flow --
 * 
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
 *  	- (For extra credit -- continue process of operand, operator, operand, and so on)
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
 *  - addition
 *  - subtraction
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
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
    	//check if operation is being divided by zero
    	if (input.endsWith("/ 0")) {
    		return "Error! -- You tried to divide by Zero! No!!";
    	}
    	//check if operation is being multiplied by zero
    	if (input.endsWith("* 0") || input.startsWith("0 *")) {
    		return "0";
    	}
        //break up input string into operands and operators
    	//look for spaces
    	int spaceLocation = input.indexOf(' ');
    	int secondSpaceLocation = input.lastIndexOf(' ');
    	
    	//operOneStr = Operand One String version
    	String operOneStr = input.substring(0, spaceLocation);
    	//System.out.println(operOneStr);
    	//operTwoStr = Operand two String Version
    	//CHECKPOINT ONE
    	String operTwoStr = input.substring(secondSpaceLocation + 1, input.length());
    	//System.out.println(operTwoStr);
    	char operator = input.charAt(spaceLocation + 1);
    	// look if fraction
    	boolean opOneIsFrac = isFraction(operOneStr);
    	boolean opTwoIsFrac = isFraction(operTwoStr);
    	boolean opOneIsOnlyFrac = isOnlyFraction(operOneStr);
    	boolean opTwoIsOnlyFrac = isOnlyFraction(operTwoStr);
    	    	
    	//initialize all math variables
    	int wholeOne = 0;
    	int numOne = 0;
    	int denoOne = 1; //denominator equals one no matter what
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
    	//CHECKPOINT TWO!!!
    	//Turn fractions into improper fractions if there are any fractions -- whole numbers to improper fractions as well
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
    		
    	} else if (operator == '-') {
    		
    	} else if (operator == '/') {
    		
    	} else if (operator == '*') {
    		
    	} else {
    		return "error! -- Non-identifiable operator";
    	}
    	
        return "whole:" + wholeTwo + " numerator:" + numTwo + " denominator:" + denoTwo;
    }
    //simple check if fraction
    public static boolean isFraction (String input) {
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
    //sortOperator -- sorts operators
    
    //multiply -- basic fraction math, returns value as string
    
    //divide -- inverse of multiply
    
    //add
    
    //subtract
    
    //simplify -- simplifies the fraction
    
}
