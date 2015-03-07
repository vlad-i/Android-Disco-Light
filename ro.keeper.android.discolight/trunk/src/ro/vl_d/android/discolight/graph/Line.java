package ro.vl_d.android.discolight.graph;

import android.graphics.Point;

public class Line {
    private Point[] data = new Point[2];

    public Line(Point p1, Point p2) {
	data[0] = p1;
	data[1] = p2;
    }

    public Point getFirstPoint() {
	return data[0];
    }

    public Point getSecondPoint() {
	return data[1];
    }
}
