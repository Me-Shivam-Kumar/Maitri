package com.engage.maitri;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;


public class HomeFragment extends Fragment {

    private RecyclerView newArrivalsList;

    private TextView greetings,nameTextView;

   public static BooksAdapter newArrivalsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        newArrivalsList=view.findViewById(R.id.new_arrival_rcView);
        greetings=view.findViewById(R.id.grreting_textView);

        nameTextView=view.findViewById(R.id.name_text_view);

        SharedPreferences pref=getContext().getSharedPreferences("CustomerPref", Context.MODE_PRIVATE);
        String name=pref.getString("name",NULL);
        if(name!=NULL){
            nameTextView.setText(name);
        }else{
            nameTextView.setText("Friend");
        }

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        newArrivalsList.setLayoutManager(gridLayoutManager);
        if(NetworkCalls.newArrivalsModelList.size()==0){
            NetworkCalls.getNewArrivals(getContext());
        }

        newArrivalsAdapter=new BooksAdapter(NetworkCalls.newArrivalsModelList);
        newArrivalsList.setAdapter(newArrivalsAdapter);


        Calendar calendar= Calendar.getInstance();
        int jnm=calendar.get(Calendar.HOUR_OF_DAY);
        if(jnm>=0 && jnm<12){
            greetings.setText("Good \nMorning");
        }else if(jnm>=12 && jnm<16){
            greetings.setText("Good \nAfternoon");
        }else if(jnm>=16 && jnm<21){
            greetings.setText("Good \nEvening");
        }else if(jnm>=21 && jnm<=24){
            greetings.setText("Hello \n");
        }else{
            greetings.setText("Hello");

        }


        return view;
    }
}