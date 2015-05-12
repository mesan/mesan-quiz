package no.mesan.mesanquiz.view.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

public class MenuItem implements Parcelable {

    private String name;
    private int icon;
    private Class<? extends Fragment> fragment;

    public MenuItem() {
    }

    public MenuItem(String name, int icon, Class fragment) {
        super();
        this.name = name;
        this.icon = icon;
        this.fragment = fragment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public Class<? extends Fragment> getFragment() {
        return fragment;
    }

    public void setFragment(Class<? extends Fragment> fragment) {
        this.fragment = fragment;
    }

    protected MenuItem(Parcel in) {
        name = in.readString();
        icon = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(icon);
    }

    public static final Parcelable.Creator<MenuItem> CREATOR = new Parcelable.Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };
}
