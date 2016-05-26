package com.codekul.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment getInstance(){

        MenuFragment fragment = new MenuFragment();

        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView listCountries = (ListView) view.findViewById(R.id.listCountries);
        listCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final TextView textView = (TextView) view;
                final MainActivity act = (MainActivity) getActivity();
                if(textView.getText().toString().equalsIgnoreCase("india")){
                    act.runFragmentTransaction(R.id.frameContainer,ContentFragment.getInstance(R.drawable.india));
                }
                else if(textView.getText().toString().equalsIgnoreCase("china")){
                    act.runFragmentTransaction(R.id.frameContainer,ContentFragment.getInstance(R.drawable.china));
                }
                else if(textView.getText().toString().equalsIgnoreCase("japan")){
                    act.runFragmentTransaction(R.id.frameContainer,ContentFragment.getInstance(R.drawable.japan));
                }
                else if(textView.getText().toString().equalsIgnoreCase("shri lanka")){
                    act.runFragmentTransaction(R.id.frameContainer,ContentFragment.getInstance(R.drawable.shrilkanka));
                }
                else {
                    act.runFragmentTransaction(R.id.frameContainer,ContentFragment.getInstance(R.drawable.nepal));
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                Arrays.asList("India","China","Japan","Shri Lanka","Nepal"));

        listCountries.setAdapter(adapter);

        return view;
    }
}
