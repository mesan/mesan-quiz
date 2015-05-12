package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.ProgramDto;

/**
 * Created by n06849 on 22.04.2015.
 */
public class ProgramFromDatabaseEvent extends AbstractEvent{
    private ProgramDto programDto;

    public ProgramFromDatabaseEvent(ProgramDto programDto) {
        this.programDto = programDto;
    }

    public ProgramDto getProgramDto() {
        return programDto;
    }
}
