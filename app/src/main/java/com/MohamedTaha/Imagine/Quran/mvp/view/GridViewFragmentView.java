package com.MohamedTaha.Imagine.Quran.mvp.view;

import com.MohamedTaha.Imagine.Quran.mvp.model.ModelSora;

import java.util.List;

public interface GridViewFragmentView {
    void showAfterSearch();
    void showAfterQueryText(List<ModelSora> stringList);
    void hideProgress();
    void showProgress();
    void isEmpty();
    void thereData();
    void showAllINameSour(List<ModelSora> strings);
    //void showAllImages(List<ModelSora>  integers);
    void showAllImages(List<Integer>  integers);

    void showAnimation();

}
