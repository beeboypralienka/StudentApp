package ac.id.polindra.studentapp.service;

import java.util.List;

import ac.id.polindra.studentapp.model.Student;
import ac.id.polindra.studentapp.model.StudentException;

public interface StudentService {
	void addStudent(Student s) throws StudentException;
	void updateStudent(Student s) throws StudentException;
	void deleteStudent(String id) throws StudentException;
	Student findStudent(String s) throws StudentException;
	
	List<Student> getAllStudent();
}