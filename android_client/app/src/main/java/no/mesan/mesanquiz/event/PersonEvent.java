package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.PersonDto;

public class PersonEvent extends AbstractEvent{
    private PersonDto PersonDto;

    public PersonEvent(PersonDto PersonDto) {
        this.PersonDto = PersonDto;
    }

    public PersonDto getPersonDto() {
        return PersonDto;
    }
}
