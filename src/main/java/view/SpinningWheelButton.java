package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * The assisting class which supports the regular view.
 */
public class SpinningWheelButton extends JButton {
    private int currentAngle;
    private final int[] wheelSegments = {1, 2, 3, 4, 5, 6, 7, 8};
    private final Color[] segmentColors = {Color.RED.darker(), Color.GREEN.darker(),
                                           Color.YELLOW.darker(), Color.CYAN.darker(),
                                           Color.BLUE.darker(), Color.ORANGE.darker().darker().darker(),
                                           Color.PINK.darker(), Color.MAGENTA.darker()};
    private boolean isSpinning;
    private Timer timer;

    public SpinningWheelButton() {
        final int wheelHeight = 200;
        final int wheelWidth = 200;
        setPreferredSize(new Dimension(wheelWidth, wheelHeight));
        addActionListener(evt -> startSpin());
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

        // Draw the wheel
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - shellRadius, centerY - shellRadius,
                shellRadius * 2, shellRadius * 2);

        for (int i = 0; i < segmentColors.length; i++) {
            g2d.setColor(segmentColors[i]);
            g2d.fillArc(centerX - circleRadius, centerY - circleRadius, 2 * circleRadius, 2 * circleRadius,
                    currentAngle + (i * (fullCircleAngle / wheelSegments.length)),
                    fullCircleAngle / wheelSegments.length);
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

    private void startSpin() {
        if (isSpinning) {
            return;
        }
        isSpinning = true;

        final Random random = new Random();
        final int targetAngle = random.nextInt(360);
        final int fullDuration = 400;
        final int accelerationDuration = fullDuration / 2;
        final int maxSpeed = 20;
        final int timerDelay = 10;

        timer = new Timer(timerDelay, new ActionListener() {
            private int tick;

            @Override
            public void actionPerformed(ActionEvent e) {
                final double speed = getSpeed(tick);
                currentAngle = (int) ((currentAngle + speed) % 360);
                tick++;
                repaint();
                if (tick >= fullDuration) {
                    timer.stop();
                    isSpinning = false;
                    showResult(targetAngle);
                }
            }

            private double getSpeed(int tick) {
                final double speed;
                if (tick < accelerationDuration) {
                    // Acceleration phase: use sinusoidal easing
                    speed = maxSpeed * Math.sin((Math.PI / 2) * tick / accelerationDuration);
                }
                else {
                    // Deceleration phase: symmetric easing
                    final int decelerationTick = tick - accelerationDuration;
                    speed = maxSpeed * Math.cos((Math.PI / 2) * decelerationTick / accelerationDuration);
                }
                return speed;
            }
        });
        timer.start();
    }

    private void showResult(int targetAngle) {
        final int segmentIndex = (wheelSegments.length - 1) - (targetAngle / (360 / wheelSegments.length));
        final int segment = wheelSegments[segmentIndex];
        JOptionPane.showMessageDialog(this, "The wheel landed on: " + segment);
    }

    /**
     * The main method for this class.
     * @param args is usually unfilled.
     */
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Spinning Wheel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        final SpinningWheelButton wheelButton = new SpinningWheelButton();
        frame.add(wheelButton, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
