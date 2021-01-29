package com.example.clerk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import com.example.clerk.database.AccountsRepository;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentByTag("navHostFrag");
        if(navHost!=null) {
            System.out.println("hey I'm here.......................................................................");
            NavController navController = navHost.getNavController();
            DrawerLayout drawerLayout = findViewById(R.id.drawer);
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment,
                    R.id.groupsFragment, R.id.candidatesFragment, R.id.chargesFragment, R.id.penaltiesFragment,
                    R.id.removedFragment, R.id.helpFragment).setOpenableLayout(drawerLayout).build();
            NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

        }
        new AccountsRepository(getApplication());
    }
}