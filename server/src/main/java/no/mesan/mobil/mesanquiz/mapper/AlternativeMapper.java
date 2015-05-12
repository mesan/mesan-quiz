package no.mesan.mobil.mesanquiz.mapper;

import no.mesan.mobil.mesanquiz.domain.Alternative;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.domain.Person;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thomas on 12.05.2015.
 */
public class AlternativeMapper implements ResultSetMapper<Alternative> {

    public Alternative map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Alternative(resultSet.getLong("id"), resultSet.getLong("question_id"), resultSet.getString("alternative"), resultSet.getBoolean("is_answer"));
    }
}
