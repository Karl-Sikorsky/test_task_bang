package com.example.ready_steady_bang;

import android.content.Context;

public interface MvpContract {

    interface View {


    }

    interface MainView extends View {


        void startGame();

        void showWebView();

        void showError();
    }

    interface MainPresenter {


        void prepareStore(Context context);

        void showError();

        void onDestroy();
    }

    interface GameplayView extends View {

    }

    interface GameplayPresenter {

        long getRandomDelayTime(int countLabels);
    }


}
