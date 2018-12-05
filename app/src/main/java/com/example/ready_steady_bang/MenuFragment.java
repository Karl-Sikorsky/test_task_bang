package com.example.ready_steady_bang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

    FragmentHostView fragmentHostView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, null);
        Button buttonSingle = (Button) v.findViewById(R.id.buttonSingle);
        buttonSingle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentHostView.sendCommand(1);
            }
        });
        Button buttonMulti = (Button) v.findViewById(R.id.buttonMulti);
        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentHostView.sendCommand(2);
            }
        });
        return v;
    }

    public FragmentHostView getFragmentHostView() {
        return fragmentHostView;
    }

    public void setFragmentHostView(FragmentHostView fragmentHostView) {
        this.fragmentHostView = fragmentHostView;
    }


}