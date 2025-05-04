package edu.ynjgy.controller;

import edu.ynjgy.annotation.RequireLogin;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.ActivityRegistration;
import edu.ynjgy.model.Student;
import edu.ynjgy.service.ActivityRegistrationService;
import edu.ynjgy.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRegistrationService registrationService;

    // 查看所有活动
    @GetMapping("/list")
    public String listActivities(Model model) {
        List<Activity> activities = activityService.getApprovedActivities();
        model.addAttribute("activities", activities);
        return "activity/list";
    }

    // 查看活动详情
    @GetMapping("/detail/{id}")
    public String activityDetail(@PathVariable("id") Integer activityId,
                                 Model model,
                                 HttpSession session) {
        Activity activity = activityService.getActivityById(activityId);
        model.addAttribute("activity", activity);

        // 检查当前学生是否已报名
        Student student = (Student) session.getAttribute("student");
        if (student != null) {
            boolean isRegistered = registrationService.isStudentRegistered(activityId, student.getId());
            model.addAttribute("isRegistered", isRegistered);
        }

        return "activity/detail";
    }

    // 报名活动
    @PostMapping("/register/{id}")
    @RequireLogin
    @ResponseBody
    public String registerActivity(@PathVariable("id") Integer activityId,
                                   HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        boolean success = registrationService.registerActivity(activityId, student.getId());
        return success ? "success" : "error";
    }

    // 取消报名
    @PostMapping("/cancel/{id}")
    @RequireLogin
    @ResponseBody
    public String cancelRegistration(@PathVariable("id") Integer activityId,
                                     HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        boolean success = registrationService.cancelRegistration(activityId, student.getId());
        return success ? "success" : "error";
    }

    // 查看我报名的活动
    @GetMapping("/myactivities")
    @RequireLogin
    public String myActivities(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        List<Integer> activityIds = registrationService.getStudentRegisteredActivityIds(student.getId());

        // 获取活动详情
        List<Activity> activities = activityService.getAllActivities();
        model.addAttribute("activities", activities);
        model.addAttribute("registeredIds", activityIds);

        return "activity/myactivities";
    }
}