package com.MohamedTaha.Imagine.Quran.interactor;

import androidx.fragment.app.FragmentActivity;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ImageModel;
import com.MohamedTaha.Imagine.Quran.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.Quran.view.ListSoundReaderView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListSoundReaderInteractor implements ListSoundReaderPresenter {
    private ListSoundReaderView listSoundReaderView;
    private FragmentActivity context;
    private List<ImageModel> imageModels = new ArrayList<>();


    public ListSoundReaderInteractor(ListSoundReaderView listSoundReaderView, FragmentActivity context) {
        this.listSoundReaderView = listSoundReaderView;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        listSoundReaderView = null;
    }

    private void getData() {
        imageModels = new ArrayList<>();
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elsodes), R.drawable.elsodes, context.getString(R.string.hafs_an_asem), 0));
        imageModels.add(new ImageModel(context.getString(R.string.adel_elkalbany), R.drawable.elklbany, context.getString(R.string.hafs_an_asem), 1));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_elmenshawyH), R.drawable.elmenshawy, context.getString(R.string.hafs_an_asem), 2));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_elmenshawyM), R.drawable.elmenshawy, context.getString(R.string.ekmoshaf_elmogawad), 3));
        imageModels.add(new ImageModel(context.getString(R.string.abdelbaset), R.drawable.abdeelbaset, context.getString(R.string.ekmoshaf_elmogawad), 4));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryH), R.drawable.elhosary, context.getString(R.string.hafs_an_asem), 5));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryM), R.drawable.elhosary, context.getString(R.string.ekmoshaf_elmogawad), 6));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryM), R.drawable.elhosary, context.getString(R.string.warsh_an_nafea), 7));
        imageModels.add(new ImageModel(context.getString(R.string.mshary_elafasy), R.drawable.msharyelafasy, context.getString(R.string.hafs_an_asem), 8));
        imageModels.add(new ImageModel(context.getString(R.string.soud_elsherem), R.drawable.sherem, context.getString(R.string.hafs_an_asem), 9));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_eltblawy), R.drawable.eltblawy, context.getString(R.string.hafs_an_asem), 10));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_eldosary), R.drawable.eldosary, context.getString(R.string.hafs_an_asem), 11));
        imageModels.add(new ImageModel(context.getString(R.string.abdellah_elgeheny), R.drawable.elgeheny, context.getString(R.string.hafs_an_asem), 12));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_gebrer), R.drawable.mohamedgbrer, context.getString(R.string.hafs_an_asem), 13));
        imageModels.add(new ImageModel(context.getString(R.string.naser_elqatamy), R.drawable.naserelqatamy, context.getString(R.string.hafs_an_asem), 14));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_elagamy), R.drawable.elagamy, context.getString(R.string.hafs_an_asem), 15));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elbana), R.drawable.elbana, context.getString(R.string.hafs_an_asem), 16));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_elqarashy), R.drawable.elqarashy, context.getString(R.string.hafs_an_asem), 17));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elqasem), R.drawable.elqasem, context.getString(R.string.hafs_an_asem), 18));
        imageModels.add(new ImageModel(context.getString(R.string.slah_elbadry), R.drawable.bder, context.getString(R.string.hafs_an_asem), 19));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_elhawashy), R.drawable.ahmed_elhawashy, context.getString(R.string.hafs_an_asem), 20));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_eltrabolsy), R.drawable.ahmed_khdr_eltrabolsy, context.getString(R.string.hafs_an_asem), 21));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_eltrabolsy), R.drawable.ahmed_khdr_eltrabolsy, context.getString(R.string.qalon_an_nafea), 22));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_saber), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 23));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_amer), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 24));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_naeane), R.drawable.ahmed_neanea, context.getString(R.string.hafs_an_asem), 25));
        imageModels.add(new ImageModel(context.getString(R.string.akram_al_aalakmi), R.drawable.akram_al_aalakmi, context.getString(R.string.hafs_an_asem), 26));
        imageModels.add(new ImageModel(context.getString(R.string.ibrahim_al_akhdar), R.drawable.ebrahem_khoder, context.getString(R.string.hafs_an_asem), 27));
        imageModels.add(new ImageModel(context.getString(R.string.ibrahim_al_dosary), R.drawable.ebrahem_eldosary, context.getString(R.string.hafs_an_asem), 28));
        imageModels.add(new ImageModel(context.getString(R.string.ibrahim_al_dosary), R.drawable.ebrahem_eldosary, context.getString(R.string.warsh_an_nafea), 29));
        imageModels.add(new ImageModel(context.getString(R.string.dokaly_mohamed), R.drawable.aldokaly_mohamed, context.getString(R.string.qalon_an_nafea), 30));
        imageModels.add(new ImageModel(context.getString(R.string.elzen_mohamed), R.drawable.elzen_mohamed, context.getString(R.string.hafs_an_asem), 31));
        imageModels.add(new ImageModel(context.getString(R.string.al_ouoon_alkushi), R.drawable.al_ouoon_alkushi, context.getString(R.string.warsh_an_nafea), 32));
        imageModels.add(new ImageModel(context.getString(R.string.alfateh_mohamed), R.drawable.alfateh_mohamed, context.getString(R.string.aldawry_ebn_omar), 33));
        imageModels.add(new ImageModel(context.getString(R.string.yasen_elgazaery), R.drawable.yasen_elgazaery, context.getString(R.string.warsh_an_nafea), 34));
        imageModels.add(new ImageModel(context.getString(R.string.bnder_belela), R.drawable.bnder_belela, context.getString(R.string.hafs_an_asem), 35));
        imageModels.add(new ImageModel(context.getString(R.string.jamal_shaker), R.drawable.jamal_shaker, context.getString(R.string.hafs_an_asem), 36));
        imageModels.add(new ImageModel(context.getString(R.string.jaman_elesemy), R.drawable.jaman_elesemy, context.getString(R.string.hafs_an_asem), 37));
        imageModels.add(new ImageModel(context.getString(R.string.zaki_daghistani), R.drawable.zaki_daghistani, context.getString(R.string.hafs_an_asem), 38));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrshed_sofy), R.drawable.abdelrshed_sofy, context.getString(R.string.khalaf_ben_hamza), 39));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrshed_sofy), R.drawable.abdelrshed_sofy, context.getString(R.string.alsosy_ebn_omar), 40));
        imageModels.add(new ImageModel(context.getString(R.string.abdelazez_elzahrany), R.drawable.abdelazez_elzahrany, context.getString(R.string.hafs_an_asem), 41));
        imageModels.add(new ImageModel(context.getString(R.string.ali_elhozefy), R.drawable.ali_elhozefy, context.getString(R.string.qalon_an_nafea), 42));
        imageModels.add(new ImageModel(context.getString(R.string.ali_elhozefy), R.drawable.ali_elhozefy, context.getString(R.string.hafs_an_asem), 43));
        imageModels.add(new ImageModel(context.getString(R.string.ali_swiesy), R.drawable.ali_swiesy, context.getString(R.string.hafs_an_asem), 44));
        imageModels.add(new ImageModel(context.getString(R.string.omar_kzabri), R.drawable.omar_kzabri, context.getString(R.string.warsh_an_nafea), 45));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_abd_elhakem), R.drawable.mohamed_abd_elhakem, context.getString(R.string.elbezy_an_ebnkaser), 46));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.aldawry_ebn_omar), 47));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.aldawry_an_elkasai), 48));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.abnzakor), 49));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.hafs_an_asem), 50));



        imageModels.add(new ImageModel(context.getString(R.string.yosem_elshereay), R.drawable.yosem_elshereay, context.getString(R.string.hafs_an_asem), 51));
        imageModels.add(new ImageModel(context.getString(R.string.tawfik_sayegh), R.drawable.tawfik_sayegh, context.getString(R.string.hafs_an_asem), 52));
        imageModels.add(new ImageModel(context.getString(R.string.ramy_eldabs), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 53));
        imageModels.add(new ImageModel(context.getString(R.string.sahl_yassen), R.drawable.sahl_yassen, context.getString(R.string.hafs_an_asem), 54));
        imageModels.add(new ImageModel(context.getString(R.string.abdelbara_elthebety), R.drawable.abdelbara_elthebety, context.getString(R.string.hafs_an_asem), 55));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elosa), R.drawable.abdelrhman_elosa, context.getString(R.string.hafs_an_asem), 56));
        imageModels.add(new ImageModel(context.getString(R.string.maher_elmeaqly), R.drawable.almaqely, context.getString(R.string.hafs_an_asem), 57));
        imageModels.add(new ImageModel(context.getString(R.string.wadea_elyamany), R.drawable.wadea_elyamany, context.getString(R.string.hafs_an_asem), 58));
        imageModels.add(new ImageModel(context.getString(R.string.nabel_elrefaay), R.drawable.nabel_elrefaay, context.getString(R.string.hafs_an_asem), 59));
        imageModels.add(new ImageModel(context.getString(R.string.waled_elnaehy), R.mipmap.logo, context.getString(R.string.alon_ben_nafea), 60));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_elmazroay), R.mipmap.logo, context.getString(R.string.yaqob_elhadrmy), 61));
        imageModels.add(new ImageModel(context.getString(R.string.hatem_fared), R.drawable.hatem_fared, context.getString(R.string.hafs_an_asem), 62));
        imageModels.add(new ImageModel(context.getString(R.string.khaled_elmehana), R.drawable.khaled_elmehana, context.getString(R.string.hafs_an_asem), 63));
        imageModels.add(new ImageModel(context.getString(R.string.khaled_abdelkafy), R.drawable.khaled_abdelkafy, context.getString(R.string.hafs_an_asem), 64));



        }

    @Override
    public void getAllData() {

        listSoundReaderView.showProgress();
        getData();
        Observable<List<ImageModel>> modelAzkarObservable = Observable.just(imageModels)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        modelAzkarObservable.subscribe(new Subscriber<List<ImageModel>>() {
            @Override
            public void onCompleted() {
                if (listSoundReaderView != null) {
                    listSoundReaderView.hideProgress();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (listSoundReaderView != null) {
                    listSoundReaderView.hideProgress();
                }
            }

            @Override
            public void onNext(List<ImageModel> imageModels) {
                if (listSoundReaderView != null) {
                    listSoundReaderView.showAllData(imageModels);
                    listSoundReaderView.showAnimation();
                }

            }
        });
    }

    @Override
    public void setOnSearchViewListener(MaterialSearchView searchView) {
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                listSoundReaderView.showAfterSearch();
                listSoundReaderView.thereData();
            }
        });
    }

    @Override
    public void setOnQueryTextListener(MaterialSearchView searchView, List<ImageModel> imageModel) {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ImageModel> lstFound = new ArrayList<>();
                    for (ImageModel item : imageModel) {
                        if (item.getName_shekh().contains(newText))
                            lstFound.add(item);
                    }
                    if (!lstFound.isEmpty()) {
                        listSoundReaderView.showAfterQueryText(lstFound);
                        listSoundReaderView.thereData();

                    } else {
                        listSoundReaderView.noData();

                    }


                } else {
                    //If search is Null return default
                    listSoundReaderView.thereData();
                    listSoundReaderView.showAllData(imageModel);
                }
                return false;
            }
        });
    }
}