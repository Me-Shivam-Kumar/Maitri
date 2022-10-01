package com.engage.maitri;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class IssuedBooksAdapter extends RecyclerView.Adapter<IssuedBooksAdapter.ViewHolder> {
    List<IssuedBooksModel> issuedBooksModelList;

    public IssuedBooksAdapter(List<IssuedBooksModel> issuedBooksModelList) {
        this.issuedBooksModelList = issuedBooksModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IssuedBooksAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_issued_books,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(issuedBooksModelList.get(position).getBookName(),
                issuedBooksModelList.get(position).authorName,issuedBooksModelList.get(position).getAccNum(),
                issuedBooksModelList.get(position).getTransDay(),
                issuedBooksModelList.get(position).getTransMonth(),
                issuedBooksModelList.get(position).getTransYear(),
                issuedBooksModelList.get(position).getReturnDay(),
                issuedBooksModelList.get(position).getReturnMonth(),
                issuedBooksModelList.get(position).getReturnYear(),
                issuedBooksModelList.get(position).getImageLink());
    }

    @Override
    public int getItemCount() {
        return issuedBooksModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookName,authorName,transDate,accNum,returnDate;
        ImageView issueBookImageView;
        Button reissueBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName=itemView.findViewById(R.id.isb_book_name);
            authorName=itemView.findViewById(R.id.isb_author_name);
            transDate=itemView.findViewById(R.id.trans_date);
            accNum=itemView.findViewById(R.id.acc_num);
            issueBookImageView=itemView.findViewById(R.id.imageView_issuedBooks);
            returnDate=itemView.findViewById(R.id.isb_return_date);
            reissueBtn=itemView.findViewById(R.id.reissue_btn);
        }
        void setData(String bookNameText,String authorNameText,String accNumText,int transDay,int transMonth,int TransYear,int  returnDay, int returnMonth, int returnYear ,String imageLink){
            bookName.setText(bookNameText);
            authorName.setText(authorNameText);
            accNum.setText(accNumText);
            transDate.setText(Integer.toString(transDay)+"/"+Integer.toString(transMonth)+"/"+Integer.toString(TransYear));
            returnDate.setText(Integer.toString(returnDay)+"/"+Integer.toString(returnMonth)+"/"+Integer.toString(returnYear));
            final int[] returnDayy = {returnDay};
            final int[] returnMonthh = {returnMonth};
            final int[] returnYearr = {returnYear};

            Glide.with(itemView.getContext()).load(imageLink).placeholder(R.drawable.plain_book).into(issueBookImageView);
            reissueBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());

                    // Set the message show for the Alert time
                    builder.setMessage("Do you want reissue the book?");

                    // Set Alert Title
                    builder.setTitle("Confirm !");

                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                    builder.setCancelable(false);

                    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // When the user click yes button then app will close
                        Toast.makeText(itemView.getContext(),"Book Reissued Successfully",Toast.LENGTH_SHORT).show();
                        returnDayy[0] =(returnDayy[0] +6);
                        if(returnDayy[0] >30){
                            returnDayy[0] =(returnDayy[0])%30;
                            returnMonthh[0]++;
                        }
                        if(returnMonthh[0] >13){
                            returnMonthh[0] =(returnMonthh[0])%12;
                            returnYearr[0]++;
                        }
                        returnDate.setText(Integer.toString(returnDayy[0])+"/"+Integer.toString(returnMonthh[0])+"/"+Integer.toString(returnYearr[0]));
                        dialog.cancel();
                    });

                    // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // If user click no then dialog box is canceled.
                        dialog.cancel();
                    });

                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }
            });
        }
    }
}
