package sample1.carousell_first;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * OneFragment to display data to be voted
 */

public class OneFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListenerPlus;
    static View.OnClickListener myOnClickListenerMinus;
    static int mCount = 0;
    static int Pos = 0;
    static int value = 0;
    static DataPassListener mCallback;
    static HashMap<Integer,Integer> mMapData = new HashMap<>();

    public interface DataPassListener {
        void passData(HashMap map);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (DataPassListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ " must implement OnClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);

        myOnClickListenerPlus = new MyOnClickListenerPlus();
        myOnClickListenerMinus = new MyOnClickListenerMinus();
        recyclerView = rootView.findViewById(R.id.rv_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < Mydata.nameArray.length; i++) {
            data.add(new DataModel(
                    Mydata.nameArray[i],
                    Mydata.plusButton,
                    Mydata.minusButton,
                    Mydata.val
            ));
        }

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private static class MyOnClickListenerPlus implements View.OnClickListener {

        @Override
        public void onClick(View v) {
           View rootview = (View) v.getParent().getParent().getParent().getParent().getParent();
            int selectedItemPosition = recyclerView.getChildPosition(rootview);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.vote_val);
            if(Pos != selectedItemPosition) {
                Pos = selectedItemPosition;
                mCount=0;
            }
            mCount = mCount-1;
            if(mCount <0){
                mCount =0;
            }
            mMapData.put(selectedItemPosition,mCount);
            String count = "" +mCount;
            textViewName.setText(count);
            sendData(mMapData);
            }
        }


    private static class MyOnClickListenerMinus implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            View rootview = (View) v.getParent().getParent().getParent().getParent().getParent();
            int selectedItemPosition = recyclerView.getChildPosition(rootview);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.vote_val);
            if(Pos != selectedItemPosition) {
                Pos = selectedItemPosition;
                mCount=0;
            }
            mCount = mCount+1;
            mMapData.put(selectedItemPosition,mCount);
            String count = "" +mCount;
            textViewName.setText(count);
            sendData(mMapData);
        }
    }

    public static void sendData(HashMap map){
        for(int i =0;i<23;i++){
            Log.d("CHECK","Send data: "+map.get(i));
            if(map.get(i) == null){
                Log.d("CHECK","Data Received: "+value);
                value+=1;
                map.put(i,0);
            }
        }
        if(value >= 1){
            mCallback.passData(map);
        }
    }
}