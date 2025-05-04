package edu.ynjgy.service;

import edu.ynjgy.model.Activity;


import java.util.List;

public interface ActivityService {
    // 获取所有活动
    List<Activity> getAllActivities();

    // 获取已审核通过的活动
    List<Activity> getApprovedActivities();

    // 获取待审核的活动
    List<Activity> getPendingActivities();

    //  获取拒绝的活动
    List<Activity> getRejectedActivities();

    // 根据社团负责人ID获取活动
    List<Activity> getActivitiesByOrganizerId(Integer organizerId);

    // 根据ID获取活动
    Activity getActivityById(Integer id);

    // 创建活动
    boolean createActivity(Activity activity);

    // 更新活动
    boolean updateActivity(Activity activity);

    // 删除活动
    boolean deleteActivity(Integer id);
}
