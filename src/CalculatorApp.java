import javax.swing.*;
import java.awt.*;

public class CalculatorApp extends JFrame {

    private final JTextField firstNumberField;
    private final JTextField secondNumberField;
    private final JLabel resultLabel;
    private final Calculator calculator

    public CalculatorApp() {
        calculator = new Calculator();

        setTitle("Calculator App");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        firstNumberField = new JTextField();
        secondNumberField = new JTextField();
        resultLabel = new JLabel("Result: ");

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");

        addButton.addActionListener(e -> calculate("add"));
        subtractButton.addActionListener(e -> calculate("subtract"));
        multiplyButton.addActionListener(e -> calculate("multiply"));
        divideButton.addActionListener(e -> calculate("divide"));

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("First number:"));
        add(firstNumberField);

        add(new JLabel("Second number:"));
        add(secondNumberField);

        add(addButton);
        add(subtractButton);

        add(multiplyButton);
        add(divideButton);

        add(resultLabel);

        setVisible(true);
    }

    private void calculate(String operation) {
        try {
            double a = Double.parseDouble(firstNumberField.getText());
            double b = Double.parseDouble(secondNumberField.getText());
            double result = 0;

            switch (operation) {
                case "add":
                    result = calculator.add(a, b);
                    break;
                case "subtract":
                    result = calculator.subtract(a, b);
                    break;
                case "multiply":
                    result = calculator.multiply(a, b);
                    break;
                case "divide":
                    result = calculator.divide(a, b);
                    break;
            }

            resultLabel.setText("Result: " + result);

        } catch (NumberFormatException ex) {
            resultLabel.setText("Result: Invalid number");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText("Result: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorApp::new);
    }
}
