package com.uwutever.RegexApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.uwutever.RegexApp.db.RegexObj;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**
 * RegexCardAdapter: returns a view for each item in the list
 *
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
class RegexCardAdapter extends RecyclerView.Adapter<RegexCardAdapter.RegexViewHolder> {

    private final LayoutInflater mInflater;
    private List<RegexObj> mRegexObjs;
    private OnRegexListener mOnRegexListener;

    RegexCardAdapter(Context context, OnRegexListener onRegexListener) {
        mInflater = LayoutInflater.from(context);
        mOnRegexListener = onRegexListener;
    }

    @NonNull
    @Override
    public RegexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* 
        * Inflate the layout for each item in the list 
        * @param parent: the parent view
        * @param viewType: the view type
        * @return: the view holder
        */
        View itemView = mInflater.inflate(R.layout.recyclerview_regex, parent, false);
        return new RegexViewHolder(itemView, mOnRegexListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RegexViewHolder holder, int position) {
        /* 
        * Get the current RegexObj 
        * @param holder: the view holder
        * @param position: the position of the current item
        */
        if (mRegexObjs != null) {
            RegexObj currentRegex = mRegexObjs.get(position);
            holder.RegexItemView.setText(currentRegex.getRegex());
        } else {
            holder.RegexItemView.setText("Create your first regex");
        }
    }

    List<RegexObj> setRegexObjs(List<RegexObj> RegexObjs){
        /*
        * Set the list of RegexObjs
        * @param RegexObjs: the list of RegexObjs
        * @return: the list of RegexObjs 
        */
        mRegexObjs = RegexObjs;
        notifyDataSetChanged();
        return RegexObjs;
    }

    @Override
    public int getItemCount() {
        /*
        * Get the size of the list of RegexObjs
        * @return: the size of the list of RegexObjs 
        */
        if (mRegexObjs != null)
            return mRegexObjs.size();
        else return 0;
    }

    class RegexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /*
        * ViewHolder for each item in the list
        */
        private final TextView RegexItemView;
        OnRegexListener onRegexListener;

        private RegexViewHolder(View itemView, OnRegexListener onRegexListener) {
            super(itemView);
            RegexItemView = itemView.findViewById(R.id.RegexCard);
            this.onRegexListener = onRegexListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            /*
            * When an item is clicked, call the onRegexListener
            */
            onRegexListener.onRegexClick(getAdapterPosition());
        }
    }

    public interface OnRegexListener{
        void onRegexClick(int position);
    }
}