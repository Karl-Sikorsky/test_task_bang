package com.example.ready_steady_bang;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ready_steady_bang.POJO.AllowData;
import com.example.ready_steady_bang.POJO.AllowRequest;
import com.example.ready_steady_bang.POJO.AllowResult;
import com.example.ready_steady_bang.network.NetworkService;
import com.example.ready_steady_bang.network.RetrofitHelper;
import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PagePresenter implements MvpContract.MainPresenter {
    private NetworkService mNetworkService;
    private Preference<String> allowPreference;
    private MvpContract.MainView mView;

    PagePresenter(MvpContract.MainView mView) {
        this.mView = mView;
    }


    @Override
    public void prepareStore(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        allowPreference = rxPreferences.getString("allow", "null");
        Log.d("allow", "now is "+ allowPreference.get());
        checkPreferences();
    }

    private void checkPreferences() {

        String s = allowPreference.get();
        if (s.equals("true")) {
            mView.startGame();
        } else if (s.equals("false")) {
            mView.showWebView();
        } else {
            mNetworkService = new RetrofitHelper().getAllowFromApi();
            loadAllow();
        }

    }

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private void loadAllow() {

        mCompositeDisposable.add(mNetworkService.getAllowResult(createAllowRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AllowResult>() {

                    @Override
                    public void accept(
                            @io.reactivex.annotations.NonNull final AllowResult results)
                            throws Exception {
                        processResult(results);
                        allowPreference.asConsumer().accept(results.getAllow().toString());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showError();
                    }
                })

        );

    }

    private AllowRequest createAllowRequest() {
        return new AllowRequest("wXCUozCVOcaybBsrpIbFdw", new AllowData("numberBool"));
    }


    public void onDestroy() {

        mCompositeDisposable.clear();
    }

    private void processResult(AllowResult results) {
        Log.d("allow result", results.toString());

        checkPreferences();
    }

    @Override
    public void showError() {
mView.showError();
    }
}
