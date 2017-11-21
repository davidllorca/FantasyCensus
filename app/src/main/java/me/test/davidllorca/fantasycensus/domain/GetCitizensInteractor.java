package me.test.davidllorca.fantasycensus.domain;

import java.util.List;

import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;

public interface GetCitizensInteractor {

    void execute();

    interface Callback {

        void onSuccess(List<CitizenViewModel> citizens);

        void onError(String error);

    }
}
