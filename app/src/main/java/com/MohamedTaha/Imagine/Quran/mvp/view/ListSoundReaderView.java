package com.MohamedTaha.Imagine.Quran.mvp.view;

import com.MohamedTaha.Imagine.Quran.mvp.model.ImageModel;

import java.util.List;

public interface ListSoundReaderView {
    void showProgress();
    void hideProgress();
    void noData();
    void thereData();
    void showAllData(List<ImageModel> imageModels);
    void showAfterSearch();
    void showAfterQueryText(List<ImageModel> lstFound);
    void showAnimation();
}
