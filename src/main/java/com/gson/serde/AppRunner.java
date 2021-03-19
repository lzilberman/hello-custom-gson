package com.gson.serde;

import java.util.Comparator;
import org.springframework.boot.CommandLineRunner;

import com.gson.serde.collection.TopQueue;
import com.gson.serde.implement.JsonDeserializer;
import com.gson.serde.implement.JsonSerializer;
import com.gson.serde.model.GradeStore;
import com.gson.serde.model.Student;

public class AppRunner implements CommandLineRunner {

	@Override
	@SuppressWarnings("resource")
	public void run(String... args) throws Exception {
		
		// Creating a set object 
		Comparator<GradeStore> c = (c1, c2) -> (int)(c2.calcAvgGrade() - c1.calcAvgGrade()) ;
		TopQueue<GradeStore> topQueue = new TopQueue<>(c, 3);

		// Adding elements to this set 
		Student st_10 = new Student(10, "student_10", 10);
		GradeStore gradeStore_10 = GradeStore.newBuilder(st_10).build();
		topQueue.add(gradeStore_10);
		
		Student st_20 = new Student(20, "student_20", 20);
		GradeStore gradeStore_20 = GradeStore.newBuilder(st_20).build();
		topQueue.add(gradeStore_20);
		
		Student st_30 = new Student(30, "student_30", 30);
		GradeStore gradeStore_30 = GradeStore.newBuilder(st_30).build();
		topQueue.add(gradeStore_30);

		Student st_40 = new Student(40, "student_40", 40);
		GradeStore gradeStore_40 = GradeStore.newBuilder(st_40).build();
		topQueue.add(gradeStore_40);
		
		// Printing the content of the set 
		System.out.println("##### set size: " + topQueue.size());
		topQueue.printQueue(); 

		JsonSerializer serializer = new JsonSerializer();
		JsonDeserializer deserializer = new JsonDeserializer();
		
		byte[] bytes = serializer.serialize("topic", topQueue);
		TopQueue<GradeStore> topQueue2 = deserializer.deserialize("topic", bytes);

		System.out.println("%%%%% deserialized set size: " + topQueue2.size());
		topQueue2.printQueue(); 
		
	}

}
