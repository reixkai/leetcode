/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
import java.util.Map;
import java.util.HashMap;

class Point {
	int x;
	int y;
	Point() { x = 0; y = 0; }
	Point(int a, int b) { x = a; y = b; }
}

public class Solution {
	// for every point, get its slope to another point and store it in a hashmap
	// store the max number points found per slope each time it increases
	// after all points have been exhausted, return the max number
	public int maxPoints(Point[] points) {
		if (points == null) {
			return 0;
		}
		if (points.length < 3) {
			return points.length;
		}

		int max = 2;

		for (int i = 0; i < points.length; i++) {
			Map<Double, Integer> slopeMap = new HashMap<Double, Integer>();
			int samePoints = 0;
			int currentMax = 1;
			for (int j = 0; j < points.length; j++) {
				if (i != j) {
					double denom = (points[i].x - points[j].x);
					double numerator = (points[i].y - points[j].y);
					double slope = 0;
					if (denom == 0 && numerator == 0) {
						// same point 
						samePoints++;
					} else {
						if (denom == 0) {
							slope = Double.NaN;
						} else {
							slope = numerator / denom;
						}
						int numPoints = 2;
						if (slopeMap.containsKey(slope)) {
							numPoints = slopeMap.get(slope);
							numPoints = numPoints + 1;
							if (numPoints > max) {
								max = numPoints;
							}
							if (numPoints > currentMax) {
								currentMax = numPoints;
							}
						}
						slopeMap.put(slope, numPoints);
					}
				}
			}
			if (samePoints > 0) {
				currentMax = currentMax + samePoints;
			}
			if (currentMax > max) {
				max = currentMax;
			}
		}
		return max;		
	}
}