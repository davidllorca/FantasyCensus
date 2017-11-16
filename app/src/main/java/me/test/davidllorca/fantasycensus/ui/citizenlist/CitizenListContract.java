package me.test.davidllorca.fantasycensus.ui.citizenlist;

import java.util.List;

import me.test.davidllorca.fantasycensus.data.model.Citizen;

/**
 * Contract between the View and the Presenter.
 */
public interface CitizenListContract {

    interface View {

        void showCitizens(List<Citizen> citizens);

    }

    interface Presenter {

        void loadCitizens();

    }
}
