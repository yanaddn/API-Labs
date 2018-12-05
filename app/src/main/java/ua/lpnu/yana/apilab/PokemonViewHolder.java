package ua.lpnu.yana.apilab;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tags)
    protected TextView title;
    @BindView(R.id.user)
    protected TextView user;
    @BindView(R.id.image)
    protected ImageView image;
    @BindView(R.id.parent_layout)
    LinearLayout parentLayout;

    PokemonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
