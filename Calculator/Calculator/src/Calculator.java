import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Arrays;


public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"}; 


    JFrame frame= new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    //A+B, A-B, A*B, A/B, A%B, A^B, √A
    String A= "0";
    String operator= "null";
    String B= "null";


    Calculator() {

    frame.setSize(boardWidth, boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    displayLabel.setBackground(customBlack);
    displayLabel.setForeground(Color.WHITE);
    displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
    displayLabel.setHorizontalAlignment(JLabel.RIGHT);
    displayLabel.setText("0");
    displayLabel.setOpaque(true);

    displayPanel.setLayout(new BorderLayout());
    displayPanel.add(displayLabel, BorderLayout.CENTER);
    frame.add(displayPanel, BorderLayout.NORTH);

    buttonPanel.setLayout(new GridLayout(5, 4));
    buttonPanel.setBackground(customBlack);
    

    frame.add(buttonPanel, BorderLayout.CENTER);

    for (int i = 0; i < buttonValues.length; i++) {
        JButton button = new JButton();

        String buttonValue = buttonValues[i];
        button.setText(buttonValue);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setFocusable(false);
        button.setBorder(new LineBorder(customBlack, 1));

        if (Arrays.asList(topSymbols).contains(buttonValue)) {
            button.setBackground(customLightGray);
            button.setForeground(customBlack);
        }
        else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
            button.setBackground(customOrange);
            button.setForeground(Color.WHITE);
        } 
            
        else {
            button.setBackground(customDarkGray);
            button.setForeground(Color.WHITE);
        }

        button.setOpaque(true);
        button.setBorderPainted(true);

    buttonPanel.add(button);

        buttonPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonValue = button.getText();
                
                if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                    // Handle right symbols
                    if (buttonValue == "="){
                        if (A!= null){
                            B= displayLabel.getText();
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(B);

                            if (operator == "+") {
                                double result = numA + numB;
                                displayLabel.setText(removeZeroDecimal(result));
                            } else if (operator == "-") {
                                double result = numA - numB;
                                displayLabel.setText(removeZeroDecimal(result));
                            } else if (operator == "×") {
                                double result = numA * numB;
                                displayLabel.setText(removeZeroDecimal(result));
                            } else if (operator == "÷") {
                                if (numB != 0) {
                                    double result = numA / numB;
                                    displayLabel.setText(removeZeroDecimal(result));
                                } else {
                                    displayLabel.setText("Error");
                                }
                            }
                            clearAll();
                        }
                    }
                    if ("÷×-+".contains(buttonValue)) {
                        if (operator == "null") {
                           A = displayLabel.getText();
                           displayLabel.setText("0");
                           B = "0";
                        }
                        operator = buttonValue;

                    }
                    


                } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                    // Handle top symbols
                    if (buttonValue == "AC") {
                       clearAll();
                       displayLabel.setText("0");
                    }else if (buttonValue == "+/-"){
                        double numDisplay = Double.parseDouble(displayLabel.getText());
                        numDisplay *= -1;
                        displayLabel.setText(removeZeroDecimal(numDisplay));
                    }
                    else if (buttonValue == "%"){
                        double numDisplay = Double.parseDouble(displayLabel.getText());
                        numDisplay *= 0.01;
                        displayLabel.setText(removeZeroDecimal(numDisplay));
                    }


                } else {//digits or .
                    if (buttonValue == "."){
                        if (!displayLabel.getText().contains(buttonValue)) {
                            displayLabel.setText(displayLabel.getText() + buttonValue);
                        }

                    }
                    else if ("0123456789".contains(buttonValue)){
                        if (displayLabel.getText() =="0") {
                            displayLabel.setText(buttonValue);
                        } else {
                            displayLabel.setText(displayLabel.getText() + buttonValue);
                        }
                    }
                   
                }
               
            }
        });
    }

    frame.setVisible(true);

    }
    void clearAll() {
        A = "0";
        operator = "null";
        B = "null";
    }
    String removeZeroDecimal(double num) {
        if (num == (long) num) {
            return String.format("%d", (long) num);
        } else {
            return String.format("%s", num);
        }
    }

}