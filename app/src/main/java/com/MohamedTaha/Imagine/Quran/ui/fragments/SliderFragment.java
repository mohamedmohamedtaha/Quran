package com.MohamedTaha.Imagine.Quran.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.Quran.Adapter.AdapterSlider;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.interactor.SliderInteractor;
import com.MohamedTaha.Imagine.Quran.presenter.SliderPresenter;
import com.MohamedTaha.Imagine.Quran.ui.activities.NavigationDrawaberActivity;
import com.MohamedTaha.Imagine.Quran.view.SliderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SliderFragment extends Fragment implements SliderView {
    @BindView(R.id.SliderFragment_ViewPager)
    ViewPager SliderFragmentViewPager;
    @BindView(R.id.SliderFragment_DotLayout)
    LinearLayout SliderFragmentDotLayout;
    @BindView(R.id.SliderFragment_Skip)
    Button SliderFragmentSkip;
    private int[] layouts;
    private AdapterSlider adapterSlider;
    private SliderPresenter presenter;

    public SliderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        ButterKnife.bind(this, view);
        layouts = new int[]{R.drawable.abdeelbaset, R.drawable.abdeelbaset, R.drawable.abdeelbaset};
        adapterSlider = new AdapterSlider(getActivity(), layouts);
        presenter = new SliderInteractor(this, getActivity());
        SliderFragmentViewPager.setAdapter(adapterSlider);
        SliderFragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                presenter.swipePage(position, SliderFragmentDotLayout, getActivity(), layouts);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        presenter.swipePage(0, SliderFragmentDotLayout, getActivity(), layouts);
        return view;
    }

    @OnClick(R.id.SliderFragment_Skip)
    public void onViewClicked() {
        presenter.skip();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void skip() {
        HelperClass.startActivity(getActivity(), NavigationDrawaberActivity.class);
        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void changeTextToNext() {
        SliderFragmentSkip.setText(getString(R.string.next));
    }

    @Override
    public void changeTextToSkip() {
        SliderFragmentSkip.setText(getString(R.string.skip));
    }
}
