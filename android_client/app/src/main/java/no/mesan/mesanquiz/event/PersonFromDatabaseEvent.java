package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.PersonDto;

public class PersonFromDatabaseEvent extends AbstractEvent{
    private PersonDto PersonDto;

    public PersonFromDatabaseEvent(PersonDto PersonDto) {
        this.PersonDto = PersonDto;
    }

    public PersonDto getPersonDto() {
        return PersonDto;
    }
}
