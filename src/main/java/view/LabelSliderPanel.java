package view;

import entity.AppColors;

import javax.swing.*;
import java.awt.*;

public class LabelSliderPanel extends JPanel {
    public LabelSliderPanel(JLabel label, JSlider slider, JTextField textField) {

        this.add(label, BorderLayout.CENTER);
        this.add(slider, BorderLayout.SOUTH);
        this.add(textField, BorderLayout.EAST);


    }
}
