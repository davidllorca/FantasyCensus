package me.test.davidllorca.fantasycensus.ui;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import me.test.davidllorca.fantasycensus.RxImmediateSchedulerRule;
import me.test.davidllorca.fantasycensus.domain.GetCitizensInteractor;
import me.test.davidllorca.fantasycensus.domain.GetCitizensInteractorImpl;
import me.test.davidllorca.fantasycensus.ui.citizenlist.CitizenListContract;
import me.test.davidllorca.fantasycensus.ui.citizenlist.CitizenListPresenter;
import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link CitizenListPresenter}
 */
public class CitizenListPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private static List<CitizenViewModel> RESPONSE;

    @Mock
    CitizenViewModel citizen;
    @Mock
    GetCitizensInteractor.Callback mCallback;
    @Mock
    private GetCitizensInteractorImpl mGetCitizensInteractor;
    @Mock
    private CitizenListContract.View mCitizenListView;


    private CitizenListPresenter mCitizenListPresenter;

    @Before
    public void setupCitizenListPresenter() {
        MockitoAnnotations.initMocks(this);

        mCitizenListPresenter = new CitizenListPresenter(mCitizenListView);

        RESPONSE = new ArrayList<>();
        RESPONSE.add(citizen);
    }

    @Test
    public void loadCitizensFromRepositoryAndLoadIntoView() {
        mCitizenListPresenter.loadCitizens();

        verify(mCitizenListView).setLoading(true);
        verify(mCitizenListView).setLoading(false);
        verify(mCitizenListView).showCitizens(anyList());
    }

}
