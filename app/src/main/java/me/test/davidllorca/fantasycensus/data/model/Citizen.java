package me.test.davidllorca.fantasycensus.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model of class
 */
public class Citizen implements Parcelable {

    public static final Creator<Citizen> CREATOR = new Creator<Citizen>() {
        @Override
        public Citizen createFromParcel(Parcel in) {
            return new Citizen(in);
        }

        @Override
        public Citizen[] newArray(int size) {
            return new Citizen[size];
        }
    };
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

    protected Citizen(Parcel in) {
        id = in.readInt();
        name = in.readString();
        thumbnail = in.readString();
        age = in.readInt();
        weight = in.readDouble();
        height = in.readDouble();
        hairColor = in.readString();
        professions = in.createStringArrayList();
        friends = in.createStringArrayList();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(thumbnail);
        dest.writeInt(age);
        dest.writeDouble(weight);
        dest.writeDouble(height);
        dest.writeString(hairColor);
        dest.writeStringList(professions);
        dest.writeStringList(friends);
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", hairColor='" + hairColor + '\'' +
                ", professions=" + professions +
                ", friends=" + friends +
                '}';
    }
}
