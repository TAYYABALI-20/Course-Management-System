package StudentCRUDOperations;

import javax.swing.JOptionPane;

import StudentList.StudentList;
import table.Table;

public class DeleteStudent {

    private Table table;

    public void deleteStudent(Table table) {

        this.table = table;

        String idInput = JOptionPane.showInputDialog(null,
                "Enter the Student ID to delete:",
                "Delete Student",
                JOptionPane.QUESTION_MESSAGE);

        if (idInput != null && !idInput.isEmpty()) {

            try {

                int id = Integer.parseInt(idInput);

                StudentList.STUDENT_RECORDS.stream()
                        .filter(student -> student.getId() == id)
                        .findFirst()
                        .ifPresentOrElse(student -> {
                            int confirm = JOptionPane.showConfirmDialog(
                                    null,
                                    "Are you sure you want to delete the record for Student ID: " + id
                                            + "?\n" +
                                            "This action cannot be undone.",
                                    "Delete Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE);

                            if (confirm == JOptionPane.YES_OPTION) {

                                StudentList.removeStudent(student);

                                StudentList.reassignIDs();

                                table.insertStudentList();

                                JOptionPane.showMessageDialog(null,
                                        "Student record deleted successfully.",
                                        "Deletion Successful",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }

                        }, () -> {
                            JOptionPane.showMessageDialog(null,
                                    "No student found with the ID: " + id + ".",
                                    "Student Not Found",
                                    JOptionPane.WARNING_MESSAGE);
                        });

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid ID! Please enter a numeric ID.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid Student ID!",
                    "Empty Input",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}