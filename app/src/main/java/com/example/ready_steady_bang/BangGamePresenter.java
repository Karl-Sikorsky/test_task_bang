package com.example.ready_steady_bang;

import java.util.Random;

public class BangGamePresenter implements MvpContract.GameplayPresenter {
    private MvpContract.GameplayView mView;


    BangGamePresenter(MvpContract.GameplayView mView) {
        this.mView = mView;
        random = new Random();
    }

    private Random random;

    public long getRandomDelayTime(int countLabels) {
        if (countLabels <= 3) {
            return (random.nextInt(3000) + 2000);
        } else {
            return (random.nextInt(300) + 300);
        }
    }


}
