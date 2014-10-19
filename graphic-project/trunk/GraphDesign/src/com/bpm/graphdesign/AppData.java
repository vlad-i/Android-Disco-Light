/**This class will hold all the necessary variable values*/
/**
 * At first, things will work only in Vertical(Landscape) mode.
 * */
package com.bpm.graphdesign;

public class AppData {

	/**Activity Orientation*/
	private static int orientation = -1;
	
	/**Maximum recorded value*/
	private static Double maxValue = Double.MIN_VALUE;
	
	/**Minimum recorded value*/
	private static Double minValue = Double.MAX_VALUE;
	
	/**The height of the graph*/
	private static Double graphHeight = 0D;
	
	/**The ratio used to position the numbers accordingly*/
	private static Double ratio = 0D;
	
	/**Window width and height for horizontal orientation
	 * !!!!!!!!For further use
	 * */
	private static int windowWidthH = -1;
	private static int windowHeightH = -1;
	
	/**Window width and height for vertical orientation*/
	private static int windowWidthV = -1;
	private static int windowHeightV = -1;
	
	
	
	
	public static int getWindowWidthH() {
		return windowWidthH;
	}
	public static void setWindowWidthH(int windowWidthH) {
		AppData.windowWidthH = windowWidthH;
	}
	
	
	
	
	public static int getWindowHeightH() {
		return windowHeightH;
	}
	public static void setWindowHeightH(int windowHeightH) {
		AppData.windowHeightH = windowHeightH;
	}
	
	
	
	
	
	public static int getWindowHeightV() {
		return windowHeightV;
	}
	public static void setWindowHeightV(int windowHeightV) {
		AppData.windowHeightV = windowHeightV;
	}
	
	
	
	public static int getWindowWidthV() {
		return windowWidthV;
	}
	public static void setWindowWidthV(int windowWidthV) {
		AppData.windowWidthV = windowWidthV;
	}
	
	
	
	public static int getOrientation() {
		return orientation;
	}
	public static void setOrientation(int orientation) {
		AppData.orientation = orientation;
	}
	
	
	
	
	public static Double getMaxValue() {
		return maxValue;
	}
	public static void setMaxValue(Double maxValue) {
		AppData.maxValue = maxValue;
	}
	
	
	
	public static Double getMinValue() {
		return minValue;
	}
	public static void setMinValue(Double minValue) {
		AppData.minValue = minValue;
	}
	
	
	
	public static Double getGraphHeight() {
		return graphHeight;
	}
	
	
	
	public static Double getRatio() {
		return ratio;
	}
	public static void setRatio() {
		AppData.ratio = AppData.windowHeightV/(AppData.maxValue - AppData.minValue);
	}
	
	
}
