package np.com.onlyrj.repository;

import np.com.onlyrj.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "password";
//    Connection connection = null;
//    PreparedStatement preparedStatement;


    public void createStudent(Student student){

        String sql = """
                            INSERT INTO student(name, email, age)
                            VALUES (?, ?, ?)
                            """;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){

//            Statement statement = connection.createStatement();

//            String sql = """
//                            INSERT INTO student(name, email, age)
//                            VALUES ('%s', '%s', '%d')
//                            """.formatted(student.getName(), student.getEmail(), student.getAge());
//
//            int result = statement.executeUpdate(sql);
//            if(result == 1){
//                System.out.println("Student created successfully");
//            }
//            else {
//                System.out.println("Creation failed");
//            }


            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());

            int rowAffecdted = preparedStatement.executeUpdate();

            if(rowAffecdted == 1){
                System.out.println("Student created successfully");
            }
            else {
                System.out.println("Student created failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateStudent(Student student, Long id){

        String sql = """
                        UPDATE student
                        SET name = ?,
                            email = ?,
                            age = ?
                        WHERE id = ?
                        """;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);){

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setLong(4, id);


            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected == 1){
                System.out.println("Student update successfully");
            }
            else {
                System.out.println("Updation failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id){

        String sql = """
                        DELETE FROM student WHERE id = ?
                        """;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();
            if(result == 1){
                System.out.println("Student deleted successfully");
            }
            else {
                System.out.println("deletion failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getStudentById(Long id){
        String sql = """
                        SELECT id, name, email, age FROM student
                        WHERE id = ?
                        """;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Student student = mapRow(resultSet);
                    System.out.println(student);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getAllStudents(){
        String sql = """
                        SELECT id, name, email, age FROM student
                        """;

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                List<Student> studentList = new ArrayList<>();

                while (resultSet.next()){
                    Student student = mapRow(resultSet);
                    studentList.add(student);
                    System.out.println(student);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }

    public void completeCrud(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String sql = "SELECT id, name, email, age FROM student where id = 2";

            boolean restult = statement.execute(sql);

            if(restult){
                ResultSet resultSet = statement.getResultSet();
            }else {
                int rowAffected = statement.getUpdateCount();
            }
            connection.close();

        }catch (SQLException e){
            System.out.println("Database connection failed...");
            e.printStackTrace();
        }
    }
}
