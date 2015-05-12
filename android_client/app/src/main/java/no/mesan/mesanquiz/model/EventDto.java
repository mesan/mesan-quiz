package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

public class EventDto extends SugarRecord<EventDto> {
    private long start;
    private long end;
    private String title;
    private String description;
    private String eventImageUrl;
    private String hostNames;
    private PersonDto responsible;

    // private List<Tag> tags;

    public EventDto() {
        super();
    }

    public EventDto(long start, long end, String title, String description, String eventImageUrl, String hostNames, PersonDto responsible) {
        super();
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.eventImageUrl = eventImageUrl;
        this.hostNames = hostNames;
        this.responsible = responsible;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostNames() {
        return hostNames;
    }

    public void setHostNames(String hostNames) {
        this.hostNames = hostNames;
    }

    public PersonDto getResponsible() {
        return responsible;
    }

    public void setResponsible(PersonDto responsible) {
        this.responsible = responsible;
    }

    public String getEventImageUrl() {
        return eventImageUrl;
    }

    public void setEventImageUrl(String eventImageUrl) {
        this.eventImageUrl = eventImageUrl;
    }

}