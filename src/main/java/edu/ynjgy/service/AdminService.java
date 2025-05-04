package edu.ynjgy.service;

import edu.ynjgy.model.Activity;
import edu.ynjgy.model.Admin;
import edu.ynjgy.model.Student;

import java.util.List;

public interface AdminService {
    // 管理员登录
    public Admin login(String username, String password);
    // 获取所有待审核活动
    List<Activity> getPendingActivities();

    // 审核活动
    boolean approveActivity(Integer activityId);

    // 驳回活动
    boolean rejectActivity(Integer activityId);

    // 获取所有已审核活动
    List<Activity> getApprovedActivities();

    // 获取学生
     List<Student> getAllStudents();

    //管理学生
    Student getStudentByName(String name);
    //删除学生
    boolean deleteStudent(Integer id);


//    // 管理yuan
//    Admin getAdminById(Integer id);
}
