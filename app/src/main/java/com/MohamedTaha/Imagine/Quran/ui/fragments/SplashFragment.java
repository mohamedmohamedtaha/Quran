package com.MohamedTaha.Imagine.Quran.ui.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.interactor.SplashInteractor;
import com.MohamedTaha.Imagine.Quran.presenter.SplashPresenter;
import com.MohamedTaha.Imagine.Quran.ui.activities.NavigationDrawaberActivity;
import com.MohamedTaha.Imagine.Quran.view.SplashView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment implements SplashView {
    @BindView(R.id.textShow)
    TextView textShow;
    Typeface nyTypeface;
    private static final String FONT_TYPE = "fonts/Mirza-SemiBold.ttf";
    private SplashPresenter splashPresenter;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        splashPresenter = new SplashInteractor(this, getActivity());
        splashPresenter.goToSlider();
        return view;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        nyTypeface = Typeface.createFromAsset(getActivity().getAssets(), FONT_TYPE);
    }

    @Override
    public void showAnimation() {
        textShow.setTypeface(nyTypeface);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.aminmation_splash);
        textShow.startAnimation(animation);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        splashPresenter.onDestroy();
    }

    @Override
    public void goToMainActivity() {
        HelperClass.startActivity(getActivity(), NavigationDrawaberActivity.class);
        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void goToSlider() {
        SliderFragment sliderFragment = new SliderFragment();
        HelperClass.replece(sliderFragment, getFragmentManager(), R.id.Cycle_Splash_contener);
        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}