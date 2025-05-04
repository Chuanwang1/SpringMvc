package edu.ynjgy.dao;

import edu.ynjgy.model.Admin;

import java.util.List;

public interface AdminDao {
    //  查询所有管理员
    List<Admin> findAllAdmin();
    //  根据id查询管理员
    Admin findByIdAdmin(Integer id);
    //  根据用户名查询管理员
    Admin findByUsernameAdmin(String username);

}
