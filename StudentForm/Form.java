package StudentForm;

import javax.swing.*;

import java.awt.*;

public class Form extends JPanel {

    public static JTextField idField;
    public static JTextField nameField;
    public static JTextField rollField;
    public static JTextField programField;

    public Form() {

        setLayout(new BorderLayout(0, 40));
        setBackground(new Color(245, 245, 245));

        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 20, 15));
        fieldsPanel.setBackground(new Color(245, 245, 245));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        idField = createStyledTextField();
        nameField = createStyledTextField();
        rollField = createStyledTextField();
        programField = createStyledTextField();

        idField.setEnabled(false);
        nameField.setEnabled(false);
        rollField.setEnabled(false);
        programField.setEnabled(false);

        addFormRow(fieldsPanel, "Student ID:", idField);
        addFormRow(fieldsPanel, "Student Name:", nameField);
        addFormRow(fieldsPanel, "Roll Number:", rollField);
        addFormRow(fieldsPanel, "Program:", programField);

        add(fieldsPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(250, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        field.setBackground(Color.WHITE);
        return field;
    }

    private void addFormRow(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(51, 51, 51));
        panel.add(label);
        panel.add(field);
    }

    public static void enableFormFields(boolean enable) {
        idField.setEnabled(enable);
        nameField.setEnabled(enable);
        rollField.setEnabled(enable);
        programField.setEnabled(enable);
    }

    public static void enableIDField(boolean enable) {
        idField.setEnabled(enable);
    }

}