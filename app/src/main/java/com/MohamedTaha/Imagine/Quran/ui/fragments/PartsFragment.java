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
import com.MohamedTaha.Imagine.Quran.mvp.interactor.PartsFragmentsInteractor;
import com.MohamedTaha.Imagine.Quran.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.PartsFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.mvp.view.PartsFragmentView;
import com.MohamedTaha.Imagine.Quran.ui.activities.SwipePagesActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.Quran.ui.activities.NavigationDrawaberActivity.searchView;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment.SAVE_STATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartsFragment extends Fragment implements PartsFragmentView {
    public static final String SAVE_IMAGES = "save_images";
    Bundle bundle;
    @BindView(R.id.PartsFragment_GV_Show_Images)
    RecyclerView PartsFragmentGVShowImages;
    @BindView(R.id.PartsFragment_TV_No_Data)
    TextView PartsFragmentTVNoData;
    @BindView(R.id.PartsFragment_ProgressBar)
    ProgressBar PartsFragmentProgressBar;
    private List<ModelSora> name_part;
    private AdapterGridView adapterNamePart;
    private PartsFragmentPresenter presenter;
    private List<Integer> integers_bundle;

    public PartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parts, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle(getString(R.string.read_parts));
        bundle = new Bundle();
        presenter = new PartsFragmentsInteractor(this, getActivity());
        presenter.getAllPartSoura();
        presenter.getAllImages();
        presenter.setOnSearchView(searchView);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        PartsFragmentGVShowImages.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showAfterSearch() {
        adapterNamePart = new AdapterGridView(name_part, true, new AdapterGridView.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_part.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        PartsFragmentGVShowImages.setAdapter(adapterNamePart);
    }

    @Override
    public void showAfterQueryText(List<ModelSora> stringList) {
        //name_part.clear();
        name_part = stringList;
        adapterNamePart = new AdapterGridView(stringList, true, new AdapterGridView.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_part.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        PartsFragmentGVShowImages.setAdapter(adapterNamePart);
    }

    @Override
    public void showAllImages(List<Integer> integers) {
        integers_bundle = integers;
    }

    @Override
    public void isEmpty() {
        PartsFragmentTVNoData.setVisibility(View.VISIBLE);
        PartsFragmentGVShowImages.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        PartsFragmentTVNoData.setVisibility(View.GONE);
        PartsFragmentGVShowImages.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        PartsFragmentProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        PartsFragmentProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAllINamePart(List<ModelSora> strings) {
        name_part = strings;
        adapterNamePart = new AdapterGridView(name_part, true, new AdapterGridView.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_part.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        PartsFragmentGVShowImages.setAdapter(adapterNamePart);
        adapterNamePart.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryText(searchView, name_part);
    }

    @Override
    public void showAnimation() {
        //For animation
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_fall_dwon);
        PartsFragmentGVShowImages.setLayoutAnimation(controller);
        PartsFragmentGVShowImages.scheduleLayoutAnimation();
    }
}