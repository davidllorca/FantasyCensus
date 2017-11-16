package me.test.davidllorca.fantasycensus;

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
    public static CitizensRepository provideMoviesRepository() {
        return CitizensRepository
                .getInstance(CitizensRemoteDataSource.getInstance());
    }

}
