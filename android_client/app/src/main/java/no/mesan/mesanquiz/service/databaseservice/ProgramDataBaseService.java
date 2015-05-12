package no.mesan.mesanquiz.service.databaseservice;

import android.content.Context;
import android.util.Log;

import java.util.List;

import no.mesan.mesanquiz.model.EventDto;
import no.mesan.mesanquiz.model.ProgramDto;


public class ProgramDataBaseService extends AbstractDatabaseService {


    public ProgramDataBaseService(Context context) {
        super(context);
    }

    public void saveProgram(final ProgramDto programToBeSaved) {
        programToBeSaved.save();
        for (EventDto events :programToBeSaved.getEvents()){
            Log.d("KLOVN", "events: " + events.getId());
        }

        EventDto.saveInTx(programToBeSaved.getEvents());
    }

    public ProgramDto getProgram() {
        List<ProgramDto> programList = ProgramDto.listAll(ProgramDto.class);
        List<EventDto> eventDtoList = EventDto.listAll(EventDto.class);

        ProgramDto programDto = programList != null && !programList.isEmpty() ? programList.get(0) : null;
        if(programDto!=null){
            programDto.setEvents(eventDtoList);
        }
        return programDto;
    }

}
