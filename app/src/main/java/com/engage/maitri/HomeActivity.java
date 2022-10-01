package com.engage.maitri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private MeowBottomNavigation botBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        botBottomNavigation = findViewById(R.id.bottom_navigation);

        //Add Menu Item
        botBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        botBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_issued_books));
        botBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_list));
        botBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_profile));


        botBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new IssuedBooksFragment();
                        break;

                    case 3:
                        fragment = new AvailableBooksFragment();
                        break;
                    case 4:
                        fragment = new ProfileFragment();
                        break;
                }
                //load fragment
                loadFragment(fragment);
            }
        });
        //set notification count
//        botBottomNavigation.setCount(1,"10");

        //set home fragment initially selected
        botBottomNavigation.show(1, true);
        botBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        botBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });


    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }
}