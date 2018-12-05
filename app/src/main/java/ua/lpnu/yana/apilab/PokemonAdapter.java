package ua.lpnu.yana.apilab;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder>{

    private ArrayList<Card> cards;
    private static final String DETAILS = "Details";
    private final Context context;

    public PokemonAdapter(Context context, ArrayList <Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    public void loadData(ArrayList <Card> photos) {
        int position = getItemCount();
        this.cards.addAll(photos);
        notifyItemRangeInserted(position, this.cards.size());
    }

    @Override
    public final PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info,
                parent, false);
        return new PokemonViewHolder(view);
    }

    public final void onBindViewHolder(@NonNull PokemonViewHolder holder, final int position) {
        //final ContactsContract.Contacts.Data card = cards.get(position).showData();
        Picasso.get().load(cards.get(position).getImageUrl()).into(holder.image);
        holder.title.setText(cards.get(position).getId());
        holder.user.setText(cards.get(position).getName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListDetailsFragment fragment = new ListDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(DETAILS, new Gson().toJson(cards.get(position)));
                fragment.setArguments(bundle);
                ((MainActivity) view.getContext()).setFragment(fragment, true);
            }
        });
    }

    public void clearData() {
        cards.clear();
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return cards.size();
    }

    public void setItems(ArrayList <Card> photos) {
        this.cards = photos;
    }
}


/*
public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private ArrayList<Card> cards;
    private static final String DETAILS = "details";

    public PokemonAdapter(ArrayList<Card> cards) {
        this.cards = cards;
        Log.i("TAG", "RSIZE:" + Integer.toString(this.cards.size()));
        for (Card card : this.cards) {
            Log.i("TAG", card.getId());
        }
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
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
*/