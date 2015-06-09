package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.PersonDao;
import no.mesan.mobil.mesanquiz.domain.Person;

import java.util.List;

public class PersonService {

    private PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getPeople() {
        return personDao.getPeople();
    }

    public Person getPerson(String short_name) {
        return personDao.getPerson(short_name);
    }

}
