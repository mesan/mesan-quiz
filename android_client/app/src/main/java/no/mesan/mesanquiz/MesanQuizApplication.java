package no.mesan.mesanquiz;

import android.app.Application;

import com.orm.SugarApp;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by kajas on 11-Aug-15.
 */
public class MesanQuizApplication extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
