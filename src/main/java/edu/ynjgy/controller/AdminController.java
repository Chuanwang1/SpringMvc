package edu.ynjgy.controller;

import edu.ynjgy.annotation.RequireLogin;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.Admin;
import edu.ynjgy.model.Student;
import edu.ynjgy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Admin admin = adminService.login(username, password);
        if (admin != null) {
            session.setAttribute("admin", admin);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "admin/login";
        }
    }

    // 管理员仪表盘
    @GetMapping("/ybp")
    @RequireLogin
    public String dashboard(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);

        List<Activity> pendingActivities = adminService.getPendingActivities();
        model.addAttribute("pendingActivities", pendingActivities);

        return "admin/ybp";
    }

    // 审核活动
    @PostMapping("/activity/approve/{id}")
    @RequireLogin
    @ResponseBody
    public String approveActivity(@PathVariable("id") Integer activityId) {
        boolean success = adminService.approveActivity(activityId);
        return success ? "success" : "error";
    }

    // 拒绝活动
    @PostMapping("/activity/reject/{id}")
    @RequireLogin
    @ResponseBody
    public String rejectActivity(@PathVariable("id") Integer activityId) {
        boolean success = adminService.rejectActivity(activityId);
        return success ? "success" : "error";
    }

    // 查看所有学生
    @GetMapping("/students")
    @RequireLogin
    public String viewStudents(Model model) {
        List<Student> students = adminService.getAllStudents();
        model.addAttribute("students", students);
        return "admin/students";
    }

    // 删除学生
    @PostMapping("/student/delete/{id}")
    @RequireLogin
    @ResponseBody
    public String deleteStudent(@PathVariable("id") Integer studentId) {
        boolean success = adminService.deleteStudent(studentId);
        return success ? "success" : "error";
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
