package com.codekul.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment; // support for all versions
//import android.app.Fragment; only >= honey comb
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment getInstance(String userName){

        FirstFragment fragment = new FirstFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key_user_name",userName);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle !=null){
            String userName = bundle.getString("key_user_name");
            Log.i("@codekul","User Name is "+userName);
        }

        final View view = inflater.inflate(R.layout.fragment_first, container,false);

        final Button btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
