package com.engage.maitri;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;
import static com.google.common.primitives.Ints.min;

public class ProfileFragment extends Fragment {


    private RecyclerView newArrivalsList;
    private List<BooksModel> newArrivalsModelList;
    private TextView libraryFeedbackTV,registerComplaint;
    private TextView profileName,profileEmail;
    private Button logOut;
    private EditText complaintEditText;
    private FirebaseFirestore db;
    private LoadingDialog loadingDialog;
    String name;
    String email;
public static NewArrivalsAdapter newArrivalsAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        libraryFeedbackTV=view.findViewById(R.id.textView_libraryFeedback);
        newArrivalsList=view.findViewById(R.id.latest_issued_books_recyclerView);
        registerComplaint=view.findViewById(R.id.register_complaint);
        profileName=view.findViewById(R.id.profile_name);
        db=FirebaseFirestore.getInstance();
        profileEmail=view.findViewById(R.id.profile_email);
        logOut=view.findViewById(R.id.log_out_button);
        loadingDialog=new LoadingDialog(getActivity());


        newArrivalsModelList=new ArrayList<>();
        SharedPreferences pref=getContext().getSharedPreferences("CustomerPref", Context.MODE_PRIVATE);
         name=pref.getString("name",NULL);
        email=pref.getString("aadharCardNumber",NULL);

        profileEmail.setText(email);
        profileName.setText(name);


        if(NetworkCalls.issuedBooksModelList.size() ==0){
            NetworkCalls.getIssuedBooks(getContext(),1);
        }
        for(int i=0;i<Math.min(NetworkCalls.issuedBooksModelList.size(),3);i++){
            newArrivalsModelList.add(new BooksModel(NetworkCalls.issuedBooksModelList.get(i).getBookName(),
                    "",NetworkCalls.issuedBooksModelList.get(i).imageLink,"","",""));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        newArrivalsList.setLayoutManager(linearLayoutManager);
        newArrivalsAdapter=new NewArrivalsAdapter(newArrivalsModelList);
        newArrivalsList.setAdapter(newArrivalsAdapter);
        newArrivalsAdapter.notifyDataSetChanged();

        libraryFeedbackTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),LibraryFeedbackActivity.class));
            }
        });
        registerComplaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showComplaintBottomSheet();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("aadharCardNumber",NULL);
                editor.putString("name",NULL);
                editor.apply();
                Intent intent = new Intent(getContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        return view;
    }

     void showComplaintBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());

        View parentView = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_register_complaint, null);
        Button submitComplaint=parentView.findViewById(R.id.button_submit_complaint);
        EditText complaint=parentView.findViewById(R.id.editText_complaint);

        submitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.showDialog();
                if(complaint.getText().length()==0){
                    Toast.makeText(getContext(),"Enter complaint in the given box",Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,String> map=new HashMap<String,String>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("complaint",complaint.getText().toString());
                    db.collection("Complaint").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                loadingDialog.hideDialog();
                                Toast.makeText(getActivity(),"Your Complaint had been recorded",Toast.LENGTH_SHORT).show();
                                complaint.setText("");
                            }else{
                                loadingDialog.hideDialog();
                                Toast.makeText(getActivity(),"Error occurred. Please try again",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });
        bottomSheetDialog.setContentView(parentView);
         bottomSheetDialog.show();
    }
}