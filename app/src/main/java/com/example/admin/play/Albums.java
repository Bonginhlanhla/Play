package com.example.admin.play;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class Albums extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ImageButton btnPressJay;
    private View v;
    public Albums() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          v = inflater.inflate(R.layout.fragment_albums, container, false);
        pressImageButton();
        return v;
    }
    public void pressImageButton()
    {
        btnPressJay = (ImageButton) v.findViewById(R.id.imgJayZ);

        btnPressJay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intNext = new Intent(getActivity(),AlbumSongsActivity.class);
                startActivity(intNext);
            }
        });
    }



}
