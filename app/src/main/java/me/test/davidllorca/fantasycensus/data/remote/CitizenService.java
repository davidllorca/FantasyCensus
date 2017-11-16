package me.test.davidllorca.fantasycensus.data.remote;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Movie End-points.
 */
interface CitizenService {

    @GET("data.json")
    Single<CitizenListResponse> getCitizens();
}
