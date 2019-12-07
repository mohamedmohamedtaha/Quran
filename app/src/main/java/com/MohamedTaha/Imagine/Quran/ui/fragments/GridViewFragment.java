package com.MohamedTaha.Imagine.Quran.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.Quran.Adapter.AdapterGridView;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.mvp.interactor.GridViewFragmentInteractor;
import com.MohamedTaha.Imagine.Quran.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.GridViewFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.mvp.view.GridViewFragmentView;
import com.MohamedTaha.Imagine.Quran.ui.activities.SwipePagesActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.Quran.ui.activities.NavigationDrawaberActivity.searchView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridViewFragment extends Fragment implements GridViewFragmentView {
    @BindView(R.id.GridViewActivity_TV_No_Data)
    TextView GridViewActivityTVNoData;
    @BindView(R.id.GridViewActivity_GV_Show_Images)
    RecyclerView GridViewActivityGVShowImages;
    @BindView(R.id.GridViewActivity_ProgressBar)
    ProgressBar GridViewActivityProgressBar;
    public static final String SAVE_IMAGES = "save_images";
    public static final String SAVE_STATE = "save_state";
    public static final String SAVE_Position_Notification = "save_position_notification";

    Bundle bundle;
    private List<ModelSora> name_swar;
    private List<Integer> integers_bundle;
    private AdapterGridView adapterGridView;
    private GridViewFragmentPresenter presenter;

    public GridViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_grid_view, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle(getString(R.string.readswar));
        bundle = new Bundle();
        presenter = new GridViewFragmentInteractor(this, getActivity());
        presenter.getAllNameSour();
        presenter.getAllImages();
        presenter.setOnSearchView(searchView);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        GridViewActivityGVShowImages.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showAfterSearch() {
        adapterGridView = new AdapterGridView(name_swar, false, new AdapterGridView.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_swar.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        GridViewActivityGVShowImages.setAdapter(adapterGridView);
    }

    @Override
    public void showAfterQueryText(List<ModelSora> stringList) {
        name_swar = stringList;
        adapterGridView = new AdapterGridView(stringList, false, new AdapterGridView.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_swar.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        GridViewActivityGVShowImages.setAdapter(adapterGridView);
    }

    @Override
    public void hideProgress() {
        GridViewActivityProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        GridViewActivityProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAllINameSour(List<ModelSora> strings) {
        name_swar = strings;
        adapterGridView = new AdapterGridView(name_swar, false, new AdapterGridView.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_swar.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        GridViewActivityGVShowImages.setAdapter(adapterGridView);
        adapterGridView.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryText(searchView, name_swar);
    }

    @Override
    public void showAllImages(List<Integer> integers) {
        integers_bundle = integers;
    }

    @Override
    public void isEmpty() {
        GridViewActivityTVNoData.setVisibility(View.VISIBLE);
        GridViewActivityGVShowImages.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        GridViewActivityTVNoData.setVisibility(View.GONE);
        GridViewActivityGVShowImages.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAnimation() {
        //For animation
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_fall_dwon);
        GridViewActivityGVShowImages.setLayoutAnimation(controller);
        GridViewActivityGVShowImages.scheduleLayoutAnimation();
    }
}