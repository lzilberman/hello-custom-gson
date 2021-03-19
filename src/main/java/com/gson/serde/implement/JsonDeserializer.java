package com.gson.serde.implement;

import java.lang.reflect.Type;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gson.serde.collection.TopQueue;
import com.gson.serde.model.GradeStore;


//public class JsonDeserializer<T> implements Deserializer<T> {
public class JsonDeserializer implements Deserializer<TopQueue<GradeStore>> {
	
    private Gson gson;
    private Class <?> deserializedClass;
    private Type reflectionTypeToken;
	
    public JsonDeserializer()  {
	    try {
			this.deserializedClass =  Class.forName("com.gson.serde.collection.TopQueue");
		} catch (ClassNotFoundException e) {
			System.out.println("############ "  + e.getMessage());
			e.printStackTrace();
		}
        init();

    }
    public JsonDeserializer(Type reflectionTypeToken) {
        this.reflectionTypeToken = reflectionTypeToken;
        init();
    }
//    public JsonDeserializer() { }
    
    private void init () {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(TopQueue.class, new TopQueueAdapter().nullSafe());
        gson = builder.create();
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        if(deserializedClass == null) {
            deserializedClass = (Class<?>) map.get("serializedClass");
        }
    }
    
	@Override
	public TopQueue<GradeStore> deserialize(String s, byte[] bytes) {
        if(bytes == null){
            return null;
        }
        Type deserializeFrom = deserializedClass != null ? deserializedClass : reflectionTypeToken;

        return gson.fromJson(new String(bytes), deserializeFrom);
	}

}
