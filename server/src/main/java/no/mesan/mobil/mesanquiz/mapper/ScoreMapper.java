package no.mesan.mobil.mesanquiz.mapper;

import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.domain.Person;
import no.mesan.mobil.mesanquiz.domain.Score;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreMapper implements ResultSetMapper<Score> {

    public Score map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Person creator = new Person(resultSet.getString("creator_full_name"), resultSet.getString("creator"));
        Person player = new Person(resultSet.getString("player_full_name"), resultSet.getString("player"));
        Game game = new Game(resultSet.getLong("game"), resultSet.getString("name"), creator,
                resultSet.getString("topic"), resultSet.getInt("time_limit"));
        return new Score(resultSet.getLong("id"), game, player, resultSet.getInt("correct_answers"),
                resultSet.getInt("question_count"), resultSet.getInt("time_used"));
    }
}
