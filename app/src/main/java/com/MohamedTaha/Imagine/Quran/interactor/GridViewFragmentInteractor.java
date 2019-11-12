package com.MohamedTaha.Imagine.Quran.interactor;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MohamedTaha.Imagine.Quran.R;
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
                bundle.putInt(SAVE_POSITION, 0);
                break;
            case 1:
                bundle.putInt(SAVE_POSITION, 1);
                break;
            case 2:
                bundle.putInt(SAVE_POSITION, 49);
                break;
            case 3:
                bundle.putInt(SAVE_POSITION, 76);
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
                bundle.putInt(SAVE_POSITION, 234);
                break;
            case 12:
                bundle.putInt(SAVE_POSITION, 248);
                break;
            case 13:
                bundle.putInt(SAVE_POSITION, 254);
                break;
            case 14:
                bundle.putInt(SAVE_POSITION, 261);
                break;
            case 15:
                bundle.putInt(SAVE_POSITION, 266);
                break;
            case 16:
                bundle.putInt(SAVE_POSITION, 281);
                break;
            case 17:
                bundle.putInt(SAVE_POSITION, 292);
                break;
            case 18:
                bundle.putInt(SAVE_POSITION, 304);
                break;
            case 19:
                bundle.putInt(SAVE_POSITION, 311);
                break;
            case 20:
                bundle.putInt(SAVE_POSITION, 321);
                break;
            case 21:
                bundle.putInt(SAVE_POSITION, 331);
                break;
            case 22:
                bundle.putInt(SAVE_POSITION, 341);
                break;
            case 23:
                bundle.putInt(SAVE_POSITION, 349);
                break;
            case 24:
                bundle.putInt(SAVE_POSITION, 358);
                break;
            case 25:
                bundle.putInt(SAVE_POSITION, 366);
                break;
            case 26:
                bundle.putInt(SAVE_POSITION, 376);
                break;
            case 27:
                bundle.putInt(SAVE_POSITION, 384);
                break;
            case 28:
                bundle.putInt(SAVE_POSITION, 395);
                break;
            case 29:
                bundle.putInt(SAVE_POSITION, 403);
                break;
            case 30:
                bundle.putInt(SAVE_POSITION, 410);
                break;
            case 31:
                bundle.putInt(SAVE_POSITION, 414);
                break;
            case 32:
                bundle.putInt(SAVE_POSITION, 417);
                break;
            case 33:
                bundle.putInt(SAVE_POSITION, 427);
                break;
            case 34:
                bundle.putInt(SAVE_POSITION, 433);
                break;
            case 35:
                bundle.putInt(SAVE_POSITION, 439);
                break;
            case 36:
                bundle.putInt(SAVE_POSITION, 445);
                break;
            case 37:
                bundle.putInt(SAVE_POSITION, 452);
                break;
            case 38:
                bundle.putInt(SAVE_POSITION, 457);
                break;
            case 39:
                bundle.putInt(SAVE_POSITION, 466);
                break;
            case 40:
                bundle.putInt(SAVE_POSITION, 476);
                break;
            case 41:
                bundle.putInt(SAVE_POSITION, 482);
                break;
            case 42:
                bundle.putInt(SAVE_POSITION, 488);
                break;
            case 43:
                bundle.putInt(SAVE_POSITION, 495);
                break;
            case 44:
                bundle.putInt(SAVE_POSITION, 498);
                break;
            case 45:
                bundle.putInt(SAVE_POSITION, 501);
                break;
            case 46:
                bundle.putInt(SAVE_POSITION, 506);
                break;
            case 47:
                bundle.putInt(SAVE_POSITION, 510);
                break;
            case 48:
                bundle.putInt(SAVE_POSITION, 514);
                break;
            case 49:
                bundle.putInt(SAVE_POSITION, 517);
                break;
            case 50:
                bundle.putInt(SAVE_POSITION, 519);
                break;
            case 51:
                bundle.putInt(SAVE_POSITION, 522);
                break;
            case 52:
                bundle.putInt(SAVE_POSITION, 525);
                break;
            case 53:
                bundle.putInt(SAVE_POSITION, 527);
                break;
            case 54:
                bundle.putInt(SAVE_POSITION, 530);
                break;
            case 55:
                bundle.putInt(SAVE_POSITION, 533);
                break;
            case 56:
                bundle.putInt(SAVE_POSITION, 536);
                break;
            case 57:
                bundle.putInt(SAVE_POSITION, 541);
                break;
            case 58:
                bundle.putInt(SAVE_POSITION, 544);
                break;
            case 59:
                bundle.putInt(SAVE_POSITION, 548);
                break;
            case 60:
                bundle.putInt(SAVE_POSITION, 550);
                break;
            case 61:
                bundle.putInt(SAVE_POSITION, 552);
                break;
            case 62:
                bundle.putInt(SAVE_POSITION, 553);
                break;
            case 63:
                bundle.putInt(SAVE_POSITION, 555);
                break;
            case 64:
                bundle.putInt(SAVE_POSITION, 557);
                break;
            case 65:
                bundle.putInt(SAVE_POSITION, 559);
                break;
            case 66:
                bundle.putInt(SAVE_POSITION, 561);
                break;
            case 67:
                bundle.putInt(SAVE_POSITION, 563);
                break;
            case 68:
                bundle.putInt(SAVE_POSITION, 565);
                break;
            case 69:
                bundle.putInt(SAVE_POSITION, 567);
                break;
            case 70:
                bundle.putInt(SAVE_POSITION, 569);
                break;
            case 71:
                bundle.putInt(SAVE_POSITION, 571);
                break;
            case 72:
                bundle.putInt(SAVE_POSITION, 573);
                break;
            case 73:
                bundle.putInt(SAVE_POSITION, 574);
                break;
            case 74:
                bundle.putInt(SAVE_POSITION, 576);
                break;
            case 75:
                bundle.putInt(SAVE_POSITION, 577);
                break;
            case 76:
                bundle.putInt(SAVE_POSITION, 579);
                break;
            case 77:
                bundle.putInt(SAVE_POSITION, 581);
                break;
            case 78:
                bundle.putInt(SAVE_POSITION, 582);
                break;
            case 79:
                bundle.putInt(SAVE_POSITION, 584);
                break;
            case 80:
                bundle.putInt(SAVE_POSITION, 585);
                break;
            case 81:
            case 82:
                bundle.putInt(SAVE_POSITION, 586);
                break;
            case 83:
                bundle.putInt(SAVE_POSITION, 588);
                break;
            case 84:
                bundle.putInt(SAVE_POSITION, 589);
                break;
            case 85:
            case 86:
                bundle.putInt(SAVE_POSITION, 590);
                break;
            case 87:
                bundle.putInt(SAVE_POSITION, 591);
                break;
            case 88:
                bundle.putInt(SAVE_POSITION, 592);
                break;
            case 89:
                bundle.putInt(SAVE_POSITION, 593);
                break;
            case 90:
            case 91:
                bundle.putInt(SAVE_POSITION, 594);
                break;
            case 92:
            case 93:
                bundle.putInt(SAVE_POSITION, 595);
                break;
            case 94:
            case 95:
                bundle.putInt(SAVE_POSITION, 596);
                break;
            case 96:
            case 97:
                bundle.putInt(SAVE_POSITION, 597);
                break;
            case 98:
            case 99:
                bundle.putInt(SAVE_POSITION, 598);
                break;
            case 100:
                case 101:
                bundle.putInt(SAVE_POSITION, 599);
                break;
            case 102:
            case 103:
            case 104:
                bundle.putInt(SAVE_POSITION, 600);
                break;
            case 105:
            case 106:
            case 107:
                bundle.putInt(SAVE_POSITION, 601);
                break;
            case 108:
            case 109:
            case 110:
                bundle.putInt(SAVE_POSITION, 602);
                break;
            case 111:
            case 112:
            case 113:
                bundle.putInt(SAVE_POSITION, 603);
                break;
            case 114:
                bundle.putInt(SAVE_POSITION, 604);
                break;
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
        List<ModelSora> modelSoraList = new ArrayList<>();

           String [] a = activity.getResources().getStringArray(R.array.name_swar_strings);
            for (int i = 0; i < a.length; i++) {
                ModelSora name_Sroa_local = new ModelSora();
                name_Sroa_local.setName_sora(a[i]);
                name_Sroa_local.setPosition(i);
                modelSoraList.add(name_Sroa_local);
        }
        fragmentView.showAllImages(modelSoraList);


//        imagesViewHolder.getAllImages().observe((LifecycleOwner) activity, new Observer<List<Integer>>() {
//            @Override
//            public void onChanged(List<Integer> integers) {
//                if (fragmentView != null) {
//                    fragmentView.showAllImages(integers);
//                }
//            }
//        });
//
   }

//    @Override
//    public void getDisplayMetrics(ListView GridViewActivityGVShowImages, int imageDiemn) {
//        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
//        width = displayMetrics.widthPixels / displayMetrics.density;
//        count = (int) width / imageDiemn;
//        GridViewActivityGVShowImages.setNumColumns(count);
//
//    }

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
