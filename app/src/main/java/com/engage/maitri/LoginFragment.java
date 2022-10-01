package com.engage.maitri;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;


public class LoginFragment extends Fragment {
    private TextView dontHaveAnAccount;
    private TextInputEditText aitMailId,password;
    Button logInBtn;
    FirebaseFirestore dB,idConfirmDB;
    LoadingDialog loadingDialog;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        SharedPreferences pref=getContext().getSharedPreferences("CustomerPref", Context.MODE_PRIVATE);

        String email=pref.getString("aadharCardNumber",NULL);
        if(email!=NULL){
            startActivity(new Intent(getContext(),HomeActivity.class));
        }
        dontHaveAnAccount=view.findViewById(R.id.do_not_have_an_account);
        aitMailId=view.findViewById(R.id.aadhar_number_signIn);
        password=view.findViewById(R.id.password_signIn);
        logInBtn=view.findViewById(R.id.logInBtn);
        dB=FirebaseFirestore.getInstance();
        loadingDialog=new LoadingDialog(getActivity());
        return view;
        //getFragmentManager().beginTransaction().replace(R.id.register_frame_layout,new SignUpFragment()).commit();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.register_frame_layout,new SignUpFragment()).commit();
            }
        });
        aitMailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmLogin();
            }
        });


    }

    private void confirmLogin() {

        String aadharCardNumber= aitMailId.getText().toString().trim();
        String passwordNo=password.getText().toString().trim();


            loadingDialog.showDialog();

                dB.collection("Users").document(aadharCardNumber).collection("UserProfile").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.getResult().isEmpty()){
                            if(task.isSuccessful()){
                                for(DocumentSnapshot snapshot:task.getResult()){
                                    String passWordStored=snapshot.getString("password");
                                    if(passWordStored.equals(passwordNo)){
                                        loadingDialog.hideDialog();
                                        SharedPreferences pref=getActivity().getSharedPreferences("CustomerPref", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor=pref.edit();
                                        editor.putString("aadharCardNumber",aadharCardNumber);
                                        editor.putString("name",snapshot.getString("name"));
                                        editor.apply();


                                        Intent intent=new Intent(getContext(),HomeActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }else {
                                        loadingDialog.hideDialog();
                                        Toast.makeText(getContext(),"Wrong Password Entered",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                        }else{
                            loadingDialog.hideDialog();
                            Toast.makeText(getContext(),"User Not Registered",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }




    private void checkInputs(){
        if(!TextUtils.isEmpty(aitMailId.getText())){
            if(!TextUtils.isEmpty(password.getText())){
                logInBtn.setEnabled(true);
                logInBtn.setTextColor(getResources().getColor(R.color.white));

            }else{
                logInBtn.setEnabled(false);
                logInBtn.setTextColor(Color.argb(50,255,255,255));
            }
        } else {
            logInBtn.setEnabled(false);
            logInBtn.setTextColor(Color.argb(50,255,255,255));
        }

    }
}

