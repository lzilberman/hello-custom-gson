package com.gson.serde.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.gson.serde.collection.TopQueue;
import com.gson.serde.model.GradeStore;

public class TopQueueAdapter extends TypeAdapter<TopQueue<GradeStore>> {

   private Gson gson = new Gson();
   
	@Override
	public void write(JsonWriter writer, TopQueue<GradeStore> value) throws IOException {

		if (value == null) {
            writer.nullValue();
            return;
        }
        Iterator<GradeStore> iterator = value.iterator();
        List<GradeStore> list = new ArrayList<>();
        while (iterator.hasNext()) {
            GradeStore gradeStore = iterator.next();
            if (gradeStore != null) {
                list.add(gradeStore);
            }
        }
        writer.beginArray();
        for (GradeStore gradeStore : list) {
            writer.value(gson.toJson(gradeStore));
        }
        writer.endArray();		
	}
	
	@Override
	public TopQueue<GradeStore> read(JsonReader reader) throws IOException {
		List<GradeStore> list = new ArrayList<>();
		reader.beginArray();
        while (reader.hasNext()) {
            list.add(gson.fromJson(reader.nextString(), GradeStore.class));
        }		
		reader.endArray();
		
        Comparator<GradeStore> c = (c1, c2) -> (int)(c2.calcAvgGrade() - c1.calcAvgGrade()) ;
        TopQueue<GradeStore> topQueue = new TopQueue<>(c, 5);
		
        for (GradeStore gradeStore : list) {
            topQueue.add(gradeStore);
        }
		
		return topQueue;
	}

}
