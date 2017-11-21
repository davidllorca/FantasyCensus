package me.test.davidllorca.fantasycensus.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model of class
 */
public class Citizen {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("thumbnail")
    String thumbnail;
    @SerializedName("age")
    int age;
    @SerializedName("weight")
    double weight;
    @SerializedName("height")
    double height;
    @SerializedName("hair_color")
    String hairColor;
    @SerializedName("professions")
    List<String> professions;
    @SerializedName("friends")
    List<String> friends;

    public Citizen(int id, String name, String thumbnail, int age, double weight, double height,
                   String hairColor, List<String> professions, List<String> friends) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
        this.professions = professions;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public List<String> getFriends() {
        return friends;
    }
}
