package no.mesan.mobil.mesanquiz.dao;

import no.mesan.mobil.mesanquiz.domain.Person;
import no.mesan.mobil.mesanquiz.mapper.PersonMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @author Mikkel Steine
 */
public interface PersonDao {

    @SqlQuery("SELECT * FROM person p")
    @RegisterMapper(PersonMapper.class)
    List<Person> getPeople();

    @SqlQuery("SELECT * FROM person p WHERE p.short_name = :short_name")
    @RegisterMapper(PersonMapper.class)
    Person getPerson(@Bind("short_name") String short_name);

}
