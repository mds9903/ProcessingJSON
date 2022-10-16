package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        serializeCarObj(new Car("red", "suv"));
        String filepath = "target/car.json";
//        System.out.println(readFile(filepath));
        Car car = deserializeFile(filepath);
        System.out.println(car);

    }

    private static Car deserializeFile(String filepath) {
        // deserialize a json file into a java object
        String json = readFile(filepath);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Car car = objectMapper.readValue(json, Car.class);
            return car;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFile(String filepath) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            return br.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void serializeCarObj(Car car) {
        // serializing a Java object into JSON using the writeValue method of the ObjectMapper class:
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("target/car.json"), car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}