package com.example.ready_steady_bang;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GamePlayFragment extends Fragment implements View.OnClickListener, MvpContract.GameplayView {

    TextView tvStartGame, tvScore1, tvScore2;
    int countLabels;
    boolean isShotInTime, isMultiplayer;
    int score1, score2;
    MvpContract.GameplayPresenter mPresenter;

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    View field1, field2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gameplay, null);
        mPresenter = new BangGamePresenter(this);
        countLabels = 0;
        tvStartGame = (TextView) v.findViewById(R.id.textViewTimer);
        field1 = (View) v.findViewById(R.id.viewField1);
        field2 = (View) v.findViewById(R.id.viewField2);
        tvScore1 = (TextView) v.findViewById(R.id.textViewScore1Player);
        tvScore2 = (TextView) v.findViewById(R.id.textViewScore2Player);
        score1 = 0;
        score2 = 0;
        field1.setOnClickListener(this);
        field2.setOnClickListener(this);

        isShotInTime = false;
        if (!isMultiplayer) {
            v.findViewById(R.id.textViewPlayer1Shoot).setVisibility(View.GONE);
            TextView tvShoot = (TextView) v.findViewById(R.id.textViewPlayer2Shoot);
            tvShoot.setText(R.string.tap_for_shoot);
        }
        recallTitleChanging();

        return v;
    }

    private void recallTitleChanging() {
        if (gameStopped) {
            return;
        }
        countLabels++;
        if (countLabels == 4) {
            isShotInTime = true;
        }
        if (countLabels <= 4) {
            tvStartGame.postDelayed(new Runnable() {
                public void run() {
                    tvStartGame.setText(getNextTitle());
                    Log.d("gameplay title", "called settext");
                    recallTitleChanging();
                }
            }, mPresenter.getRandomDelayTime(countLabels));
        }
    }


    public String getNextTitle() {
        String newLabel = tvStartGame.getText().toString();
        switch (countLabels) {
            case 1:
                newLabel = "Ready";
                break;
            case 2:
                newLabel = "Steady";
                break;
            case 3:
                newLabel = "BANG!";
                break;
            case 4: {
                if (!isMultiplayer) {
                    newLabel = "TOO LATE! YOU LOSE";
                    updateScores(score1 + 1, score2);
                    isShotInTime = false;
                    stopRound();
                }
            }
        }
        return newLabel;
    }

    private void updateScores(int score1, int score2) {
        this.score2 = score2;
        this.score1 = score1;
        tvScore1.setText(String.valueOf(score1));
        tvScore2.setText(String.valueOf(score2));
    }

    @Override
    public void onClick(View view) {
        if (gameStopped) {
            return;
        }
        switch (view.getId()) {
            case R.id.viewField1:
                checkResult(1);
                break;
            case R.id.viewField2:
                checkResult(2);
                break;
        }
    }

    private void checkResult(int player) {
        if (isMultiplayer) {

            if ((isShotInTime)) {
                tvStartGame.setText("PLAYER " + player + " WIN");

                updateScores(score1 + (2 - player), score2 + (player - 1));

                stopRound();
            } else {
                tvStartGame.setText("PLAYER " + (3 - player) + " WIN");
                updateScores(score1 + (player - 1), score2 + (2 - player));
                stopRound();
            }


        } else {
            if (isShotInTime) {
                tvStartGame.setText(R.string.player_win);
                updateScores(score1, score2 + 1);
                stopRound();
            } else {
                tvStartGame.setText(R.string.too_early);
                updateScores(score1 + 1, score2);
                stopRound();
            }
        }
    }

    private boolean gameStopped;

    private void stopRound() {
        gameStopped = true;
        isShotInTime = false;
        countLabels = 0;
        tvStartGame.postDelayed(new Runnable() {
            public void run() {
                tvStartGame.setText(R.string.wait_for_next_round);
                Log.d("gameplay title", "called settext");
                gameStopped = false;
                recallTitleChanging();
            }
        }, 3000);

    }


}
