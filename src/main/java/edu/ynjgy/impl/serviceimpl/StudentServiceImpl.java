package edu.ynjgy.impl.serviceimpl;

import edu.ynjgy.dao.ActivityDao;
import edu.ynjgy.dao.ActivityRegistrationDao;
import edu.ynjgy.dao.StudentDao;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.ActivityRegistration;
import edu.ynjgy.model.Student;
import edu.ynjgy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ActivityRegistrationDao activityRegistrationDao;
    @Autowired
    private ActivityDao activityDao;

    //  获取所有学生
    @Override
    public List<Student> getAllStudent() {
        return studentDao.findAll();
    }

    //  根据id获取学生
    @Override
    public Student getStudentById(Integer id) {
        return studentDao.findById(id);
    }

    //  登录
    @Override
    public Student login(String username, String password) {
        Student student = studentDao.findByname(username);
        if(student != null && student.getPassword().equals(password)){
            return student;
        }
        return null;
    }

    //  注册
    @Override
    public boolean register(Student student) {
        Student student1 = studentDao.findByname(student.getUsername());
        if(student1 != null){
            return false;
        }else{
            return studentDao.add(student) > 0;
        }
    }

    //  更新
    @Override
    public boolean update(Student student) {
        Student student2 = studentDao.findByname(student.getUsername());
        if(student2 != null && student2.getId().equals(student.getId())){
            return false;
        }
        return studentDao.update(student) > 0;
    }

    //  删除
    @Override
    public Integer delete(Integer id) {
        //
        return 0;
    }

    //  报名活动
    @Override
    public boolean joinActivity(Integer studentId, Integer activityId) {
        // 检查学生是否已经报名该活动
        if (activityRegistrationDao.isRegisteredActivityRegistration(activityId, studentId)) {
            return false;
        }
        
        // 创建新的报名记录
        ActivityRegistration registration = new ActivityRegistration();
        registration.setActivity_id(activityId);
        registration.setStudent_id(studentId);
        // 设置报名时间为当前时间
        registration.setRegister_time((int) (System.currentTimeMillis() / 1000));
        
        // 保存报名记录到数据库
        int result = activityRegistrationDao.saveActivityRegistration(registration);
        
        // 返回操作结果
        return result > 0;
    }

    @Override
    public List<Activity> getStudentActivityRegistration(Integer studentId) {
        List<ActivityRegistration> registrations = activityRegistrationDao.findByStudentIdActivityRegistration(studentId);
        List<Activity> activities = new ArrayList<>();

        for (ActivityRegistration registration : registrations) {
            Activity activity = activityDao.findByIdActivity(registration.getActivity_id());
            if (activity != null) {
                activities.add(activity);
            }
        }

        return activities;
    }
}
