package no.mesan.mesanquiz.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by n06849 on 22.04.2015.
 */
public class BaseRestService {
    public static ProgramService getProgramService() {
        return getRestAdapter().create(ProgramService.class);
    }

    private static RestAdapter getRestAdapter() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                .create();

        return new RestAdapter.Builder()
                .setEndpoint("http://faghelg.herokuapp.com")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i("KLOVN", msg);
                    }
                })
                .build();
    }
}
