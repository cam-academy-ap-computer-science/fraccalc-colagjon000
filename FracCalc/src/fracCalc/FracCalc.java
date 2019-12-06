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
 *  - userBoi != "quit", go on
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
 *  	- goes through 10 if statements for each base 10 number
 *  	- OUTPUT returns a single integer value
 *  - impropFrac
 *  - operator sort
 *  - multiply
 *  - divide
 *  - addition
 *  - subtraction
 */

public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation

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
        // TODO: Implement this function to produce the solution to the input
        
        return "";
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
