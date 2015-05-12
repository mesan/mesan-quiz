package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

import java.util.List;

public class ProgramDto extends SugarRecord<ProgramDto> {

    private List<EventDto> events;
    private int numberOfEvents;

    public ProgramDto() {
        super();
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public List<EventDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "ProgramDto [events=" + events + ", numberOfEvents=" + numberOfEvents + "]";
    }



}