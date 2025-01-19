package loaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loading extends JPanel implements ActionListener {

    private int angle = 0;
    private Timer timer;

    public Loading() {
        timer = new Timer(16, this);
        timer.start();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = 50;

        for (int i = 0; i < 12; i++) {
            float alpha = (i + angle / 30.0f) % 12 / 12.0f;
            g2d.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
            g2d.drawLine(x, y - radius, x, y - radius - 10);
            g2d.rotate(Math.toRadians(30), x, y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle = (angle + 5) % 360;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Loading...");

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        Loading rotatingLoader = new Loading();
        rotatingLoader.setPreferredSize(new Dimension(100, 100));

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setOpaque(false);

        centerPanel.setOpaque(false);
        centerPanel.add(rotatingLoader, gbc);

        gbc.gridy = 1;
        centerPanel.add(loadingLabel, gbc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300, 300));

    }

}