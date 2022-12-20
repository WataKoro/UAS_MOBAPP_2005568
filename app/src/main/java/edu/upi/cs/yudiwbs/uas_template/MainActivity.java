package edu.upi.cs.yudiwbs.uas_template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.itmSatu:
                                Toast.makeText(getApplicationContext(), "Satu", Toast.LENGTH_SHORT).show();
                                if (savedInstanceState == null) {
                                    getSupportFragmentManager().beginTransaction()
                                            .setReorderingAllowed(true)
                                            .replace(R.id.fragmentContainerView, FragmentSatu.class, null)
                                            .commit();
                                }
                                break;
                            case R.id.itmDua:
                                Toast.makeText(getApplicationContext(), "Dua", Toast.LENGTH_SHORT).show();
                                if (savedInstanceState == null) {
                                    getSupportFragmentManager().beginTransaction()
                                            .setReorderingAllowed(true)
                                            .replace(R.id.fragmentContainerView, FragmentDua.class, null)
                                            .commit();
                                }
                                break;

                        }

                        return true;
                    }
                });

    }
}