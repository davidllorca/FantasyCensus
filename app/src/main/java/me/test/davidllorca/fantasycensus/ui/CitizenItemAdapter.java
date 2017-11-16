package me.test.davidllorca.fantasycensus.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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

/*
TODO
 */
public class CitizenItemAdapter
        extends RecyclerView.Adapter<CitizenItemAdapter.ViewHolder> {

    public static final int HORIZONTAL_LINEAR_LAYOUT = 0;
    public static final int STAGGERED_LAYOUT = 1;
    private final Context mContext;
    private final OnCitizenItemAdapterListener mListener;
    private int mItemLayoutResource;
    private List<Citizen> mDataSet;
    private List<Citizen> mDataSetFiltered;

    public CitizenItemAdapter(Context context, int type,
                              OnCitizenItemAdapterListener listener) {
        mContext = context;
        mDataSet = new ArrayList<>();
        mDataSetFiltered = new ArrayList<>();
        mListener = listener;
        setItemLayout(type);
    }

    private void setItemLayout(int type) {
        switch (type) {
            case HORIZONTAL_LINEAR_LAYOUT:
                mItemLayoutResource = R.layout.citizen_list_item_horizontal;
                break;
            default:
                mItemLayoutResource = R.layout.citizen_list_item_vertical;
                break;
        }
    }

    @Override
    public CitizenItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(mItemLayoutResource, parent, false);
        return new CitizenItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CitizenItemAdapter.ViewHolder holder, int
            position) {
        Citizen item = mDataSetFiltered.get(position);
        holder.mName.setText(item.getName());
        Picasso.with(mContext)
                .load(item.getThumbnail())
                .error(android.R.drawable.picture_frame)// TODO set placeholder
                .into(holder.mPoster);

        holder.itemView.setOnClickListener(v -> mListener.onClickItem(item));
    }

    @Override
    public int getItemCount() {
        return mDataSetFiltered.size();
    }

    public void loadData(List<Citizen> items) {
        mDataSet.clear();
        mDataSet.addAll(items);
        mDataSetFiltered.clear();
        mDataSetFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        if (TextUtils.isEmpty(query)) {
            resetFilter();
        } else {
            findMatches(query)
                    .delay(400, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(citizens -> {
                                mDataSetFiltered.clear();
                                mDataSetFiltered.addAll(citizens);
                                notifyDataSetChanged();
                                mListener.onSearchResults(query, citizens.size() > 0);
                                Log.d("Search", "filter() returned: " + citizens.size());
                            },
                            throwable -> resetFilter());
        }
    }

    public Single<List<Citizen>> findMatches(String query) {
        return Observable.fromArray(mDataSet)
                .flatMapIterable(citizens -> citizens)
                .filter(citizen -> citizen.getName().toLowerCase().contains(query.toLowerCase
                        ()))
                .toList();
    }

    public void resetFilter() {
        Log.d("Search", "resetFilter() called");
        mDataSetFiltered.clear();
        mDataSetFiltered.addAll(mDataSet);
        mListener.onResetSearch();
        notifyDataSetChanged();
    }

    public interface OnCitizenItemAdapterListener {

        /**
         * Called on click event on item collection.
         */
        void onClickItem(Citizen item);

        /**
         * Called on any search on collection.
         */
        void onSearchResults(String query, boolean matches);

        /**
         * Called when there are no search matches.
         */
        void onResetSearch();
    }

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