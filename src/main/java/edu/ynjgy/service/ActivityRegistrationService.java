package edu.ynjgy.service;

import edu.ynjgy.model.ActivityRegistration;

import java.util.List;

public interface ActivityRegistrationService {
    // 学生报名活动
    boolean registerActivity(Integer activityId, Integer studentId);

    // 取消报名
    boolean cancelRegistration(Integer activityId, Integer studentId);

    // 获取活动的所有报名学生
    List<ActivityRegistration> getRegisteredStudents(Integer activityId);

    // 检查学生是否已报名某活动
    boolean isStudentRegistered(Integer activityId, Integer studentId);

    // 获取学生报名的所有活动ID
    List<Integer> getStudentRegisteredActivityIds(Integer studentId);
}
