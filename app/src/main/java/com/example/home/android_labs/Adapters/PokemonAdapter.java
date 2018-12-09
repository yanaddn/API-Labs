package com.example.home.android_labs.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.home.android_labs.ApplicationEx;
import com.example.home.android_labs.Entity.Card;
import com.example.home.android_labs.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>  {

    private List<Card> cards;
    private Context mContext;

    public PokemonAdapter(Context context, List<Card> cards) {
        mContext = context;
        this.cards = cards;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.listview_layout, parent, false);
        return new ViewHolder(view);
    }

    public void clear() {
        cards.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Card> cards) {
        int pos = getItemCount();
        this.cards.addAll(cards);
        notifyItemRangeInserted(pos, this.cards.size());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.userName.setText(cards.get(position).getName());
        Picasso.get().load(cards.get(position).getImageUrl()).into(holder.imageView);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationEx mApplication = (ApplicationEx) mContext.getApplicationContext();
                mApplication.getFragmentHandler().goToDetails(cards.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.user)
        TextView userName;
        @BindView(R.id.parent_layout)
        LinearLayout parentLayout;

        ViewHolder(View convertView) {
            super(convertView);
            ButterKnife.bind(this, convertView);
        }
    }
}
