package no.mesan.mobil.mesanquiz.dao;

import no.mesan.mobil.mesanquiz.domain.Score;
import no.mesan.mobil.mesanquiz.mapper.ScoreMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

public interface ScoreDao {

    @SqlQuery("SELECT s.*,g.name, g.creator, g.topic, g.time_limit,p.full_name AS player_full_name,c.full_name AS creator_full_name FROM score s, game g, person p, person c WHERE s.player = p.short_name AND s.game = g.id AND s.game = :game AND g.creator = c.short_name ORDER BY correct_answers DESC, time_used ASC LIMIT 10")
    @RegisterMapper(ScoreMapper.class)
    List<Score> getHighScores(@Bind("game") long game);

    @SqlUpdate("INSERT INTO score (game, player, correct_answers, question_count, time_used) values (:game, :player, :correct_answers, :question_count, :time_used)")
    void insert(@Bind("game") long id, @Bind("player") String short_name,
                @Bind("correct_answers") int correct_answers, @Bind("question_count") int question_count,
                @Bind("time_used") int time_used);

}
