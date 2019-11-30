package com.MohamedTaha.Imagine.Quran.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.Quran.Adapter.ImageAdapter;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.interactor.ListSoundReaderInteractor;
import com.MohamedTaha.Imagine.Quran.model.ImageModel;
import com.MohamedTaha.Imagine.Quran.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.Quran.view.ListSoundReaderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.MohamedTaha.Imagine.Quran.ui.activities.NavigationDrawaberActivity.searchView;

/**
 * Created by MANASATT on 20/08/17.
 */


public class FragmentSound extends Fragment implements ListSoundReaderView {
    @BindView(R.id.Fragment_Grid_View_GridView)
    GridView FragmentGridViewGridView;
    @BindView(R.id.Fragment_Grid_View_TV_No_Data)
    TextView FragmentGridViewTVNoData;
    Unbinder unbinder;
    ImageAdapter imageAdapter;
    @BindView(R.id.FragmentGridView_ProgressBar)
    ProgressBar FragmentGridViewProgressBar;
    private List<ImageModel> imageModel;
    private ListSoundReaderPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_grid_view, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        getActivity().setTitle(getString(R.string.listen_swar));
        presenter = new ListSoundReaderInteractor(this, getActivity());
        presenter.getAllData();
        presenter.setOnSearchViewListener(searchView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {

        FragmentGridViewProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        FragmentGridViewProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void noData() {
        FragmentGridViewTVNoData.setVisibility(View.VISIBLE);
        FragmentGridViewGridView.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        FragmentGridViewTVNoData.setVisibility(View.GONE);
        FragmentGridViewGridView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAllData(List<ImageModel> imageModels) {
        imageModel = imageModels;
        imageAdapter = new ImageAdapter(getActivity(), imageModel, getActivity());
        FragmentGridViewGridView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryTextListener(searchView, imageModel);
    }

    @Override
    public void showAfterSearch() {
        imageAdapter = new ImageAdapter(getActivity(), imageModel, getActivity());
        FragmentGridViewGridView.setAdapter(imageAdapter);
    }

    @Override
    public void showAfterQueryText(List<ImageModel> lstFound) {
        imageAdapter = new ImageAdapter(getActivity(), lstFound, getActivity());
        FragmentGridViewGridView.setAdapter(imageAdapter);
    }

    @Override
    public void showAnimation() {
        //For animation
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_fall_dwon);
        FragmentGridViewGridView.setLayoutAnimation(controller);
        FragmentGridViewGridView.scheduleLayoutAnimation();
    }
}