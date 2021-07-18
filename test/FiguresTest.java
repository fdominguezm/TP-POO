package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import backend.model.*;
import org.junit.jupiter.api.*;

public class FiguresTest {

    Point tpRect = new Point(0.5, 3.1);
    Point btmRect = new Point(9.8, 15.9);
    Point tpOval = new Point(2.5, 4.1);
    Point btmOval = new Point(15.8, 20.9);
    double radius = 6.0;

    Figure rectangle = new Rectangle(new Point(0.5, 3.1), new Point(9.8, 15.9));
    Figure square = new Square(new Point(0.5, 3.1), new Point(9.8, 15.9));
    Figure ellipse = new Ellipse(new Point(2.5, 4.1), new Point(15.8, 20.9));
    Figure circle = new Circle(new Point(2.5, 4.1), 6.0);
    Figure line = new Line(new Point(2.5, 4.1), new Point(15.8, 20.9));


    Point p1 = new Point(2.4, 6.1);
    Point p2 = new Point(0.0, 1.1);
    Point p3 = new Point(21.1, 22.1);

     //poner belongs(p1), belongs(p1,p2), move, getxdiameter, getydiameter, gettopleft, get topright
    //para todas las figuras
    @Test
    void circle () {
        assert(circle.belongs(p1));
        assert(circle.belongs(p2, p3));
        assertEquals(2*radius,circle.getXDiameter());
        assertEquals(2*radius,circle.getYDiameter());
        assert(circle.getTopLeft().equals(new Point(2.5-radius, 4.1-radius)));
        assert(circle.getBottomRight().equals(new Point(9.8+radius, 15.9+radius)));
    }

    @Test
    void ellipse () {
        assert(ellipse.belongs(p1));
        assert(ellipse.belongs(p2, p3));
        assertEquals(13.3,ellipse.getXDiameter());
        assertEquals(16.8,ellipse.getYDiameter());
        assert(ellipse.getTopLeft().equals(new Point(2.5, 4.1)));
        assert(ellipse.getBottomRight().equals( new Point(15.8, 20.9)));
    }

    @Test
    void line () {
        assert(!line.belongs(p1));
        assert(line.belongs(p2,p3));
        assertEquals(13.3,line.getXDiameter());
        assertEquals(16.8,line.getYDiameter());
        assert(line.getTopLeft().equals(new Point(2.5, 4.1)));
        assert(line.getBottomRight().equals(new Point(15.8, 20.9)));
    }

    @Test
    void rectangle () {
        assert(rectangle.belongs(p1));
        assert(rectangle.belongs(p2, p3));
        assertEquals(9.3,rectangle.getXDiameter());
        assertEquals(12.8,rectangle.getYDiameter());
        assert(rectangle.getTopLeft().equals(new Point(0.5, 3.1)));
        assert(rectangle.getBottomRight().equals(new Point(9.8, 15.9)));
    }

    @Test
    void square () {
        assert(square.belongs(p1));
        assert(square.belongs(p2, p3));
        assertEquals(9.3,square.getXDiameter());
        assertEquals(12.8,square.getYDiameter());
        assert(square.getTopLeft().equals(new Point(0.5, 3.1)));
        assert(square.getBottomRight().equals(new Point(9.8, 15.9)));
    }
}
