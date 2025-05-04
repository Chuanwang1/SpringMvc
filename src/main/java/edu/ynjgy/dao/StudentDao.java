package edu.ynjgy.dao;

import edu.ynjgy.model.Student;

import java.util.List;

public interface StudentDao {
    //查询所有学生
    List<Student> findAll();
    //根据id查询学生
    Student findById(Integer id);
    //根据名字查询学生
    Student findByname(String name);
    //添加学生
    int add(Student student);
    //更新学生
    Integer update(Student student);
    //删除学生
    Integer delete(Integer id);
    //保存学生信息
    Integer save(Student student);

}
