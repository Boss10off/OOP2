package CurrencyCalculatorApp;

import CurrencyCalculatorApp.CurrencyCalculatorGUI.CalculatorGUI;

import javax.swing.*;

public class AppCurrencyCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
