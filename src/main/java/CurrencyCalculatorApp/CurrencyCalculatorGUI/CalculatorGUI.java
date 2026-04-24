package CurrencyCalculatorApp.CurrencyCalculatorGUI;

import CurrencyCalculatorApp.CurrencyCalculator.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private Calculate currentCalculator;
    private final Calculator dynamicCalculator;

    private JComboBox<String> fromBox, toBox;
    private JTextField inputField;
    private JLabel resultLabel;
    private JRadioButton enumToggle;

    // --- GUI Constructor ---
    public CalculatorGUI() {
        dynamicCalculator = new Calculator();
        currentCalculator = dynamicCalculator; // Start with the class version

        setTitle("Currency Converter - Implementation Switcher");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 300);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Row 0: Implementation Switcher
        JPanel togglePanel = new JPanel();
        enumToggle = new JRadioButton("Use Fixed Enum Mode (No 'Add' allowed)");
        enumToggle.addActionListener(e -> {
            if (enumToggle.isSelected()) {
                // Switch to Enum constant (using EURO as the entry point for the logic)
                currentCalculator = BaseCurrencyEnum.EURO;
            } else {
                currentCalculator = dynamicCalculator;
            }
            updateDropdownModels();
            updateResult();
        });
        togglePanel.add(enumToggle);

        // Row 1: Dropdowns
        JPanel dropPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        fromBox = new JComboBox<>();
        toBox = new JComboBox<>();
        updateDropdownModels();
        dropPanel.add(fromBox);
        dropPanel.add(toBox);

        // Row 3: Input and Result
        JPanel ioPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        inputField = new JTextField("1.0");
        resultLabel = new JLabel("---", SwingConstants.CENTER);
        ioPanel.add(inputField);
        ioPanel.add(resultLabel);

        // --- Listeners ---
        ActionListener comboListener = e -> {
            String selected = (String) ((JComboBox) e.getSource()).getSelectedItem();
            if ("+ Add New...".equals(selected)) {
                openAddCurrencyDialog();
            } else {
                updateResult();
            }
        };

        fromBox.addActionListener(comboListener);
        toBox.addActionListener(comboListener);

        inputField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateResult(); }
            public void removeUpdate(DocumentEvent e) { updateResult(); }
            public void changedUpdate(DocumentEvent e) { updateResult(); }
        });

        add(togglePanel);
        add(new JLabel("Select Currencies:", SwingConstants.CENTER));
        add(dropPanel);
        add(ioPanel);

        setLocationRelativeTo(null);
        setVisible(true);
        updateResult();
    }

    private void updateResult() {
        try {
            double amount = Double.parseDouble(inputField.getText().trim());
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();

            if (from != null && to != null && !from.contains("+")) {
                // This call works for BOTH the class and the enum now
                double res = currentCalculator.calculate(from, to, amount);
                resultLabel.setText(String.format("%.2f", res));
            }
        } catch (Exception e) {
            resultLabel.setText("---");
        }
    }

    private void updateDropdownModels() {
        String[] names;
        boolean allowAdd = true;

        if (currentCalculator instanceof BaseCurrencyEnum) {
            // Get names from Enum
            BaseCurrencyEnum[] enums = BaseCurrencyEnum.values();
            names = new String[enums.length];
            for (int i = 0; i < enums.length; i++) names[i] = enums[i].getName();
            allowAdd = false; // Disable adding for Enum
        } else {
            // Get names from the original Calculator class
            names = dynamicCalculator.getCurrencyNames();
        }

        DefaultComboBoxModel<String> modelFrom = new DefaultComboBoxModel<>(names);
        DefaultComboBoxModel<String> modelTo = new DefaultComboBoxModel<>(names);

        if (allowAdd) {
            modelFrom.addElement("+ Add New...");
            modelTo.addElement("+ Add New...");
        }

        fromBox.setModel(modelFrom);
        toBox.setModel(modelTo);
        fromBox.setSelectedIndex(0);
        toBox.setSelectedIndex(1);
    }

    private void openAddCurrencyDialog() {
        // Double check: if someone clicks it while in Enum mode (shouldn't happen)
        if (currentCalculator instanceof BaseCurrencyEnum) {
            JOptionPane.showMessageDialog(this, "Adding is disabled in Enum Mode!");
            updateDropdownModels();
            return;
        }

        JDialog dialog = new JDialog(this, "Add New Currency", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField rateField = new JTextField();

        // Dropdown for the "Relative To" currency
        // We use the existing names from the rechner
        JComboBox<String> relativeToBox = new JComboBox<>(dynamicCalculator.getCurrencyNames());

        JButton okBtn = new JButton("Add");
        JButton cancelBtn = new JButton("Cancel");

        dialog.add(new JLabel(" Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel(" Rate:"));
        dialog.add(rateField);
        dialog.add(new JLabel(" Relative to:"));
        dialog.add(relativeToBox);
        dialog.add(okBtn);
        dialog.add(cancelBtn);

        okBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String rateText = rateField.getText().trim();
                String relativeTo = (String) relativeToBox.getSelectedItem();

                if (name.isEmpty() || rateText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all fields.");
                    return;
                }

                if (dynamicCalculator.checkIfNewName(name)) {
                    JOptionPane.showMessageDialog(dialog, "This currency already exists!");
                    return;
                }

                double rate = Double.parseDouble(rateText);

                if (!(rate>0)){
                    JOptionPane.showMessageDialog(dialog, "Rate must be greater than zero!");
                    return;
                }
                // Call the updated logic
                dynamicCalculator.createNewCurrency(name, rate, relativeTo);

                // Refresh main GUI dropdowns
                updateDropdownModels();
                updateResult();

                dialog.dispose(); // Close only on success
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid number for the rate.");
            }
        });

        cancelBtn.addActionListener(_ -> {
            // Reset main boxes so they don't stay on "+ Add New..."
            fromBox.setSelectedIndex(0);
            toBox.setSelectedIndex(1);
            dialog.dispose();
        });

        // Handle user closing window via 'X'
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                fromBox.setSelectedIndex(0);
                toBox.setSelectedIndex(1);
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}