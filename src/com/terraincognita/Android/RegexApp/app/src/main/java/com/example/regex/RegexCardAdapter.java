package com.example.regex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.regex.db.RegexObj;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
        View itemView = mInflater.inflate(R.layout.recyclerview_regex, parent, false);
        return new RegexViewHolder(itemView, mOnRegexListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RegexViewHolder holder, int position) {
        if (mRegexObjs != null) {
            RegexObj currentRegex = mRegexObjs.get(position);
            holder.RegexItemView.setText(currentRegex.getRegex());
        } else {
            holder.RegexItemView.setText("Create your first regex");
        }
    }

    List<RegexObj> setRegexObjs(List<RegexObj> RegexObjs){
        mRegexObjs = RegexObjs;
        notifyDataSetChanged();
        return RegexObjs;
    }

    @Override
    public int getItemCount() {
        if (mRegexObjs != null)
            return mRegexObjs.size();
        else return 0;
    }

    class RegexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            onRegexListener.onRegexClick(getAdapterPosition());
        }
    }

    public interface OnRegexListener{
        void onRegexClick(int position);
    }
}