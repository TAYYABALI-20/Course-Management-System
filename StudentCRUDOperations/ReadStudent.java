package StudentCRUDOperations;

import javax.swing.JOptionPane;

import StudentList.StudentList;

import table.Table;

public class ReadStudent {

    private Table table;

    public void readStudent(Table table) {

        this.table = table;

        String idInput = JOptionPane.showInputDialog(null,
                "Please enter the Student ID:",
                "Enter Student ID",
                JOptionPane.QUESTION_MESSAGE);

        if (idInput != null && !idInput.isEmpty()) {

            try {

                int id = Integer.parseInt(idInput);

                StudentList.STUDENT_RECORDS.stream()
                        .filter(student -> student.getId() == id)
                        .findFirst()
                        .ifPresentOrElse(student -> {

                            String studentData = "Student Details:\n" +
                                    "--------------------\n" +
                                    "Student ID: " + student.getId() + "\n" +
                                    "Name: " + student.getName() + "\n" +
                                    "Roll No: " + student.getRollNo() + "\n" +
                                    "Program: " + student.getProgram();

                            JOptionPane.showMessageDialog(null,
                                    studentData,
                                    "Student Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }, () -> {
                            JOptionPane.showMessageDialog(null,
                                    "No record found for the Student ID: " + id + ".\n" +
                                            "Please ensure the ID is correct and try again.",
                                    "Student Not Found",
                                    JOptionPane.WARNING_MESSAGE);
                        });

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "The entered ID is invalid.\nPlease enter a valid numeric Student ID.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "You must enter a Student ID to proceed.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}