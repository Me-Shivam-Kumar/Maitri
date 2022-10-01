package com.engage.maitri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.engage.maitri.onBoardingScreen.OnBoardingAdapter;
import com.engage.maitri.onBoardingScreen.OnBoardingItem;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class OnBoardingActivity extends AppCompatActivity {
    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnBoardingIndicators;
    private MaterialButton buttonOnBoardingAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        SharedPreferences pref=getSharedPreferences("CustomerPref", Context.MODE_PRIVATE);

        String email=pref.getString("shown",NULL);
        if(email==NULL){
            layoutOnBoardingIndicators=findViewById(R.id.layoutOnBoardingIndicators);
            buttonOnBoardingAction=findViewById(R.id.buttonOnBoardingScreen);
            setupOnBoardingItem();
            ViewPager2 onBoardingViewPager=findViewById(R.id.onBoardingViewPager);
            onBoardingViewPager.setAdapter(onBoardingAdapter);
            setUpOnBoardingIndicators();
            setCurrentOnBoardingIndicator(0);

            onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setCurrentOnBoardingIndicator(position);
                }
            });
            buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onBoardingViewPager.getCurrentItem()==onBoardingAdapter.getItemCount()-1){
                        startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
                        finish();
                    }else{

                        onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem()+1);

                    }
                }
            });
            SharedPreferences.Editor editor=pref.edit();
            editor.putString("shown","shown");
            editor.apply();
        }else{
             SharedPreferences preff=getSharedPreferences("CustomerPref", Context.MODE_PRIVATE);

            String emaill=pref.getString("aadharCardNumber",NULL);
            if(emaill!=NULL){
                startActivity(new Intent(OnBoardingActivity.this,HomeActivity.class));

            }else{
                startActivity(new Intent(OnBoardingActivity.this,MainActivity.class));
            }


        }


    }
    private void setupOnBoardingItem(){
        List<OnBoardingItem> onBoardingItems=new ArrayList<>();
        OnBoardingItem item1=new OnBoardingItem();
        item1.setTitle("Ait Library Management App");
        item1.setDescription("Developed By An Aitian For The Aitians");
        item1.setImage(R.drawable.ait_pune_logo);
        OnBoardingItem item2=new OnBoardingItem();
        item2.setTitle("Learn More, Grow More");
        item2.setDescription("Seamlessly Manage Your Library Activities ");
        item2.setImage(R.drawable.reading_book_generated);

        onBoardingItems.add(item1);
        onBoardingItems.add(item2);

        onBoardingAdapter=new OnBoardingAdapter(onBoardingItems);



    }

    private void setUpOnBoardingIndicators(){
        ImageView[]indicators =new  ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){
            indicators[i]=new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);
        }
    }
    private void setCurrentOnBoardingIndicator(int index){
        int childCount = layoutOnBoardingIndicators.getChildCount();
        for(int i=0;i<childCount;i++){
            ImageView imageView=(ImageView)layoutOnBoardingIndicators.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));

            }
        }
        if(index==onBoardingAdapter.getItemCount()-1){
            buttonOnBoardingAction.setText("Start");
        }else{
            buttonOnBoardingAction.setText("Next");
        }
    }
    }
