package com.MohamedTaha.Imagine.Quran.view;

import com.MohamedTaha.Imagine.Quran.model.ModelSora;

import java.util.List;

public interface PartsFragmentView {
    void showAfterSearch();
    void showAfterQueryText(List<ModelSora> stringList);
    void isEmpty();
    void thereData();
    void showAllINamePart(List<ModelSora> strings);
    void showAllImages(List<Integer> integers);
    void showAnimation();
}
