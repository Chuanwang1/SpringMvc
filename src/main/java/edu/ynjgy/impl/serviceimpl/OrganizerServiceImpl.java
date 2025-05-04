package edu.ynjgy.impl.serviceimpl;

import edu.ynjgy.dao.ActivityDao;
import edu.ynjgy.dao.ActivityRegistrationDao;
import edu.ynjgy.dao.OrganizerDao;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.Organizer;
import edu.ynjgy.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    @Autowired
    private OrganizerDao organizerDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityRegistrationDao activityRegistrationDao;

    //获取所有社团
    @Override
    public List<Organizer> getAllOrganizers() {
        return organizerDao.findAllOrganizer();
    }

    //根据id获取社团
    @Override
    public Organizer getOrganizerById(Integer id) {
        return organizerDao.findByIdOrganizer(id);
    }

    //登录
    @Override
    public Organizer login(String username, String password) {
        Organizer organizer = organizerDao.findByUsernameOrganizer(username);
        if(organizer != null && organizer.getPassword().equals(password)) {
            return organizer;
        }
        return null;
    }

    //注册
    @Override
    public boolean register(Organizer organizer) {
        return false;
    }

    //修改
    @Override
    public boolean updateOrganizer(Organizer organizer) {
        return false;
    }

    //删除
    @Override
    public boolean deleteOrganizer(Integer id) {
        try {
            // 1. 查询该社团下的所有活动
            List<Activity> activities = (List<Activity>) activityDao.findByIdActivity(id);

            //  删除该社团的所有活动及其相关报名记录
            for (Activity activity : activities) {
                // 假设 ActivityRegistrationDao 提供了根据活动ID删除报名记录的方法
                activityRegistrationDao.deleteActivityRegistration(activity.getId());

                // 删除活动
                activityDao.deleteActivity(activity.getId());
            }

            // 3. 最后删除社团
            int result = organizerDao.deleteOrganizer(id);

            return result > 0;
        } catch (Exception e) {
            // 可以记录日志或抛出异常
            e.printStackTrace();
            return false;
        }
    }

}
