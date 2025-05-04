package edu.ynjgy.impl.serviceimpl;

import edu.ynjgy.dao.ActivityDao;
import edu.ynjgy.dao.ActivityRegistrationDao;
import edu.ynjgy.dao.StudentDao;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.ActivityRegistration;
import edu.ynjgy.service.ActivityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ActivityRegistrationServiceImpl implements ActivityRegistrationService {

    @Autowired
    private ActivityRegistrationDao activityRegistrationDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private Activity activity;

//    // 活动报名情况
//    @Override
//    public boolean registerActivity(Integer activityId, Integer studentId) {
////        try{
////            Activity activity = activityDao.findByIdActivity(activityId);
//////            Student student = studentDao.findById(studentId);
////            if (activity != null && "Approved".equals(activity.getStatus())){
////                return false;
////            }
////
////        }
////        catch (Exception e){
////            return false;
////        }
//////        return activityRegistrationDao.saveActivityRegistration(new ActivityRegistration(activityId, studentId)) > 0;
//        return false;
//    }
//
//    // 取消活动报名
//    @Override
//    public boolean cancelRegistration(Integer activityId, Integer studentId) {
//        return false;
//    }
//
//    // 获取报名学生
//    @Override
//    public List<ActivityRegistration> getRegisteredStudents(Integer activityId) {
//        return activityRegistrationDao.findByActivityIdActivityRegistration(activityId);
//    }
//
//    // 判断学生是否报名
//    @Override
//    public boolean isStudentRegistered(Integer activityId, Integer studentId) {
//        return false;
//    }
//
//    // 获取学生报名的活动id
//    @Override
//    public List<Integer> getStudentRegisteredActivityIds(Integer studentId) {
//        return Collections.emptyList();
//
//        //级联查询
//    }
// 活动报名情况
@Override
public boolean registerActivity(Integer activityId, Integer studentId) {
    try {
        // 检查活动是否存在且状态为已批准
        Activity activity = activityDao.findByIdActivity(activityId);
        if (activity == null || !"Approved".equals(activity.getStatus())) {
            return false;
        }

        // 检查学生是否已报名
        if (activityRegistrationDao.isRegisteredActivityRegistration(activityId, studentId)) {
            return false;
        }

        // 创建新的报名记录
        ActivityRegistration registration = new ActivityRegistration();
        registration.setActivity_id(activityId);
        registration.setStudent_id(studentId);
        registration.setRegister_time((int) (System.currentTimeMillis() / 1000));

        return activityRegistrationDao.saveActivityRegistration(registration) > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    // 取消活动报名
    @Override
    public boolean cancelRegistration(Integer activityId, Integer studentId) {
        try {
            // 查找该学生的报名记录
            List<ActivityRegistration> registrations = activityRegistrationDao.findByStudentIdActivityRegistration(studentId);
            for (ActivityRegistration registration : registrations) {
                if (registration.getActivity_id().equals(activityId)) {
                    return activityRegistrationDao.deleteActivityRegistration(registration.getId()) > 0;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取报名学生
    @Override
    public List<ActivityRegistration> getRegisteredStudents(Integer activityId) {
        return activityRegistrationDao.findByActivityIdActivityRegistration(activityId);
    }

    // 判断学生是否报名
    @Override
    public boolean isStudentRegistered(Integer activityId, Integer studentId) {
        return activityRegistrationDao.isRegisteredActivityRegistration(activityId, studentId);
    }

    // 获取学生报名的活动id
    @Override
    public List<Integer> getStudentRegisteredActivityIds(Integer studentId) {
        List<ActivityRegistration> registrations = activityRegistrationDao.findByStudentIdActivityRegistration(studentId);
        List<Integer> activityIds = new ArrayList<>();
        for (ActivityRegistration registration : registrations) {
            activityIds.add(registration.getActivity_id());
        }
        return activityIds;
    }
}
