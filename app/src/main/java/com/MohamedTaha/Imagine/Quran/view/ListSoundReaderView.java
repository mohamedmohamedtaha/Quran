package com.MohamedTaha.Imagine.Quran.view;

import com.MohamedTaha.Imagine.Quran.model.ImageModel;

import java.util.List;

public interface ListSoundReaderView {
    void showProgress();
    void hideProgress();
    void showAllData(List<ImageModel> imageModels);
    void showAfterSearch();
    void showAfterQueryText(List<ImageModel> lstFound);
    void showAnimation();
}
