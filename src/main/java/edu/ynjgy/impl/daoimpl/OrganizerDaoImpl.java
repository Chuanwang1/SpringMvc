package edu.ynjgy.impl.daoimpl;

import edu.ynjgy.dao.OrganizerDao;
import edu.ynjgy.model.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    @Autowired
    private JdbcTemplate  jdbcTemplate;

    private RowMapper<Organizer> rowMapper = new RowMapper<Organizer>() {
        @Override
        public Organizer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organizer  organizer = new Organizer();
            organizer.setId(rs.getInt("id"));
            organizer.setUsername(rs.getString("username"));
            organizer.setPassword(rs.getString("password"));
            organizer.setName(rs.getString("name"));
            organizer.setCub_name(rs.getString("cub_name"));
            return organizer;
        }
    };

    @Override
    public List<Organizer> findAllOrganizer() {
        String OrganizerSql = "select * from Organizer";
        return jdbcTemplate.query(OrganizerSql, rowMapper);
    }

    @Override
    public Organizer findByIdOrganizer(Integer id) {
        String OrganizerSql = "select * from Organizer where id = ?";
        return jdbcTemplate.queryForObject(OrganizerSql, rowMapper, id);
    }

    @Override
    public Organizer findByUsernameOrganizer(String username) {
        String OrganizerSql = "select * from Organizer where username = ?";
        return jdbcTemplate.queryForObject(OrganizerSql, rowMapper, username);
    }

    @Override
    public int addOrganizer(Organizer organizer) {
        String OrganizerSql = "insert into Organizer(username, password, name, cub_name) values(?,?,?,?)";
        return jdbcTemplate.update(OrganizerSql, organizer.getUsername(), organizer.getPassword(), organizer.getName(), organizer.getCub_name());
    }

    @Override
    public Integer updateOrganizer(Organizer organizer) {
        String OrganizerSql = "update Organizer set username = ?, password = ?, name = ?, cub_name = ? where id = ?";
        return jdbcTemplate.update(OrganizerSql, organizer.getUsername(), organizer.getPassword(), organizer.getName(), organizer.getCub_name(), organizer.getId());
    }

    @Override
    public Integer deleteOrganizer(Integer id) {
        String OrganizerSql = "delete from Organizer where id = ?";
        return jdbcTemplate.update(OrganizerSql, id);
    }

    @Override
    public Integer saveOrganizer(Organizer organizer) {
        String OrganizerSql = "insert into Organizer(username, password, name, cub_name) values(?,?,?,?)";
        return jdbcTemplate.update(OrganizerSql, organizer.getUsername(), organizer.getPassword(), organizer.getName(), organizer.getCub_name());
    }
}
