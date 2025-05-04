package edu.ynjgy.controller;

import edu.ynjgy.model.Activity;
import edu.ynjgy.model.Student;
import edu.ynjgy.service.ActivityRegistrationService;
import edu.ynjgy.service.ActivityService;
import edu.ynjgy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ActivityRegistrationService activityRegistrationService;
    @Autowired
    private ActivityService activityService;

    //登录
    @GetMapping("/login")
    public String LoginPage(){
        return "student/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model){
        Student student = studentService.login(username, password);
        if(student != null){
            session.setAttribute("student", student);
            return "redirect:index";
        }else{
            model.addAttribute("msg", "�q??��名或密码错误");
            return "student/login";
        }
    }

    //注册



    // 查看所有活�?
    @GetMapping("/activities")
    public String viewActivities(Model model) {
        List<Activity> activities = activityService.getApprovedActivities();
        model.addAttribute("activities", activities);
        return "student/activities";
    }

    // 查看活动详情
    @GetMapping("/activity/{id}")
    public String viewActivityDetail(@PathVariable("id") Integer activityId, Model model, HttpSession session) {
        Activity activity = activityService.getActivityById(activityId);
        model.addAttribute("activity", activity);

        // 检查学生是否已报名
        Student student = (Student) session.getAttribute("student");
        if (student != null) {
            boolean isRegistered = activityRegistrationService.isStudentRegistered(activityId, student.getId());
            model.addAttribute("isRegistered", isRegistered);
        }

        return "student/activitydetail";
    }

    // 报名活动
    @PostMapping("/activity/join/{id}")
    @ResponseBody
    public String joinActivity(@PathVariable("id") Integer activityId, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "login_required";
        }

        boolean success = studentService.joinActivity(student.getId(), activityId);
        return success ? "success" : "error";
    }

    // 取消报名
    @PostMapping("/activity/cancel/{id}")
    @ResponseBody
    public String cancelRegistration(@PathVariable("id") Integer activityId, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "login_required";
        }

        boolean success = activityRegistrationService.cancelRegistration(activityId, student.getId());
        return success ? "success" : "error";
    }

    // 查看我的活动
    @GetMapping("/my-activities")
    public String myActivities(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/student/login";
        }

        List<Activity> activities = studentService.getStudentActivityRegistration(student.getId());
        model.addAttribute("activities", activities);
        return "student/my-activities";
    }
    //更新信息
    @GetMapping("/update")
    public String updatePage(){
        return "student/update";
    }
    @PostMapping("/update")
    public String update(Student student, HttpSession session, Model model){
        Student student1 = (Student) session.getAttribute("student");
        if(studentService.update(student)){
            session.setAttribute("student", student);
            return "redirect:index";
        }else{
            model.addAttribute("msg", "更新失败");
            return "student/update";
        }
    }
}
