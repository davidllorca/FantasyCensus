package me.test.davidllorca.fantasycensus.ui.viewmodel;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * View model of class.
 */
public class CitizenViewModel implements Parcelable {

    public static final Creator<CitizenViewModel> CREATOR = new Creator<CitizenViewModel>() {
        @Override
        public CitizenViewModel createFromParcel(Parcel in) {
            return new CitizenViewModel(in);
        }

        @Override
        public CitizenViewModel[] newArray(int size) {
            return new CitizenViewModel[size];
        }
    };
    int id;
    String name;
    String thumbnail;
    int age;
    double weight;
    double height;
    String hairColor;
    List<String> professions;
    List<String> friends;

    public CitizenViewModel(int id, String name, String thumbnail, int age, double weight, double
            height, String hairColor, List<String> professions, List<String> friends) {
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

    protected CitizenViewModel(Parcel in) {
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
}
