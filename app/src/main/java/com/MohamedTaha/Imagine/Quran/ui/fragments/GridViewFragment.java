package com.MohamedTaha.Imagine.Quran.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MohamedTaha.Imagine.Quran.Adapter.AdapterGridView;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.interactor.GridViewFragmentInteractor;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.presenter.GridViewFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.ui.activities.SwipePagesActivity;
import com.MohamedTaha.Imagine.Quran.view.GridViewFragmentView;
import com.MohamedTaha.Imagine.Quran.viewmodel.ImagesViewHolder;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

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
    GridView GridViewActivityGVShowImages;
    @BindView(R.id.GridViewActivity_ProgressBar)
    ProgressBar GridViewActivityProgressBar;
    public static final String SAVE_IMAGES = "save_images";
    Bundle bundle;
    private List<ModelSora> name_swar ;
    AdapterGridView adapterGridView;
    private GridViewFragmentPresenter presenter;
    private int imageDiemn = 200;

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
        presenter = new GridViewFragmentInteractor(this,getActivity());
        presenter.getAllNameSour();
        presenter.getAllImages();
        presenter.getDisplayMetrics(GridViewActivityGVShowImages,imageDiemn);
        presenter.setOnSearchView(searchView);
       /* searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                adapterGridView = new AdapterGridView(getActivity(), name_swar, imageDiemn);
                GridViewActivityGVShowImages.setAdapter(adapterGridView);
                GridViewActivityTVNoData.setVisibility(View.GONE);
                GridViewActivityGVShowImages.setVisibility(View.VISIBLE);
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
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
                      //  name_swar = stringList;
                         adapterGridView = new AdapterGridView(getActivity(), stringList, imageDiemn);
                        GridViewActivityGVShowImages.setAdapter(adapterGridView);
                        GridViewActivityTVNoData.setVisibility(View.GONE);
                        GridViewActivityGVShowImages.setVisibility(View.VISIBLE);

                    } else {
                        GridViewActivityTVNoData.setVisibility(View.VISIBLE);
                        GridViewActivityGVShowImages.setVisibility(View.GONE);
                    }
                }else {
                    GridViewActivityTVNoData.setVisibility(View.GONE);
                    GridViewActivityGVShowImages.setVisibility(View.VISIBLE);
                    //If search is Null return defaultFragmentGridViewTVNoData.setVisibility(View.GONE);
                     adapterGridView = new AdapterGridView(getActivity(), name_swar, imageDiemn);
                    GridViewActivityGVShowImages.setAdapter(adapterGridView);

                }
                return false;
            }
        });*/
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showAfterSearch() {
        adapterGridView = new AdapterGridView(getActivity(), name_swar,imageDiemn);
        GridViewActivityGVShowImages.setAdapter(adapterGridView);
    }

    @Override
    public void showAfterQueryText(List<ModelSora> stringList) {
        //name_swar.clear();
        name_swar = stringList;
        adapterGridView = new AdapterGridView(getActivity(), stringList,imageDiemn);
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
        adapterGridView = new AdapterGridView(getActivity(), name_swar,imageDiemn);
        GridViewActivityGVShowImages.setAdapter(adapterGridView);
        adapterGridView.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryText(searchView,name_swar);

    }

    @Override
    public void showAllImages(List<Integer> integers) {
        GridViewActivityGVShowImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.getPosition(name_swar.get(position).getPosition(),bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top,R.anim.item_anim_no_thing);


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
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_fall_dwon);
        GridViewActivityGVShowImages.setLayoutAnimation(controller);
        GridViewActivityGVShowImages.scheduleLayoutAnimation();
    }}
