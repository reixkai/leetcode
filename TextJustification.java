import java.util.LinkedList;
import java.util.List;
/**
 * Given an array of words and a length L, 
 * format the text such that each line has exactly L characters and is fully (left and right) justified.
 *  
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
 * Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *  
 * Extra spaces between words should be distributed as evenly as possible. 
 * If the number of spaces on a line do not divide evenly between words, 
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *  
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *  
 * For example,
 * 	words: ["This", "is", "an", "example", "of", "text", "justification."]
 * 	L: 16.
 *  
 * Return the formatted lines as:
 * 	[
 * 		"This    is    an",
 * 		"example  of text",
 * 		"justification.  "
 * 	]
 *  
 * Note: Each word is guaranteed not to exceed L in length. 
 *
 */

public class Solution {
    final int sizeOfSpace = 1;
	final String space = " ";
	
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> justified = new LinkedList<String>();
		List<String> currentWords = new LinkedList<String>();
		int currentLineSize = 0;
		boolean endLine = false;
		boolean lastLine = false;
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i];

			if (currentLineSize == 0 && currentWords.size() == 0) { // new line
				currentLineSize += word.length();
				currentWords.add(word);
			} else if (word.length() + currentLineSize < maxWidth || currentLineSize == 0) {
				currentLineSize = currentLineSize + sizeOfSpace + word.length();
				currentWords.add(word);
			} else { // cannot fit word so new line needed
				endLine = true;
			}
			
			if (i == words.length - 1) {
				if (currentWords.contains(word)) {
					lastLine = true;
				}
			}

			if ((maxWidth - currentLineSize) < 2 || endLine || lastLine) {	// new line
				int spaces = maxWidth - currentLineSize;
				int spaceEach = spaces;
				int numSpaceSpots = currentWords.size() - 1;
				int spaceLeftOver = 0;
				StringBuilder line = new StringBuilder("");
				if (currentWords.size() > 1) {
					spaceEach = spaces / numSpaceSpots;
					spaceLeftOver = spaces - (spaceEach * numSpaceSpots);
					
					for (int j = 0; j < currentWords.size(); j++) {
						line.append(currentWords.get(j));
						
						if (lastLine) {
							if (j != currentWords.size() - 1) {
								line.append(space);
							} else {
								for (int k = 0; k < spaces; k++) {
									line.append(space);
								}
							}
						} else if (j != currentWords.size() - 1) {
							line.append(space);
							for (int k = 0; k < spaceEach; k++) {
								line.append(space);
							}
							
							if (spaceLeftOver > 0) {
								line.append(space);
								spaceLeftOver--;
							}
						}
						
					}
				} else if (currentWords.size() == 1){
					line.append(currentWords.get(0));
					for (int j = 0; j < spaces; j++) {
						line.append(space);
					}
				}
				currentWords.clear();
				justified.add(line.toString());
				
				currentLineSize = 0;
						
				if (endLine) { // couldn't fit the word
					endLine = false;
					i--; // do not 'finish' word
				}
			}

		}
		
		return justified;
	}
}