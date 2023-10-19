package com.example.tp_mobdev;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tp_mobdev.databinding.MapFragmentBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;
import java.util.Random;

public class MapFragment extends Fragment {

    private MapFragmentBinding binding;
    private MyLocationNewOverlay myLocationNewOverlay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false);

        Context context = binding.getRoot().getContext();
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));

        // Source des données cartographiques
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK);

//        Marker marker = new Marker(binding.mapView);
//        marker.setPosition(new GeoPoint(45.75, 4.85));
//        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
//        marker.setTitle("Start point");
//        binding.mapView.getOverlays().add(marker);

        // Suivre son déplacement
        myLocationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(context), binding.mapView) {
            @Override
            public void onLocationChanged(Location location, IMyLocationProvider source) {
                super.onLocationChanged(location, source);
                if (location != null) {
                    GeoPoint myLocation = new GeoPoint(location);
                    binding.mapView.getController().animateTo(myLocation);
                }
            }
        };
        myLocationNewOverlay.enableMyLocation();
        binding.mapView.getOverlays().add(myLocationNewOverlay);

        binding.mapView.getController().setZoom(12.0);


        FragmentManager fragmentManager = getFragmentManager();
        Fragment pokedexFragment = fragmentManager.findFragmentByTag("PokedexFragment");

//        if (pokedexFragment instanceof PokedexFragment) {
//            System.out.println("OUI");
//            List<Pokemon> pokemonList = ((PokedexFragment) getParentFragment()).getPokemonList();
//            for (Pokemon pokemon : pokemonList) {
//
//                double latitude = getRandomLatitude();
//                double longitude = getRandomLongitude();
//
//                Marker marker = new Marker(binding.mapView);
//                marker.setPosition(new GeoPoint(latitude, longitude));
//                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
//                marker.setTitle(pokemon.getName());
//                binding.mapView.getOverlays().add(marker);
//            }
//        } else {
//            System.out.println("NON");
//        }


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    public double getRandomLatitude() {
        double minLatitude = 45.43;
        double maxLatitude = 45.75;
        Random random = new Random();
        double latitude = minLatitude + (maxLatitude - minLatitude) * random.nextDouble();
        return latitude;
    }

    public double getRandomLongitude() {
        double minLongitude = 4.76;
        double maxLongitude = 5.03;
        Random random = new Random();
        double longitude = minLongitude + (maxLongitude - minLongitude) * random.nextDouble();
        return longitude;
    }
}
