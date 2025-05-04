package edu.ynjgy.impl.daoimpl;

import edu.ynjgy.dao.ActivityDao;
import edu.ynjgy.model.Activity;
import edu.ynjgy.model.ActivityRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class ActivityDaoImpl implements ActivityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Activity>  activeRowMapper= new RowMapper<Activity>(){
        @Override
        public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
            Activity activity = new Activity();
            activity.setId(rs.getInt("id"));
            activity.setTitle(rs.getString("title"));
            activity.setDescription(rs.getString("description"));
            activity.setOrganizer_id(rs.getString("organizer_id"));
            activity.setEvent_time(rs.getString("event_time"));
            activity.setLocation(rs.getString("location"));
           activity.setStatus(rs.getString("status"));
           return activity;
        }
    };

    //查询所有活动
    @Override
    public List<Activity> findAllActivity() {
        String ActivitySql = "select * from activity";
        return jdbcTemplate.query(ActivitySql,activeRowMapper);
    }

    //根据id查询活动
    @Override
    public Activity findByIdActivity(Integer id) {
        String ActivitySql = "select * from activity where id = ?";
        return (Activity) jdbcTemplate.query(ActivitySql,activeRowMapper,id);
    }

    //根据标题查询活动
    @Override
    public Activity findByTitleActivity(String title) {
        String ActivitySql = "select * from activity where title = ?";
        return (Activity) jdbcTemplate.query(ActivitySql,activeRowMapper,title);
    }

    //根据发起人id查询活动
    @Override
    public List<Activity> findByOrganizerId(Integer organizerId) {
        String ActivitySql = "select * from activity where organizer_id = ?";
        return jdbcTemplate.query(ActivitySql,activeRowMapper,organizerId);
    }

    //添加活动
    @Override
    public int addActivity(Activity activity) {
        String ActivitySql = "insert into activity(title, description, organizer_id, event_time, location, status) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(ActivitySql ,
                activity.getTitle() ,
                activity.getDescription() ,
                activity.getOrganizer_id() ,
                activity.getEvent_time() ,
                activity.getLocation() ,
                activity.getStatus());
    }

    //更新活动
    @Override
    public Integer updateActivity(Activity activity) {
        String ActivitySql = "update activity set title = ? , description = ? , organizer_id = ? , event_time = ? , location = ? , status = ? where id = ?";
        return jdbcTemplate.update(ActivitySql ,
                activity.getTitle() ,
                activity.getDescription() ,
                activity.getOrganizer_id() ,
                activity.getEvent_time() ,
                activity.getLocation() ,
                activity);
    }

    //删除活动
    @Override
    public Integer deleteActivity(Integer id) {
        String ActivitySql = "delete from activity where id = ?";
        return jdbcTemplate.update(ActivitySql , id);
    }

    //保存活动
    @Override
    public Integer saveActivity(Activity activity) {
        String ActivitySql = "insert into activity(title, description, organizer_id, event_time, location, status) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(ActivitySql ,activity);
    }

    //查询所有活动报名
    @Override
    public List<ActivityRegistration> findAllActivityRegistration() {
//        String  ActivityRegistrationSql = "select * from activity_registration";
        return null;
    }

    //根据活动id查询报名
    @Override
    public List<ActivityRegistration> findByActivityIdActivityRegistration(Integer activity_id) {
        return Collections.emptyList();
    }

    //查询活动状态
    @Override
    public List<Activity> findByStatus(String status) {
        String sql = "SELECT * FROM activity WHERE status = ?";
        return jdbcTemplate.query(sql, activeRowMapper, status);
    }
}
