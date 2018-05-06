package sample1.carousell_first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TwoFragment extends Fragment {
	
	static HashMap<Integer,Integer> mMapData = new HashMap<>();
	final static String DATA_RECEIVE = "data_receive";
	static String[] nameArray = {"Apple","Apricot","Avocado","Banana","Bilberry","Blackberry",
            "Blackcurrant","Blueberry","Boysenberry","Buddha's hand","Crab apples","Currant",
            "Cherry","Cherimoya","Chico fruit","Cloudberry","Coconut","Cranberry","Cucumber",
            "Custard apple","Damson","Date","Dragonfruit","Durian"};

	ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_two, container, false);
		mListView = rootView.findViewById(R.id.list);
        return rootView;
	}

	//Display data prints the list of data received from Fragment1 in sorted

	protected void displayReceivedData(Bundle bundle)
	{
		int count = 0;
		mMapData = (HashMap<Integer, Integer>) bundle.getSerializable(DATA_RECEIVE);
		if(mMapData != null) {
			Log.d("CHECK","Data Received: "+mMapData);
			Map<Integer, Integer> map = new TreeMap<>(mMapData);
	     	Set<Map.Entry<Integer,Integer>>entries = map.entrySet();
			List<Map.Entry<Integer,Integer>>list = new ArrayList<Map.Entry<Integer, Integer>>(entries);
			Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
				@Override
				public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});

			String[] display = new String[list.size()];
			for(Map.Entry<Integer,Integer>ent:list) {
			    Log.d("CHECK","key Received: "+ent.getKey()+"size :"+list.size());
				display[count] = nameArray[ent.getKey()];
                Log.d("CHECK","F2 loop Entered :"+display[count]);
                count+=1;
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
					android.R.layout.simple_list_item_1,display);
			mListView.setAdapter(adapter);
		}
	}
}