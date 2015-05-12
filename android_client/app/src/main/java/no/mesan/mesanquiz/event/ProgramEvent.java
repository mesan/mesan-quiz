package no.mesan.mesanquiz.event;

import no.mesan.mesanquiz.model.ProgramDto;

public class ProgramEvent extends AbstractEvent{
    private ProgramDto programDto;

    public ProgramEvent(ProgramDto programDto) {
        this.programDto = programDto;
    }

    public ProgramDto getProgramDto() {
        return programDto;
    }
}
