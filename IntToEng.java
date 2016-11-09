import java.util.HashMap;
import java.util.Map;

/**
 *  Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 *  2,147,483,647 Two Billion One Hundred Forty Seven Million Four Hundred Eigthy Three Thousand Six Hundred Forty Seven.
 *  For example,
 *  123 -> "One Hundred Twenty Three"
 *  12345 -> "Twelve Thousand Three Hundred Forty Five"
 *  1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * @author ShaoKun
 *
 */
 
public class Solution {
    Map<Integer, String> map = new HashMap<Integer, String>();

	public String numberToWords(int num) {
		if (num == 0) {
			return "Zero";
		}
		generateMap();
		StringBuilder result = new StringBuilder();
		
		int newNum = num / 1000000000;
		if (newNum > 0) {
			num = num % 1000000000;
			result.append(map.get(newNum)).append(" ").append("Billion");
		}
		
		newNum = num / 1000000;
		if (newNum > 0) {
			if (result.length() != 0) {
				result.append(" ");
			}
			result.append(convertHundred(newNum)).append(" ").append("Million");
		}
		num = num % 1000000;
		
		newNum = num / 1000;
		if (newNum > 0) {
			if (result.length() != 0) {
				result.append(" ");
			}
			result.append(convertHundred(newNum)).append(" ").append("Thousand");
		}
		
		num = num % 1000;
		if (num > 0) {
			if (result.length() != 0) {
				result.append(" ");
			}
			result.append(convertHundred(num));
		}
		return result.toString();
    }
	
	public String convertHundred(int num) {
		StringBuilder result = new StringBuilder();
		
		int newNum = num / 100;
		if (newNum > 0) {
			if (result.length() != 0) {
				result.append(" ");
			}
			result.append(map.get(newNum)).append(" ").append("Hundred");
		}
		num = num % 100;
		
		newNum = num / 10;
		if (newNum > 0) {
			if (result.length() != 0) {
				result.append(" ");
			}
			if (newNum > 1) {
				newNum = newNum * 10;
				result.append(map.get(newNum));
			} else {
				result.append(map.get(num));
				num = 0;
			}	
		}
		
		num = num % 10;
		if (num > 0) {
			if (result.length() != 0) {
				result.append(" ");
			}
			result.append(map.get(num));
		}
		return result.toString();
	}
	
	public void generateMap() {
		map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
	}
}