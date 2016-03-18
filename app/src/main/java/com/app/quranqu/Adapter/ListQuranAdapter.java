package com.app.quranqu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.quranqu.Model.DataHolder;
import com.app.quranqu.R;

import java.util.List;

public class ListQuranAdapter extends RecyclerView.Adapter<ListQuranAdapter.ListOrderViewHolder>{

    private Context appContext;
    private List<DataHolder> mListOrder;

    public ListQuranAdapter(List<DataHolder> mListOrder, Context c){
        this.mListOrder = mListOrder;
        this.appContext = c;
    }

    @Override
    public int getItemCount() {
        return mListOrder.size();
    }

    @Override
    public void onBindViewHolder(ListOrderViewHolder holder, int position) {
        final DataHolder dh = mListOrder.get(position);
        holder.mtxtNoSurah.setText(dh.data1);
        holder.mtxtNameSurah.setText(dh.data2);
    }

    @Override
    public ListOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_quran_layout, parent, false);
        return new ListOrderViewHolder(mItemView);
    }

    public static class ListOrderViewHolder extends RecyclerView.ViewHolder{
        protected LinearLayout mLytRelative;
        protected TextView mtxtNoSurah;
        protected TextView mtxtNameSurah;
        public ListOrderViewHolder(View v) {
            super(v);
            mLytRelative        = (LinearLayout)v.findViewById(R.id.lytListQuran);
            mtxtNoSurah         = (TextView)v.findViewById(R.id.txtNoSurah);
            mtxtNameSurah       = (TextView)v.findViewById(R.id.txtNameSurah);
        }
    }
}
