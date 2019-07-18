package com.hzw.dataside.mongodb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzw.dataside.mongodb.model.Address;
import com.hzw.dataside.mongodb.model.Person;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoGridFSException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * @author hzw
 * @date 2019/7/18  6:28 PM
 * @Description:
 */
public class Pojo4MongoDBService {

  public static void main(String[] args) {

    CodecRegistry codecRegistry = CodecRegistries
        .fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(
            PojoCodecProvider.builder().automatic(true).build()));

    //创建链接的指定编解码器
    com.mongodb.client.MongoClient mongoClient = MongoClients
        .create(MongoClientSettings.builder().applyToServerSettings(builder -> builder.applyConnectionString(new ConnectionString("mongodb://127.0.0.1:27017")) ).codecRegistry(codecRegistry).build());

    MongoDatabase hzwdb = mongoClient.getDatabase("hzw");

    //使用database的时候也可以指定编解码器

//    hzwdb.withCodecRegistry(codecRegistry);

//    MongoCollection<Document> collection = hzwdb.getCollection("guding");
    //使用集合的时候也可以指定编解码器
//    collection.withCodecRegistry(codecRegistry);

    MongoCollection<Person> person = hzwdb.getCollection("person", Person.class);
//单条插入
//    Person person1 = new Person("Ada Byron", 20, new Address("St James Square", "London", "W1"));
//    person.insertOne(person1);

    //批量插入
//    Person[] personarr = {new Person("json", 10, new Address("hellp", "London", "W1"))
//        ,new Person("marry", 22, new Address("St James Square", "London", "W1"))
//    ,new Person("tom", 5, new Address("St James Square", "London", "W1"))};
//
//    person.insertMany(Arrays.asList(personarr));

    //查询collection

    FindIterable<Person> people = person.find();

    ObjectMapper objectMapper = new ObjectMapper();
    people.forEach((Block<? super Person>) person1 -> {
      try {
        System.out.println(objectMapper.writer().writeValueAsString(person1));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    MongoCollection<Document> person1 = hzwdb.getCollection("person");
//    ArrayList<String> strings = new ArrayList<>();

//    strings.stream().map()

    person1.find().map(dd -> {
      return dd.toJson();
    }).forEach((Block<? super String>) ss -> {
      System.out.println(ss);
    });

    mongoClient.close();


  }

}
