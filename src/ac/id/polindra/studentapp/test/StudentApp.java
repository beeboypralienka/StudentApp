package ac.id.polindra.studentapp.test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import ac.id.polindra.studentapp.model.Student;
import ac.id.polindra.studentapp.model.StudentException;
import ac.id.polindra.studentapp.service.StudentService;
import ac.id.polindra.studentapp.service.StudentServiceImpl;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class StudentApp extends JFrame{
	
	private JTextField txtId;
	private JTextField txtNama;
	private JTextField txtAlamat;
	
	private StudentService service;
	
	public StudentApp() {
		service = new StudentServiceImpl();
		getContentPane().setLayout(null);
		
		JLabel lblStudentApplication = new JLabel("Student Application");
		lblStudentApplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentApplication.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStudentApplication.setBounds(115, 11, 194, 28);
		getContentPane().add(lblStudentApplication);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(21, 92, 46, 14);
		getContentPane().add(lblAlamat);
		
		JLabel lblNamax = new JLabel("Nama");
		lblNamax.setBounds(21, 67, 46, 14);
		getContentPane().add(lblNamax);
		
		JLabel lblStudentId = new JLabel("Student Id");
		lblStudentId.setBounds(21, 42, 70, 14);
		getContentPane().add(lblStudentId);
		
		txtId = new JTextField();
		txtId.setBounds(101, 39, 249, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtNama = new JTextField();
		txtNama.setBounds(100, 64, 250, 20);
		getContentPane().add(txtNama);
		txtNama.setColumns(10);
		
		txtAlamat = new JTextField();
		txtAlamat.setBounds(101, 89, 249, 20);
		getContentPane().add(txtAlamat);
		txtAlamat.setColumns(10);

		JButton btnCari = new JButton("Cari");
		
		btnCari.setBounds(21, 116, 59, 23);
		getContentPane().add(btnCari);
		
		btnCari.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Student s = service.findStudent(txtId.getText());
					if(s!=null) {
						txtId.setText(s.getName());
						txtNama.setText(s.getAddress());
						txtAlamat.setText(s.getStudentId());
					}											
				} catch (StudentException se) {
					System.out.println(se.getMessage());
					JOptionPane.showMessageDialog(null, txtId.getText()+" Tidak ditemukan!");
				}
			}
		});
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setBounds(101, 116, 70, 23);
		getContentPane().add(btnTambah);
		
		btnTambah.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					service.addStudent(new Student(txtNama.getText(),txtAlamat.getText(),txtId.getText()));
					System.out.println("Data berhasil disimpan!");
				} catch (StudentException se) {
					System.out.println(se.getMessage());
				}
				
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(192, 116, 67, 23);
		getContentPane().add(btnUpdate);
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					service.updateStudent(new Student(txtNama.getText(),txtAlamat.getText(),txtId.getText()));
					System.out.println(service.findStudent(txtId.getText()));
				} catch (StudentException se) {
					System.out.println(se.getMessage());
					JOptionPane.showMessageDialog(null, txtId.getText()+" Tidak ditemukan!");
				}
				
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(280, 116, 70, 23);
		getContentPane().add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					service.deleteStudent(txtId.getText());
					service.findStudent(txtId.getText());
				} catch (StudentException se) {
					System.out.println(se.getMessage());
				}
				
			}
		});
		
		JButton btnView = new JButton("View");
		btnView.setBounds(280, 150, 70, 23);
		getContentPane().add(btnView);
		
		btnView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Student> students = service.getAllStudent();
				for (Student student : students) {
					System.out.print(student.getName()+"\t"+student.getAddress()+"\t"+student.getStudentId()+"\n");			
				}			
			}
		});
		
		
		setSize(400, 250);
	}
	public static void main(String[] args) {
		new StudentApp().setVisible(true);
	}
}
