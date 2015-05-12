package no.mesan.mesanquiz.model;

import com.orm.SugarRecord;

public class InfoDto  extends SugarRecord<InfoDto> {
    private String imageUrl;
    private String locationName;
    private String locationDescription;
    private String hotelName;
    private String hotelDescription;
    private String todoList;
    private String address;
    private String directions;
    private String lat;
    private String lng;

    public InfoDto() {
        super();
    }

    public InfoDto(String imageUrl, String locationName, String locationDescription,
                   String hotelName, String hotelDescription, String todoList, String address,
                   String directions, String lat, String lng) {
        this.imageUrl = imageUrl;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.todoList = todoList;
        this.address = address;
        this.directions = directions;
        this.lat = lat;
        this.lng = lng;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getTodoList() {
        return todoList;
    }

    public void setTodoList(String todoList) {
        this.todoList = todoList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}