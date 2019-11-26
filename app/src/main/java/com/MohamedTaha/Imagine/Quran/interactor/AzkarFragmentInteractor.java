package com.MohamedTaha.Imagine.Quran.interactor;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ModelAzkar;
import com.MohamedTaha.Imagine.Quran.presenter.AzkarFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.view.AzkarFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AzkarFragmentInteractor implements AzkarFragmentPresenter {
    private AzkarFragmentView azkarFragmentView;
    private List<ModelAzkar> modelAzkar;
    private Context context;

    String[] array_azkar;
    String[] array_describe_azkar;

    public AzkarFragmentInteractor(AzkarFragmentView azkarFragmentView, Context context) {
        this.azkarFragmentView = azkarFragmentView;
        this.context = context;
    }

    @Override
    public void getAllData() {
        azkarFragmentView.showProgress();
        Observable<List<ModelAzkar>> modelAzkarObservable = Observable.create(new Observable.OnSubscribe<List<ModelAzkar>>() {
            @Override
            public void call(Subscriber<? super List<ModelAzkar>> subscriber) {
                try {
                    modelAzkar = new ArrayList<>();
                    array_azkar = context.getResources().getStringArray(R.array.azkar);
                    array_describe_azkar = context.getResources().getStringArray(R.array.describe_azkar);
                    for (int i = 0; i < array_azkar.length; i++) {
                        ModelAzkar modelAzkarLocal = new ModelAzkar();
                        modelAzkarLocal.setName_azkar(array_azkar[i]);
                        modelAzkarLocal.setDescribe_azkar(array_describe_azkar[i]);
                        modelAzkar.add(modelAzkarLocal);
                    }
                    subscriber.onNext(modelAzkar);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List<ModelAzkar>> observer = new Observer<List<ModelAzkar>>() {
            @Override
            public void onCompleted() {
                if (azkarFragmentView != null) {
                    azkarFragmentView.hideProgress();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (azkarFragmentView != null) {
                    azkarFragmentView.hideProgress();
                }
            }

            @Override
            public void onNext(List<ModelAzkar> modelAzkars) {
                if (azkarFragmentView != null) {
                    azkarFragmentView.showAllINameAzkar(modelAzkars);
                    azkarFragmentView.showAnimation();
                }
            }
        };
        modelAzkarObservable.subscribe(observer);
    }

    @Override
    public void onDestroy() {
        this.azkarFragmentView = null;
    }

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                azkarFragmentView.showAfterSearch();
                azkarFragmentView.thereData();
            }

        });
    }

    @Override
    public void setOnQueryTextForAzkar(MaterialSearchView materialSearchView, List<ModelAzkar> name_azkar) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelAzkar> stringList = new ArrayList<>();
                    for (ModelAzkar item : name_azkar) {
                        if (item.getName_azkar().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        azkarFragmentView.showAfterQueryText(stringList);
                        azkarFragmentView.thereData();
                    } else {
                        azkarFragmentView.isEmpty();
                    }

                } else {
                    azkarFragmentView.thereData();
                    azkarFragmentView.showAllINameAzkar(name_azkar);
                }
                return false;
            }
        });
    }
}
