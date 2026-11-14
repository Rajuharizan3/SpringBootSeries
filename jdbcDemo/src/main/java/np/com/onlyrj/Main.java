package np.com.onlyrj;

import np.com.onlyrj.model.Student;
import np.com.onlyrj.repository.StudentRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server is running...");

        StudentRepository studentRepository = new StudentRepository();
//        studentRepository.createStudent(new Student("Premlal Harizan", "premharizan@gmail.com", 35));
//        studentRepository.updateStudent(new Student("Dhanmaya Harizan", "dhanmaya@gmail.com", 36), 7L);
//        studentRepository.deleteStudent(2L);
        studentRepository.getStudentById(3L);
//        studentRepository.getAllStudents();

    }
}