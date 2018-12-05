package com.example.ready_steady_bang;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements FragmentHostView {
    MenuFragment menuFragment;
    GamePlayFragment gamePlayFragment;
    FragmentTransaction fragmentTransaction;
    private static final int CODE_SINGLEPLAYER = 1;
    private static final int CODE_MULTIPLAYER = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gamePlayFragment = new GamePlayFragment();
        menuFragment = new MenuFragment();
        menuFragment.setFragmentHostView(this);

        setMenuFragment();
    }

    private void setMenuFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, menuFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void replaceFragment(boolean isMultiplayer) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, gamePlayFragment);
        gamePlayFragment.setMultiplayer(isMultiplayer);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void sendCommand(int commandCode) {
        if (commandCode == CODE_SINGLEPLAYER) replaceFragment(false);
        if (commandCode == CODE_MULTIPLAYER) replaceFragment(true);
    }

    @Override
    public int receiveCommand() {
        return 0;
    }
}