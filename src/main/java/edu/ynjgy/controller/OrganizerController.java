package edu.ynjgy.controller;

import edu.ynjgy.annotation.RequireLogin;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.ActivityRegistration;
import edu.ynjgy.model.Organizer;
import edu.ynjgy.service.ActivityRegistrationService;
import edu.ynjgy.service.ActivityService;
import edu.ynjgy.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRegistrationService registrationService;

    // 登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "organizer/login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Organizer organizer = organizerService.login(username, password);
        if (organizer != null) {
            session.setAttribute("organizer", organizer);
            return "redirect:/organizer/dashboard";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "organizer/login";
        }
    }

    // 组织者仪表盘
    @GetMapping("/dashboard")
    @RequireLogin
    public String dashboard(HttpSession session, Model model) {
        Organizer organizer = (Organizer) session.getAttribute("organizer");
        model.addAttribute("organizer", organizer);

        // 获取该组织者创建的所有活动
        List<Activity> activities = activityService.getActivitiesByOrganizerId(organizer.getId());
        model.addAttribute("activities", activities);

        return "organizer/dashboard";
    }

    // 创建活动页面
    @GetMapping("/activity/create")
    @RequireLogin
    public String createActivityPage(Model model) {
        model.addAttribute("activity", new Activity());
        return "organizer/create_activity";
    }

    // 处理创建活动请求
    @PostMapping("/activity/create")
    @RequireLogin
    public String createActivity(Activity activity, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("organizer");
        activity.setOrganizer_id(String.valueOf(organizer.getId()));
        activityService.createActivity(activity);
        return "redirect:/organizer/dashboard";
    }

    // 编辑活动页面
    @GetMapping("/activity/edit/{id}")
    @RequireLogin
    public String editActivityPage(@PathVariable("id") Integer activityId, Model model, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("organizer");
        Activity activity = activityService.getActivityById(activityId);

        // 检查活动是否属于当前组织者
        if (activity != null && activity.getOrganizer_id().equals(String.valueOf(organizer.getId()))) {
            model.addAttribute("activity", activity);
            return "organizer/activity-form";
        } else {
            return "redirect:/organizer/dashboard";
        }
    }

    // 处理编辑活动请求
    @PostMapping("/activity/edit/{id}")
    @RequireLogin
    public String updateActivity(@PathVariable("id") Integer activityId, Activity activity, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("organizer");
        Activity existingActivity = activityService.getActivityById(activityId);

        // 检查活动是否属于当前组织者
        if (existingActivity != null && existingActivity.getOrganizer_id().equals(String.valueOf(organizer.getId()))) {
            activity.setId(activityId);
            activity.setOrganizer_id(String.valueOf(organizer.getId()));
            activityService.updateActivity(activity);
        }

        return "redirect:/organizer/dashboard";
    }

    // 删除活动
    @PostMapping("/activity/delete/{id}")
    @RequireLogin
    @ResponseBody
    public String deleteActivity(@PathVariable("id") Integer activityId, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("organizer");
        Activity activity = activityService.getActivityById(activityId);

        // 检查活动是否属于当前组织者
        if (activity != null && activity.getOrganizer_id().equals(String.valueOf(organizer.getId()))) {
            boolean success = activityService.deleteActivity(activityId);
            return success ? "success" : "error";
        } else {
            return "unauthorized";
        }
    }

    // 查看活动报名情况
    @GetMapping("/activity/registrations/{id}")
    @RequireLogin
    public String viewRegistrations(@PathVariable("id") Integer activityId, Model model, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("organizer");
        Activity activity = activityService.getActivityById(activityId);

        // 检查活动是否属于当前组织者
        if (activity != null && activity.getOrganizer_id().equals(String.valueOf(organizer.getId()))) {
            List<ActivityRegistration> registrations = registrationService.getRegisteredStudents(activityId);
            model.addAttribute("activity", activity);
            model.addAttribute("registrations", registrations);
            return "organizer/registrations";
        } else {
            return "redirect:/organizer/dashboard";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/organizer/login";
    }
}