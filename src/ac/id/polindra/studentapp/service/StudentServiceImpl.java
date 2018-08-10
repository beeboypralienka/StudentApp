package ac.id.polindra.studentapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ac.id.polindra.studentapp.model.Student;
import ac.id.polindra.studentapp.model.StudentException;

public class StudentServiceImpl implements StudentService{
	String dbUrl = "jdbc:derby://localhost:1527/javaprogramming";
	String username ="eclipse";
	String password = "eclipse";
	@Override
	public void addStudent(Student s) throws StudentException {
		String sql = "Insert into student values(?,?,?)";
		try(
			Connection conn = DriverManager.getConnection(dbUrl, username, password);
			PreparedStatement st = conn.prepareStatement(sql);
		){
			st.setString(1, s.getStudentId());
			st.setString(2, s.getName());
			st.setString(3, s.getAddress());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new StudentException("Gagal add student: " + e.getMessage());
		} 		
	}
	@Override
	public void updateStudent(Student s) throws StudentException {		
		Student sTemp = findStudent(s.getStudentId());
		if(sTemp != null) {
			System.out.println("Update student!!!");
			String sql = "update student set name = '"+s.getName() + 
					"', address = '"+s.getAddress()+
					"' where studentid = '"+s.getStudentId()+"'";
			System.out.println(sql);
			try(
				Connection conn = DriverManager.getConnection(dbUrl, username, password);
				Statement st = conn.createStatement();
			){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				throw new StudentException("Gagal update student: " + e.getMessage());
			} 					
		} else {
			throw new StudentException("Student dengan id " + s.getStudentId() + " tidak ditemukan");					
		}
	}	
	
	@Override
	public List<Student> getAllStudent() {
		String sql = "select * from student";
		List<Student> students = new ArrayList<>();
		try(
			Connection conn = DriverManager.getConnection(dbUrl, username, password);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
		){
			while(rs.next()) {
				students.add(new Student(rs.getString("studentid"),
						rs.getString("name"),rs.getString("address")));				
			}
		} catch (SQLException e) {
			e.printStackTrace();				
		} 
		return students;
	}

@Override
public Student findStudent(String id) throws StudentException {
	String sql = "Select * from student where studentid = '"+id+"'";
	Student s = null;
	try(
		Connection conn = DriverManager.getConnection(dbUrl, username, password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);						
	){		
		while(rs.next()) {
			s = new Student(rs.getString("studentid"),rs.getString("name"),rs.getString("address"));
		}			
	} catch (SQLException e) {
		throw new StudentException("Student tidak ditemukan: " + e.getMessage());
	} 		
	if(s != null) {
		return s;
	} else {
		throw new StudentException("Student dengan id " + id + " tidak ditemukan");					
	}
			
}



@Override
public void deleteStudent(String id) throws StudentException {
	Student sTemp = findStudent(id);
	if(sTemp != null) {
		String sql = "Delete from student where studentid ='"+id+"'";
		try(
			Connection conn = DriverManager.getConnection(dbUrl, username, password);
			Statement st = conn.createStatement();			
		){
			st.executeUpdate(sql);
			System.out.println(id+" berhasil dihapus!");
		} catch (SQLException e) {
			throw new StudentException("Gagal delete student: " + e.getMessage());
		} 					
	} else {
		throw new StudentException("Student dengan id " + id + " tidak ditemukan");					
	}
	
}


}
