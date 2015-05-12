package no.mesan.mobil.mesanquiz.mapper;

import no.mesan.mobil.mesanquiz.domain.Alternative;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.domain.Person;
import no.mesan.mobil.mesanquiz.domain.Question;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameMapper implements ResultSetMapper<Game> {

    public Game map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Person creator = new Person(resultSet.getString("full_name"), resultSet.getString("creator"));
        return new Game(resultSet.getLong("id"), resultSet.getString("name"), creator, resultSet.getString("topic"), resultSet.getInt("time_limit"));
    }
}
