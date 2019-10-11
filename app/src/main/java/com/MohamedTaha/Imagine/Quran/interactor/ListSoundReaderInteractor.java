package com.MohamedTaha.Imagine.Quran.interactor;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MohamedTaha.Imagine.Quran.model.ImageModel;
import com.MohamedTaha.Imagine.Quran.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.Quran.view.ListSoundReaderView;
import com.MohamedTaha.Imagine.Quran.viewmodel.SoundViewHolder;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class ListSoundReaderInteractor implements ListSoundReaderPresenter {
    private ListSoundReaderView listSoundReaderView;
    private SoundViewHolder soundViewHolder;
    private FragmentActivity context;

    public ListSoundReaderInteractor(ListSoundReaderView listSoundReaderView, FragmentActivity context) {
        this.listSoundReaderView = listSoundReaderView;
        soundViewHolder = ViewModelProviders.of(context).get(SoundViewHolder.class);
        this.context = context;
    }

    @Override
    public void onDestroy() {
        listSoundReaderView = null;
    }

    @Override
    public void getAllData() {
        soundViewHolder.getAllImageModle().observe(context, new Observer<List<ImageModel>>() {
            @Override
            public void onChanged(List<ImageModel> imageModels) {
                if (listSoundReaderView != null){
                    //                listSoundReaderView.hideProgress();
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
                listSoundReaderView.hideProgress();
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
                        listSoundReaderView.hideProgress();
                    } else {
                        listSoundReaderView.showProgress();
                    }


                } else {
                    //If search is Null return default
                    listSoundReaderView.hideProgress();
                    listSoundReaderView.showAllData(imageModel);
                }
                return false;
            }
        });
    }
}