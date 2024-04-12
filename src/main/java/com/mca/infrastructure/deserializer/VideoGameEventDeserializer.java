package com.mca.infrastructure.deserializer;

import com.google.gson.Gson;
import com.mca.infrastructure.model.VideoGameEvent;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class VideoGameEventDeserializer implements Deserializer<VideoGameEvent> {

    private final Gson gson = new Gson();

    @Override
    public VideoGameEvent deserialize(String topic, byte[] data) {
        try {
            String jsonString = new String(data, StandardCharsets.UTF_8);
            return gson.fromJson(jsonString, VideoGameEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}