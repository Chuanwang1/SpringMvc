package edu.ynjgy.service;

import edu.ynjgy.model.Activity;
import edu.ynjgy.model.Student;

import java.util.List;

public interface StudentService {
    //查询获取所有学生
    List<Student> getAllStudent();
    //根据id查询学生
    Student getStudentById(Integer id);
    //学生登录
    Student login(String username , String password);
    //学生注册
    boolean register(Student student);
    //更新学生信息
    boolean update(Student student);
    //删除学生
    Integer delete(Integer id);
    //加入活动
    boolean joinActivity(Integer studentId, Integer activityId);
    //获取学生活动报名记录
    List<Activity> getStudentActivityRegistration(Integer id);
}
