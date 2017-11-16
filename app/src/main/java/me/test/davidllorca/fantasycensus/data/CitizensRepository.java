package me.test.davidllorca.fantasycensus.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Single;
import me.test.davidllorca.fantasycensus.data.model.Citizen;

/**
 * Repository implementation of data layer.
 * <p>
 * (NOTE: Just remote source covered)
 */
public class CitizensRepository {

    //Singleton instantiation
    private static final Object LOCK = new Object();
    private static CitizensRepository sInstance;

    private CitizensDataSource mRemoteDataSource;

    private CitizensRepository(@Nullable CitizensDataSource localDataSource, @NonNull
            CitizensDataSource remoteDataSource) {
        //NOTE: local data feature is not implemented in this project.
        mRemoteDataSource = remoteDataSource;
    }

    /**
     * Return single instance of repository.
     *
     * @param remoteDataSource remote data source.
     * @return {@link CitizensRepository} instance.
     */
    public static synchronized CitizensRepository getInstance(CitizensDataSource remoteDataSource) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new CitizensRepository(null, remoteDataSource);
            }
        }
        return sInstance;
    }

    public Single<List<Citizen>> getCitizens() {
        return mRemoteDataSource.getCitizens();
    }

}
