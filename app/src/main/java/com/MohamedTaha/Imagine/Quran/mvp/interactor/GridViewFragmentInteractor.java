package com.MohamedTaha.Imagine.Quran.mvp.interactor;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.GridViewFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.mvp.view.GridViewFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.Quran.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.Quran.helper.Images.getPositionForNameSwars;

public class GridViewFragmentInteractor implements GridViewFragmentPresenter {
    private GridViewFragmentView fragmentView;
    private FragmentActivity activity;
    private List<ModelSora> name_Sroa;
    private String[] a = null;
    private String[] nzol_elsora = null;
    private Subscription subscription;
    private Subscription subscriptionForNameSora;

    public GridViewFragmentInteractor(GridViewFragmentView fragmentView, FragmentActivity context) {
        this.fragmentView = fragmentView;
        activity = context;
    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        getPositionForNameSwars(position, bundle);
    }

    @Override
    public void getAllNameSour() {
        Observable<List<ModelSora>> observable = Observable.fromCallable(new Callable<List<ModelSora>>() {
            @Override
            public List<ModelSora> call() throws Exception {
                name_Sroa = new ArrayList<>();
                a = activity.getResources().getStringArray(R.array.name_allSwar_read);
                nzol_elsora = activity.getResources().getStringArray(R.array.nzolElswar);
                for (int i = 0; i < a.length; i++) {
                    ModelSora name_Sroa_local = new ModelSora();
                    name_Sroa_local.setName_sora(a[i]);
                    name_Sroa_local.setPosition(i);
                    name_Sroa_local.setNzol_elsora(nzol_elsora[i]);
                    name_Sroa.add(name_Sroa_local);
                }
                return name_Sroa;
            }
        });
        subscriptionForNameSora = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ModelSora>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<ModelSora> modelSoras) {
                        if (fragmentView != null) {
                            fragmentView.showAllINameSour(name_Sroa);
                            fragmentView.thereData();
                            fragmentView.showAnimation();
                            Log.i("SetNameSora", "onNext");

                        }
                    }
                });
    }

    @Override
    public void getAllImages() {
        fragmentView.showProgress();
        Observable<List<Integer>> modelAzkarObservable = Observable.fromCallable(new Callable<List<Integer>>() {
            @Override
            public List<Integer> call() throws Exception {
                return addImagesList();
            }
        });
        subscription = modelAzkarObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        if (fragmentView != null) {
                            fragmentView.hideProgress();
                        }
                        Log.i("addImages", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (fragmentView != null) {
                            fragmentView.hideProgress();
                        }
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        if (fragmentView != null) {
                            fragmentView.showAllImages(integers);
                            Log.i("addImages", "onNext");
                        }
                    }
                });

    }


    @Override
    public void onDestroy() {
        fragmentView = null;
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        if (subscriptionForNameSora != null && !subscriptionForNameSora.isUnsubscribed()) {
            subscriptionForNameSora.unsubscribe();
        }
    }

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                fragmentView.showAfterSearch();
                fragmentView.thereData();
                // fragmentView.hideProgress();
            }

        });

    }

    @Override
    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelSora> stringList = new ArrayList<>();
                    for (ModelSora item : name_swar) {
                        if (item.getName_sora().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        fragmentView.showAfterQueryText(stringList);
                        fragmentView.thereData();
                    } else {
                        fragmentView.isEmpty();
                    }

                } else {
                    fragmentView.thereData();
                    fragmentView.showAllINameSour(name_swar);
                }
                return false;
            }
        });
    }
}