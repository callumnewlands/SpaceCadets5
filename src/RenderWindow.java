import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Random;


// TODO: save as image -- maybe see canvas.snapshot();
// TODO: validation on parameter input from text-boxes


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
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
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

        Slider velocitySlider = new Slider();
        velocitySlider.setMin(1);
        velocitySlider.setMax(30);
        velocitySlider.setValue(10);

        Button btnRun = new Button();
        btnRun.setText("New Hypercycloid");
        btnRun.setOnAction(new EventHandler<>()
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
        btnClear.setOnAction(new EventHandler<>()
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


        Button btnRandom = new Button();
        btnRandom.setText("Generate a random curve");
        btnRandom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Random rnd = new Random();
                hypocycloids.add(new Hypocycloid(rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200), colourPicker.getValue(), theta, graphics));

            }
        });


        VBox rightColumn = new VBox();
        rightColumn.setSpacing(15);
        rightColumn.getChildren().addAll(RBox, rBox, OBox, colourPicker, velocitySlider, btnRun, btnClear, btnRandom);

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(canvas, rightColumn);

        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);

        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                angularVelocity = velocitySlider.getValue() / 10.0;

                for (Hypocycloid hypocycloid : hypocycloids)
                {
                    hypocycloid.paint(theta);
                }

                if (hypocycloids.size() > 0)
                {
                    theta += (Math.PI / 100) * angularVelocity;
                }
            }
        }.start();

        theta = 0.0f;

        primaryStage.show();
    }


}
