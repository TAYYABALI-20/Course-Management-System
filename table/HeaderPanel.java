package table;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel {
    public JPanel createHeaderPanel() {

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(52, 168, 83));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 50, 50)),
                BorderFactory.createEmptyBorder(10, 10, 20, 10)));

        JLabel universityLabel = new JLabel("Sir Syed University of Engineering & Technology");
        universityLabel.setFont(new Font("Arial", Font.BOLD, 22));
        universityLabel.setForeground(Color.WHITE);
        universityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel portalLabel = new JLabel("Course Management System");
        portalLabel.setFont(new Font("Arial", Font.BOLD, 19));
        portalLabel.setForeground(Color.WHITE);
        portalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(universityLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(portalLabel);

        return headerPanel;

    }

}