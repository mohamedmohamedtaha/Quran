package com.MohamedTaha.Imagine.Quran.mvp.interactor;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.PartsFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.mvp.view.PartsFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.Quran.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.Quran.helper.Images.getPositionForNameParts;

public class PartsFragmentsInteractor implements PartsFragmentPresenter {
    private PartsFragmentView partsFragmentView;
    private String[] name_parts;
    private Context context;
    private List<ModelSora> name_part_list = new ArrayList<>();
    private Subscription subscription_name_sora;
    private Subscription subscriptionImages;


    public PartsFragmentsInteractor(PartsFragmentView partsFragmentView, FragmentActivity fragmentActivity) {
        this.context = fragmentActivity;
        this.partsFragmentView = partsFragmentView;

    }

    @Override
    public void getAllImages() {
        if (partsFragmentView != null) {
            partsFragmentView.showProgress();
            Observable<List<Integer>> modelAzkarObservable = Observable.fromCallable(new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    return addImagesList();
                }
            });
            subscriptionImages = modelAzkarObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Integer>>() {
                        @Override
                        public void onCompleted() {
                            if (partsFragmentView != null) {
                                partsFragmentView.hideProgress();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (partsFragmentView != null) {
                                partsFragmentView.hideProgress();
                            }
                        }

                        @Override
                        public void onNext(List<Integer> integers) {
                            if (partsFragmentView != null) {
                                partsFragmentView.showAllImages(integers);
                            }
                        }
                    });
        }
    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        getPositionForNameParts(position, bundle);
    }

    @Override
    public void getAllPartSoura() {
        Observable<List<ModelSora>> modelAzkarObservable = Observable.fromCallable(new Callable<List<ModelSora>>() {
            @Override
            public List<ModelSora> call() throws Exception {
                try {
                    if (partsFragmentView != null) {
                        name_parts = context.getResources().getStringArray(R.array.allParts);
                        for (int i = 0; i < name_parts.length; i++) {
                            ModelSora name_Sroa_local = new ModelSora();
                            name_Sroa_local.setName_part(name_parts[i]);
                            name_Sroa_local.setPosition(i);
                            name_part_list.add(name_Sroa_local);
                        }
                    }
                } catch (Exception e) {
                }
                return name_part_list;
            }
        });
        subscription_name_sora = modelAzkarObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ModelSora>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<ModelSora> modelSoras) {
                        if (partsFragmentView != null) {
                            partsFragmentView.showAllINamePart(modelSoras);
                            partsFragmentView.showAnimation();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        this.partsFragmentView = null;
        if (subscription_name_sora != null && !subscription_name_sora.isUnsubscribed()) {
            subscription_name_sora.unsubscribe();
        }
        if (subscriptionImages != null && !subscriptionImages.isUnsubscribed()) {
            subscriptionImages.unsubscribe();
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
                partsFragmentView.showAfterSearch();
                partsFragmentView.thereData();
            }
        });
    }

    @Override
    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_part) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelSora> stringList = new ArrayList<>();
                    for (ModelSora item : name_part) {
                        if (item.getName_part().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        partsFragmentView.showAfterQueryText(stringList);
                        partsFragmentView.thereData();
                    } else {
                        partsFragmentView.isEmpty();
                    }

                } else {
                    partsFragmentView.thereData();
                    partsFragmentView.showAllINamePart(name_part);
                }
                return false;
            }
        });
    }
}