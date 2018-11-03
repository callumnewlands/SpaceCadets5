import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Hypocycloid
{
    private GraphicsContext graphics;
    private double CANVAS_HEIGHT;
    private double CANVAS_WIDTH;

    private double x;
    private double y;

    private double R;
    private double r;
    private double O;
    private Color colour;


    public Hypocycloid(double R, double r, double O, Color colour, double initialTheta, GraphicsContext graphics)
    {
        this.R = R;
        this.r = r;
        this.O = O;
        this.colour = colour;
        this.graphics = graphics;
        CANVAS_HEIGHT = graphics.getCanvas().getHeight();
        CANVAS_WIDTH = graphics.getCanvas().getWidth();
        x = (R-r)*Math.cos(initialTheta) + O * Math.cos(((R-r)/r)*initialTheta);
        y = (R-r)*Math.sin(initialTheta) - O * Math.sin(((R-r)/r)*initialTheta);
    }


    public void paint(double theta)
    {
        double newX = (R-r)*Math.cos(theta) + O * Math.cos(((R-r)/r)*theta);
        double newY = (R-r)*Math.sin(theta) - O * Math.sin(((R-r)/r)*theta);

        graphics.setStroke(colour);
        graphics.strokeLine(x, y, newX, newY);
        //graphics.setFill(Color.BLUE);
        //graphics.fillOval(newX, newY, 2, 2);
        x = newX;
        y = newY;
    }
}
