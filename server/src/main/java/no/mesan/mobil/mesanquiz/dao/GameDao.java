package no.mesan.mobil.mesanquiz.dao;

import no.mesan.mobil.mesanquiz.domain.Alternative;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.domain.Question;
import no.mesan.mobil.mesanquiz.mapper.AlternativeMapper;
import no.mesan.mobil.mesanquiz.mapper.GameMapper;
import no.mesan.mobil.mesanquiz.mapper.QuestionMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

public interface GameDao {

    @SqlQuery("SELECT * FROM game g, person p WHERE g.creator = p.short_name")
    @RegisterMapper(GameMapper.class)
    List<Game> getGames();

    @SqlQuery("SELECT * FROM game g, person p WHERE g.id = :id AND g.creator = p.short_name")
    @RegisterMapper(GameMapper.class)
    Game getGame(@Bind("id") long id);

    @SqlQuery("SELECT * FROM game g, person p WHERE LOWER(g.topic) = LOWER(:topic) AND g.creator = p.short_name")
    @RegisterMapper(GameMapper.class)
    List<Game> getGamesForTopic(@Bind("topic") String topic);

    @SqlQuery("SELECT * FROM question WHERE game_id = :gameId")
    @RegisterMapper(QuestionMapper.class)
    List<Question> getQuestionsForGame(@Bind("gameId") long gameId);

    @SqlQuery("SELECT * FROM alternative WHERE question_id = :questionId")
    @RegisterMapper(AlternativeMapper.class)
    List<Alternative> getAlternativesForQuestion(@Bind("questionId") long questionId);

//    @SqlQuery("INSERT INTO game (name, ")
//    void insert(Game game);
}
