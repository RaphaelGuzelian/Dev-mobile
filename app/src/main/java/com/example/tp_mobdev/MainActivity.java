package com.example.tp_mobdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.example.tp_mobdev.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    // Permet de lier les éléments de l'interface définis dans "activity_main.xml"
    // avec les objets java correspondants
    private ActivityMainBinding binding;
    private LocationListener myLocationListener;

    PokedexFragment.OnClickOnNoteListener listener = new PokedexFragment.OnClickOnNoteListener() {
        @Override
        public void onClickOnNote(Pokemon pokemon) {
            showNoteDetail(pokemon);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityCompat.checkSelfPermission( this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions =
                    {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,
                    permissions,1);
        }

        myLocationListener = new LocationListener(){
            @Override
            public void onLocationChanged(Location newLocation){}
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras){}
            @Override
            public void onProviderEnabled(String provider){}
            @Override
            public void onProviderDisabled(String provider){}
        };

        LocationManager manager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 120, 100,
                myLocationListener);
        manager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 120, 100,
                myLocationListener);



        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.map) {
                showMap();
                return true;
            }
            if (item.getItemId() == R.id.pokedex) {
                showStartup();
                return true;
            }
            if (item.getItemId() == R.id.captured) {
                return true;
            }
            if (item.getItemId() == R.id.inventory) {
                return true;
            }
            return false;
        });

        showStartup();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //on a la permission
        } else {
            //afficher un message d’erreur
            System.out.println("Permission réfusée");
        }
    }


    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokedexFragment fragment = new PokedexFragment();
        fragment.setOnClickOnNoteListener(listener); // On passe le listener à PokedexFragment
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void showNoteDetail(Pokemon pokemon) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetails fragment = new PokemonDetails();
        fragment.setPokemon(pokemon);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    public void showMap() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

}