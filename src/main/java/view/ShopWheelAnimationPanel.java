package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class responsible for creating and updating the animation of the spinning wheel.
 */
public class ShopWheelAnimationPanel extends JPanel {

    private static final int FULL_CIRCLE = 360;
    private static final int PANEL_DIMENSION = 300;
    private boolean isSpinning;

    private int currentAngle;
    private Timer animationTimer;

    public ShopWheelAnimationPanel() {
        setPreferredSize(new Dimension(PANEL_DIMENSION, PANEL_DIMENSION));
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
        final Color[] segmentColors = {Color.RED.darker(), Color.GREEN.darker(),
                Color.YELLOW.darker(), Color.CYAN.darker(),
                Color.BLUE.darker(), Color.ORANGE.darker().darker().darker(),
                Color.PINK.darker(), Color.MAGENTA.darker()};
        final int numSegments = segmentColors.length;

        // Draw the wheel
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - shellRadius, centerY - shellRadius,
                shellRadius * 2, shellRadius * 2);

        for (int i = 0; i < segmentColors.length; i++) {
            g2d.setColor(segmentColors[i]);
            g2d.fillArc(centerX - circleRadius, centerY - circleRadius, 2 * circleRadius, 2 * circleRadius,
                    currentAngle + (i * FULL_CIRCLE / numSegments),
                    FULL_CIRCLE / numSegments);
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

    /**
     * The method that animates the created wheel.
     * @param targetAngle is the angle the wheel is set to land.
     * @param afterSpin is the method calls performed only after the wheel is spun.
     */
    public void startSpinAnimation(int targetAngle, Runnable afterSpin) {
        if (isSpinning) {
            return;
        }
        isSpinning = true;

        // Adjustable parameters
        final int spinDuration = 3000;
        final int timerDelay = 10;
        final int totalTicks = spinDuration / timerDelay;
        final int finalFastTicks = totalTicks / 4;
        final double maxSpeed = (double) FULL_CIRCLE / 20;
        final double accelerationExponent = 1;

        animationTimer = new Timer(timerDelay, new ActionListener() {
            private int tick;

            @Override
            public void actionPerformed(ActionEvent e) {
                final double speed;

                if (tick < totalTicks - finalFastTicks) {
                    // Acceleration phase: Adjust speed growth
                    speed = maxSpeed * Math.pow((double) tick / (totalTicks - finalFastTicks), accelerationExponent);
                }
                else {
                    // Fast phase: Cap speed at maxSpeed
                    speed = maxSpeed;
                }

                currentAngle = (int) ((currentAngle + speed) % FULL_CIRCLE);
                repaint();
                tick++;

                // Stop the animation and snap to the target angle
                if (tick >= totalTicks) {
                    currentAngle = targetAngle % FULL_CIRCLE;
                    repaint();
                    stopSpinAnimation();
                    isSpinning = false;

                    if (afterSpin != null) {
                        afterSpin.run();
                    }
                }
            }
        });

        animationTimer.start();
    }

    /**
     * The method that stops the animation once the spin has completed.
     */
    public void stopSpinAnimation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

}
