package WeahrungsrechnerAPP.WaehrungsrechnerUI;

import WeahrungsrechnerAPP.Waehrungsrechner.Rechner;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class RechnerGUI extends JFrame {
    private Rechner rechner;
    private JComboBox<String> fromBox, toBox;
    private JTextField inputField;
    private JLabel resultLabel;

    // --- GUI Constructor ---
    public RechnerGUI() {
        rechner = new Rechner();

        setTitle("Live Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 250);
        setLayout(new GridLayout(4, 1, 10, 10)); // Grid for clean rows

        // Row 1: Dropdowns
        JPanel dropPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        fromBox = new JComboBox<>();
        toBox = new JComboBox<>();
        updateDropdownModels();
        dropPanel.add(fromBox);
        dropPanel.add(toBox);

        // Row 2: Labels
        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        labelPanel.add(new JLabel("Amount:", SwingConstants.CENTER));
        labelPanel.add(new JLabel("Converted Value:", SwingConstants.CENTER));

        // Row 3: Input and Result
        JPanel ioPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        inputField = new JTextField("1.0");
        inputField.setFont(new Font("SansSerif", Font.BOLD, 16));
        resultLabel = new JLabel("---", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 102, 204));
        ioPanel.add(inputField);
        ioPanel.add(resultLabel);

        // --- Event Listeners ---
        ActionListener comboListener = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            if ("+ Add New...".equals(cb.getSelectedItem())) {
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

        // Add to Frame
        add(new JLabel("Select Currencies:", SwingConstants.CENTER));
        add(dropPanel);
        add(labelPanel);
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

            if (from != null && to != null && !from.contains("+") && !to.contains("+")) {
                double res = rechner.calculate(from, to, amount);
                resultLabel.setText(String.format("%.2f", res));
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid Input");
        }
    }

    private void updateDropdownModels() {
        String[] names = rechner.getCurrencyNames();
        String[] modelData = new String[names.length + 1];
        System.arraycopy(names, 0, modelData, 0, names.length);
        modelData[names.length] = "+ Add New...";

        fromBox.setModel(new DefaultComboBoxModel<>(modelData));
        toBox.setModel(new DefaultComboBoxModel<>(modelData));

        fromBox.setSelectedIndex(0);
        toBox.setSelectedIndex(1);
    }

    private void openAddCurrencyDialog() {
        JDialog dialog = new JDialog(this, "Add New Currency", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField rateField = new JTextField();

        // Dropdown for the "Relative To" currency
        // We use the existing names from the rechner
        JComboBox<String> relativeToBox = new JComboBox<>(rechner.getCurrencyNames());

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

                if (rechner.checkIfNewName(name)) {
                    JOptionPane.showMessageDialog(dialog, "This currency already exists!");
                    return;
                }

                double rate = Double.parseDouble(rateText);

                if (!(rate>0)){
                    JOptionPane.showMessageDialog(dialog, "Rate must be greater than zero!");
                    return;
                }
                // Call the updated logic
                rechner.createNewCurrency(name, rate, relativeTo);

                // Refresh main GUI dropdowns
                updateDropdownModels();
                updateResult();

                dialog.dispose(); // Close only on success
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid number for the rate.");
            }
        });

        cancelBtn.addActionListener(e -> {
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