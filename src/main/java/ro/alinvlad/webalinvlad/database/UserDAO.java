package ro.alinvlad.webalinvlad.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findByEmail(String email) {
        return jdbcTemplate.query("select * from users where email='" + email + "';", new UserRowMapper());
    }

    public void save(String email, String password) {
        jdbcTemplate.update("insert into users values(null, ?, ?)", email, password);
    }
}
