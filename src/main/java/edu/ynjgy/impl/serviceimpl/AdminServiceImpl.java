package edu.ynjgy.impl.serviceimpl;

import edu.ynjgy.dao.*;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.Admin;
import edu.ynjgy.model.Student;
import edu.ynjgy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityRegistrationDao activityRegistrationDao;
    @Autowired
    private OrganizerDao organizerDao;
    @Autowired
    private StudentDao studentDao;

    // 管理员登录
    @Override
    public Admin login(String username, String password) {
        Admin admin = adminDao.findByUsernameAdmin(username);
        if(admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    //  获取待审核活动
    @Override
    public List<Activity> getPendingActivities() {

        return activityDao.findByStatus("Pending");
    }


    // 审核活动
    @Override
    public boolean approveActivity(Integer activityId) {
//        Activity activity = activityDao.findByIdActivity(activityId);
//        if (activity != null && activity.getStatus().equals("pending")){
//            return activityDao.updateActivity(activity) > 0;
//        }
//        else {
//            return false;
//        }
        try {
            Activity activity = activityDao.findByIdActivity(activityId);
            if (activity != null && "Pending".equals(activity.getStatus())) {
                activity.setStatus("Approved");
                return activityDao.updateActivity(activity) > 0;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 拒绝活动
    @Override
    public boolean rejectActivity(Integer activityId) {
        try {
            Activity activity = activityDao.findByIdActivity(activityId);
            if (activity != null && "Pending".equals(activity.getStatus())) {
                activity.setStatus("Rejected");
                return activityDao.updateActivity(activity) > 0;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取已审核活动
    @Override
    public List<Activity> getApprovedActivities() {
        return activityDao.findByStatus("Approved");
    }

    //  获取所有学生
    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    // 管理学生
    @Override
    public Student getStudentByName(String name) {
        return studentDao.findByname(name);
    }

    // 删除学生
    @Override
    public boolean deleteStudent(Integer id) {
        Student student = studentDao.findById(id);
        if (student != null){
            return studentDao.delete(id) > 0;
        }
        return false;
    }

}
