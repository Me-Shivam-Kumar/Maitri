package com.engage.maitri;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.engage.maitri.api.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AvailableBooksFragment extends Fragment {
    private RecyclerView availableBookRecyclerView;
    SearchView searchView;


    public AvailableBooksFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_available_books, container, false);
        availableBookRecyclerView=view.findViewById(R.id.available_books_rv);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);


        availableBookRecyclerView.setLayoutManager(gridLayoutManager);
        searchView=view.findViewById(R.id.searchView2);

        BooksAdapter availableBookAdapter=new BooksAdapter(NetworkCalls.booksModelList);
        if(NetworkCalls.booksModelList.size()==0){
            NetworkCalls.getBooks(getContext(),availableBookAdapter);
        }

        availableBookRecyclerView.setAdapter(availableBookAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                availableBookAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }
}