package edu.ynjgy.dao;

import edu.ynjgy.model.Organizer;

import java.util.List;

public interface OrganizerDao {
    //  查询所有组织者
    List<Organizer> findAllOrganizer();
    //  查询指定id的组织者
    Organizer findByIdOrganizer(Integer id);
    //  查询指定username的组织者
    Organizer findByUsernameOrganizer(String username);
    //  添加组织者
    int addOrganizer(Organizer organizer);
    //  更新组织者信息
    Integer updateOrganizer(Organizer organizer);
    //  删除组织者
    Integer deleteOrganizer(Integer id);
    //  保存组织者信息
    Integer saveOrganizer(Organizer organizer);

}
