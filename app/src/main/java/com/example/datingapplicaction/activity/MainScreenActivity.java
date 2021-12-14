package com.example.datingapplicaction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;

import com.example.datingapplicaction.R;

import com.example.datingapplicaction.databinding.ActivityMainScreenBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;



public class MainScreenActivity extends AppCompatActivity {



    FirebaseAuth firebaseAuth;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();

        setSupportActionBar(binding.appBarMainScreen.toolbar);
        binding.appBarMainScreen.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent shareintent = new Intent(android.content.Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Applications Link");
                shareintent.putExtra(Intent.EXTRA_TEXT,"Enter The Application Link");
                startActivity(Intent.createChooser(shareintent,"Send To Your Friend"));

            }
        });





        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_screen);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_screen);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}