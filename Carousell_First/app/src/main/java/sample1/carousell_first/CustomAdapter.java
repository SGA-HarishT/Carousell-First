package sample1.carousell_first;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by harish on 24/4/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,voteValue;
        ImageView imageViewIcon;
        ImageButton plusButton,minusButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.voteValue = itemView.findViewById(R.id.vote_val);
         //   this.imageViewIcon = itemView.findViewById(R.id.imageView);
            this.plusButton = itemView.findViewById(R.id.plus_button);
            this.minusButton = itemView.findViewById(R.id.minus_button);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textVoteVal = holder.voteValue;
        ImageView imageView = holder.imageViewIcon;
        ImageButton plusButton = holder.plusButton;
        ImageButton minusButton = holder.minusButton;


        textViewName.setText(dataSet.get(listPosition).getName());
//        textVoteVal.setText(dataSet.get(listPosition).getValue());
        //imageView.setImageResource(dataSet.get(listPosition).getImage());
        plusButton.setImageResource(dataSet.get(listPosition).getplusImageButton());
        minusButton.setImageResource(dataSet.get(listPosition).getminusImageButton());

        plusButton.setOnClickListener(OneFragment.myOnClickListenerPlus);
        minusButton.setOnClickListener(OneFragment.myOnClickListenerMinus);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
