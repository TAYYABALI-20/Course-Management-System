package StudentCRUDOperations;

import javax.swing.JOptionPane;

import StudentForm.Form;
import StudentList.StudentList;
import table.Table;

public class CreateStudent {

    private Table table;

    private boolean isEditMode = false;

    private static int currentId = -1;

    private static ResetForm resetForm = new ResetForm();

    public void createStudent(Table table) {

        this.table = table;

        String newName = Form.nameField.getText().trim();
        String newRollNo = Form.rollField.getText().trim();
        String newProgram = Form.programField.getText().trim();

        if (!newName.isEmpty() && !newRollNo.isEmpty() && !newProgram.isEmpty()) {

            if (isEditMode) {

                StudentList.STUDENT_RECORDS.stream()
                        .filter(s -> s.getId() == currentId)
                        .findFirst()
                        .ifPresent(student -> {
                            student.setName(newName);
                            student.setRollNo(newRollNo);
                            student.setProgram(newProgram);
                        });

                table.insertStudentList();

                resetForm.resetForm(table);

                JOptionPane.showMessageDialog(null,
                        "The student record has been successfully updated.",
                        "Update Successful",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {

                try {

                    int id = Integer.parseInt(Form.idField.getText().trim());

                    boolean idExists = StudentList.STUDENT_RECORDS.stream()
                            .anyMatch(student -> student.getId() == id);

                    if (idExists) {
                        JOptionPane.showMessageDialog(null,
                                "The entered ID already exists in the system. Please use a unique ID.",
                                "Duplicate ID Detected",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    StudentList newStudent = new StudentList(id, newName, newRollNo, newProgram);
                    StudentList.addStudent(newStudent);

                    table.insertStudentList();

                    resetForm.resetForm(table);

                    JOptionPane.showMessageDialog(null,
                            "The student record has been successfully added to the system.",
                            "Addition Successful",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "The ID entered is invalid! Please enter a numeric ID.",
                            "Invalid ID",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "All fields are required! Please ensure that Name, Roll No, and Program are filled before submission.",
                    "Incomplete Form",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}