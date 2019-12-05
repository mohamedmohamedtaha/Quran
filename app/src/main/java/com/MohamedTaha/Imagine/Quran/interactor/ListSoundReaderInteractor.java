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

        imageModels.add(new ImageModel(context.getString(R.string.abdelbaset), R.drawable.abdeelbaset, context.getString(R.string.hafs_an_asem), 0));
        imageModels.add(new ImageModel(context.getString(R.string.abdelbaset), R.drawable.abdeelbaset, context.getString(R.string.warsh_an_nafea), 1));
        imageModels.add(new ImageModel(context.getString(R.string.abdelbaset), R.drawable.abdeelbaset, context.getString(R.string.ekmoshaf_elmogawad), 2));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryM), R.drawable.elhosary, context.getString(R.string.ekmoshaf_elmogawad), 3));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryM), R.drawable.elhosary, context.getString(R.string.warsh_an_nafea), 4));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elhosaryH), R.drawable.elhosary, context.getString(R.string.hafs_an_asem), 5));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_elmenshawyM), R.drawable.elmenshawy, context.getString(R.string.ekmoshaf_elmogawad), 6));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_elmenshawyH), R.drawable.elmenshawy, context.getString(R.string.hafs_an_asem), 7));
        imageModels.add(new ImageModel(context.getString(R.string.mshary_elafasy), R.drawable.msharyelafasy, context.getString(R.string.hafs_an_asem), 8));
        imageModels.add(new ImageModel(context.getString(R.string.soud_elsherem), R.drawable.sherem, context.getString(R.string.hafs_an_asem), 9));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_eltblawy), R.drawable.eltblawy, context.getString(R.string.hafs_an_asem), 10));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_eldosary), R.drawable.eldosary, context.getString(R.string.hafs_an_asem), 11));
        imageModels.add(new ImageModel(context.getString(R.string.abdellah_elgeheny), R.drawable.elgeheny, context.getString(R.string.hafs_an_asem), 12));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_gebrer), R.drawable.mohamedgbrer, context.getString(R.string.hafs_an_asem), 13));
        imageModels.add(new ImageModel(context.getString(R.string.naser_elqatamy), R.drawable.naserelqatamy, context.getString(R.string.hafs_an_asem), 14));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_elagamy), R.drawable.elagamy, context.getString(R.string.hafs_an_asem), 15));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elbana), R.drawable.elbana, context.getString(R.string.hafs_an_asem), 16));
        imageModels.add(new ImageModel(context.getString(R.string.mahmoud_elbana), R.drawable.elbana, context.getString(R.string.ekmoshaf_elmogawad), 17));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elqasem), R.drawable.elqasem, context.getString(R.string.hafs_an_asem), 18));
        imageModels.add(new ImageModel(context.getString(R.string.slah_elbadry), R.drawable.bder, context.getString(R.string.hafs_an_asem), 19));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_eltrabolsy), R.drawable.ahmed_khdr_eltrabolsy, context.getString(R.string.qalon_an_nafea), 20));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_eltrabolsy), R.drawable.ahmed_khdr_eltrabolsy, context.getString(R.string.hafs_an_asem), 21));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_elhawashy), R.drawable.ahmed_elhawashy, context.getString(R.string.hafs_an_asem), 22));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_saber), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 23));
        imageModels.add(new ImageModel(context.getString(R.string.ahmed_amer), R.drawable.ahmed_amer, context.getString(R.string.hafs_an_asem), 24));
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
        imageModels.add(new ImageModel(context.getString(R.string.abdelrshed_sofy), R.drawable.abdelrshed_sofy, context.getString(R.string.alsosy_ebn_omar), 38));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrshed_sofy), R.drawable.abdelrshed_sofy, context.getString(R.string.khalaf_ben_hamza), 39));
        imageModels.add(new ImageModel(context.getString(R.string.zaki_daghistani), R.drawable.zaki_daghistani, context.getString(R.string.hafs_an_asem), 40));
        imageModels.add(new ImageModel(context.getString(R.string.abdelazez_elzahrany), R.drawable.abdelazez_elzahrany, context.getString(R.string.hafs_an_asem), 41));
        imageModels.add(new ImageModel(context.getString(R.string.ali_elhozefy), R.drawable.ali_elhozefy, context.getString(R.string.qalon_an_nafea), 42));
        imageModels.add(new ImageModel(context.getString(R.string.ali_elhozefy), R.drawable.ali_elhozefy, context.getString(R.string.hafs_an_asem), 43));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_abd_elhakem), R.drawable.mohamed_abd_elhakem, context.getString(R.string.elbezy_an_ebnkaser), 44));
        imageModels.add(new ImageModel(context.getString(R.string.mohamed_abd_elhakem), R.drawable.mohamed_abd_elhakem, context.getString(R.string.aldawry_an_elkasai), 45));
        imageModels.add(new ImageModel(context.getString(R.string.ali_swiesy), R.drawable.ali_swiesy, context.getString(R.string.hafs_an_asem), 46));
        imageModels.add(new ImageModel(context.getString(R.string.yosem_elshereay), R.drawable.yosem_elshereay, context.getString(R.string.hafs_an_asem), 47));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.aldawry_an_elkasai), 48));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.abnzakor), 49));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.hafs_an_asem), 50));
        imageModels.add(new ImageModel(context.getString(R.string.moftah_elslty), R.drawable.moftah_elslty, context.getString(R.string.aldawry_ebn_omar), 51));
        imageModels.add(new ImageModel(context.getString(R.string.tawfik_sayegh), R.drawable.tawfik_sayegh, context.getString(R.string.hafs_an_asem), 52));
        imageModels.add(new ImageModel(context.getString(R.string.ramy_eldabs), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 53));
        imageModels.add(new ImageModel(context.getString(R.string.sahl_yassen), R.drawable.sahl_yassen, context.getString(R.string.hafs_an_asem), 54));
        imageModels.add(new ImageModel(context.getString(R.string.abdelbara_elthebety), R.drawable.abdelbara_elthebety, context.getString(R.string.hafs_an_asem), 55));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elosa), R.drawable.abdelrhman_elosa, context.getString(R.string.hafs_an_asem), 56));
        imageModels.add(new ImageModel(context.getString(R.string.maher_elmeaqly), R.drawable.almaqely, context.getString(R.string.hafs_an_asem), 57));
        imageModels.add(new ImageModel(context.getString(R.string.wadea_elyamany), R.drawable.wadea_elyamany, context.getString(R.string.hafs_an_asem), 58));
        imageModels.add(new ImageModel(context.getString(R.string.nabel_elrefaay), R.drawable.nabel_elrefaay, context.getString(R.string.hafs_an_asem), 59));
        imageModels.add(new ImageModel(context.getString(R.string.waled_elnaehy), R.mipmap.logo, context.getString(R.string.alon_ben_nafea), 60));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_elmazroay), R.drawable.yaser_elmazroay, context.getString(R.string.yaqob_elhadrmy), 61));
        imageModels.add(new ImageModel(context.getString(R.string.hatem_fared), R.drawable.hatem_fared, context.getString(R.string.hafs_an_asem), 62));
        imageModels.add(new ImageModel(context.getString(R.string.khaled_elmehana), R.drawable.khaled_elmehana, context.getString(R.string.hafs_an_asem), 63));
        imageModels.add(new ImageModel(context.getString(R.string.khaled_abdelkafy), R.drawable.khaled_abdelkafy, context.getString(R.string.hafs_an_asem), 64));
        imageModels.add(new ImageModel(context.getString(R.string.jleel), R.drawable.jleel, context.getString(R.string.hafs_an_asem), 65));
        imageModels.add(new ImageModel(context.getString(R.string.qht), R.drawable.qht, context.getString(R.string.hafs_an_asem), 66));
        imageModels.add(new ImageModel(context.getString(R.string.tnjy), R.drawable.tnjy, context.getString(R.string.hafs_an_asem), 67));
        imageModels.add(new ImageModel(context.getString(R.string.s_gmd), R.drawable.s_gmd, context.getString(R.string.hafs_an_asem), 68));
        imageModels.add(new ImageModel(context.getString(R.string.saad), R.drawable.saad, context.getString(R.string.hafs_an_asem), 69));
        imageModels.add(new ImageModel(context.getString(R.string.sayed), R.drawable.syed_rmdan, context.getString(R.string.hafs_an_asem), 70));
        imageModels.add(new ImageModel(context.getString(R.string.shatri), R.drawable.shatri, context.getString(R.string.hafs_an_asem), 71));
        imageModels.add(new ImageModel(context.getString(R.string.taher), R.drawable.taher, context.getString(R.string.hafs_an_asem), 72));
        imageModels.add(new ImageModel(context.getString(R.string.hkm), R.drawable.hkm, context.getString(R.string.hafs_an_asem), 73));
        imageModels.add(new ImageModel(context.getString(R.string.salah_hashim_m), R.drawable.salah_hashim_m, context.getString(R.string.hafs_an_asem), 74));
        imageModels.add(new ImageModel(context.getString(R.string.bu_khtr), R.drawable.bu_khtr, context.getString(R.string.hafs_an_asem), 75));
        imageModels.add(new ImageModel(context.getString(R.string.tareq), R.drawable.tarek_abdelany, context.getString(R.string.qalon_an_nafea), 76));
        imageModels.add(new ImageModel(context.getString(R.string.ryan), R.drawable.ryan, context.getString(R.string.hafs_an_asem), 77));
        imageModels.add(new ImageModel(context.getString(R.string.bari), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 78));
        imageModels.add(new ImageModel(context.getString(R.string.abdelrhman_elsodes), R.drawable.elsodes, context.getString(R.string.hafs_an_asem), 79));
        imageModels.add(new ImageModel(context.getString(R.string.adel_elkalbany), R.drawable.elklbany, context.getString(R.string.hafs_an_asem), 80));
        imageModels.add(new ImageModel(context.getString(R.string.a_majed), R.drawable.a_majed, context.getString(R.string.hafs_an_asem), 81));
        imageModels.add(new ImageModel(context.getString(R.string.soufi_hfs), R.drawable.soufi_hfs, context.getString(R.string.hafs_an_asem), 82));
        imageModels.add(new ImageModel(context.getString(R.string.a_ahmed), R.drawable.a_ahmed, context.getString(R.string.hafs_an_asem), 83));
        imageModels.add(new ImageModel(context.getString(R.string.khalf), R.drawable.khalf, context.getString(R.string.hafs_an_asem), 84));
        imageModels.add(new ImageModel(context.getString(R.string.abdullahk), R.drawable.abdullahk, context.getString(R.string.hafs_an_asem), 85));
        imageModels.add(new ImageModel(context.getString(R.string.bsfr), R.drawable.bsfr, context.getString(R.string.hafs_an_asem), 86));
        imageModels.add(new ImageModel(context.getString(R.string.kyat), R.drawable.kyat, context.getString(R.string.hafs_an_asem), 87));
        imageModels.add(new ImageModel(context.getString(R.string.gulan), R.drawable.gulan, context.getString(R.string.hafs_an_asem), 88));
        imageModels.add(new ImageModel(context.getString(R.string.mohsin_harthi), R.drawable.mohsin_harthi, context.getString(R.string.hafs_an_asem), 89));
        imageModels.add(new ImageModel(context.getString(R.string.obk), R.drawable.obk, context.getString(R.string.hafs_an_asem), 90));
        imageModels.add(new ImageModel(context.getString(R.string.kanakeri), R.drawable.kanakeri, context.getString(R.string.hafs_an_asem), 91));
        imageModels.add(new ImageModel(context.getString(R.string.wdod), R.drawable.wdod, context.getString(R.string.hafs_an_asem), 92));
        imageModels.add(new ImageModel(context.getString(R.string.a_jbr), R.drawable.a_jbr, context.getString(R.string.hafs_an_asem), 93));
        imageModels.add(new ImageModel(context.getString(R.string.hafz), R.drawable.hafz, context.getString(R.string.hafs_an_asem), 94));
        imageModels.add(new ImageModel(context.getString(R.string.frs_a), R.drawable.frs_a, context.getString(R.string.hafs_an_asem), 95));
        imageModels.add(new ImageModel(context.getString(R.string.shaksh), R.drawable.shaksh, context.getString(R.string.hafs_an_asem), 96));
        imageModels.add(new ImageModel(context.getString(R.string.ayyub), R.drawable.ayyub, context.getString(R.string.hafs_an_asem), 97));
        imageModels.add(new ImageModel(context.getString(R.string.earawi), R.drawable.earawi, context.getString(R.string.warsh_an_nafea_by_abe_bakr), 98));
        imageModels.add(new ImageModel(context.getString(R.string.m_krm), R.drawable.m_krm, context.getString(R.string.warsh_an_nafea_by_abe_bakr), 99));
        imageModels.add(new ImageModel(context.getString(R.string.m_krm), R.drawable.m_krm, context.getString(R.string.hafs_an_asem), 100));
        imageModels.add(new ImageModel(context.getString(R.string.shah), R.drawable.shah, context.getString(R.string.hafs_an_asem), 101));
        imageModels.add(new ImageModel(context.getString(R.string.omar_kzabri), R.drawable.omar_kzabri, context.getString(R.string.warsh_an_nafea), 102));
        imageModels.add(new ImageModel(context.getString(R.string.rashad), R.drawable.rashad, context.getString(R.string.hafs_an_asem), 103));
        imageModels.add(new ImageModel(context.getString(R.string.lhdan), R.drawable.lhdan, context.getString(R.string.hafs_an_asem), 104));
        imageModels.add(new ImageModel(context.getString(R.string.khan), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 105));
        imageModels.add(new ImageModel(context.getString(R.string.mrifai), R.drawable.mrifai, context.getString(R.string.hafs_an_asem), 106));
        imageModels.add(new ImageModel(context.getString(R.string.sheimy), R.drawable.sheimy, context.getString(R.string.aldawry_an_elkasai), 107));
        imageModels.add(new ImageModel(context.getString(R.string.yaser_elqarashy), R.drawable.elqarashy, context.getString(R.string.hafs_an_asem), 108));
        imageModels.add(new ImageModel(context.getString(R.string.mustafa), R.drawable.mustafa, context.getString(R.string.hafs_an_asem), 109));
        imageModels.add(new ImageModel(context.getString(R.string.lahoni), R.drawable.lahoni, context.getString(R.string.hafs_an_asem), 110));
        imageModels.add(new ImageModel(context.getString(R.string.ra3ad), R.drawable.ra3ad, context.getString(R.string.hafs_an_asem), 111));
        imageModels.add(new ImageModel(context.getString(R.string.harthi), R.mipmap.logo, context.getString(R.string.hafs_an_asem), 112));
        imageModels.add(new ImageModel(context.getString(R.string.bilal), R.drawable.bilal, context.getString(R.string.hafs_an_asem), 113));
        imageModels.add(new ImageModel(context.getString(R.string.nasser_almajed), R.drawable.nasser_almajed, context.getString(R.string.hafs_an_asem), 114));
        imageModels.add(new ImageModel(context.getString(R.string.namh), R.drawable.namh, context.getString(R.string.hafs_an_asem), 115));
        imageModels.add(new ImageModel(context.getString(R.string.hani), R.drawable.hani, context.getString(R.string.hafs_an_asem), 116));
        imageModels.add(new ImageModel(context.getString(R.string.salamah), R.drawable.salamah, context.getString(R.string.hafs_an_asem), 117));
        imageModels.add(new ImageModel(context.getString(R.string.yahya), R.drawable.yahya, context.getString(R.string.hafs_an_asem), 118));
        imageModels.add(new ImageModel(context.getString(R.string.noah), R.drawable.noah, context.getString(R.string.hafs_an_asem), 119));


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