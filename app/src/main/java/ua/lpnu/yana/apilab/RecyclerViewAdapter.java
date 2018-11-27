package ua.lpnu.yana.apilab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Card> cards;

    public RecyclerViewAdapter(ArrayList<Card> cards) {
        this.cards = cards;
        Log.i("TAG", "RSIZE:" + Integer.toString(this.cards.size()));
        for (Card card : this.cards) {
            Log.i("TAG", card.getId());
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.header.setText(cards.get(i).getName());
        viewHolder.description.setText(
                "id: " + cards.get(i).getId() + "\n"
                        + "name: " + cards.get(i).getName() + "\n"
                        + "number: " + cards.get(i).getNationalPokedexNumber());
        Picasso.get().load(cards.get(i).getImageUrl()).resize(300, 500).centerCrop().into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void clearData() {
        cards.clear();
        notifyDataSetChanged();
    }

   class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView header;
        TextView description;
        RelativeLayout recyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            header = itemView.findViewById(R.id.header);
            description = itemView.findViewById(R.id.description);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }
    }
}