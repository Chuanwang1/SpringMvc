package edu.ynjgy.impl.serviceimpl;

import edu.ynjgy.dao.ActivityDao;
import edu.ynjgy.dao.ActivityRegistrationDao;
import edu.ynjgy.dao.AdminDao;
import edu.ynjgy.model.Activity;
import edu.ynjgy.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityRegistrationDao registrationDao;

    @Autowired
    private AdminDao adminDao;



    //获取所有活动
    @Override
    public List<Activity> getAllActivities() {
        return activityDao.findAllActivity();
    }

    //获取已审批的活动
    @Override
    public List<Activity> getApprovedActivities() {
        return activityDao.findByStatus("Approved");
    }

    //获取待审批的活动
    @Override
    public List<Activity> getPendingActivities() {
        return activityDao.findByStatus("Pending");
    }

    @Override
    public List<Activity> getRejectedActivities() {
        return activityDao.findByStatus("Rejected");
    }

    //获取某个组织者发起的活动
    @Override
    public List<Activity> getActivitiesByOrganizerId(Integer organizerId) {
        return activityDao.findByOrganizerId(organizerId);
    }

    //获取某个活动
    @Override
    public Activity getActivityById(Integer id) {
        return activityDao.findByIdActivity(id);
    }

    //创建活动
    @Override
    public boolean createActivity(Activity activity) {
        activity.setStatus("Pending");
        return activityDao.addActivity(activity) > 0;
    }

    //更新活动
    @Override
    public boolean updateActivity(Activity activity) {
        return activityDao.updateActivity(activity) > 0;
    }

    //删除活动
    @Override
    public boolean deleteActivity(Integer id) {
        return activityDao.deleteActivity(id) > 0;
    }
}
