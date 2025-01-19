package StudentCRUDOperations;

import javax.swing.*;

import cms.CourseManagementSystem;
import table.Table;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import StudentForm.Form;

public class Navbar extends JPanel {

    private Table table;

    public Navbar(Table table) {

        this.table = table;

        setLayout(new BorderLayout());
        setBackground(new Color(235, 236, 240));

        JPanel logoPanel = createLogoPanel();

        JPanel navPanel = createNavPanel();

        add(logoPanel, BorderLayout.WEST);
        add(navPanel, BorderLayout.CENTER);

    }

    private JPanel createLogoPanel() {

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        logoPanel.setBackground(new Color(235, 236, 240));

        JLabel logoLabel = new JLabel("Course Management System");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoLabel.setForeground(Color.BLACK);

        logoLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                logoLabel.setForeground(new Color(0, 191, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoLabel.setForeground(Color.BLACK);
            }

        });

        logoPanel.add(logoLabel);
        return logoPanel;

    }

    private JPanel createNavPanel() {

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        navPanel.setBackground(new Color(235, 236, 240));

        navPanel.add(createNavButton("Home", false));
        navPanel.add(createDropdownButton("Student CMS"));
        navPanel.add(createLogoutButton());

        return navPanel;

    }

    private JButton createNavButton(String text, boolean isBoldItalic) {

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", isBoldItalic ? Font.BOLD | Font.ITALIC : Font.BOLD, 16));
        button.setBackground(new Color(235, 236, 240));
        button.setForeground(Color.BLACK);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(24, 79, 167));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK);
            }

        });

        return button;

    }

    private JButton createDropdownButton(String text) {

        JButton dropdownButton = createNavButton(text, false);

        dropdownButton.addActionListener(e -> {

            JPopupMenu menu = new JPopupMenu();

            JMenuItem item1 = new JMenuItem("Create Student");

            item1.addActionListener(event -> {
                Form.enableFormFields(true);
                Form.idField.setText("");
                Form.nameField.setText("");
                Form.rollField.setText("");
                Form.programField.setText("");
            });

            JMenuItem item2 = new JMenuItem("Read Student");

            item2.addActionListener(new ActionListener() {
                ReadStudent readStudent = new ReadStudent();

                @Override
                public void actionPerformed(ActionEvent event) {
                    readStudent.readStudent(table);

                }
            });

            JMenuItem item3 = new JMenuItem("Update Student");

            item3.addActionListener(new ActionListener() {
                UpdateStudent updateStudent = new UpdateStudent();

                @Override
                public void actionPerformed(ActionEvent event) {
                    updateStudent.updateStudent(table);
                }
            });

            JMenuItem item4 = new JMenuItem("Delete Student");

            item4.addActionListener(new ActionListener() {
                DeleteStudent deleteStudent = new DeleteStudent();

                @Override
                public void actionPerformed(ActionEvent event) {
                    deleteStudent.deleteStudent(table);
                }
            });

            JMenuItem item5 = new JMenuItem("Submit");

            item5.addActionListener(new ActionListener() {
                CreateStudent createStudent = new CreateStudent();

                @Override
                public void actionPerformed(ActionEvent event) {
                    createStudent.createStudent(table);
                }
            });

            menu.add(item1);
            menu.add(item2);
            menu.add(item3);
            menu.add(item4);
            menu.add(item5);

            menu.show(dropdownButton, 0, dropdownButton.getHeight());

        });

        return dropdownButton;

    }

    private JButton createLogoutButton() {

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(251, 188, 4));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logoutButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                logoutButton.setBackground(new Color(255, 210, 100));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutButton.setBackground(new Color(251, 188, 4));
            }

        });

        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int confirm = JOptionPane.showConfirmDialog(
                        Navbar.this,
                        "Are you sure you want to log out?",
                        "Logout Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    SwingUtilities.getWindowAncestor(Navbar.this).dispose();
                    CourseManagementSystem.start();
                }

            }

        });

        return logoutButton;

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Course Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Table table = new Table();

        Navbar navbar = new Navbar(table);
        frame.add(navbar, BorderLayout.NORTH);

        frame.setVisible(true);

    }

}