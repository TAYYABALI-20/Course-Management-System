package StudentCRUDOperations;

import StudentForm.Form;

import table.Table;

public class ResetForm {

    private Table table;

    private boolean isEditMode = false;

    private static int currentId = -1;

    public void resetForm(Table table) {
        
        this.table = table;
        
        Form.enableFormFields(false);
        Form.idField.setText("");
        Form.nameField.setText("");
        Form.rollField.setText("");
        Form.programField.setText("");
        isEditMode = false;
        currentId = -1;

    }
    
}