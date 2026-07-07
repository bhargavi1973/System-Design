import java.util.HashMap;
import java.util.Map;

//------------------------------------------------------------------------------- violation of SRP Principle
class StudentManagement {
    private Map<Integer, String> students = new HashMap<>();
    String grade = null;

    public void addStudent(String name, int rollNumber){
        students.put(rollNumber, name);
    }

    public void removeStudent(int rollNumber){
        students.remove(rollNumber);
    }

    public void calcGrade(int obtainedMarks, int rollNumber){
        if (obtainedMarks >= 90) {
            grade = " A";
        } else if (obtainedMarks >= 80) {
            grade = "B";
        } else {
            grade = "C";
        }
        System.out.println("Grade for studentt with roll number " + rollNumber + " is:" + grade);
    }

    public void printReportCard(int rollNumber){
        String studentName = students.get(rollNumber);
        System.out.println("-------- Printing Report Card----------" );
        System.out.println("Student Name:" + students.get(rollNumber));
        System.out.println("Roll Number:" + rollNumber);
        System.out.println("Grade:" + grade);
    }

    public void saveToDatabase(int rollNumber){
        
        System.out.println("---------Saving to database:" + students.get(rollNumber) + ", Roll Number: " + rollNumber);
    }

    public void sendEmail(int rollNumber){
        String studentName = students.get(rollNumber);
        System.out.println("---------sending email to :" + studentName);
    }
} 




// ----------------------------------------------------------------------------refactored code adhering to SRP Principles
// Responsible only for managing students
class StudentManagement {

    private Map<Integer, String> students = new HashMap<>();

    public void addStudent(String name, int rollNumber) {
        if (!students.containsKey(rollNumber)) {
            students.put(rollNumber, name);
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " already exists.");
        }
    }

    public void removeStudent(int rollNumber) {
        if (!students.containsKey(rollNumber)) {
            System.out.println("Student with roll number " + rollNumber + " does not exist.");
        } else {
            students.remove(rollNumber);
            System.out.println("Student removed successfully.");
        }
    }

    public String getStudent(int rollNumber) {
        return students.get(rollNumber);
    }
}

// Responsible only for grade calculation
class GradeCalculator {

    public String calcGrade(int obtainedMarks) {

        if (obtainedMarks >= 90) {
            return "A";
        } else if (obtainedMarks >= 80) {
            return "B";
        } else {
            return "C";
        }
    }
}

// Responsible only for printing report cards
class ReportCardPrinter {

    public void printReportCard(String studentName, int rollNumber, String grade) {

        System.out.println("\n--------- Report Card ---------");
        System.out.println("Student Name : " + studentName);
        System.out.println("Roll Number  : " + rollNumber);
        System.out.println("Grade        : " + grade);
    }
}

// Responsible only for database operations
class DatabaseServer {

    public void saveToDatabase(String studentName, int rollNumber) {

        System.out.println("\nSaving to Database...");
        System.out.println("Student Name : " + studentName);
        System.out.println("Roll Number  : " + rollNumber);
    }
}

// Responsible only for sending emails
class EmailService {

    public void sendEmail(String studentName) {

        System.out.println("\nSending email to " + studentName + "...");
    }
}
public class StudentManagementSystem{
    public static void main(String[] args){
        StudentManagement obj = new StudentManagement();
        obj.addStudent("Riya", 1);
        obj.addStudent("Rohit", 2);
        obj.calcGrade(95, 1);
        obj.printReportCard(1);
        obj.saveToDatabase(1);
        obj.sendEmail(1);


        // ----------------------------------------------------------------Refractored code for main function
        StudentManagement studentService = new StudentManagement();
        studentService.addStudent("Riya", 1);
        studentService.addStudent("Rohit", 2);

        String studentName = studentService.getStudent(1);

        GradeCalculator gradeCalculator = new GradeCalculator();
        String grade = gradeCalculator.calcGrade(95);

        ReportCardPrinter printer = new ReportCardPrinter();
        printer.printReportCard(studentName, 1, grade);

        DatabaseServer database = new DatabaseServer();
        database.saveToDatabase(studentName, 1);

        EmailService emailService = new EmailService();
        emailService.sendEmail(studentName);
    }
}

