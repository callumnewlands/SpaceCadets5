import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

class InputBox extends HBox
{

    private Text label = new Text();
    private TextField textBox = new TextField();

    InputBox()
    {
        this.setSpacing(10);
        this.getChildren().addAll(label, textBox);
    }

    void setLabelText(String str)
    {
        this.label.setText(str);
    }

    void setInitialValue(String str)
    {
        this.textBox.setText(str);
    }

    String getValue()
    {
        return this.textBox.getText();
    }

    Double getValueAsDouble()
    {
        return Double.valueOf(this.getValue());
    }
}
