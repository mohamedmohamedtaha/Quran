package com.MohamedTaha.Imagine.Quran.interactor;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ImageModel;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.Quran.view.ListSoundReaderView;
import com.MohamedTaha.Imagine.Quran.viewmodel.SoundViewHolder;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListSoundReaderInteractor implements ListSoundReaderPresenter {
    private ListSoundReaderView listSoundReaderView;
    private FragmentActivity context;
    private List<ImageModel> imageModels = new ArrayList<>();


    public ListSoundReaderInteractor(ListSoundReaderView listSoundReaderView, FragmentActivity context) {
        this.listSoundReaderView = listSoundReaderView;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        listSoundReaderView = null;
    }

    private void getData() {
        imageModels = new ArrayList<>();
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elsodes), R.drawable.elsodes, context.getString(R.string.hafs_an_asem), 0));
        imageModels.add(new ImageModel(context.getString(R.string.adel_elkalbany), R.drawable.elklbany, context.getString(R.string.hafs_an_asem), 1));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_elmenshawyH), R.drawable.elmenshawy, context.getString(R.string.hafs_an_asem), 2));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_elmenshawyM), R.drawable.elmenshawy, context.getString(R.string.ekmoshaf_elmogawad), 3));
        imageModels.add(new ImageModel(context.getString(R.string.abdelbaset), R.drawable.abdeelbaset, context.getString(R.string.ekmoshaf_elmogawad), 4));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryH), R.drawable.elhosary, context.getString(R.string.hafs_an_asem), 5));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryM), R.drawable.elhosary, context.getString(R.string.ekmoshaf_elmogawad), 6));
        imageModels.add(new ImageModel(context.getString(R.string.maher_elmeaqly), R.drawable.almaqely, context.getString(R.string.hafs_an_asem), 7));
        imageModels.add(new ImageModel(context.getString(R.string.mshary_elafasy), R.drawable.msharyelafasy, context.getString(R.string.hafs_an_asem), 8));
        imageModels.add(new ImageModel(context.getString(R.string.soud_elsherem), R.drawable.sherem, context.getString(R.string.hafs_an_asem), 9));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_eltblawy), R.drawable.eltblawy, context.getString(R.string.hafs_an_asem), 10));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_eldosary), R.drawable.eldosary, context.getString(R.string.hafs_an_asem), 11));
        imageModels.add(new ImageModel(context.getString(R.string.abdellah_elgeheny), R.drawable.elgeheny, context.getString(R.string.hafs_an_asem), 12));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_gebrer), R.drawable.mohamedgbrer, context.getString(R.string.hafs_an_asem), 13));
        imageModels.add(new ImageModel(context.getString(R.string.naser_elqatamy), R.drawable.naserelqatamy, context.getString(R.string.hafs_an_asem), 14));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_elagamy), R.drawable.elagamy, context.getString(R.string.hafs_an_asem), 15));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elbana), R.drawable.elbana, context.getString(R.string.hafs_an_asem), 16));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_elqarashy), R.drawable.elqarashy, context.getString(R.string.hafs_an_asem), 17));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elqasem), R.drawable.elqasem, context.getString(R.string.hafs_an_asem), 18));
        imageModels.add(new ImageModel(context.getString(R.string.slah_elbadry), R.drawable.bder, context.getString(R.string.hafs_an_asem), 19));
    }

    @Override
    public void getAllData() {

        listSoundReaderView.showProgress();
        getData();
        Observable<List<ImageModel>> modelAzkarObservable = Observable.just(imageModels)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        modelAzkarObservable.subscribe(new Subscriber<List<ImageModel>>() {
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