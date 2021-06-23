package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener onLongClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener onLongClickListener){
        this.items = items;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Use layer inflator to inflate the view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }

    // Responsible for binding data to a particular ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab item at position
        String item = items.get(position);
        // bind the item to the specified ViewHolder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to the views that represent each row of the list (each item)
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Update de view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    //notify mainActivity and send position of item
                    onLongClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
