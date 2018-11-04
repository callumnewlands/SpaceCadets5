import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import javax.imageio.ImageWriter;
import java.util.ArrayList;


// TODO: save as image -- maybe see canvas.snapshot();
// TODO: variable angularVelocity
// TODO: validation on parameter input from textboxes


public class RenderWindow extends Application
{
    private static final int WIDTH = 500;
    private static final float HALF_WIDTH = WIDTH / 2.0f;
    private static final int HEIGHT = 500;
    private static final float HALF_HEIGHT = HEIGHT / 2.0f;
    private ArrayList<Hypocycloid> hypocycloids = new ArrayList<>();
    private double theta = 0.0;
    private double angularVelocity = 1;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Canvas canvas = new Canvas(WIDTH, 500);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.translate(HALF_WIDTH, HALF_HEIGHT);

        graphics.setFill(Color.WHITE);
        graphics.fillRect(-HALF_WIDTH, -HALF_HEIGHT, WIDTH, HEIGHT);

        InputBox RBox = new InputBox();
        RBox.setLabelText("R:");
        RBox.setInitialValue("200");

        InputBox rBox = new InputBox();
        rBox.setLabelText("r:");
        rBox.setInitialValue("70");

        InputBox OBox = new InputBox();
        OBox.setLabelText("O:");
        OBox.setInitialValue("50");

        ColorPicker colourPicker = new ColorPicker();
        colourPicker.setValue(Color.RED);

        Button btnRun = new Button();
        btnRun.setText("New Hypercycloid");
        btnRun.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                hypocycloids.add(new Hypocycloid(RBox.getValueAsDouble(), rBox.getValueAsDouble(), OBox.getValueAsDouble(),
                        colourPicker.getValue(), theta, graphics));
            }
        });


        Button btnClear = new Button();
        btnClear.setText("Clear");
        btnClear.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               hypocycloids.clear();
               graphics.setFill(Color.WHITE);
               graphics.fillRect(-HALF_WIDTH, -HALF_HEIGHT, WIDTH, HEIGHT);
               theta = 0.0;
            }
        });

        VBox rightColumn = new VBox();
        rightColumn.setSpacing(15);
        rightColumn.getChildren().addAll(RBox, rBox, OBox, colourPicker, btnRun, btnClear);

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(canvas, rightColumn);

        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);

        //long startTime = System.nanoTime();
        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                //double t = (now - startTime) / 1000000000.0;
                for (Hypocycloid hypocycloid : hypocycloids)
                {
                    hypocycloid.paint(theta);
                }
                if (hypocycloids.size() > 0)
                {
                    theta += Math.PI / 100 * angularVelocity;
                }
            }
        }.start();

        theta = 0.0f;

        primaryStage.show();
    }


}
