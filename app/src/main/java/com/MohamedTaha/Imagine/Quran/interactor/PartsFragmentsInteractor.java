package com.MohamedTaha.Imagine.Quran.interactor;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.presenter.PartsFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.view.PartsFragmentView;
import com.MohamedTaha.Imagine.Quran.viewmodel.ImagesViewHolder;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class PartsFragmentsInteractor implements PartsFragmentPresenter {
    private PartsFragmentView partsFragmentView;
    private String []name_parts;
    private Context context;
    private ImagesViewHolder imagesViewHolder;

    private List<ModelSora> name_part_list = new ArrayList<>();
    public static final String SAVE_POSITION = "save_position";

    public PartsFragmentsInteractor(PartsFragmentView partsFragmentView, FragmentActivity fragmentActivity) {
        this.context = fragmentActivity;
        this.partsFragmentView = partsFragmentView;
        imagesViewHolder = ViewModelProviders.of(fragmentActivity).get(ImagesViewHolder.class);

    }

    @Override
    public void getAllImages() {
        imagesViewHolder.getAllImages().observe((LifecycleOwner) context, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                if (partsFragmentView != null) {
                    partsFragmentView.showAllImages(integers);
                }
            }
        });
    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        switch (position) {
            case 0:
                bundle.putInt(SAVE_POSITION, 0);
                break;
            case 1:
                bundle.putInt(SAVE_POSITION, 21);
                break;
            case 2:
                bundle.putInt(SAVE_POSITION, 41);
                break;
            case 3:
                bundle.putInt(SAVE_POSITION, 61);
                break;
            case 4:
                bundle.putInt(SAVE_POSITION, 81);
                break;
            case 5:
                bundle.putInt(SAVE_POSITION, 101);
                break;
            case 6:
                bundle.putInt(SAVE_POSITION, 120);
                break;
            case 7:
                bundle.putInt(SAVE_POSITION, 141);
                break;
            case 8:
                bundle.putInt(SAVE_POSITION, 161);
                break;
            case 9:
                bundle.putInt(SAVE_POSITION, 181);
                break;
            case 10:
                bundle.putInt(SAVE_POSITION, 200);
                break;
            case 11:
                bundle.putInt(SAVE_POSITION, 221);
                break;
            case 12:
                bundle.putInt(SAVE_POSITION, 240);
                break;
            case 13:
                bundle.putInt(SAVE_POSITION, 261);
                break;
            case 14:
                bundle.putInt(SAVE_POSITION, 281);
                break;
            case 15:
                bundle.putInt(SAVE_POSITION, 301);
                break;
            case 16:
                bundle.putInt(SAVE_POSITION, 321);
                break;
            case 17:
                bundle.putInt(SAVE_POSITION, 341);
                break;
            case 18:
                bundle.putInt(SAVE_POSITION, 361);
                break;
            case 19:
                bundle.putInt(SAVE_POSITION, 381);
                break;
            case 20:
                bundle.putInt(SAVE_POSITION, 401);
                break;
            case 21:
                bundle.putInt(SAVE_POSITION, 421);
                break;
            case 22:
                bundle.putInt(SAVE_POSITION, 441);
                break;
            case 23:
                bundle.putInt(SAVE_POSITION, 461);
                break;
            case 24:
                bundle.putInt(SAVE_POSITION, 481);
                break;
            case 25:
                bundle.putInt(SAVE_POSITION, 501);
                break;
            case 26:
                bundle.putInt(SAVE_POSITION, 521);
                break;
            case 27:
                bundle.putInt(SAVE_POSITION, 541);
                break;
            case 28:
                bundle.putInt(SAVE_POSITION, 561);
                break;
            case 29:
                bundle.putInt(SAVE_POSITION, 581);
                break;
            case 30:
                bundle.putInt(SAVE_POSITION, 604);
                break;
    }}

    @Override
    public void getAllPartSoura() {
        if (partsFragmentView != null){
            name_parts = context.getResources().getStringArray(R.array.allParts);
            for (int i = 0; i < name_parts.length; i++) {
                ModelSora name_Sroa_local = new ModelSora();
                name_Sroa_local.setName_part(name_parts[i]);
                name_Sroa_local.setPosition(i);
                name_part_list.add(name_Sroa_local);
            }
            partsFragmentView.showAllINamePart(name_part_list);
            partsFragmentView.showAnimation();

        }
    }

    @Override
    public void onDestroy() {
        this.partsFragmentView = null;

    }

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                partsFragmentView.showAfterSearch();
                partsFragmentView.thereData();
                // fragmentView.hideProgress();
            }

        });
    }

    @Override
    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_part) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelSora> stringList = new ArrayList<>();
                    for (ModelSora item : name_part) {
                        if (item.getName_part().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        partsFragmentView.showAfterQueryText(stringList);
                        partsFragmentView.thereData();
                    } else {
                        partsFragmentView.isEmpty();
                    }

                } else {
                    partsFragmentView.thereData();
                    partsFragmentView.showAllINamePart(name_part);
                }
                return false;
            }
        });
    }
}
