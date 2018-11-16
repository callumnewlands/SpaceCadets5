import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Hypocycloid
{
    private GraphicsContext graphics;

    private double x;
    private double y;

    private double R;
    private double r;
    private double O;
    private Color colour;


    Hypocycloid(double R, double r, double O, Color colour, double initialTheta, GraphicsContext graphics)
    {
        this.R = R;
        this.r = r;
        this.O = O;
        this.colour = colour;
        this.graphics = graphics;
        this.x = (R-r)*Math.cos(initialTheta) + O * Math.cos(((R-r)/r)*initialTheta);
        this.y = (R-r)*Math.sin(initialTheta) - O * Math.sin(((R-r)/r)*initialTheta);
    }


    void paint(double theta)
    {
        double newX = (R-r)*Math.cos(theta) + O * Math.cos(((R-r)/r)*theta);
        double newY = (R-r)*Math.sin(theta) - O * Math.sin(((R-r)/r)*theta);

        this.graphics.setStroke(colour);
        this.graphics.strokeLine(x, y, newX, newY);

        this.x = newX;
        this.y = newY;
    }
}
