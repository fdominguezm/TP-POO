package backend;

import backend.model.*;

public class FigureTester {
    public static void main(String[] args) {
        Figure rectangle = new Rectangle(new Point(0.5, 3.1), new Point(9.8, 15.9));
        Figure square = new Square(new Point(0.5, 3.1), new Point(9.8, 15.9));
        Figure ellipse = new Ellipse(new Point(2.5, 4.1), new Point(15.8, 20.9));
        Figure circle = new Circle(new Point(2.5, 4.1), 6.0);
        Figure line = new Line(new Point(2.5, 4.1), new Point(15.8, 20.9));

        System.out.println(rectangle);
        System.out.println(square);
        System.out.println(ellipse);
        System.out.println(circle);
        System.out.println(line);
        System.out.println();

        assert(rectangle.getXDiameter() == 9.3);
        assert(rectangle.getYDiameter() == 12.8);
        assert(rectangle.getTopLeft().equals(new Point(0.5, 3.1)));
        assert(rectangle.getBottomRight().equals(new Point(9.8, 15.9)));

        Point p1 = new Point(2.4, 6.1);
        Point p2 = new Point(0.0, 1.1);
        Point p3 = new Point(21.1, 22.1);

        assert(p1.getX() == 2.4);
        assert(p1.getY() == 6.1);

        // chequeo si p1 esta dentro de las figuras
        assert(rectangle.belongs(p1));
        assert(square.belongs(p1));
        assert(!ellipse.belongs(p1));
        assert(circle.belongs(p1));
        assert(!line.belongs(p1));

        // chequeo si las figuras estan adentro del rectangulo formado por los puntos p2 y p3
        assert(rectangle.belongs(p2, p3));
        assert(square.belongs(p2, p3));
        assert(ellipse.belongs(p2, p3));
        assert(circle.belongs(p2, p3));
/*
        p1.setX(1.0);
        p1.setY(2.0);
*/
        assert(p1.getX() == 1.0);
        assert(p1.getY() == 2.0);
/*
        System.out.println("Moviendo las figuras en x = 4, y = 7 coordenadas.");
        System.out.println();
        System.out.println(rectangle + " movido a ");
        rectangle.move(4.0, 7.0);
        System.out.println(rectangle);
        System.out.println();
        System.out.println(square + " movido a ");
        square.move(4.0, 7.0);
        System.out.println(square);
        System.out.println();
        System.out.println(ellipse + " movido a ");
        ellipse.move(4.0, 7.0);
        System.out.println(ellipse);
        System.out.println();
        System.out.println(circle + " movido a ");
        circle.move(4.0, 7.0);
        System.out.println(circle);
        System.out.println();
        System.out.println(line + " movido a ");
        line.move(4.0, 7.0);
        System.out.println(line);
        System.out.println();
*/
    }
}
