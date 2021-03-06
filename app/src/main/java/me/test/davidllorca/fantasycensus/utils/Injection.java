package me.test.davidllorca.fantasycensus.utils;

import me.test.davidllorca.fantasycensus.data.CitizensRepository;
import me.test.davidllorca.fantasycensus.data.remote.CitizensRemoteDataSource;

/**
 * Dependency injector.
 */
public class Injection {

    /**
     * Provides implementation of {@link me.test.davidllorca.fantasycensus.data.CitizensRepository}.
     *
     * @return MoviesRepository
     */
    public static CitizensRepository provideCitizenRepository() {
        return CitizensRepository
                .getInstance(CitizensRemoteDataSource.getInstance());
    }

}
