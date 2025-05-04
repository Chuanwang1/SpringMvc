package edu.ynjgy.dao;

import edu.ynjgy.model.Activity;
import edu.ynjgy.model.ActivityRegistration;

import java.util.List;

public interface ActivityDao {
    //  查询所有活动
    List<Activity> findAllActivity();
    //  查询指定id的活动
    Activity findByIdActivity(Integer id);
    //  查询指定标题的活动
    Activity findByTitleActivity(String title);
    //  查询指定组织者的活动
    List<Activity> findByOrganizerId(Integer organizerId);
    //  添加活动
    int addActivity(Activity activity);
    //  更新活动
    Integer updateActivity(Activity activity);
    //  删除活动
    Integer deleteActivity(Integer id);
    //  保存活动
    Integer saveActivity(Activity activity);
    //  查询活动报名情况
    List<ActivityRegistration> findAllActivityRegistration();
    //  查询指定活动报名情况
    List<ActivityRegistration> findByActivityIdActivityRegistration(Integer activity_id);
    //  查询活动状态
    List<Activity> findByStatus(String pending);
}
