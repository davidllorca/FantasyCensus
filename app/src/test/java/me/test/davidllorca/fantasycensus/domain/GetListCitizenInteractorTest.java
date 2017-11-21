package me.test.davidllorca.fantasycensus.domain;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import me.test.davidllorca.fantasycensus.RxImmediateSchedulerRule;
import me.test.davidllorca.fantasycensus.data.CitizensRepository;
import me.test.davidllorca.fantasycensus.data.model.Citizen;
import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetListCitizenInteractorTest {
    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private static List<Citizen> RESPONSE;

    @Mock
    Citizen citizen;
    @Mock
    GetCitizensInteractor.Callback mCallback;
    @Mock
    CitizensRepository mRepository;
    @Mock
    private GetCitizensInteractor mGetCitizensInteractor;

    @Before
    public void setupCitizenListPresenter() {
        MockitoAnnotations.initMocks(this);

        mGetCitizensInteractor = new GetCitizensInteractorImpl(mRepository, mCallback);

        RESPONSE = new ArrayList<>();
        RESPONSE.add(citizen);
    }

    @Test
    public void executeGetCitizensInteractor() {
        when(mRepository.getCitizens()).thenReturn(Single.just(RESPONSE));

        mGetCitizensInteractor.execute();

        verify(mCallback).onSuccess(anyListOf(CitizenViewModel.class));
    }
}
