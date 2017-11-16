package me.test.davidllorca.fantasycensus.data.remote;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.test.davidllorca.fantasycensus.data.model.Citizen;

/**
 * Response's model of movie list.
 */
class CitizenListResponse {

    @SerializedName("Brastlewark")
    private List<Citizen> citizens;

    List<Citizen> getCitizens() {
        return citizens;
    }

}
