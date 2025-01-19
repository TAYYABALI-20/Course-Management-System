package authentication;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.border.*;

import cms.CourseManagementSystem;

public class StudentCard extends JFrame {
    private static final double CARD_WIDTH_RATIO = 0.3;
    private static final double CARD_HEIGHT_RATIO = 0.4;
    private static final double MIN_WIDTH = 575;
    private static final double MIN_HEIGHT = 500;
    private static final Color THEME_COLOR = new Color(33, 33, 33);
    private static final Color ACCENT_COLOR = new Color(0, 102, 204);
    private static final String DEFAULT_IMAGE_PATH = "C:/Users/TAYYAB/OneDrive/Desktop/ITSE Project - ( Course Management System )/assets/user.png";

    private int currentImageSize;
    private static final double IMAGE_SIZE_RATIO = 0.3;
    private static final int MIN_IMAGE_SIZE = 250;
    private static final int MAX_IMAGE_SIZE = 5000;

    private String studentName;
    private int studentId;
    private String rollNumber;
    private String program;
    private ImageIcon studentImage;

    public StudentCard(String studentName, int studentId, String rollNumber, String program) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.program = program;
        loadDefaultImage();
        initializeCard();
    }

    private void loadDefaultImage() {
        try {
            File imageFile = new File(DEFAULT_IMAGE_PATH);
            if (!imageFile.exists()) {
                System.out.println("Image file not found at: " + imageFile.getAbsolutePath());
                java.net.URL resourceUrl = getClass().getResource(DEFAULT_IMAGE_PATH);
                if (resourceUrl != null) {
                    imageFile = new File(resourceUrl.getFile());
                } else {
                    System.out.println("Image not found in resources");
                    throw new FileNotFoundException("Image not found: " + DEFAULT_IMAGE_PATH);
                }
            }

            BufferedImage originalImage = ImageIO.read(imageFile);
            if (originalImage != null) {
                updateImageSize();
                Image resized = originalImage.getScaledInstance(currentImageSize, currentImageSize, Image.SCALE_SMOOTH);
                this.studentImage = new ImageIcon(resized);
                System.out.println("Image loaded successfully");
            } else {
                System.out.println("Failed to read image file");
                throw new IOException("Failed to read image file");
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateImageSize() {
        // Get screen and window size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Get the window size (if you want dynamic resizing)
        int windowWidth = this.getWidth(); // Replace with actual method to get window width
        int windowHeight = this.getHeight(); // Replace with actual method to get window height

        // Calculate image size based on screen or window size
        if (windowWidth > 1280) { // For larger screens (or full-screen mode)
            currentImageSize = Math.min(windowWidth, 1000); // Max size 1000px or window width
        } else { // Small screens
            currentImageSize = 250; // Set size for small screens
        }

        // Ensure the image size stays within a reasonable range
        currentImageSize = Math.max(currentImageSize, 100); // Minimum size
        currentImageSize = Math.min(currentImageSize, 1000); // Maximum size for large screens

        // Optionally print values for debugging
        System.out.println("Window Width: " + windowWidth);
        System.out.println("Calculated Image Size: " + currentImageSize);
    }

    private void initializeCard() {
        setTitle("Student Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int cardWidth = Math.max((int) (screenSize.width * CARD_WIDTH_RATIO), (int) MIN_WIDTH);
        int cardHeight = Math.max((int) (screenSize.height * CARD_HEIGHT_RATIO), (int) MIN_HEIGHT);
        setSize(cardWidth, cardHeight);
        setLocationRelativeTo(null);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                updateFontSizes();
            }
        });

        add(createMainPanel());
        updateFontSizes();
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(15, 15, 15, 15),
                new CompoundBorder(
                        createShadowBorder(),
                        BorderFactory.createLineBorder(THEME_COLOR, 2))));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createBodyPanel(), BorderLayout.CENTER);
        mainPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(THEME_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Create the left arrow button
        JButton leftArrowButton = new JButton("←");
        leftArrowButton.setForeground(Color.WHITE);
        leftArrowButton.setBackground(THEME_COLOR);
        leftArrowButton.setBorderPainted(false);
        leftArrowButton.setFocusPainted(false);
        leftArrowButton.setFont(new Font("Segoe UI", Font.BOLD, 30));
        leftArrowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftArrowButton.addActionListener(e -> {
            // Close the current window
            StudentCard.this.dispose();
            // Start the Course Management System
            SwingUtilities.invokeLater(CourseManagementSystem::start);
        });

        // Style the button to blend with the theme
        leftArrowButton.setOpaque(true);

        JLabel headerLabel = new JLabel("Welcome, " + studentName + "!");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setName("header");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel linePanel = new JPanel();
        linePanel.setBackground(ACCENT_COLOR);
        linePanel.setPreferredSize(new Dimension(0, 2));

        // Add the components to the header panel
        headerPanel.add(leftArrowButton, BorderLayout.WEST); // Add the arrow button on the left
        headerPanel.add(headerLabel, BorderLayout.CENTER); // Add the header label at the center
        headerPanel.add(linePanel, BorderLayout.SOUTH); // Add the line panel at the bottom

        return headerPanel;
    }

    private JPanel createBodyPanel() {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(Color.WHITE);
        bodyPanel.setLayout(new BorderLayout());

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(Color.WHITE);

        JPanel infoPanel = createInfoPanel();
        JPanel imagePanel = createImagePanel();

        GridBagConstraints gbc = new GridBagConstraints();

        // Info panel constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        containerPanel.add(infoPanel, gbc);

        // Image panel constraints
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        containerPanel.add(imagePanel, gbc);

        bodyPanel.add(containerPanel, BorderLayout.CENTER);
        return bodyPanel;
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for centering
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        updateImageSize(); // Ensure the updated size is used
        JLabel imageLabel = new JLabel(studentImage);
        imageLabel.setPreferredSize(new Dimension(currentImageSize, currentImageSize)); // Adjust preferred size

        // Add image label to center of panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        imagePanel.add(imageLabel, gbc);

        return imagePanel;
    }

    private JPanel createInfoPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int ID = studentId;
        String Id = String.valueOf(ID);

        createInfoSection(contentPanel, "Student ID", Id, gbc);
        addDivider(contentPanel, gbc);
        createInfoSection(contentPanel, "Full Name", studentName, gbc);
        addDivider(contentPanel, gbc);
        createInfoSection(contentPanel, "Roll Number", rollNumber, gbc);
        addDivider(contentPanel, gbc);
        createInfoSection(contentPanel, "Program", program, gbc);

        return contentPanel;
    }

    private void createInfoSection(JPanel panel, String label, String value, GridBagConstraints gbc) {
        JPanel sectionPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        sectionPanel.setBackground(Color.WHITE);

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.setBackground(Color.WHITE);
        JLabel labelComponent = new JLabel(label);
        labelComponent.setName("label");
        labelComponent.setForeground(ACCENT_COLOR);
        labelPanel.add(labelComponent);

        JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        valuePanel.setBackground(Color.WHITE);
        JLabel valueComponent = new JLabel(value);
        valueComponent.setName("value");
        valueComponent.setForeground(THEME_COLOR);
        valuePanel.add(valueComponent);

        sectionPanel.add(labelPanel);
        sectionPanel.add(valuePanel);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.add(sectionPanel, BorderLayout.CENTER);

        panel.add(wrapperPanel, gbc);
    }

    private void addDivider(JPanel panel, GridBagConstraints gbc) {
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setForeground(new Color(230, 230, 230));

        JPanel separatorPanel = new JPanel(new BorderLayout());
        separatorPanel.setBackground(Color.WHITE);
        separatorPanel.add(separator, BorderLayout.CENTER);

        GridBagConstraints sepGbc = (GridBagConstraints) gbc.clone();
        sepGbc.insets = new Insets(8, 0, 8, 0);
        panel.add(separatorPanel, sepGbc);
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(THEME_COLOR);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JPanel linePanel = new JPanel();
        linePanel.setBackground(ACCENT_COLOR);
        linePanel.setPreferredSize(new Dimension(0, 2));
        footerPanel.add(linePanel, BorderLayout.NORTH);

        JLabel footerLabel = new JLabel("Sir Syed University Of Engineering & Technology");
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setName("footer");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        footerPanel.add(footerLabel, BorderLayout.CENTER);
        return footerPanel;
    }

    private void updateFontSizes() {
        int headerFontSize = Math.max((int) (getWidth() * 0.035), 18);
        int footerFontSize = Math.max((int) (getWidth() * 0.025), 16);
        int labelFontSize = Math.max((int) (getWidth() * 0.022), 13);
        int valueFontSize = Math.max((int) (getWidth() * 0.025), 14);

        updateComponentFonts(this, headerFontSize, footerFontSize, labelFontSize, valueFontSize);
    }

    private void updateComponentFonts(Container container, int headerSize, int footerSize,
            int labelSize, int valueSize) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                String name = label.getName();
                if (name != null) { // Add null check
                    switch (name) {
                        case "header":
                            label.setFont(new Font("Segoe UI", Font.BOLD, headerSize));
                            break;
                        case "footer":
                            label.setFont(new Font("Segoe UI", Font.BOLD, footerSize));
                            break;
                        case "label":
                            label.setFont(new Font("Segoe UI", Font.BOLD, labelSize));
                            break;
                        case "value":
                            label.setFont(new Font("Segoe UI", Font.PLAIN, valueSize));
                            break;
                        default:
                            // Set a default font for labels without specific names
                            label.setFont(new Font("Segoe UI", Font.PLAIN, valueSize));
                    }
                } else {
                    // Set a default font for labels without names
                    label.setFont(new Font("Segoe UI", Font.PLAIN, valueSize));
                }
            }
            if (comp instanceof Container) {
                updateComponentFonts((Container) comp, headerSize, footerSize, labelSize, valueSize);
            }
        }
    }

    private Border createShadowBorder() {
        return new Border() {
            private final int shadowSize = 6;

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                for (int i = 0; i < shadowSize; i++) {
                    g2.setColor(new Color(0, 0, 0, ((shadowSize - i) * 8)));
                    g2.drawRoundRect(x + i, y + i, width - (i * 2), height - (i * 2), 12, 12);
                }
                g2.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(shadowSize, shadowSize, shadowSize, shadowSize);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentCard card = new StudentCard("Admin", 3, "003", "Software Engineering Technology");
            card.setVisible(true);
        });
    }
}