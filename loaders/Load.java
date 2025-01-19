package loaders;

import javax.swing.*;

import cms.CourseManagementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Load extends JPanel implements ActionListener {

    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private Timer timer;
    private float hue = 0.0f;
    private int animationStep = 0;
    private int numCircles = 10;
    private int fontSize = 10;
    private float fontSizeTransition = 10;

    private static final Color GOOGLE_GREEN = new Color(52, 168, 83);
    private long startTime;
    private boolean animationComplete = false;
    private Timer transitionTimer;
    private float fadeAlpha = 0.0f;
    private float circleFadeAlpha = 1.0f;
    private float underlineWidth = 0.0f;
    private String logoText = "COURSE MANAGEMENT SYSTEM";

    private float underlineMaxWidthPercentage = 40.0f;

    private int underlineAnimationSpeed = 8;

    public Load() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);

        timer = new Timer(40, this);
        timer.start();

        startTime = System.currentTimeMillis();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        Color startColor = Color.WHITE;
        Color endColor = new Color(230, 230, 230);

        g2d.setPaint(new GradientPaint(0, 0, startColor, width, height, endColor));
        g2d.fillRect(0, 0, width, height);

        if (!animationComplete) {

            long elapsedTime = System.currentTimeMillis() - startTime;

            if (elapsedTime <= 15000) {

                int centerX = width / 2;
                int centerY = height / 2;

                for (int i = 0; i < numCircles; i++) {

                    float alpha = Math.max(0, circleFadeAlpha - (float) i / numCircles);
                    g2d.setColor(new Color(0, 0, 0, alpha));
                    int size = 50 + (animationStep + i * 20) % 400;
                    g2d.drawOval(centerX - size / 2, centerY - size / 2, size, size);

                    drawCrazyEffect(g2d, centerX, centerY, size, i);

                }

                for (int i = 0; i < 360; i += 45) {

                    double angle = Math.toRadians(i + animationStep * 10);

                    int lineLength = 120;
                    int x1 = centerX + (int) (Math.cos(angle) * lineLength);
                    int y1 = centerY + (int) (Math.sin(angle) * lineLength);
                    int x2 = centerX + (int) (Math.cos(angle) * (lineLength + 20));
                    int y2 = centerY + (int) (Math.sin(angle) * (lineLength + 20));

                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(GOOGLE_GREEN);
                    g2d.drawLine(x1, y1, x2, y2);

                }

            } else {
                animationComplete = true;
            }

        }

        if (animationComplete) {

            if (fadeAlpha < 1.0f) {
                fadeAlpha += 0.02f;
            }

            int centerX = width / 2;
            int centerY = height / 2;

            g2d.setFont(new Font("SansSerif", Font.BOLD, fontSize));

            int shadowOffsetX = 4;
            int shadowOffsetY = 4;
            g2d.setColor(Color.BLACK);
            int textWidth = g2d.getFontMetrics().stringWidth(logoText);

            g2d.drawString(logoText, centerX - textWidth / 2 + shadowOffsetX, centerY + shadowOffsetY);

            g2d.setColor(GOOGLE_GREEN);
            g2d.drawString(logoText, centerX - textWidth / 2, centerY);

            int underlineY = centerY + 5;
            FontMetrics fm = g2d.getFontMetrics();
            int logoWidth = fm.stringWidth(logoText);

            int underlineStartX = centerX - logoWidth / 2;
            int underlineEndX = centerX + logoWidth / 2;

            g2d.setColor(new Color(50, 50, 50));

            if (animationComplete && underlineWidth < (underlineEndX - underlineStartX)) {
                underlineWidth += underlineAnimationSpeed;
            }

            g2d.drawLine(underlineStartX, underlineY, (int) (underlineStartX + underlineWidth), underlineY);

        }

    }

    private void drawCrazyEffect(Graphics2D g2d, int centerX, int centerY, int size, int index) {

        double angleStep = Math.toRadians(360.0 / 8);

        int outerRadius = size / 6;
        int innerRadius = size / 12;

        double rotationAngle = Math.toRadians((animationStep + index * 15) % 360);
        double scale = 0.8 + 0.2 * Math.sin(Math.toRadians(animationStep * 2));

        Color outerColor = new Color(144, 238, 144);
        Color innerColor = new Color(50, 50, 50);

        g2d.setStroke(new BasicStroke(2));

        for (int i = 0; i < 8; i++) {

            double currentAngle = i * angleStep + rotationAngle;

            int xOuter = centerX + (int) (Math.cos(currentAngle) * outerRadius * scale);
            int yOuter = centerY + (int) (Math.sin(currentAngle) * outerRadius * scale);

            double nextAngle = currentAngle + angleStep / 2;
            int xInner = centerX + (int) (Math.cos(nextAngle) * innerRadius * scale);
            int yInner = centerY + (int) (Math.sin(nextAngle) * innerRadius * scale);

            if (i % 2 == 0) {
                g2d.setColor(outerColor);
            } else {
                g2d.setColor(innerColor);
            }

            g2d.drawLine(centerX, centerY, xOuter, yOuter);
            g2d.drawLine(xOuter, yOuter, xInner, yInner);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        hue += 0.005f;

        if (hue > 1.0f)
            hue = 0.0f;

        animationStep += 2;

        if (fontSize < 50) {
            fontSizeTransition += 0.5f;
            fontSize = Math.min(50, (int) fontSizeTransition);
        }

        if (animationComplete && circleFadeAlpha > 0.0f) {
            circleFadeAlpha -= 0.02f;
        }

        int maxUnderlineWidth = (int) (getWidth() * (underlineMaxWidthPercentage / 100.0f));
        if (animationComplete && underlineWidth < maxUnderlineWidth) {
            underlineWidth += underlineAnimationSpeed;
        }

        if (animationStep >= 360) {
            animationComplete = true;
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime > 15000) {
            timer.stop();
        }

        if (animationComplete && transitionTimer == null) {

            transitionTimer = new Timer(4000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.getWindowAncestor(Load.this).dispose();
                    CourseManagementSystem.start();
                }
            });

            transitionTimer.setRepeats(false);
            transitionTimer.start();

        }

        repaint();

    }

}