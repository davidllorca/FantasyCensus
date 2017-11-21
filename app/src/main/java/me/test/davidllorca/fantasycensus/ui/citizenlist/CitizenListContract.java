package me.test.davidllorca.fantasycensus.ui.citizenlist;

import java.util.List;

import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;

/**
 * Contract between the View and the Presenter.
 */
public interface CitizenListContract {

    interface View {

        void showCitizens(List<CitizenViewModel> citizens);

        void setLoading(boolean isVisible);

        void showError(String error);
    }

    interface Presenter {

        void loadCitizens();

    }
}
