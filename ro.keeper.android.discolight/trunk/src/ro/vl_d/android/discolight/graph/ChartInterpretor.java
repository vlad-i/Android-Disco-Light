package ro.vl_d.android.discolight.graph;

import ro.vl_d.android.discolight.BuildConfig;

/**
 * This is a class responsible for translating scalar integer values into a
 * graphical representation on a chart.
 * 
 * @author Vlad
 *
 */
public class ChartInterpretor {
    private int bottomMargin;
    private int leftMargin;
    private int maxAllowedValue;
    private int innerCanvasWidth;
    private int innerCanvasHeight;
    private int stepPixels;

    public ChartInterpretor(int leftMargin, int bottomMargin,
	    int maxAllowedValue, int innerCanvasWidth, int innerCanvasHeight,
	    int stepPixels) {
	this.bottomMargin = bottomMargin;
	this.leftMargin = leftMargin;
	this.maxAllowedValue = maxAllowedValue;
	this.innerCanvasWidth = innerCanvasWidth;
	this.innerCanvasHeight = innerCanvasHeight;
	this.stepPixels = stepPixels;

    }

    public int getMaximumStepsWithinCanvasWidth() {
	return innerCanvasWidth / stepPixels;
    }

    public int getYCoordinateForValue(int value) {
	if (BuildConfig.DEBUG && value > maxAllowedValue) {
	    throw new AssertionError();
	}
	int y = (int) (getPartOfMaxValue(value) * ((double) innerCanvasHeight));
	return y;
    }

    public int getXCoordinateForStep(int stepNumber) {
	int x;
	if (isStepNumberOutOfScreen(stepNumber)) {
	    // in this scenario, the X will always correspond to the last step
	    x = leftMargin + getLastStepXCoordinate();
	} else {
	    x = leftMargin + stepNumber * stepPixels;
	}
	return x;
    }

    /**
     * @param value
     * @return the part the given value represents from the instance's
     *         {@link ChartInterpretor#maxAllowedValue}
     */
    private double getPartOfMaxValue(int value) {
	return (((double) maxAllowedValue - value) / ((double) maxAllowedValue));
    }

    private int getLastStepXCoordinate() {
	// all below operands are integers, therefore this shows the greatest
	// number smaller than innerCanvasWidth that is divisible by stepPixels
	return (innerCanvasWidth / stepPixels) * stepPixels;
    }

    private boolean isStepNumberOutOfScreen(int stepNumber) {
	return stepNumber * stepPixels > innerCanvasWidth;
    }
}
