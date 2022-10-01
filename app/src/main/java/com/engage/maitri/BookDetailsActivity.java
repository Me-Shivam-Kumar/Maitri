package com.engage.maitri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class BookDetailsActivity extends AppCompatActivity {
    TextView authorName,bookName,rating,noOfPages,bookDescription;
    ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Intent intent=getIntent();
        String bookNamee= intent.getStringExtra("bookName");
        String authorNamee=intent.getStringExtra("authorName");

        String bookDesc=intent.getStringExtra("bookDesc");
       String ratingg= intent.getStringExtra("rating");
       String noOfPagess= intent.getStringExtra("noOfPages");
        String imageLink=intent.getStringExtra("imageLink");



        authorName=findViewById(R.id.textView_authorName);
        bookName=findViewById(R.id.textView_bookname);
        rating=findViewById(R.id.rating_text_view);
        noOfPages=findViewById(R.id.noOfPages_tv);
        bookDescription=findViewById(R.id.bookDescription_tv);
        bookImage=findViewById(R.id.imageView_bookInfo);

        authorName.setText(authorNamee);
        bookName.setText(bookNamee);
        rating.setText(ratingg);
        noOfPages.setText(noOfPagess);
        bookDescription.setText(bookDesc);
        Glide.with(BookDetailsActivity.this).load(imageLink).placeholder(R.drawable.plain_book).into(bookImage);




    }
}