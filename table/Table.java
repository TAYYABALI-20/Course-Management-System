package table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import StudentForm.Form;
import StudentList.StudentList;

import java.awt.*;

public class Table extends JFrame {

    private JTable table;
    private JPanel headerPanel;

    private DefaultTableModel model;

    public Table() {

        setLayout(new BorderLayout(0, 20));

        initializeHeader();
        initializeForm();
        initializeTable();

        setSize(1000, 835);

        setResizable(true);
    }

    private void initializeHeader() {
        headerPanel = new HeaderPanel().createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
    }

    private void initializeTable() {
        table = new TablePanel().createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);

        insertStudentList();
    }

    public void insertStudentList() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (StudentList student : StudentList.STUDENT_RECORDS) {
            model.addRow(student.getStudentData());
        }
    }

    public void addNewStudentToTable(StudentList newStudent) {
        model.addRow(newStudent.getStudentData());
    }

    private void initializeForm() {
        Form formPanel = new Form();
        add(formPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            Table gui = new Table();
            gui.setVisible(true);
        });

    }

}