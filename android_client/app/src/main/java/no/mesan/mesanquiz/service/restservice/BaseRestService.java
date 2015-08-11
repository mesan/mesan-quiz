package no.mesan.mesanquiz.service.restservice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class BaseRestService {
    public static PersonRestService getPeopleService() {
        return getRestAdapter().create(PersonRestService.class);
    }

    public static GameRestService getGameRestService() {
        return getRestAdapter().create(GameRestService.class);
    }

    public static ScoreRestService getScoreRestService() {
        return getRestAdapter().create(ScoreRestService.class);
    }

    private static RestAdapter getRestAdapter() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                .create();

        return new RestAdapter.Builder()
                .setEndpoint("http://warm-earth-6928.herokuapp.com")
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
