package me.test.davidllorca.fantasycensus.domain;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.fantasycensus.data.CitizensRepository;

public class GetCitizensInteractorImpl implements GetCitizensInteractor {


    private final Callback mListener;
    private CitizensRepository mRepository;

    public GetCitizensInteractorImpl(CitizensRepository repository, GetCitizensInteractor.Callback
            listener) {
        this.mListener = listener;
        this.mRepository = repository;
    }

    @Override
    public void execute() {
        mRepository.getCitizens()
                .map(DataMapper::convertCitizenToDomain)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mListener::onSuccess,
                        throwable -> mListener.onError(throwable.getMessage()));
    }
}
