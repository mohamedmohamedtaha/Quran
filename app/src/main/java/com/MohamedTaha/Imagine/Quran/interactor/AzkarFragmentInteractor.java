package com.MohamedTaha.Imagine.Quran.interactor;

import android.content.Context;
import android.widget.Toast;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ModelAzkar;
import com.MohamedTaha.Imagine.Quran.presenter.AzkarFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.view.AzkarFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class AzkarFragmentInteractor implements AzkarFragmentPresenter {
    private AzkarFragmentView azkarFragmentView;
    private List<ModelAzkar> modelAzkar;
    private Context context;

    String[] array_azkar;
    String[] array_describe_azkar;

    public AzkarFragmentInteractor(AzkarFragmentView azkarFragmentView, Context context) {
        this.azkarFragmentView = azkarFragmentView;
        this.context = context;
    }

    @Override
    public void getAllData() {
        modelAzkar = new ArrayList<>();
        if (azkarFragmentView != null) {
            array_azkar = context.getResources().getStringArray(R.array.azkar);
            array_describe_azkar = context.getResources().getStringArray(R.array.describe_azkar);
            for (int i = 0; i < array_azkar.length; i++) {
                ModelAzkar modelAzkarLocal = new ModelAzkar();
                modelAzkarLocal.setName_azkar(array_azkar[i]);
                modelAzkarLocal.setDescribe_azkar(array_describe_azkar[i]);
                modelAzkar.add(modelAzkarLocal);
            }
            azkarFragmentView.showAllINameAzkar(modelAzkar);
            azkarFragmentView.showAnimation();
        }else {
            Toast.makeText(context, "Null", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onDestroy() {
        this.azkarFragmentView = null;
    }

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                azkarFragmentView.showAfterSearch();
                azkarFragmentView.thereData();
                // fragmentView.hideProgress();
            }

        });
    }

    @Override
    public void setOnQueryTextForAzkar(MaterialSearchView materialSearchView, List<ModelAzkar> name_azkar) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelAzkar> stringList = new ArrayList<>();
                    for (ModelAzkar item : name_azkar) {
                        if (item.getName_azkar().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        azkarFragmentView.showAfterQueryText(stringList);
                        azkarFragmentView.thereData();
                    } else {
                        azkarFragmentView.isEmpty();
                    }

                } else {
                    azkarFragmentView.thereData();
                    azkarFragmentView.showAllINameAzkar(name_azkar);
                }
                return false;
            }
        });
    }
}
