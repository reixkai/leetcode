/**
 * Validate if a given string is numeric.
 * 	Some examples:
 * 	"0" => true
 * 	" 0.1 " => true
 * 	"abc" => false
 * 	"1 a" => false
 * 	"2e10" => true
 * 
 * Note: It is intended for the problem statement to be ambiguous. 
 * You should gather all requirements up front before implementing one. 
 */
public class ValidNumber {
	public boolean isNumber(String s) {
        return s.matches("\\s*[-|+]?((\\d*[.]?\\d+([e][+|-]?\\d+)?)|(\\d+[.]?\\d*([e][+|-]?\\d+)?))\\d*\\s*");
    }
}
