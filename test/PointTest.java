package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import backend.model.Point;
import org.junit.Test;
import org.junit.jupiter.api.*;

public class PointTest {
    double x = 1.5;
    double y = 1.5;
    Point p = new Point(1.5,1.5);

    @Test
    void get () {
        assertEquals(x,p.getX());
        assertEquals(y,p.getY());
    }

    @Test
    void set () {
        x = 2.3;
        y = 1.6;
        p.setX(2.30);
        p.setY(1.60);
        get();
    }

    @Test
    void move () {
        x=1.8;
        y=1.0;
        p.move(0.3,-0.5);
        get();
    }

    @Test
    void equals () {
        assert(p.equals(new Point(x,y)));
    }
}
