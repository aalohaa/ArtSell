package com.example.artsell;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.artsell.activities.HomeActivity;
import com.example.artsell.activities.LoginActivity;
import com.example.artsell.databinding.ActivityMainBinding;
import com.example.artsell.models.UserModel;
import com.example.artsell.ui.category.CategoryFragment;
import com.example.artsell.ui.home.HomeFragment;
import com.example.artsell.ui.profile.ProfileFragment;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth auth;
    private AppBarConfiguration mAppBarConfiguration;

    ActivityMainBinding binding;
    BottomNavigationView navigationView;
    FloatingActionMenu fabMenu;
    com.github.clans.fab.FloatingActionButton fabProfile, fabCategory, fabOffers, fabNewProducts,fabMyCarts, fabShowLogin;
    FloatingActionButton fabSearch;

//    View headerView = navigationView.getHeaderView(0);
//    TextView headerName = headerView.findViewById(R.id.nav_header_name);
//    TextView headerEmail = headerView.findViewById(R.id.nav_header_email);
//    CircleImageView headerImg = headerView.findViewById(R.id.nav_header_img);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        fabMenu = findViewById(R.id.fabMenu);
        fabProfile = findViewById(R.id.nav_profile);
        fabCategory = findViewById(R.id.nav_category);
        fabOffers = findViewById(R.id.nav_offers);
        fabNewProducts = findViewById(R.id.nav_new_products);
        fabMyCarts = findViewById(R.id.nav_my_carts);
        fabShowLogin = findViewById(R.id.show_login);

        fabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ProfileFragment());
                fabMenu.close( true );
            }
        });
        fabCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                replaceFragment(new CategoryFragment());
                fabMenu.close( true );
            }
        });
        fabOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                replaceFragment(new OffersFragment());
                fabMenu.close( true );
            }
        });
        fabNewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                replaceFragment(new newProductsFragment());
                fabMenu.close( true );
            }
        });
        fabMyCarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                replaceFragment(new MyCartsFragment());

                fabMenu.close( true );
            }
        });
        fabShowLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                fabMenu.close( true );
            }
        });
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_my_orders:
                    replaceFragment(new myOrdersFragment());
                    break;
                case R.id.scene_chat:
                    replaceFragment(new ChatFragment());
                    break;
                case R.id.scene_btn:

                    break;
                default:
                    return true;
            }

            return true;
        });




//        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        UserModel userModel = snapshot.getValue(UserModel.class);
//
//                        headerName.setText(userModel.getName());
//                        headerEmail.setText(userModel.getEmail());
//                        Glide.with(MainActivity.this).load(userModel.getProfileImg()).into(headerImg);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.drawer_layout, fragment);
        fragmentTransaction.commit();
    }


    private void showToast(String message){
        Toast.makeText( this, message, Toast.LENGTH_SHORT).show();
    }
}