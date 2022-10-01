package com.engage.maitri;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class IssuedBooksFragment extends Fragment {
    private RecyclerView issuedBooksModelRecyclerView;
    private List<IssuedBooksModel> issuedBooksModelList;


    public IssuedBooksFragment() {
        // Required empty public constructor
    }
    public static IssuedBooksAdapter issuedBooksAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_issued_books, container, false);
        issuedBooksModelRecyclerView=view.findViewById(R.id.issued_books_rcView);
        issuedBooksModelList=new ArrayList<>();
        //issuedBooksModelList.add(new IssuedBooksModel("My Book",));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        issuedBooksModelRecyclerView.setLayoutManager(linearLayoutManager);
        issuedBooksAdapter=new IssuedBooksAdapter(NetworkCalls.issuedBooksModelList);
        if(NetworkCalls.issuedBooksModelList.size()==0){
            NetworkCalls.getIssuedBooks(getContext(),0);
        }
        issuedBooksModelRecyclerView.setAdapter(issuedBooksAdapter);
        issuedBooksAdapter.notifyDataSetChanged();
        return view;
    }
}