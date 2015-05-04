package no.mesan.mobil.mesanquiz.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import no.mesan.mobil.mesanquiz.MesanQuizApplication;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    private String fullName;
    private String shortName;
    private String profileImageUrl;

    public Person() {

    }

    public Person(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;

        if(shortName != null && !shortName.isEmpty()) {
            profileImageUrl = MesanQuizApplication.IMAGE_BASE_URL + shortName + MesanQuizApplication.IMAGE_URL_SUFFIX;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Person [fullName=" + fullName + ", shortName=" + shortName + "]";
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}

