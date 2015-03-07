package ro.vl_d.android.discolight.graph;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

public class LineMaker {
    private ChartInterpretor interpretor;
    private int leftMargin;
    private int bottomMargin;

    private int maxAllowedValue;
    private int canvasWidth;
    private int canvasHeight;

    private int stepPixels;

    public LineMaker(int leftMargin, int bottomMargin, int maxAllowedValue,
	    int canvasWidth, int canvasHeight, int stepPixels) {
	this.leftMargin = leftMargin;
	this.bottomMargin = bottomMargin;
	this.maxAllowedValue = maxAllowedValue;
	this.canvasHeight = canvasHeight;
	this.canvasWidth = canvasWidth;
	interpretor = new ChartInterpretor(leftMargin, bottomMargin,
		maxAllowedValue, (canvasWidth - leftMargin),
		(canvasHeight - bottomMargin), stepPixels);
    }

    /**
     * 
     * @param values
     *            the values registered so far
     * @return the lines to be drawn onto the canvas, with absolute coordinates
     */
    public List<Line> getLinesForValues(List<Integer> values) {
	List<Point> points = getPointsForValues(values);
	List<Line> lines = new ArrayList<Line>();
	for (int index = 1; index < points.size(); index++) {
	    lines.add(new Line(points.get(index - 1), points.get(index)));
	}
	return lines;
    }

    private List<Point> getPointsForValues(List<Integer> values) {

	List<Point> points = new ArrayList<Point>();
	int maxSteps = interpretor.getMaximumStepsWithinCanvasWidth();
	if (values.size() <= maxSteps) {
	    for (int step = 0; step < values.size(); step++) {
		points.add(getPoint(step, values.get(step)));
	    }
	} else {
	    int firstVisibleIndex = values.size()
		    - interpretor.getMaximumStepsWithinCanvasWidth();
	    for (int i = firstVisibleIndex; i < values.size(); i++) {
		int step = i - firstVisibleIndex;
		points.add(getPoint(step, values.get(i)));
	    }
	}
	return points;
    }

    private Point getPoint(int step, Integer value) {
	return new Point(interpretor.getXCoordinateForStep(step),
		interpretor.getYCoordinateForValue(value));
    }
}
