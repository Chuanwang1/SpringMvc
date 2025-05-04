package edu.ynjgy.service;

import edu.ynjgy.model.Organizer;

import java.util.List;

public interface OrganizerService {
    // 获取所有社团负责人
    List<Organizer> getAllOrganizers();

    // 根据ID获取社团负责人
    Organizer getOrganizerById(Integer id);

    // 社团负责人登录
    Organizer login(String username, String password);

    // 社团负责人注册
    boolean register(Organizer organizer);

    // 更新社团负责人信息
    boolean updateOrganizer(Organizer organizer);

    // 删除社团负责人
    boolean deleteOrganizer(Integer id);
}
