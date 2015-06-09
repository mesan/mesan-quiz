package no.mesan.mobil.mesanquiz.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface AlternativeDao {

    @SqlUpdate("INSERT INTO alternative (question_id, alternative, is_answer) " +
            "values (:question_id, :alternative, :is_answer)")
    @GetGeneratedKeys
    int insert(
            @Bind("question_id") long questionId,
            @Bind("alternative") String alternative,
            @Bind("is_answer") boolean isAnswer
    );

    @SqlUpdate("update alternative question_id = :question_id, alternative = :alternative, is_answer = :is_answer where id = :id")
    int update(
            @Bind("id") int id,
            @Bind("question_id") long questionId,
            @Bind("alternative") String alternative,
            @Bind("is_answer") boolean isAnswer
    );

}
