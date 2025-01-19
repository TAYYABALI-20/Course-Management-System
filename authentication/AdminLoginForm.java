    package authentication;

    import javax.swing.*;
    import StudentCRUDOperations.Navbar;
    import table.Table;
    import java.awt.*;
    import java.awt.event.*;

    public class AdminLoginForm {

        private static final String VALID_EMAIL = "admin@gmail.com";
        private static final String VALID_PASSWORD = "123";

        public static void showLoginForm(JFrame parentFrame) {

            JFrame loginFrame = new JFrame("Admin Login");
            loginFrame.setSize(400, 300);
            loginFrame.setMinimumSize(new Dimension(400, 300));
            loginFrame.setLayout(new BorderLayout());
            loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel formPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {

                    super.paintComponent(g);

                    try {

                        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/assets/login-bg.png"));
                        Image backgroundImage = backgroundIcon.getImage();
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                        Color overlayColor = new Color(255, 255, 255, 100);
                        g.setColor(overlayColor);
                        g.fillRect(0, 0, getWidth(), getHeight());

                    } catch (Exception e) {
                        System.out.println("Error loading background image: " + e.getMessage());
                    }

                }

            };

            formPanel.setLayout(new GridBagLayout());

            JLabel titleLabel = new JLabel("COURSE MANAGEMENT SYSTEM");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setForeground(Color.BLACK);
            titleLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            emailLabel.setForeground(Color.BLACK);
            emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

            JTextField emailField = new JTextField();
            emailField.setPreferredSize(new Dimension(255, 35));
            emailField.setFont(new Font("Arial", Font.PLAIN, 18));

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            passwordLabel.setForeground(Color.BLACK);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setPreferredSize(new Dimension(255, 35));
            passwordField.setFont(new Font("Arial", Font.PLAIN, 18));

            JButton loginButton = new JButton("Login");
            loginButton.setFont(new Font("Arial", Font.BOLD, 16));
            loginButton.setBackground(new Color(66, 133, 244));
            loginButton.setForeground(Color.WHITE);
            loginButton.setFocusPainted(false);
            loginButton.setPreferredSize(new Dimension(348, 35));

            loginButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String enteredEmail = emailField.getText().trim();
                    String enteredPassword = new String(passwordField.getPassword());

                    if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(loginFrame, "Please fill in all fields.", "Login Failed",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (!enteredEmail.equals(VALID_EMAIL)) {
                        JOptionPane.showMessageDialog(loginFrame, "Invalid email address.", "Login Failed",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (!enteredPassword.equals(VALID_PASSWORD)) {
                        JOptionPane.showMessageDialog(loginFrame, "Incorrect password.", "Login Failed",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        loginFrame.dispose();
                        showNavbarAndTable();
                    }
                }
            });

            emailField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String emailText = emailField.getText();
                    if (emailText.endsWith(".com")) {
                        passwordField.requestFocus();
                    }
                }
            });

            KeyAdapter enterKeyAdapter = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        loginButton.doClick();
                    }
                }
            };

            emailField.addKeyListener(enterKeyAdapter);
            passwordField.addKeyListener(enterKeyAdapter);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            formPanel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(emailLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(emailField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(passwordLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            formPanel.add(loginButton, gbc);

            loginFrame.add(formPanel, BorderLayout.CENTER);
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        }

        private static void showNavbarAndTable() {

            JFrame mainFrame = new JFrame("Course Management System");
            mainFrame.setSize(1000, 880);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout());

            Table tableComponent = new Table();
            tableComponent.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

            Navbar navbar = new Navbar(tableComponent);

            mainPanel.add(navbar, BorderLayout.NORTH);
            mainPanel.add(tableComponent.getContentPane(), BorderLayout.CENTER);

            mainFrame.add(mainPanel);
            mainFrame.setVisible(true);

        }

        public static void main(String[] args) {
            showLoginForm(null);
        }

    }

    // public static void main(String[] args) {
    // try {
    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // SwingUtilities.invokeLater(() -> {
    // showLoginForm(null);
    // });
    // }