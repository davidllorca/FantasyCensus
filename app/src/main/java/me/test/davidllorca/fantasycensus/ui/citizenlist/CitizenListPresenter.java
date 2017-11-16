package me.test.davidllorca.fantasycensus.ui.citizenlist;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.fantasycensus.data.CitizensRepository;

/**
 * Presenter's implementation of {@link CitizenListFragment}.
 */
public class CitizenListPresenter implements CitizenListContract.Presenter {

    private static final String LOG_TAG = CitizenListPresenter.class.getSimpleName();

    private CitizenListContract.View mView;

    private CitizensRepository mRepository;

    public CitizenListPresenter(CitizenListContract.View view, CitizensRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadCitizens() {
        mRepository.getCitizens()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(citizens -> mView.showCitizens(citizens),
                        throwable -> Log.e(LOG_TAG, throwable.getMessage()));
    }
}
