import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import backend.model.*;
import org.junit.jupiter.api.*;

public class FiguresTest {


    Figure rectangle = new Rectangle(new Point(5.0, 5.0), new Point(10.0, 15.0));
    Figure square = new Square(new Point(5.0, 5.0), new Point(10.0, 15.0));
    Figure ellipse = new Ellipse(new Point(5.0, 5.0), new Point(10.0, 15.0));
    Figure circle = new Circle(new Point(7.5, 7.5), 2.0);
    Figure line = new Line(new Point(5.0, 5.0), new Point(10.0, 15.0));


    Point p1 = new Point(7.0, 7.0);
    Point p2 = new Point(2.5, 2.5);
    Point p3 = new Point(20.0, 20.0);

    Point p4 = new Point(50.0,50.0);

     //poner belongs(p1), belongs(p1,p2), move, getxdiameter, getydiameter, gettopleft, get topright
    //para todas las figuras
    @Test
    void circle () {
        assertTrue(circle.belongs(p1));
        assertTrue(!circle.belongs(p2));
        assertTrue(!circle.belongs(p3));
        assertTrue(!circle.belongs(new Point(6.0,6.0)));

        assertTrue(!circle.belongs(p1,p3));
        assertTrue(circle.belongs(p2, p3));

        double radius = 2.0;

        assertEquals(2*radius,circle.getXDiameter());
        assertEquals(2*radius,circle.getYDiameter());

        assertTrue(circle.getTopLeft().equals(new Point(7.5-radius, 7.5-radius)));
        assertTrue(circle.getBottomRight().equals(new Point(7.5+radius, 7.5+radius)));

        circle.move(25.0,25.0);

        assertTrue(!circle.belongs(p2, p3));
        assertTrue(circle.belongs(p3, p4));
    }

    @Test
    void ellipse () {
        assertTrue(ellipse.belongs(p1));
        assertTrue(!ellipse.belongs(p2));
        assertTrue(!ellipse.belongs(p3));
        assertTrue(!ellipse.belongs(new Point(5.0,5.0)));

        assertTrue(ellipse.belongs(p2, p3));

        assertEquals(5.0,ellipse.getXDiameter());
        assertEquals(10.0,ellipse.getYDiameter());

        assertTrue(ellipse.getTopLeft().equals(new Point(5.0, 5.0)));
        assertTrue(ellipse.getBottomRight().equals( new Point(10.0, 15.0)));

        ellipse.move(25.0,25.0);

        assertTrue(!ellipse.belongs(p2,p3));
        assertTrue(ellipse.belongs(p3,p4));
    }

    @Test
    void line () {
        assertTrue(!line.belongs(p1));
        assertTrue(!line.belongs(p2));
        assertTrue(!line.belongs(p3));
        assertTrue(!line.belongs(new Point(5.0,5.0)));

        assertTrue(line.belongs(p2, p3));

        assertTrue(line.getTopLeft().equals(new Point(5.0, 5.0)));
        assertTrue(line.getBottomRight().equals( new Point(10.0, 15.0)));

        line.move(25.0,25.0);

        assertTrue(!line.belongs(p2,p3));
        assertTrue(line.belongs(p3,p4));
    }

    @Test
    void rectangle () {
        assertTrue(rectangle.belongs(p1));
        assertTrue(!rectangle.belongs(p2));
        assertTrue(!rectangle.belongs(p3));
        assertTrue(rectangle.belongs(new Point(5.0,5.0)));

        assertTrue(rectangle.belongs(p2, p3));

        assertEquals(5.0,rectangle.getXDiameter());
        assertEquals(10.0,rectangle.getYDiameter());

        assertTrue(rectangle.getTopLeft().equals(new Point(5.0, 5.0)));
        assertTrue(rectangle.getBottomRight().equals( new Point(10.0, 15.0)));

        rectangle.move(25.0,25.0);

        assertTrue(!rectangle.belongs(p2,p3));
        assertTrue(rectangle.belongs(p3,p4));
    }

    @Test
    void square () {
        assertTrue(square.belongs(p1));
        assertTrue(!square.belongs(p2));
        assertTrue(!square.belongs(p3));
        assertTrue(square.belongs(new Point(14.0,14.0)));

        assertTrue(square.belongs(p2, p3));

        assertEquals(10.0,square.getXDiameter());
        assertEquals(10.0,square.getYDiameter());

        assertTrue(square.getTopLeft().equals(new Point(5.0, 5.0)));
        assertTrue(square.getBottomRight().equals( new Point(15.0, 15.0)));

        square.move(25.0,25.0);

        assertTrue(!square.belongs(p2,p3));
        assertTrue(square.belongs(p3,p4));
    }
}
