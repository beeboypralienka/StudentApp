package ac.id.polindra.studentapp.model;

public class Student extends Person{
	
	private String studentId;	
		
//	public Student(String studentId) {		
//		super();
//		this.studentId = studentId;
//	}
	
	public Student(String name, String address, String studentId) {
		super(name, address);
		this.studentId = studentId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
		
}