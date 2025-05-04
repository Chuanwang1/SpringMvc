package edu.ynjgy.impl.daoimpl;

import edu.ynjgy.dao.ActivityRegistrationDao;
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
public class ActivityRegistrationDaoImpl implements ActivityRegistrationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<ActivityRegistration> ActivetyRegistrationMapper = new RowMapper<ActivityRegistration>() {
        @Override
        public ActivityRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActivityRegistration registration = new ActivityRegistration();
            registration.setId(rs.getInt("id"));
            registration.setActivity_id(rs.getInt("activity_id"));
            registration.setStudent_id(rs.getInt("student_id"));
            registration.setRegister_time(rs.getInt("register_time"));
            return registration;
        }
    };

    //查找所有活动报�?
    @Override
    public List<ActivityRegistration> findAllActivityRegistration() {
        String ActivityRegistrationSql = "select * from activity_registration";
        return jdbcTemplate.query(ActivityRegistrationSql, ActivetyRegistrationMapper);
    }

    //根据活动id查找活动报名
    @Override
    public List<ActivityRegistration> findByActivityIdActivityRegistration(Integer activityId) {
        String ActivityRegistrationSql = "select * from activity_registration where activity_id = ?";
        return jdbcTemplate.query(ActivityRegistrationSql, ActivetyRegistrationMapper, activityId);
    }

    //根据学生id查找活动报名
    @Override
    public List<ActivityRegistration> findByStudentIdActivityRegistration(Integer studentId) {
        String ActivityRegistrationSql = "select * from activity_registration where student_id = ?";
        return jdbcTemplate.query(ActivityRegistrationSql, ActivetyRegistrationMapper, studentId);
    }

    //判断学生是否已经报名某活�??
    @Override
    public boolean isRegisteredActivityRegistration(Integer activityId, Integer studentId) {
        String ActivityRegistrationSql = "select * from activity_registration where activity_id = ? and student_id = ?";
        List<ActivityRegistration> list = jdbcTemplate.query(ActivityRegistrationSql,
                ActivetyRegistrationMapper,
                activityId,
                studentId);
        return list != null && !list.isEmpty();
    }

    //保存活动报名
    @Override
    public int saveActivityRegistration(ActivityRegistration registration) {
        String sql = "INSERT INTO activity_registration(activity_id, student_id, register_time) VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, 
                registration.getActivity_id(), 
                registration.getStudent_id(), 
                registration.getRegister_time());
    }

    //删除活动报名
    @Override
    public int deleteActivityRegistration(Integer id) {
        String ActivityRegistrationSql = "delete from activity_registration where id = ?";
        return jdbcTemplate.update(ActivityRegistrationSql, id);
    }

    //根据活动id删除活动报名
    @Override
    public int deleteByActivityIdActivityRegistration(Integer activityId) {
        String sql = "DELETE FROM activity_registration WHERE activity_id = ?";
        return jdbcTemplate.update(sql, activityId);
    }

    //根据学生id删除活动报名
    @Override
    public int deleteByStudentIdActivityRegistration(Integer studentId) {
        String sql = "DELETE FROM activity_registration WHERE student_id = ?";
        return jdbcTemplate.update(sql, studentId);
    }
}
