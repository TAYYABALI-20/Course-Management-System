package authentication;

import javax.swing.*;
import StudentList.StudentList;
import java.awt.*;
import java.awt.event.*;

public class StudentLoginForm {

    private static StudentList studentList;

    public static void showLoginForm(JFrame parentFrame) {

        JFrame loginFrame = new JFrame("Student Login");
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

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(255, 35));
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel rollNoLabel = new JLabel("Roll No:");
        rollNoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        rollNoLabel.setForeground(Color.BLACK);
        rollNoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 21));

        JTextField rollNoField = new JTextField();
        rollNoField.setPreferredSize(new Dimension(255, 35));
        rollNoField.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(66, 133, 244));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(348, 35));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String enteredName = nameField.getText().trim();
                String enteredRollNo = rollNoField.getText().trim();

                if (enteredName.isEmpty()) {
                    JOptionPane.showMessageDialog(loginFrame, "Name field cannot be empty. Please enter your name.",
                            "Login Failed",
                            JOptionPane.WARNING_MESSAGE);
                    nameField.requestFocus();
                    return;
                }

                if (enteredRollNo.isEmpty()) {
                    JOptionPane.showMessageDialog(loginFrame,
                            "Roll No field cannot be empty. Please enter your roll number.", "Login Failed",
                            JOptionPane.WARNING_MESSAGE);
                    rollNoField.requestFocus();
                    return;
                }

                if (!enteredRollNo.matches("\\d+")) {
                    JOptionPane.showMessageDialog(loginFrame,
                            "Invalid Roll No format. Please enter a numeric roll number.", "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                    rollNoField.requestFocus();
                    return;
                }

                boolean studentExists = false;
                StudentList matchedStudent = null;

                for (StudentList student : StudentList.STUDENT_RECORDS) {
                    if (student.getName().equalsIgnoreCase(enteredName) && student.getRollNo().equals(enteredRollNo)) {
                        studentExists = true;
                        matchedStudent = student;
                        break;
                    }
                }

                if (!studentExists) {
                    JOptionPane.showMessageDialog(loginFrame,
                            "Invalid credentials. No student found with the entered Name and Roll No.", "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (matchedStudent != null) {
                    loginFrame.dispose();
                    StudentCard studentCard = new StudentCard(
                            matchedStudent.getName(),
                            matchedStudent.getId(),
                            matchedStudent.getRollNo(),
                            matchedStudent.getProgram());
                    studentCard.setVisible(true);

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

        nameField.addKeyListener(enterKeyAdapter);
        rollNoField.addKeyListener(enterKeyAdapter);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(rollNoLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(rollNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

        loginFrame.add(formPanel, BorderLayout.CENTER);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public static void main(String[] args) {
        showLoginForm(null);
    }
}