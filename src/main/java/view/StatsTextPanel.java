package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel containing a label and a text field.
 */
class StatsTextPanel extends JPanel {
    StatsTextPanel(JLabel label, JLabel info) {
        this.add(label);
        this.add(info);
    }
}
