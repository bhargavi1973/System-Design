1. UML DIAGRAM FOR STUDENT MANAGEMENT SYSTEM
  1(a). Violation of SRP Principle
   ```mermaid
classDiagram

class StudentManagement{
    -Map~Integer,String~ students
    -String grade

    +addStudent(String name, int rollNumber)
    +removeStudent(int rollNumber)
    +calcGrade(int obtainedMarks, int rollNumber)
    +printReportCard(int rollNumber)
    +saveToDatabase(int rollNumber)
    +sendEmail(int rollNumber)
}
```
