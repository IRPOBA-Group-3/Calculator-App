import javax.swing.*;
import java.awt.*;

public class CalculatorApp extends JFrame {

    private final JTextField display;
    private final Calculator calculator;

    private double firstNumber = 0;
    private String currentOperation = "";
    private boolean startNewNumber = true;

    public CalculatorApp() {
        calculator = new Calculator();

        setTitle("Modern Calculator");
        setSize(360, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(30, 30, 30));

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setBackground(new Color(45, 45, 45));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBackground(new Color(30, 30, 30));

        String[] buttons = {
                "C", "Del", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        for (String text : buttons) {
            JButton button = createButton(text);
            button.addActionListener(e -> handleButtonClick(text));
            buttonPanel.add(button);
        }

        mainPanel.add(display, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);

        if (text.equals("/") || text.equals("*") || text.equals("-") || text.equals("+") || text.equals("=")) {
            button.setBackground(new Color(255, 140, 0));
        } else if (text.equals("C") || text.equals("Del") || text.equals("%")) {
            button.setBackground(new Color(90, 90, 90));
        } else {
            button.setBackground(new Color(60, 60, 60));
        }

        return button;
    }

    private void handleButtonClick(String value) {
        switch (value) {
            case "C":
                clearAll();
                break;
            case "Del":
                deleteLastCharacter();
                break;
            case "+/-":
                toggleSign();
                break;
            case "%":
                applyPercent();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperation(value);
                break;
            case "=":
                calculateResult();
                break;
            case ".":
                addDecimalPoint();
                break;
            default:
                appendNumber(value);
                break;
        }
    }

    private void appendNumber(String number) {
        if (display.getText().equals("Error") || display.getText().equals("Cannot divide by 0")) {
            display.setText("0");
        }

        if (startNewNumber) {
            display.setText(number);
            startNewNumber = false;
        } else {
            if (display.getText().equals("0")) {
                display.setText(number);
            } else {
                display.setText(display.getText() + number);
            }
        }
    }

    private void addDecimalPoint() {
        if (display.getText().equals("Error") || display.getText().equals("Cannot divide by 0")) {
            display.setText("0");
        }

        if (startNewNumber) {
            display.setText("0.");
            startNewNumber = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void setOperation(String operation) {
        try {
            firstNumber = Double.parseDouble(display.getText());
            currentOperation = operation;
            startNewNumber = true;
        } catch (NumberFormatException ex) {
            display.setText("Error");
            startNewNumber = true;
        }
    }

    private void calculateResult() {
        try {
            if (currentOperation.isEmpty()) {
                return;
            }

            double secondNumber = Double.parseDouble(display.getText());
            double result;

            switch (currentOperation) {
                case "+":
                    result = calculator.add(firstNumber, secondNumber);
                    break;
                case "-":
                    result = calculator.subtract(firstNumber, secondNumber);
                    break;
                case "*":
                    result = calculator.multiply(firstNumber, secondNumber);
                    break;
                case "/":
                    result = calculator.divide(firstNumber, secondNumber);
                    break;
                default:
                    return;
            }

            display.setText(formatResult(result));
            firstNumber = result;
            currentOperation = "";
            startNewNumber = true;

        } catch (NumberFormatException ex) {
            display.setText("Error");
            startNewNumber = true;
        } catch (IllegalArgumentException ex) {
            display.setText("Cannot divide by 0");
            startNewNumber = true;
        }
    }

    private void applyPercent() {
        try {
            double value = Double.parseDouble(display.getText());
            double result = calculator.percent(value);
            display.setText(formatResult(result));
            startNewNumber = true;
        } catch (NumberFormatException ex) {
            display.setText("Error");
            startNewNumber = true;
        }
    }

    private void toggleSign() {
        try {
            double value = Double.parseDouble(display.getText());
            double result = calculator.negate(value);
            display.setText(formatResult(result));
        } catch (NumberFormatException ex) {
            display.setText("Error");
            startNewNumber = true;
        }
    }

    private void deleteLastCharacter() {
        String text = display.getText();

        if (text.equals("Error") || text.equals("Cannot divide by 0")) {
            display.setText("0");
            startNewNumber = true;
            return;
        }

        if (!startNewNumber && text.length() > 1) {
            display.setText(text.substring(0, text.length() - 1));
        } else {
            display.setText("0");
            startNewNumber = true;
        }
    }

    private void clearAll() {
        display.setText("0");
        firstNumber = 0;
        currentOperation = "";
        startNewNumber = true;
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        }
        return String.format("%.10f", result)
                .replaceAll("0+$", "")
                .replaceAll("\\.$", "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorApp::new);
    }
}
