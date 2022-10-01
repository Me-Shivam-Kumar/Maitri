package com.engage.maitri;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class SignUpFragment extends Fragment {

    Button signUpBtn;
    FirebaseFirestore dB;

    TextInputEditText nameSignUp, aitMailId, passwordSignUp, confirmPasswordSignUp;
    TextView alreadyhaveanaccount;
    LoadingDialog loadingDialog;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        dB=FirebaseFirestore.getInstance();

        signUpBtn=view.findViewById(R.id.sign_up_btn);
        nameSignUp=view.findViewById(R.id.name_signUp);
        alreadyhaveanaccount=view.findViewById(R.id.already_have_an_account);
        aitMailId=view.findViewById(R.id.aadhar_number_signUp);
        passwordSignUp=view.findViewById(R.id.password_signUp);
        confirmPasswordSignUp=view.findViewById(R.id.confirm_password_signUp);
        loadingDialog=new LoadingDialog(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.register_frame_layout, new LoginFragment()).commit();
            }
        });
        nameSignUp.addTextChangedListener(new TextWatcher() {
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
        passwordSignUp.addTextChangedListener(new TextWatcher() {
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
        confirmPasswordSignUp.addTextChangedListener(new TextWatcher() {
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
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(nameSignUp.getText())) {
            if (!TextUtils.isEmpty(aitMailId.getText())) {
                if (!TextUtils.isEmpty(passwordSignUp.getText())) {
                    if (!TextUtils.isEmpty(confirmPasswordSignUp.getText())) {
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(getResources().getColor(R.color.white));

                    } else {
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
                    }
                } else {
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
                }
            } else {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
            }
        } else {
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.argb(50, 255, 255, 255));
        }

    }

    void registerNewUser() {
        Map<String, Object> newUserData = new HashMap<>();
        String name = nameSignUp.getText().toString();
        String aadharNumber = aitMailId.getText().toString();
        String password = passwordSignUp.getText().toString();
        newUserData.put("name", name);
        newUserData.put("aitMailId", aadharNumber);
        newUserData.put("password", password);
        signUpBtn.setEnabled(false);
        loadingDialog.showDialog();
        if (passwordSignUp.getText().toString().equals(confirmPasswordSignUp.getText().toString())) {
            if (passwordSignUp.length() >= 6) {
                if (true) {


                    dB.collection("Users").document(aadharNumber).collection("UserProfile").add(newUserData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            loadingDialog.hideDialog();
                            Toast.makeText(getContext(), "Profile Created Successfully", Toast.LENGTH_LONG).show();
                            getFragmentManager().beginTransaction().replace(R.id.register_frame_layout, new LoginFragment()).commit();

                        }
                    });


                }
            } else {
                loadingDialog.hideDialog();
                signUpBtn.setEnabled(true);
                Toast.makeText(getContext(), "Wrong Aadhar Card Number ", Toast.LENGTH_SHORT).show();
            }

        } else {
            loadingDialog.hideDialog();
            signUpBtn.setEnabled(true);
            Toast.makeText(getContext(), "Password should be at least 6 character long ", Toast.LENGTH_SHORT).show();
        }
        loadingDialog.hideDialog();


    }





}
