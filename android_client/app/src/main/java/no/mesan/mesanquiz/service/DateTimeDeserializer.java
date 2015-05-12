package no.mesan.mesanquiz.service;

import java.lang.reflect.Type;

import org.joda.time.DateTime;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Created by n06849 on 22.04.2015.
 */
public class DateTimeDeserializer implements JsonDeserializer<DateTime> {
    public DateTime deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
        long asLong = json.getAsJsonPrimitive().getAsLong()*1000L;
        DateTime dateTime = new DateTime(asLong);
        Log.i(this.getClass().getSimpleName(), "time: " + asLong + ", date: " + dateTime);
        return dateTime;
    }
}
