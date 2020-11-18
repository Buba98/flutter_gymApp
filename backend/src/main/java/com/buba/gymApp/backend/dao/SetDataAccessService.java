package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.model.treaningComponents.Set;
import com.buba.gymApp.backend.utils.PostgreSQLInt4Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.postgresql.util.PGInterval;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.Duration;

@Repository("postgresSet")
public class SetDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SetDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Set insertSet(int[] reps, Duration rest, Duration eccentricDuration, Duration concentricDuration, Duration setDuration){
        String sql = "INSERT INTO set (rest, \"eccentricDuration\", \"concentricDuration\", \"setDuration\", reps) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setObject(1, new PGInterval(0, 0, 0, 0, 0, (double) rest.toSeconds()));
                preparedStatement.setObject(2, new PGInterval(0, 0, 0, 0, 0, (double) eccentricDuration.toSeconds()));
                preparedStatement.setObject(3, new PGInterval(0, 0, 0, 0, 0, (double) concentricDuration.toSeconds()));
                preparedStatement.setObject(4, new PGInterval(0, 0, 0, 0, 0, (double) setDuration.toSeconds()));
                preparedStatement.setArray(5, new PostgreSQLInt4Array(reps));
                return preparedStatement;
            }, keyHolder);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }

        return new Set(keyHolder.getKey().intValue(), reps, rest, eccentricDuration, concentricDuration, setDuration);
    }

    public Set selectSetById(int id){
        String sql = "SELECT * FROM set WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), Set.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public Set updateSet(Set set){
        String sql = "UPDATE set SET rest = ?, \"eccentricDuration\" = ?, \"concentricDuration\" = ?, \"setDuration\" = ?, reps = ? WHERE id = ?";

        try {
            jdbcTemplate.update(sql, preparedStatement -> {
                preparedStatement.setObject(1, new PGInterval(0, 0, 0, 0, 0, (double) set.getRest().toSeconds()));
                preparedStatement.setObject(2, new PGInterval(0, 0, 0, 0, 0, (double) set.getEccentricDuration().toSeconds()));
                preparedStatement.setObject(3, new PGInterval(0, 0, 0, 0, 0, (double) set.getConcentricDuration().toSeconds()));
                preparedStatement.setObject(4, new PGInterval(0, 0, 0, 0, 0, (double) set.getSetDuration().toSeconds()));
                preparedStatement.setArray(5, new PostgreSQLInt4Array(set.getReps()));
            });
            return set;
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
