package no.mesan.mesanquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

public class PersonDto extends SugarRecord<PersonDto>  implements Parcelable {

private String fullName;
private String shortName;
private String profileImageUrl;

public String getFullName(){
        return fullName;
        }

public void setFullName(String fullName){
        this.fullName=fullName;
        }

public String getShortName(){
        return shortName;
        }

public void setShortName(String shortName){
        this.shortName=shortName;
        }

public String getProfileImageUrl(){
        return profileImageUrl;
        }

public void setProfileImageUrl(String profileImageUrl){
        this.profileImageUrl=profileImageUrl;
        }

protected PersonDto(Parcel in){
        fullName=in.readString();
        shortName=in.readString();
        profileImageUrl=in.readString();
        }

@Override
public int describeContents(){
        return 0;
        }

@Override
public void writeToParcel(Parcel dest,int flags){
        dest.writeString(fullName);
        dest.writeString(shortName);
        dest.writeString(profileImageUrl);
        }

public static final Parcelable.Creator<PersonDto>CREATOR=new Parcelable.Creator<PersonDto>(){
@Override
public PersonDto createFromParcel(Parcel in){
        return new PersonDto(in);
        }

@Override
public PersonDto[]newArray(int size){
        return new PersonDto[size];
        }
        };
        }