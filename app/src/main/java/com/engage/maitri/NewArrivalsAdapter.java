package com.engage.maitri;

import android.content.Intent;
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
import com.engage.maitri.onBoardingScreen.OnBoardingAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalsAdapter.ViewHolder>  {
     List<BooksModel> newArrivalsModelList;


    public NewArrivalsAdapter(List<BooksModel> newArrivalsModelList) {
        this.newArrivalsModelList = newArrivalsModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewArrivalsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_new_arivals,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(newArrivalsModelList.get(position).getBookName(),newArrivalsModelList.get(position).getImageLink(),
                newArrivalsModelList.get(position).getBookDescription(),newArrivalsModelList.get(position).getRating(),
                newArrivalsModelList.get(position).getNoOfPages(),newArrivalsModelList.get(position).getAuthorName());
    }

    @Override
    public int getItemCount() {
        return newArrivalsModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView bookName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.book_image);
            bookName=itemView.findViewById(R.id.book_name);
        }
        void setData(String bookNamee,String imgLink,String bookDescription,String rating,String noOfPages,String authorName){
            bookName.setText(bookNamee);
            Glide.with(itemView.getContext()).load(imgLink).placeholder(R.drawable.plain_book).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(),BookDetailsActivity.class);
                    intent.putExtra("bookName",bookNamee);
                    intent.putExtra("imageLink",imgLink);
                    intent.putExtra("bookDesc",bookDescription);
                    intent.putExtra("rating",rating);
                    intent.putExtra("noOfPages",noOfPages);
                    intent.putExtra("authorName",authorName);
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}
