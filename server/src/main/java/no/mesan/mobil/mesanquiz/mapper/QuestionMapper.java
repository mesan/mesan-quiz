package no.mesan.mobil.mesanquiz.mapper;

import no.mesan.mobil.mesanquiz.domain.QuestionAbc;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thomas on 12.05.2015.
 */
public class QuestionMapper  implements ResultSetMapper<QuestionAbc> {

    public QuestionAbc map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new QuestionAbc(resultSet.getLong("id"), resultSet.getLong("game_id"), resultSet.getString("question"));
    }
}
