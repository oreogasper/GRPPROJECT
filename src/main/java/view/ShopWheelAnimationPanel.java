package view;

import javax.swing.*;
import java.awt.*;

public class ShopWheelAnimationPanel extends JPanel {
    private double currentAngle;
    private Timer animationTimer;

    public ShopWheelAnimationPanel() {
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g.create();
        final int shellRadius = Math.min(getWidth(), getHeight()) / 2;
        final int circleRadius = shellRadius - shellRadius / 20;
        final int centerX = getWidth() / 2;
        final int centerY = getHeight() / 2;
        final int centerOffset = 10;
        final int shapeNumber = 3;
        final int fullCircleAngle = 360;
        final int numColors = 8;
        final Color[] segmentColors = {Color.RED.darker(), Color.GREEN.darker(),
                Color.YELLOW.darker(), Color.CYAN.darker(),
                Color.BLUE.darker(), Color.ORANGE.darker().darker().darker(),
                Color.PINK.darker(), Color.MAGENTA.darker()};

        // Draw the wheel
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - shellRadius, centerY - shellRadius,
                shellRadius * 2, shellRadius * 2);

        for (int i = 0; i < segmentColors.length; i++) {
            g2d.setColor(segmentColors[i]);
            g2d.fillArc(centerX - circleRadius, centerY - circleRadius, 2 * circleRadius, 2 * circleRadius,
                    currentAngle + (i * 45),
                    fullCircleAngle / numColors);
        }

        // Draw the center indicator
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - centerOffset, centerY - centerOffset,
                centerOffset * 2, centerOffset * 2);

        // Draw an arrow pointer at the top
        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(new int[]{centerX, centerX - centerOffset, centerX + centerOffset},
                new int[]{centerY - circleRadius, centerY - circleRadius + centerOffset * 2,
                        centerY - circleRadius + centerOffset * 2}, shapeNumber);

        g2d.dispose();
    }

    public void startSpinAnimation(double targetAngle) {
        animationTimer = new Timer(10, e -> {
            if (currentAngle < targetAngle) {
                currentAngle += 2; // Adjust speed
                repaint();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        animationTimer.start();
    }

    public void stopSpinAnimation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    private Color getSegmentColor(int index) {
        Color[] segmentColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK};
        return segmentColors[index % segmentColors.length];
    }
}
