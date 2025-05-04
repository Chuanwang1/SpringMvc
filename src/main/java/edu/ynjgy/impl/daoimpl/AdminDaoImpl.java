package edu.ynjgy.impl.daoimpl;

import edu.ynjgy.dao.AdminDao;
import edu.ynjgy.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    private JdbcTemplate  jdbcTemplate;
    private RowMapper<Admin> adminRowMapper = new RowMapper<Admin>() {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setId(rs.getInt("id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            return admin;
        }
    };

    //查询所有管理员
    @Override
    public List<Admin> findAllAdmin() {
        String adminSql = "select * from admin";
        return jdbcTemplate.query(adminSql,adminRowMapper);
    }

    //根据Id查询管理员
    @Override
    public Admin findByIdAdmin(Integer id) {
        String adminSql = "select * from admin where id = ?";
        return (Admin) jdbcTemplate.query(adminSql,adminRowMapper,id);
    }

    //根据用户名查询管理员
    @Override
    public Admin findByUsernameAdmin(String username) {
        String adminSql = "select * from admin where username = ?";
        return (Admin) jdbcTemplate.query(adminSql,adminRowMapper,username);
    }
}
