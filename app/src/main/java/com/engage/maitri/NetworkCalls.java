package com.engage.maitri;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.engage.maitri.api.ApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCalls {
    public static List<BooksModel> newArrivalsModelList=new ArrayList<>();
    public static List<NewArrivalsModel> availableBooks=new ArrayList<>();
    public static List<IssuedBooksModel> issuedBooksModelList=new ArrayList<>();
    public static List<BooksModel> booksModelList=new ArrayList<>();




    public static void getNewArrivals(Context context) {
        newArrivalsModelList.clear();
        LoadingDialog loadingDialog = new LoadingDialog((Activity) context);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        loadingDialog.showDialog();
        db.collection("NewArrivals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    loadingDialog.hideDialog();
                    for (DocumentSnapshot snapshot : task.getResult()) {
                        newArrivalsModelList.add(new BooksModel(snapshot.getString("bookName"),
                                snapshot.getString("bookDescription"),
                                snapshot.getString("imageLink"),
                                snapshot.getString("noOfPages"),
                                snapshot.getString("rating"),
                                snapshot.getString("authorName")));
                    }
                    HomeFragment.newArrivalsAdapter.notifyDataSetChanged();
                } else {
                    loadingDialog.hideDialog();
                }
            }
        });
    }
    
    public static void getIssuedBooks(Context context,int che){
        LoadingDialog loadingDialog=new LoadingDialog((Activity) context);
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        SharedPreferences pref=context.getSharedPreferences("CustomerPref", Context.MODE_PRIVATE);
        String user_email=pref.getString("aadharCardNumber","");
        loadingDialog.showDialog();
        db.collection("Users").document(user_email).collection("IssuedBooks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    loadingDialog.hideDialog();
                    for(DocumentSnapshot snapshot:task.getResult()){
                        issuedBooksModelList.add(new IssuedBooksModel(snapshot.getString("bookName"),
                                snapshot.getString("authorName"),
                                snapshot.getString("accNum"),
                                snapshot.getString("imageLink"),
                                Integer.parseInt(Long.toString(snapshot.getLong("transDay"))),
                                Integer.parseInt(Long.toString(snapshot.getLong("transMonth"))),
                                Integer.parseInt(Long.toString(snapshot.getLong("transYear"))),
                                Integer.parseInt(Long.toString(snapshot.getLong("returnDay"))),
                                Integer.parseInt(Long.toString(snapshot.getLong("returnMonth"))),
                                Integer.parseInt(Long.toString(snapshot.getLong("returnYear")))

                                ));


                    }
                    if(che==0){
                        IssuedBooksFragment.issuedBooksAdapter.notifyDataSetChanged();
                    }if(che==1){
                        ProfileFragment.newArrivalsAdapter.notifyDataSetChanged();
                    }

                }else{
                    loadingDialog.hideDialog();
                }
            }
        });
    }
    public static void getBooks(Context context,final BooksAdapter availableBooksAdapter){
        LoadingDialog loadingDialog=new LoadingDialog((Activity) context);
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        loadingDialog.showDialog();
        db.collection("Books").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    loadingDialog.hideDialog();
                    for(DocumentSnapshot snapshot:task.getResult()){
                        booksModelList.add(new BooksModel(snapshot.getString("bookName"),
                                snapshot.getString("bookDescription"),
                                snapshot.getString("imageLink"),
                                snapshot.getString("noOfPages"),
                                snapshot.getString("rating"),
                                snapshot.getString("authorName")));
                    }
                    availableBooksAdapter.notifyDataSetChanged();}
                else{
                    loadingDialog.hideDialog();
                }
            }
        });
    }

}
