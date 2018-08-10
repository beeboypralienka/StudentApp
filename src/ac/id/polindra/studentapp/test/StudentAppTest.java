package ac.id.polindra.studentapp.test;

import java.util.List;

import ac.id.polindra.studentapp.model.Person;
import ac.id.polindra.studentapp.model.Student;
import ac.id.polindra.studentapp.model.StudentException;
import ac.id.polindra.studentapp.service.StudentService;
import ac.id.polindra.studentapp.service.StudentServiceImpl;

public class StudentAppTest {
	public static void main(String[] args) {
		StudentService service = new StudentServiceImpl();
		Person p = new Student("nama", "alamat", "010");
		
		
		System.out.println("Test Retrieve All Student");
		List<Student> students = service.getAllStudent();
		for (Student student : students) {
			System.out.print(student.getName()+"\t"+student.getAddress()+"\t"+student.getStudentId()+"\n");			
		}
		
		
//		System.out.println("Test Add Student");
//		try {
//			service.addStudent(new Student("123","Badrun","Bekasi"));
//		} catch (StudentException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("Test Find Student");
//		try {
//			Student s = service.findStudent("123");
//			System.out.println(s);
//		} catch (StudentException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("Test Update Student");
//		try {
//			service.updateStudent(new Student("123","Marman","Solo"));
//			System.out.println(service.findStudent("123"));
//		} catch (StudentException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("Test Delete Student");
//		try {
//			service.deleteStudent("123");
//			service.findStudent("123");
//		} catch (StudentException e) {
//			System.out.println(e.getMessage());
//		}
	
	}
}