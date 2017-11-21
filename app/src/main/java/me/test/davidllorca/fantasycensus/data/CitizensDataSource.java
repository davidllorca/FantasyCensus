package me.test.davidllorca.fantasycensus.data;

import java.util.List;

import io.reactivex.Single;
import me.test.davidllorca.fantasycensus.data.model.Citizen;

/**
 * Contract between data sources and {@link CitizensRepository}.
 */
public interface CitizensDataSource {

    Single<List<Citizen>> getCitizens();

}
