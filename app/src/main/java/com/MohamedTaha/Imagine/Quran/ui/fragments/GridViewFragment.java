package com.MohamedTaha.Imagine.Quran.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.Quran.Adapter.AdapterGridView;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.interactor.GridViewFragmentInteractor;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.presenter.GridViewFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.ui.activities.SwipePagesActivity;
import com.MohamedTaha.Imagine.Quran.view.GridViewFragmentView;

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
    ListView GridViewActivityGVShowImages;
    @BindView(R.id.GridViewActivity_ProgressBar)
    ProgressBar GridViewActivityProgressBar;
    public static final String SAVE_IMAGES = "save_images";
    public static final String SAVE_STATE = "save_state";
    public static final String SAVE_Position_Notification = "save_position_notification";

    Bundle bundle;
    private List<ModelSora> name_swar;
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
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showAfterSearch() {
        adapterGridView = new AdapterGridView(getActivity(), name_swar, false);
        GridViewActivityGVShowImages.setAdapter(adapterGridView);
    }

    @Override
    public void showAfterQueryText(List<ModelSora> stringList) {
        name_swar = stringList;
        adapterGridView = new AdapterGridView(getActivity(), stringList, false);
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
        adapterGridView = new AdapterGridView(getActivity(), name_swar, false);
        GridViewActivityGVShowImages.setAdapter(adapterGridView);
        adapterGridView.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryText(searchView, name_swar);
    }

    @Override
    public void showAllImages(List<Integer> integers) {
        GridViewActivityGVShowImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.getPosition(name_swar.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
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