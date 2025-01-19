package StudentList;

import java.util.ArrayList;

public class StudentList {

    private int id;
    private String name;
    private String rollNo;
    private String program;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public StudentList(int id, String name, String rollNo, String program) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.program = program;
    }

    public Object[] getStudentData() {
        return new Object[] { id, name, rollNo, program };
    }

    public static ArrayList<StudentList> STUDENT_RECORDS = new ArrayList<>();

    static {
        STUDENT_RECORDS.add(new StudentList(1, "SARIM", "001", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(2, "WAHAJ", "002", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(3, "MUSAWIR", "003", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(4, "AHSAN", "004", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(5, "S.M HAMZA", "005", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(6, "TAYYAB", "006", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(7, "ALISHBA AHMED", "007", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(8, "SAMAN ZAHRA", "008", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(9, "AFSAN BALOCH", "009", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(10, "ALISHBA IQBAL", "010", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(11, "NARGIS", "011", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(12, "ALISHBA KHAN", "012", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(13, "MUHAMMAD ZAID", "013", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(14, "IRFAN RASOOL", "014", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(15, "MUHAMMAD OWAIS", "015", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(16, "NASIR KHAN", "016", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(17, "RAJ BHAGAT", "017", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(18, "ZAID", "018", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(19, "ABDUL WASAY", "019", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(20, "HASNAIN", "020", "Software Engineering Technology"));
        STUDENT_RECORDS.add(new StudentList(21, "ZAIN", "021", "Software Engineering Technology"));
    }

    public static boolean addStudent(StudentList student) {

        boolean idExists = STUDENT_RECORDS.stream()
                .anyMatch(existingStudent -> existingStudent.getId() == student.getId());

        if (!idExists) {
            STUDENT_RECORDS.add(student);
            return true;
        } else {
            return false;
        }
    }

    public static void removeStudent(StudentList student) {
        STUDENT_RECORDS.remove(student);
    }

    public static void reassignIDs() {
        for (int i = 0; i < StudentList.STUDENT_RECORDS.size(); i++) {
            StudentList.STUDENT_RECORDS.get(i).setId(i + 1);
        }
    }

    public static void printAllStudents() {
        for (StudentList student : STUDENT_RECORDS) {
            System.out.println(student);
        }
    }

    @Override
    public String toString() {
        return "StudentList [id=" + id + ", name=" + name + ", rollNo=" + rollNo + ", program=" + program + "]";
    }

}