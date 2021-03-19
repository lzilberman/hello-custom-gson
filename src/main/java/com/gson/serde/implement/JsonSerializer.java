package com.gson.serde.implement;

import java.nio.charset.Charset;

import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gson.serde.collection.TopQueue;
import com.gson.serde.model.GradeStore;

//public class JsonSerializer<T> implements Serializer<T> {
public class JsonSerializer implements Serializer<TopQueue<GradeStore>> {
		
	
    private Gson gson;

    public JsonSerializer() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(TopQueue.class, new TopQueueAdapter().nullSafe());
        gson = builder.create();
    }

	@Override
	public byte[] serialize(String topic, TopQueue<GradeStore> t) {
		return gson.toJson(t).getBytes(Charset.forName("UTF-8"));
	}

}
