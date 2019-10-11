package com.MohamedTaha.Imagine.Quran.interactor;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.presenter.GridViewFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.view.GridViewFragmentView;
import com.MohamedTaha.Imagine.Quran.viewmodel.ImagesViewHolder;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


public class GridViewFragmentInteractor implements GridViewFragmentPresenter {
    public static final String SAVE_POSITION = "save_position";

    private GridViewFragmentView fragmentView;
    private ImagesViewHolder imagesViewHolder;
    private FragmentActivity activity;

    private float width;
    int count = 1;

    public GridViewFragmentInteractor(GridViewFragmentView fragmentView, FragmentActivity context) {
        this.fragmentView = fragmentView;
        activity = context;
        imagesViewHolder = ViewModelProviders.of(context).get(ImagesViewHolder.class);
    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        switch (position) {
            case 0:
            case 1:
                bundle.putInt(SAVE_POSITION, position);
                break;
            case 2:
                bundle.putInt(SAVE_POSITION, 50);
                break;
            case 3:
                bundle.putInt(SAVE_POSITION, 77);
                break;
            case 4:
                bundle.putInt(SAVE_POSITION, 105);
                break;
            case 5:
                bundle.putInt(SAVE_POSITION, 127);
                break;
            case 6:
                bundle.putInt(SAVE_POSITION, 150);
                break;
            case 7:
                bundle.putInt(SAVE_POSITION, 176);
                break;
            case 8:
                bundle.putInt(SAVE_POSITION, 186);
                break;
            case 9:
                bundle.putInt(SAVE_POSITION, 207);
                break;
            case 10:
                bundle.putInt(SAVE_POSITION, 220);
                break;
            case 11:
                bundle.putInt(SAVE_POSITION, 235);
                break;
            case 12:
                bundle.putInt(SAVE_POSITION, 248);
                break;
            case 13:
                bundle.putInt(SAVE_POSITION, 254);
                break;

            //To Ebrahem
            default:
        }
    }

    @Override
    public void getAllNameSour() {
        imagesViewHolder.getAllNameSwar().observe(activity, new Observer<List<ModelSora>>() {
            @Override
            public void onChanged(List<ModelSora> strings) {
                if (fragmentView != null) {
                    fragmentView.showAllINameSour(strings);
                    fragmentView.hideProgress();
                    fragmentView.thereData();
                    fragmentView.showAnimation();
                }
            }
        });

    }

    @Override
    public void getAllImages() {
        imagesViewHolder.getAllImages().observe((LifecycleOwner) activity, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                if (fragmentView != null) {
                    fragmentView.showAllImages(integers);
                }
            }
        });

    }

    @Override
    public void getDisplayMetrics(GridView GridViewActivityGVShowImages, int imageDiemn) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels / displayMetrics.density;
        count = (int) width / imageDiemn;
        GridViewActivityGVShowImages.setNumColumns(count);

    }

    @Override
    public void onDestroy() {
        fragmentView = null;

    }

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                fragmentView.showAfterSearch();
                fragmentView.thereData();
                // fragmentView.hideProgress();
            }

        });

    }

    @Override
    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelSora> stringList = new ArrayList<>();
                    for (ModelSora item : name_swar) {
                        if (item.getName_sora().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        fragmentView.showAfterQueryText(stringList);
                        fragmentView.thereData();
                    } else {
                        fragmentView.isEmpty();
                    }

                } else {
                    fragmentView.thereData();
                    fragmentView.showAllINameSour(name_swar);
                }
                return false;
            }
        });
    }
}
