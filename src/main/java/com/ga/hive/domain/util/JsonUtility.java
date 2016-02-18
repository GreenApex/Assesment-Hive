package com.ga.hive.domain.util;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ga.hive.common.ErrorCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonUtility {

    // This File returns JSON string excluding the fields with @Expose
    // Annotation

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtility.class);

    public static String getJson(ErrorCodes status, Object obj) {

        Response response = new Response();

        response.setStatus(status.getErrorCode());
        response.setMessage(status.getDescription());
        response.setData(obj);
        try {
            final GsonBuilder builder = new GsonBuilder();
            // builder.excludeFieldsWithoutExposeAnnotation();
            builder.serializeNulls();
            // builder.setDateFormat("dd-MM-yyyy");
            final Gson g = builder.create();
            return g.toJson(response);
        } catch (Exception e) {
            LOG.info(e.toString());
            return "ERROR";
        }
    }

    public static String getJson(Integer status, String message) {

        Response response = new Response();

        response.setStatus(status);
        response.setMessage(message);
        try {
            final GsonBuilder builder = new GsonBuilder();
            // builder.excludeFieldsWithoutExposeAnnotation();
            builder.serializeNulls();
            // builder.setDateFormat("dd-MM-yyyy");
            final Gson g = builder.create();
            return g.toJson(response);
        } catch (Exception e) {
            LOG.info(e.toString());
            return "ERROR";
        }
    }

    public static String getJsonWithDateFormat(Object obj, String dateFormat) {
        try {
            final GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            builder.setDateFormat(dateFormat);
            final Gson g = builder.create();
            return g.toJson(obj);
        } catch (Exception e) {
            LOG.info(e.toString());
            return "";
        }
    }

    public static Object getJsonOfType(String obj, Type type) {
        try {
            final GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                // Year in 4, month in 2, day in 2
                final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                        throws JsonParseException {
                    try {
                        return df.parse(json.getAsString());
                    } catch (final java.text.ParseException e) {
                        return null;
                    }
                }
            });
            // this code for get transion variable in json to ..
            builder.excludeFieldsWithModifiers(Modifier.STATIC);

            final Gson g = builder.create();
            return g.fromJson(obj, type);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info(e.toString());
            return "";
        }
    }

    public static String getJsonexcludeTransient(Object obj) {
        try {
            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info(e.toString());
            return "";
        }
    }

    public static void main(String[] args) {

        // transient are by default excluded
        Gson gson = new Gson();

    }
}
