package me.test.davidllorca.fantasycensus.data.remote;

import java.util.List;

import io.reactivex.Single;
import me.test.davidllorca.fantasycensus.data.CitizensDataSource;
import me.test.davidllorca.fantasycensus.data.model.Citizen;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 16/11/17.
 */

public class CitizensRemoteDataSource implements CitizensDataSource {

    //Singleton instantiation
    private static final Object LOCK = new Object();
    private static CitizensRemoteDataSource sInstance;

    CitizenService mService;

    private CitizensRemoteDataSource() {
        mService = RetrofitHelper.createRetrofitService(CitizenService.class);
    }

    /**
     * Return single instance of remote data client.
     *
     * @return {@link CitizensRemoteDataSource} instance.
     */
    public static synchronized CitizensRemoteDataSource getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new CitizensRemoteDataSource();
            }
        }
        return sInstance;
    }


    @Override
    public Single<List<Citizen>> getCitizens() {
        return mService.getCitizens().map(response -> response.getCitizens());
    }
}
