package StudentCRUDOperations;

import javax.swing.JOptionPane;

import StudentForm.Form;
import StudentList.StudentList;

import table.Table;

public class UpdateStudent {

    private Table table;

    private boolean isEditMode = false;

    private static int currentId = -1;

    public void updateStudent(Table table) {

        this.table = table;

        String idInput = JOptionPane.showInputDialog(null,
                "Please enter the Student ID to update their information:",
                "Admin - Update Student Details",
                JOptionPane.QUESTION_MESSAGE);

        if (idInput != null && !idInput.trim().isEmpty()) {

            try {

                currentId = Integer.parseInt(idInput);

                StudentList student = StudentList.STUDENT_RECORDS.stream()
                        .filter(s -> s.getId() == currentId)
                        .findFirst()
                        .orElse(null);

                if (student != null) {

                    Form.nameField.setText(student.getName());
                    Form.rollField.setText(student.getRollNo());
                    Form.programField.setText(student.getProgram());
                    Form.enableFormFields(true);
                    Form.enableIDField(false);
                    isEditMode = true;

                    JOptionPane.showMessageDialog(null,
                            "Student details have been successfully loaded.\n" +
                                    "You can now make changes and click 'Submit' to save the updates.",
                            "Student Details Loaded",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(null,
                            "No record found for the entered Student ID: " + currentId + ".\n" +
                                    "Please ensure the ID is correct and try again.",
                            "Student Not Found",
                            JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input detected! Please enter a numeric Student ID.",
                        "Invalid ID Format",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "A Student ID is required to proceed.\nPlease enter a valid ID to continue.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}