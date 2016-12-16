package unicauca.movil.parkinapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import unicauca.movil.parkinapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment implements OnMapReadyCallback{


    private GoogleMap mapa;

    @Override
    public void onMapReady(GoogleMap map) {
        mapa = map;
    }

    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateV(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

}
