package com.nordberg.android.happytoday;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marcus on 2015-05-29.
 */
public class HappyAdapter extends RecyclerView.Adapter<HappyAdapter.ViewHolder> {
    private static final String LOG_TAG = HappyAdapter.class.getSimpleName();
    private ArrayList<HappyMoment> mDataset;

    public HappyAdapter(ArrayList<HappyMoment> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public HappyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.happy_card, parent, false);

        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TextView descTv = (TextView) viewHolder.mCardView.findViewById(R.id.my_textview_id);
        TextView dateTv = (TextView) viewHolder.mCardView.findViewById(R.id.date_id);

        dateTv.setText(mDataset.get(i).getDateString());
        descTv.setText(mDataset.get(i).getDesc());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }
}
