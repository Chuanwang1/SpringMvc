package edu.ynjgy.dao;


import edu.ynjgy.model.ActivityRegistration;

import java.util.List;

public interface ActivityRegistrationDao {
    // 查询所有报名记录
    List<ActivityRegistration> findAllActivityRegistration();

    // 根据活动ID查询报名记录
    List<ActivityRegistration> findByActivityIdActivityRegistration(Integer activityId);

    // 根据学生ID查询报名记录
    List<ActivityRegistration> findByStudentIdActivityRegistration(Integer studentId);

    // 查询学生是否已报名某活动
    boolean isRegisteredActivityRegistration(Integer activityId, Integer studentId);

    // 保存报名记录
    int saveActivityRegistration(ActivityRegistration registration);

    // 删除报名记录
    int deleteActivityRegistration(Integer id);

    // 根据活动ID删除所有报名记录
    int deleteByActivityIdActivityRegistration(Integer activityId);

    // 根据学生ID删除所有报名记录
    int deleteByStudentIdActivityRegistration(Integer studentId);


}