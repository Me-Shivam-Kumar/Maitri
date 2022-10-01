package com.engage.maitri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> implements Filterable {
    List<BooksModel> booksModelList;
    List<BooksModel> booksModelListAll;

    public BooksAdapter(List<BooksModel> booksModelList) {
        this.booksModelList = booksModelList;
        this.booksModelListAll=new ArrayList<>(booksModelList);
    }

    @NonNull
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BooksAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_new_arivals,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.ViewHolder holder, int position) {
        holder.setData(booksModelList.get(position).getBookName(),booksModelList.get(position).getImageLink());
    }

    @Override
    public int getItemCount() {
        return booksModelList.size();
    }
    @Override
    public Filter getFilter() {
        return myFilter;
    }
    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<BooksModel> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0 || charSequence.toString().isEmpty()) {
                filteredList.addAll(booksModelListAll);
            } else {
                for (BooksModel book: booksModelListAll) {
                    if (book.getBookName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(book);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            booksModelList.clear();
            booksModelList.addAll((ArrayList<BooksModel>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView bookName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.book_image);
            bookName=itemView.findViewById(R.id.book_name);
        }
        void setData(String bookNamee,String imgLink){
            bookName.setText(bookNamee);
            Glide.with(itemView.getContext()).load(imgLink).placeholder(R.drawable.plain_book).into(imageView);
        }
    }
}
