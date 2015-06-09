package no.mesan.mesanquiz.service.databaseservice;

import android.content.Context;

import java.util.List;

import no.mesan.mesanquiz.model.PersonDto;

public class PersonDataBaseService extends AbstractDatabaseService {


    public PersonDataBaseService(Context context) {
        super(context);
    }

    public void savePeople(final PersonDto personToBeSaved) {
        personToBeSaved.save();
    }

    public PersonDto getPeople() {
        List<PersonDto> personList = PersonDto.listAll(PersonDto.class);

        PersonDto PersonDto = personList != null && !personList.isEmpty() ? personList.get(0) : null;
        return PersonDto;
    }

}
