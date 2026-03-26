package WeahrungsrechnerAPP;

import WeahrungsrechnerAPP.WaehrungsrechnerUI.RechnerGUI;

import javax.swing.*;

public class WaehrungsRechnerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RechnerGUI::new);
    }
}
