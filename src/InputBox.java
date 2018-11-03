import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;



public class InputBox extends HBox
{

    private Text label = new Text();
    private TextField textBox = new TextField();

    public InputBox()
    {
        this.setSpacing(10);
        this.getChildren().addAll(label, textBox);
    }

    public void setLabelText(String str)
    {
        label.setText(str);
    }

    public void setInitialValue(String str)
    {
        textBox.setText(str);
    }

}
