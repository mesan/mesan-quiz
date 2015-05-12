package no.mesan.mobil.mesanquiz.dao;

import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.mapper.GameMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

public interface GameDao {
    @SqlQuery("SELECT * FROM game g, person p WHERE g.id = :id AND g.creator = p.short_name")
    @RegisterMapper(GameMapper.class)
    Game getGame(@Bind("id") long id);

    @SqlQuery("INSERT INTO game (name, ")
    void insert(Game game);
}
