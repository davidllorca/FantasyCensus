package me.test.davidllorca.fantasycensus.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.fantasycensus.R;
import me.test.davidllorca.fantasycensus.data.model.Citizen;
import me.test.davidllorca.fantasycensus.ui.viewmodel.CitizenViewModel;

/**
 * Adapter to bind {@link Citizen} items
 */
public class CitizenItemAdapter
        extends RecyclerView.Adapter<CitizenItemAdapter.ViewHolder> {

    private final Context mContext;
    private List<CitizenViewModel> mDataSet;
    private List<CitizenViewModel> mDataSetFiltered;
    private OnCitizenItemAdapterListener.OnClick mClickListener;
    private OnCitizenItemAdapterListener.OnSearch mSearchListener;

    public CitizenItemAdapter(Context context,
                              OnCitizenItemAdapterListener.OnClick clickListener) {
        mContext = context;
        mDataSet = new ArrayList<>();
        mDataSetFiltered = new ArrayList<>();
        mClickListener = clickListener;
    }

    public CitizenItemAdapter(Context context,
                              OnCitizenItemAdapterListener.OnClick clickListener,
                              OnCitizenItemAdapterListener.OnSearch searchListener) {
        this(context, clickListener);
        mSearchListener = searchListener;
    }

    @Override
    public CitizenItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.citizen_list_item_vertical, parent, false);
        return new CitizenItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CitizenItemAdapter.ViewHolder holder, int
            position) {
        CitizenViewModel item = mDataSetFiltered.get(position);
        holder.mName.setText(item.getName());
        Picasso.with(mContext)
                .load(item.getThumbnail())
                .error(android.R.drawable.picture_frame)// TODO set placeholder
                .into(holder.mPoster);

        holder.itemView.setOnClickListener(v -> onClickItem(item));
    }

    @Override
    public int getItemCount() {
        return mDataSetFiltered.size();
    }

    /**
     * Populate adapter data set.
     *
     * @param items data set.
     */
    public void loadData(List<CitizenViewModel> items) {
        mDataSet.clear();
        mDataSet.addAll(items);
        mDataSetFiltered.clear();
        mDataSetFiltered.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * Populate filtered data set.
     *
     * @param citizens filtered data set.
     */
    private void flushItems(List<CitizenViewModel> citizens) {
        mDataSetFiltered.clear();
        mDataSetFiltered.addAll(citizens);
        notifyDataSetChanged();
    }

    /**
     * Reset adapter with origin data set.
     */
    public void resetFilter() {
        flushItems(mDataSet);
        onResetSearch();
    }

    /**
     * Filter data set. If query is empty or null it reset state.
     *
     * @param query String to match.
     */
    public void filter(String query) {
        if (TextUtils.isEmpty(query)) {
            resetFilter();
        } else {
            findMatchesByName(query)
                    .delay(400, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(citizens -> {
                                flushItems(citizens);
                                notifyDataSetChanged();
                                onSearchResults(query, citizens.size() > 0);
                            },
                            throwable -> resetFilter());
        }
    }

    /**
     * Check matches in data set by name. No case sensitive.
     *
     * @param query String to match.
     * @return List Citizens.
     */
    public Single<List<CitizenViewModel>> findMatchesByName(String query) {
        return Observable.fromArray(mDataSet)
                .flatMapIterable(citizens -> citizens)
                .filter(citizen -> citizen.getName().toLowerCase().contains(query.toLowerCase
                        ()))
                .toList();
    }

    /**
     * Callback event {@link OnCitizenItemAdapterListener.OnClick}
     *
     * @param item
     */
    private void onClickItem(CitizenViewModel item) {
        mClickListener.onClickItem(item);
    }

    /**
     * Callback event {@link OnCitizenItemAdapterListener.OnSearch}. Called after filter operation.
     *
     * @param query   String to match.
     * @param matches true if it was any match, false otherwise.
     */
    private void onSearchResults(String query, boolean matches) {
        if (mSearchListener != null) mSearchListener.onSearchResults(query, matches);
    }

    /**
     * Callback event {@link OnCitizenItemAdapterListener.OnSearch}. Called when filter
     * operations are reset to previous state.
     */
    private void onResetSearch() {
        if (mSearchListener != null) mSearchListener.onResetSearch();
    }

    public interface OnCitizenItemAdapterListener {

        interface OnClick {
            /**
             * Called on click event on item collection.
             */
            void onClickItem(CitizenViewModel citizen);
        }

        interface OnSearch {
            /**
             * Called on any search on collection.
             */
            void onSearchResults(String query, boolean matches);

            /**
             * Called when there are no search matches.
             */
            void onResetSearch();
        }
    }

    /**
     * Model of ViewHolder.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_citizen_item_name)
        TextView mName;
        @BindView(R.id.img_citizen_item_thumbnail)
        ImageView mPoster;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}