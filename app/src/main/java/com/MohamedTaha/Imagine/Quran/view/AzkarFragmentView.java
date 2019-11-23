package com.MohamedTaha.Imagine.Quran.view;

import com.MohamedTaha.Imagine.Quran.model.ModelAzkar;

import java.util.List;

public interface AzkarFragmentView {
    void showAfterQueryText(List<ModelAzkar> stringList);
    void isEmpty();
    void thereData();
    void showAllINameAzkar(List<ModelAzkar> strings);
    void showAnimation();
    void showAfterSearch();

}
