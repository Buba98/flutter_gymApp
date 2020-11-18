package com.buba.gymApp.backend.dao;

import com.buba.gymApp.backend.dao.interfaces.SetDAO;
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
import java.util.Objects;

@Repository("postgresSet")
public class SetDataAccessService implements SetDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SetDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert a set into DB
     * @param reps id of the reps
     * @param rest time of rest
     * @param eccentricDuration time of eccentric execution
     * @param concentricDuration time of concentric execution
     * @param setDuration total set duration
     * @return the new set if it has been created, false otherwise
     */
    @Override
    public Set insertSet(int[] reps, Duration rest, Duration eccentricDuration, Duration concentricDuration, Duration setDuration){
        String sql = "INSERT INTO set (rest, \"eccentricDuration\", \"concentricDuration\", \"setDuration\", reps) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                //TODO check if it works
                preparedStatement.setObject(1, new PGInterval(0, 0, 0, 0, 0, (double) rest.toSeconds()));
                preparedStatement.setObject(2, new PGInterval(0, 0, 0, 0, 0, (double) eccentricDuration.toSeconds()));
                preparedStatement.setObject(3, new PGInterval(0, 0, 0, 0, 0, (double) concentricDuration.toSeconds()));
                preparedStatement.setObject(4, new PGInterval(0, 0, 0, 0, 0, (double) setDuration.toSeconds()));
                preparedStatement.setArray(5, new PostgreSQLInt4Array(reps));
                return preparedStatement;
            }, keyHolder);
            return new Set(Objects.requireNonNull(keyHolder.getKey()).intValue(), reps, rest, eccentricDuration, concentricDuration, setDuration);
        } catch (DataAccessException | NullPointerException e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Select a set from DB by his id
     * @param id id
     * @return return a set if it is in the DB, false otherwise
     */
    @Override
    public Set selectSetById(int id){
        String sql = "SELECT * FROM set WHERE id = ?";

        try {
            return jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setInt(1, id), Set.mapper()).get(0);
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update the instance of the set
     * @param set the already modified set
     * @return the same set if it has been update successfully, false otherwise
     */
    @Override
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
