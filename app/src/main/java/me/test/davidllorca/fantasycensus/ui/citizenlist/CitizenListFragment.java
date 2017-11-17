package me.test.davidllorca.fantasycensus.ui.citizenlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.test.davidllorca.fantasycensus.Injection;
import me.test.davidllorca.fantasycensus.R;
import me.test.davidllorca.fantasycensus.data.model.Citizen;
import me.test.davidllorca.fantasycensus.ui.CitizenItemAdapter;

public class CitizenListFragment extends Fragment implements CitizenListContract.View,
        CitizenItemAdapter.OnCitizenItemAdapterListener.OnClick, CitizenItemAdapter
                .OnCitizenItemAdapterListener.OnSearch {

    /* VIEWS */
    @BindView(R.id.rv_fragment_citizen_list)
    RecyclerView mList;
    @BindView(R.id.tv_fragment_citizen_list_empty_results)
    TextView mEmptyMsgTextView;

    /**
     * Implementation of {@link CitizenListContract.Presenter}
     */
    private CitizenListContract.Presenter mPresenter;

    /**
     * Adapter of RecyclerView.
     */
    private CitizenItemAdapter mAdapter;

    /**
     * Callback to fragment host.
     */
    private OnCitizenListFragmentListener mHostListener;

    public CitizenListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Init presenter
        mPresenter = new CitizenListPresenter(this, Injection.provideCitizenRepository());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citizen_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(mList, getResources().getInteger(R.integer
                .citizen_list_column_count_default));
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, @Nullable
            int nColumns) {
        RecyclerView.LayoutManager layoutManager =
                new StaggeredGridLayoutManager(nColumns, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CitizenItemAdapter(getContext(), this, this);

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter == null || mAdapter.getItemCount() == 0) {
            mPresenter.loadCitizens();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.filter(newText);
                return true;
            }
        });
        searchView.setOnCloseListener(() -> {
            mAdapter.resetFilter();
            return true;
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showCitizens(List<Citizen> citizens) {
        mAdapter.loadData(citizens);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCitizenListFragmentListener) {
            mHostListener = (OnCitizenListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCitizenListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHostListener = null;
    }

    /**
     * Callback event {@link CitizenItemAdapter.OnCitizenItemAdapterListener.OnClick}
     */
    @Override
    public void onClickItem(Citizen citizen) {
        mHostListener.onCitizenClicked(citizen);
    }

    /**
     * Callback event {@link CitizenItemAdapter.OnCitizenItemAdapterListener.OnSearch}
     */
    @Override
    public void onSearchResults(String query, boolean matches) {
        setListVisibility(matches);
        setNoResultViewVisibility(!matches, query);
    }

    /**
     * Callback event {@link CitizenItemAdapter.OnCitizenItemAdapterListener.OnSearch}
     */
    @Override
    public void onResetSearch() {
        setListVisibility(true);
        setNoResultViewVisibility(false, null);
    }

    /**
     * Show/Hide view with results.
     */
    private void setListVisibility(boolean visibility) {
        mList.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    /**
     * Show/hide view with no results message.
     */
    private void setNoResultViewVisibility(boolean visibility, String query) {
        mEmptyMsgTextView.setVisibility(visibility ? View.VISIBLE : View.GONE);
        if (visibility) mEmptyMsgTextView.setText(getString(R.string.msg_no_match_any_citizen,
                query));
    }

    /**
     * Interface between {@link CitizenListFragment} and its host.
     */
    public interface OnCitizenListFragmentListener {

        void onCitizenClicked(Citizen citizen);
    }

}
