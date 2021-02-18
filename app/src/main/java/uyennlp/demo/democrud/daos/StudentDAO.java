package uyennlp.demo.democrud.daos;

import android.widget.Toast;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uyennlp.demo.democrud.dtos.StudentDTO;
import uyennlp.demo.democrud.utils.DBHelper;

public class StudentDAO implements Serializable {

    private Connection con;
    private Statement stm;
    private ResultSet rs;

    public StudentDAO() {
    }

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }

        if (stm != null) {
            stm.close();
        }

        if (con != null) {
            con.close();
        }
    }

    public List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        List<StudentDTO> result = new ArrayList<>();

        try {
            String sql = "SELECT id, name, age " +
                    "FROM Student";
            con = DBHelper.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                result.add(new StudentDTO(id, name, age));
                System.out.println(id);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        boolean result = false;

        try {
            String sql = "UPDATE Student "
                    + "SET name = '" + dto.getName() + "'"
                    + ", age = " + dto.getAge()
                    + " WHERE id = " + dto.getId();

            con = DBHelper.getConnection();
            stm = con.createStatement();

            System.out.println(75 + "ss");
            result = stm.executeUpdate(sql) > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean deleteStudent(int id) throws SQLException, ClassNotFoundException {
        boolean result = false;

        try {
            String sql = "DELETE FROM Student "
                    + "WHERE id = " + id;

            con = DBHelper.getConnection();
            stm = con.createStatement();

            result = stm.executeUpdate(sql) > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public List<StudentDTO> searchByAge(int age) throws SQLException, ClassNotFoundException {
        List<StudentDTO> result = new ArrayList<>();

        try {
            String sql = "SELECT id, name, age " +
                    "FROM Student " +
                    "WHERE age = " + age;
            con = DBHelper.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                result.add(new StudentDTO(id, name, age));
                System.out.println(id);
            }
        } finally {
            closeConnection();
        }

        return result;
    }
}
