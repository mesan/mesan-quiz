package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.ProgramDto;

public class ProgramFromDatabaseEvent extends AbstractEvent{
    private ProgramDto programDto;

    public ProgramFromDatabaseEvent(ProgramDto programDto) {
        this.programDto = programDto;
    }

    public ProgramDto getProgramDto() {
        return programDto;
    }
}
