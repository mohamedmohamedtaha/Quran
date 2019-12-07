package com.MohamedTaha.Imagine.Quran.mvp.interactor;

import androidx.fragment.app.FragmentActivity;

import com.MohamedTaha.Imagine.Quran.mvp.model.ImageModel;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.Quran.mvp.view.ListSoundReaderView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.Quran.helper.Images.getData;

public class ListSoundReaderInteractor implements ListSoundReaderPresenter {
    private ListSoundReaderView listSoundReaderView;
    private FragmentActivity context;
    private Subscription subscription;


    public ListSoundReaderInteractor(ListSoundReaderView listSoundReaderView, FragmentActivity context) {
        this.listSoundReaderView = listSoundReaderView;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        listSoundReaderView = null;
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void getAllData() {

        listSoundReaderView.showProgress();

        Observable<List<ImageModel>> modelAzkarObservable = Observable.fromCallable(new Callable<List<ImageModel>>() {
            @Override
            public List<ImageModel> call() throws Exception {
                return getData(context);
            }
        });
        subscription = modelAzkarObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImageModel>>() {
                    @Override
                    public void onCompleted() {
                        if (listSoundReaderView != null) {
                            listSoundReaderView.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listSoundReaderView != null) {
                            listSoundReaderView.hideProgress();
                        }
                    }

                    @Override
                    public void onNext(List<ImageModel> imageModels) {
                        if (listSoundReaderView != null) {
                            listSoundReaderView.showAllData(imageModels);
                            listSoundReaderView.showAnimation();
                        }
                    }
                });
    }

    @Override
    public void setOnSearchViewListener(MaterialSearchView searchView) {
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                listSoundReaderView.showAfterSearch();
                listSoundReaderView.thereData();
            }
        });
    }

    @Override
    public void setOnQueryTextListener(MaterialSearchView searchView, List<ImageModel> imageModel) {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ImageModel> lstFound = new ArrayList<>();
                    for (ImageModel item : imageModel) {
                        if (item.getName_shekh().contains(newText))
                            lstFound.add(item);
                    }
                    if (!lstFound.isEmpty()) {
                        listSoundReaderView.showAfterQueryText(lstFound);
                        listSoundReaderView.thereData();

                    } else {
                        listSoundReaderView.noData();

                    }


                } else {
                    //If search is Null return default
                    listSoundReaderView.thereData();
                    listSoundReaderView.showAllData(imageModel);
                }
                return false;
            }
        });
    }
}