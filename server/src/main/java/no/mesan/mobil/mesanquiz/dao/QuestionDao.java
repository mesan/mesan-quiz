package no.mesan.mobil.mesanquiz.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface QuestionDao {

    @SqlUpdate("INSERT INTO question (game_id, question) values (:game_id, :question)")
    @GetGeneratedKeys
    int insert(
            @Bind("game_id") long gameId,
            @Bind("question") String creator
    );

    @SqlUpdate("update question game_id = :game_id, question = :question where id = :id")
    int update(
            @Bind("id") long id,
            @Bind("game_id") long gameId,
            @Bind("question") String creator
    );

}
