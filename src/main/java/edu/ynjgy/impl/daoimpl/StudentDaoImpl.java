package edu.ynjgy.impl.daoimpl;

import edu.ynjgy.dao.StudentDao;
import edu.ynjgy.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //  创建student映射器
    private RowMapper <Student> studentRowMapper = new RowMapper<Student>() {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setUsername(rs.getString("username"));
            student.setPassword(rs.getString("password"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            return student;
        }
    };


    // 查询所有学生
    @Override
    public List<Student> findAll() {
        String StudentSql = "select * from student";
        return jdbcTemplate.query(StudentSql , studentRowMapper);
    }


    //  查询指定id的学生
    @Override
    public Student findById(Integer id) {
        String StudentSql = "select * from student where id = ?";
        return (Student) jdbcTemplate.queryForObject(StudentSql , studentRowMapper , id);
    }


    //  查询指定username的学生
    @Override
    public Student findByname(String name) {
        String StudentSql = "select * from student where name = ?";
        return (Student) jdbcTemplate.query(StudentSql , studentRowMapper , name);
    }

    //  添加学生
    @Override
    public int add(Student student) {
        String StudentSql = "insert into student(username, password, name, email) values(?, ?, ?, ?)";
        return jdbcTemplate.update(StudentSql , student.getUsername() , student.getPassword() , student.getName() , student.getEmail());
    }

    //  更新学生
    @Override
    public Integer update(Student student) {
        String StudentSql = "Update student set username = ? , password=?,name=?,email=?";
        return jdbcTemplate.update(StudentSql , student.getUsername() , student.getPassword() , student.getName() , student.getEmail());
    }

    //  删除学生
    @Override
    public Integer delete(Integer id) {
        String StudentSql = "delete from student where id = ?";
        return jdbcTemplate.update(StudentSql , id);
    }

    //  保存学生
    @Override
    public Integer save(Student student) {
        String StudentSql = "insert into student(username, password, name, email) values(?, ?, ?, ?)";
        return jdbcTemplate.update(StudentSql , student.getUsername() , student.getPassword() , student.getName() , student.getEmail());
    }



}
