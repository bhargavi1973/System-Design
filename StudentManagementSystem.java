// violation of SRP Principle
import java.util.HashMap;
import java.util.Map;

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

public class StudentManagementSystem{
    public static void main(String[] args){
        StudentManagement obj = new StudentManagement();
        obj.addStudent("Riya", 1);
        obj.addStudent("Rohit", 2);
        obj.calcGrade(95, 1);
        obj.printReportCard(1);
        obj.saveToDatabase(1);
        obj.sendEmail(1);

    }
}
