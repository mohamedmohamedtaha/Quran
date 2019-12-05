package com.MohamedTaha.Imagine.Quran.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class SoundRepositiory {
    private List<ImageModel> imageModels = new ArrayList<>();
    private ArrayList<ImageModel> name_sora = new ArrayList<>();
    private Application application;
    private MutableLiveData<List<ImageModel>> mutableLiveDataImageModel = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ImageModel>> mutableLiveDataNameSora = new MutableLiveData<>();
    String[] ArrayLinkYaserEldosary;
    String[] ArrayLinkElhosaryMgwad;
    String[] ArrayLinkElmenshawyHafs;
    String[] ArrayLinkElmenshawyMgwad;
    String[] ArrayLinkAbdlaElgeheny;
    String[] ArrayLinkMAHER_ELMEAQLY;
    String[] ArrayLinkAbdelbasetMgwad;
    String[] ArrayLinkSoaadElsherem;
    String[] ArrayLinkALTABLAWY;
    String[] ArrayLinkElklbanyHafs;
    String[] ArrayLinkElsodes;
    String[] ArrayLinkMsharyElafasy;
    String[] ArrayLinkElhosaryHAfs;
    String[] ArrayLinkMohamedGbrer;
    String[] ArrayLinkNaserElqatamy;
    String[] ArrayLinkElagamy;
    String[] ArrayLinkElbana;
    String[] ArrayLinkElqarashy;
    String[] ArrayLinkElqasem;
    String[] ArrayLinkBder;
    String[] ArrayLinkAhmedElhawashy, ArrayLinkAhmedEltrabolsy, ArrayLinkAhmedKhedrEltrabolsy, ArrayLinkAhmedSaber, ArrayLinkAhmedAmer, ArrayLinkAhmedNeanea, ArrayLinkAkramAlAalakmi, ArrayLinkEbrahemElakhdar, ArrayLinkEbraghemeldosary, ArrayLinkEbraghem_eldosaryWarsh, ArrayLinkEldokalyMohamed, ArrayLinkElzenMohamed, ArrayLinkEloyonElkorashy, ArrayLinkElfalehMohamed, ArrayLinkYasenElgazaery, ArrayLinkBnderBelela, ArrayLinkJamalShaker, ArrayLinkJamanElesemy, ArrayLinkZaky, ArrayLinkSoufiKlf, ArrayLinkSoufi, ArrayLinkbdelazezZahrani, ArrayLinkAliElhozefy, ArrayLinkAliElhozefyHafs, ArrayLinkAlyElswesy,
            ArrayLinkOmarElqzabry, ArrayLinkAbdelhakemAlabdlah, ArrayLinklinkesMoftahElsltny, ArrayLinkNabelElrefay, ArrayLinkWaledElnaahy, ArrayLinkYaserElmazroay, ArrayLinkYosefElshereay, ArraylinkesTwfeaElsayegh, ArraylinkesRamyEldeaas, ArraylinkesSahlYassen, ArraylinkesAbdelbariElthebety, ArraylinkesAbdelrhmanEloasi, ArraylinkesElhosaryWarsh, ArraylinkesWadeaElyamany, ArraylinkesMoftahElsalatnyEldawary, ArraylinkesMoftahElsalatnyEnbZakor, ArraylinkesMoftahHafs, ArraylinkesHatemFared, ArraylinkesKhalidElmohana, ArraylinkesKhalidAbdElkafy, linkesKhaledElgalel, linkesKhaledElqahtamny, linkesKhalefaEltenegy, linkesSadElghamdy, linkeSadElmeqren, linkesSyedRamadan, linkesElshatry,
            linkesShahrzadTaher, linkesHKM, linkesSalahHashim, linkesBuKhtr, linkesTareq, linkesRyan, linkesBary, linkesAbdelbasetHAfs, linkesAbdelbasetWarsh, linkesAbdelrhmanMaged, linkesSoufiHfs, linkesAbdelazezAhmed, linkeKkhalf, linkesAbdalah, linkesBsfr, linkesKyat, linkesGulan, linkesmohsin_harthi, linkesObk, linkesKanakeri, linkesWdod, linkesAjbr, linkesHafz, linkesFrs_a, linkesShakshFs, linkesAyyub, linkesEarawi, linkesLhdan, linkesRashad, linkesShah, linkesAbdullahDoris, linkesM_krm, linkesMkreem_warsh, linkesKhan, linkesMrifai, linkesSheimy, linkesBna_mjwds, linkesMustafa, linkesLahoni, linkesRa3ad, linkesHarthi, linkesBilal, linkesNasser_almajed, linkesNamh, linkesHani, linkesSalamah, linkesYahya, linkesNoah;


    public SoundRepositiory(Application application) {
        this.application = application;
    }


    public MutableLiveData<ArrayList<ImageModel>> getMutableLiveDataNameSora(int position) {
        //Fetch the data in String.xml file
        ArrayLinkElhosaryMgwad = application.getResources().getStringArray(R.array.linkesElhosaryMgwad);
        ArrayLinkAbdelbasetMgwad = application.getResources().getStringArray(R.array.linkesAbdelbasetMgwad);
        ArrayLinkMAHER_ELMEAQLY = application.getResources().getStringArray(R.array.linkesMaherElmaqly);
        ArrayLinkSoaadElsherem = application.getResources().getStringArray(R.array.linkesSoaadElsherem);
        ArrayLinkALTABLAWY = application.getResources().getStringArray(R.array.linkesEltblawy);
        ArrayLinkElklbanyHafs = application.getResources().getStringArray(R.array.linkesElklbany);
        ArrayLinkAbdlaElgeheny = application.getResources().getStringArray(R.array.linkesAbdlaElgeheny);
        ArrayLinkElmenshawyMgwad = application.getResources().getStringArray(R.array.linkesElmenshawyMgwad);
        ArrayLinkElmenshawyHafs = application.getResources().getStringArray(R.array.linkesElmenshawyHafs);
        ArrayLinkYaserEldosary = application.getResources().getStringArray(R.array.linkesyasser);
        ArrayLinkElsodes = application.getResources().getStringArray(R.array.linkesElsodes);
        ArrayLinkMsharyElafasy = application.getResources().getStringArray(R.array.linkesMsharyElafasy);
        ArrayLinkMohamedGbrer = application.getResources().getStringArray(R.array.linkesMohamedGbrer);
        ArrayLinkElhosaryHAfs = application.getResources().getStringArray(R.array.linkesElhosaryHAfs);
        ArrayLinkNaserElqatamy = application.getResources().getStringArray(R.array.linkesNaserElqatamy);
        ArrayLinkElagamy = application.getResources().getStringArray(R.array.linkesElagamy);
        ArrayLinkElbana = application.getResources().getStringArray(R.array.linkesElbana);
        ArrayLinkElqarashy = application.getResources().getStringArray(R.array.linkesElqarashy);
        ArrayLinkElqasem = application.getResources().getStringArray(R.array.linkesElqasem);
        ArrayLinkBder = application.getResources().getStringArray(R.array.linkesBder);
        ArrayLinkAhmedElhawashy = application.getResources().getStringArray(R.array.linkesAhmedElhawashy);
        ArrayLinkAhmedEltrabolsy = application.getResources().getStringArray(R.array.linkesAhmedEltrabolsy);
        ArrayLinkAhmedKhedrEltrabolsy = application.getResources().getStringArray(R.array.linkesAhmedKhedrEltrabolsy);
        ArrayLinkAhmedSaber = application.getResources().getStringArray(R.array.linkesAhmedSaber);
        ArrayLinkAhmedAmer = application.getResources().getStringArray(R.array.linkesAhmedAmer);
        ArrayLinkAhmedNeanea = application.getResources().getStringArray(R.array.linkesAhmedNeanea);
        ArrayLinkAkramAlAalakmi = application.getResources().getStringArray(R.array.linkesAkramAlAalakmi);
        ArrayLinkEbrahemElakhdar = application.getResources().getStringArray(R.array.linkesEbrahemElakhdar);
        ArrayLinkEbraghemeldosary = application.getResources().getStringArray(R.array.linkesEbrahemEldosary);
        ArrayLinkEbraghem_eldosaryWarsh = application.getResources().getStringArray(R.array.linkesEbrahemEldosaryWarsh);
        ArrayLinkEldokalyMohamed = application.getResources().getStringArray(R.array.linkesEldokalyMohamed);
        ArrayLinkElzenMohamed = application.getResources().getStringArray(R.array.linkesElzenMohamed);
        ArrayLinkEloyonElkorashy = application.getResources().getStringArray(R.array.linkesEloyonElkorashy);
        ArrayLinkElfalehMohamed = application.getResources().getStringArray(R.array.linkesElfalemMohamed);
        ArrayLinkYasenElgazaery = application.getResources().getStringArray(R.array.linkesYasenElgazaery);
        ArrayLinkBnderBelela = application.getResources().getStringArray(R.array.linkesBnderBelela);
        ArrayLinkJamalShaker = application.getResources().getStringArray(R.array.linkesJamalShaker);
        ArrayLinkJamanElesemy = application.getResources().getStringArray(R.array.linkesJamanElesemy);
        ArrayLinkZaky = application.getResources().getStringArray(R.array.linkesZaky);
        ArrayLinkSoufiKlf = application.getResources().getStringArray(R.array.linkesSoufiKlf);
        ArrayLinkSoufi = application.getResources().getStringArray(R.array.linkesSoufi);
        ArrayLinkbdelazezZahrani = application.getResources().getStringArray(R.array.linkesAbdelazezZahrani);
        ArrayLinkAliElhozefy = application.getResources().getStringArray(R.array.linkesAlyElhozefy);
        ArrayLinkAliElhozefyHafs = application.getResources().getStringArray(R.array.linkesAliAlyElhozefyHafs);
        ArrayLinkAlyElswesy = application.getResources().getStringArray(R.array.linkesAlyElswesy);
        ArrayLinkOmarElqzabry = application.getResources().getStringArray(R.array.linkesOmarElqzabry);
        ArrayLinkAbdelhakemAlabdlah = application.getResources().getStringArray(R.array.linkesAbdelhakemAlabdlah);
        ArrayLinklinkesMoftahElsltny = application.getResources().getStringArray(R.array.linkesMoftahElsltny);
        ArrayLinkNabelElrefay = application.getResources().getStringArray(R.array.linkesNabelElrefay);
        ArrayLinkWaledElnaahy = application.getResources().getStringArray(R.array.linkesWaledElnaahy);
        ArrayLinkYaserElmazroay = application.getResources().getStringArray(R.array.linkesYaserElmazroay);
        ArrayLinkYosefElshereay = application.getResources().getStringArray(R.array.linkesYousef);
        ArraylinkesTwfeaElsayegh = application.getResources().getStringArray(R.array.linkesTwfeaElsayegh);
        ArraylinkesRamyEldeaas = application.getResources().getStringArray(R.array.linkesRamyEldeaas);
        ArraylinkesSahlYassen = application.getResources().getStringArray(R.array.linkesSahlYassen);
        ArraylinkesAbdelbariElthebety = application.getResources().getStringArray(R.array.linkesAbdelbariElthebety);
        ArraylinkesAbdelrhmanEloasi = application.getResources().getStringArray(R.array.linkesAbdelrhmanEloasi);
        ArraylinkesElhosaryWarsh = application.getResources().getStringArray(R.array.linkesElhosaryWarsh);
        ArraylinkesWadeaElyamany = application.getResources().getStringArray(R.array.linkesWadeaElyamany);
        ArraylinkesMoftahElsalatnyEldawary = application.getResources().getStringArray(R.array.linkesMoftahElsalatnyEldawary);
        ArraylinkesMoftahElsalatnyEnbZakor = application.getResources().getStringArray(R.array.linkesMoftahElsalatnyEnbZakor);
        ArraylinkesMoftahHafs = application.getResources().getStringArray(R.array.linkesMoftahHafs);
        ArraylinkesHatemFared = application.getResources().getStringArray(R.array.linkesHatemFared);
        ArraylinkesKhalidElmohana = application.getResources().getStringArray(R.array.linkesKhalidElmohana);
        ArraylinkesKhalidAbdElkafy = application.getResources().getStringArray(R.array.linkesKhalidAbdElkafy);
        linkesKhaledElgalel = application.getResources().getStringArray(R.array.linkesKhaledElgalel);
        linkesKhaledElqahtamny = application.getResources().getStringArray(R.array.linkesKhaledElqahtamny);
        linkesKhalefaEltenegy = application.getResources().getStringArray(R.array.linkesKhalefaEltenegy);
        linkesSadElghamdy = application.getResources().getStringArray(R.array.linkesSadElghamdy);
        linkeSadElmeqren = application.getResources().getStringArray(R.array.linkeSadElmeqren);
        linkesSyedRamadan = application.getResources().getStringArray(R.array.linkesSyedRamadan);
        linkesElshatry = application.getResources().getStringArray(R.array.linkesElshatry);
        linkesShahrzadTaher = application.getResources().getStringArray(R.array.linkesShahrzadTaher);
        linkesHKM = application.getResources().getStringArray(R.array.linkesHKM);
        linkesSalahHashim = application.getResources().getStringArray(R.array.linkesSalahHashim);
        linkesBuKhtr = application.getResources().getStringArray(R.array.linkesBuKhtr);
        linkesTareq = application.getResources().getStringArray(R.array.linkesTareq);
        linkesRyan = application.getResources().getStringArray(R.array.linkesRyan);
        linkesBary = application.getResources().getStringArray(R.array.linkesBary);
        linkesAbdelbasetHAfs = application.getResources().getStringArray(R.array.linkesAbdelbasetHAfs);
        linkesAbdelbasetWarsh = application.getResources().getStringArray(R.array.linkesAbdelbasetWarsh);
        linkesAbdelrhmanMaged = application.getResources().getStringArray(R.array.linkesAbdelrhmanMaged);
        linkesSoufiHfs = application.getResources().getStringArray(R.array.linkesSoufiHfs);
        linkesAbdelazezAhmed = application.getResources().getStringArray(R.array.linkesAbdelazezAhmed);
        linkeKkhalf = application.getResources().getStringArray(R.array.linkeKkhalf);
        linkesAbdalah = application.getResources().getStringArray(R.array.linkesAbdalah);
        linkesBsfr = application.getResources().getStringArray(R.array.linkesBsfr);
        linkesKyat = application.getResources().getStringArray(R.array.linkesKyat);
        linkesGulan = application.getResources().getStringArray(R.array.linkesGulan);
        linkesmohsin_harthi = application.getResources().getStringArray(R.array.linkesmohsin_harthi);
        linkesObk = application.getResources().getStringArray(R.array.linkesObk);
        linkesKanakeri = application.getResources().getStringArray(R.array.linkesKanakeri);
        linkesWdod = application.getResources().getStringArray(R.array.linkesWdod);
        linkesAjbr = application.getResources().getStringArray(R.array.linkesAjbr);
        linkesHafz = application.getResources().getStringArray(R.array.linkesHafz);
        linkesFrs_a = application.getResources().getStringArray(R.array.linkesFrs_a);
        linkesShakshFs = application.getResources().getStringArray(R.array.linkesShakshFs);
        linkesAyyub = application.getResources().getStringArray(R.array.linkesAyyub);
        linkesEarawi = application.getResources().getStringArray(R.array.linkesEarawi);
        linkesLhdan = application.getResources().getStringArray(R.array.linkesLhdan);
        linkesRashad = application.getResources().getStringArray(R.array.linkesRashad);
        linkesShah = application.getResources().getStringArray(R.array.linkesShah);
        linkesAbdullahDoris = application.getResources().getStringArray(R.array.linkesAbdullahDoris);
        linkesM_krm = application.getResources().getStringArray(R.array.linkesM_krm);
        linkesMkreem_warsh = application.getResources().getStringArray(R.array.linkesMkreem_warsh);
        linkesKhan = application.getResources().getStringArray(R.array.linkesKhan);
        linkesMrifai = application.getResources().getStringArray(R.array.linkesMrifai);
        linkesSheimy = application.getResources().getStringArray(R.array.linkesSheimy);
        linkesBna_mjwds = application.getResources().getStringArray(R.array.linkesBna_mjwds);
        linkesMustafa = application.getResources().getStringArray(R.array.linkesMustafa);
        linkesLahoni = application.getResources().getStringArray(R.array.linkesLahoni);
        linkesRa3ad = application.getResources().getStringArray(R.array.linkesRa3ad);
        linkesHarthi = application.getResources().getStringArray(R.array.linkesHarthi);
        linkesBilal = application.getResources().getStringArray(R.array.linkesBilal);
        linkesNasser_almajed = application.getResources().getStringArray(R.array.linkesNasser_almajed);
        linkesNamh = application.getResources().getStringArray(R.array.linkesNamh);
        linkesHani = application.getResources().getStringArray(R.array.linkesHani);
        linkesSalamah = application.getResources().getStringArray(R.array.linkesSalamah);
        linkesYahya = application.getResources().getStringArray(R.array.linkesYahya);
        linkesNoah = application.getResources().getStringArray(R.array.linkesNoah);
        getShekhAyat(position);
        mutableLiveDataNameSora.setValue(name_sora);
        return mutableLiveDataNameSora;
    }

    public MutableLiveData<List<ImageModel>> getMutableLiveDataImageModel() {
        imageModels = new ArrayList<>();
        getData();
        mutableLiveDataImageModel.setValue(imageModels);
        return mutableLiveDataImageModel;
    }

    private void getData() {
        imageModels.add(new ImageModel(application.getString(R.string.abdelrhman_elsodes), R.drawable.elsodes, application.getString(R.string.hafs_an_asem), 0));
        imageModels.add(new ImageModel(application.getString(R.string.adel_elkalbany), R.drawable.elklbany, application.getString(R.string.hafs_an_asem), 1));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_elmenshawyH), R.drawable.elmenshawy, application.getString(R.string.hafs_an_asem), 2));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_elmenshawyM), R.drawable.elmenshawy, application.getString(R.string.ekmoshaf_elmogawad), 3));
        imageModels.add(new ImageModel(application.getString(R.string.abdelbaset), R.drawable.abdeelbaset, application.getString(R.string.ekmoshaf_elmogawad), 4));
        imageModels.add(new ImageModel(application.getString(R.string.mahmoud_elhosaryH), R.drawable.elhosary, application.getString(R.string.hafs_an_asem), 5));
        imageModels.add(new ImageModel(application.getString(R.string.mahmoud_elhosaryM), R.drawable.elhosary, application.getString(R.string.ekmoshaf_elmogawad), 6));
        imageModels.add(new ImageModel(application.getString(R.string.maher_elmeaqly), R.drawable.almaqely, application.getString(R.string.hafs_an_asem), 7));
        imageModels.add(new ImageModel(application.getString(R.string.mshary_elafasy), R.drawable.msharyelafasy, application.getString(R.string.hafs_an_asem), 8));
        imageModels.add(new ImageModel(application.getString(R.string.soud_elsherem), R.drawable.sherem, application.getString(R.string.hafs_an_asem), 9));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_eltblawy), R.drawable.eltblawy, application.getString(R.string.hafs_an_asem), 10));
        imageModels.add(new ImageModel(application.getString(R.string.yaser_eldosary), R.drawable.eldosary, application.getString(R.string.hafs_an_asem), 11));
        imageModels.add(new ImageModel(application.getString(R.string.abdellah_elgeheny), R.drawable.elgeheny, application.getString(R.string.hafs_an_asem), 12));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_gebrer), R.drawable.mohamedgbrer, application.getString(R.string.hafs_an_asem), 13));
        imageModels.add(new ImageModel(application.getString(R.string.naser_elqatamy), R.drawable.naserelqatamy, application.getString(R.string.hafs_an_asem), 14));
        imageModels.add(new ImageModel(application.getString(R.string.ahmed_elagamy), R.drawable.elagamy, application.getString(R.string.hafs_an_asem), 15));
        imageModels.add(new ImageModel(application.getString(R.string.mahmoud_elbana), R.drawable.elbana, application.getString(R.string.hafs_an_asem), 16));
        imageModels.add(new ImageModel(application.getString(R.string.yaser_elqarashy), R.drawable.elqarashy, application.getString(R.string.hafs_an_asem), 17));
        imageModels.add(new ImageModel(application.getString(R.string.abdelrhman_elqasem), R.drawable.elqasem, application.getString(R.string.hafs_an_asem), 18));
        imageModels.add(new ImageModel(application.getString(R.string.slah_elbadry), R.drawable.bder, application.getString(R.string.hafs_an_asem), 19));
    }

    private List<ImageModel> getShekhAyat(int posision) {
        String[] soar = null;
        String[] positions = null;
        soar = application.getResources().getStringArray(R.array.name_allSwar);
        positions = application.getResources().getStringArray(R.array.positions);

        switch (posision) {
            case 0:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAbdelbasetHAfs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelbaset));
                    model.setUrl_image(R.drawable.abdeelbaset);
                    name_sora.add(model);
                }
                break;
            case 1:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAbdelbasetWarsh[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelbaset));
                    model.setUrl_image(R.drawable.abdeelbaset);
                    name_sora.add(model);
                }
                break;
            case 2:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAbdelbasetMgwad[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelbaset));
                    model.setUrl_image(R.drawable.abdeelbaset);
                    name_sora.add(model);
                }
                break;
            case 3:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElhosaryMgwad[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mahmoud_elhosaryM));
                    model.setUrl_image(R.drawable.elhosary);
                    name_sora.add(model);
                }
                break;
            case 4:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesElhosaryWarsh[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mahmoud_elhosaryM));
                    model.setUrl_image(R.drawable.elhosary);
                    name_sora.add(model);
                }
                break;
            case 5:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElhosaryHAfs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mahmoud_elhosaryH));
                    model.setUrl_image(R.drawable.elhosary);
                    name_sora.add(model);
                }
                break;
            case 6:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElmenshawyMgwad[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mohamed_elmenshawyM));
                    model.setUrl_image(R.drawable.elmenshawy);
                    name_sora.add(model);
                }
                break;
            case 7:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElmenshawyHafs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mohamed_elmenshawyH));
                    model.setUrl_image(R.drawable.elmenshawy);
                    name_sora.add(model);
                }
                break;

            case 8:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkMsharyElafasy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mshary_elafasy));
                    model.setUrl_image(R.drawable.msharyelafasy);
                    name_sora.add(model);
                }
                break;
            case 9:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkSoaadElsherem[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.soud_elsherem));
                    model.setUrl_image(R.drawable.sherem);
                    name_sora.add(model);
                }
                break;
            case 10:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkALTABLAWY[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mohamed_eltblawy));
                    model.setUrl_image(R.drawable.eltblawy);
                    name_sora.add(model);
                }
                break;
            case 11:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.yaser_eldosary));
                    model.setUrl_image(R.drawable.eldosary);
                    name_sora.add(model);
                }
                break;
            case 12:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAbdlaElgeheny[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdellah_elgeheny));
                    model.setUrl_image(R.drawable.elgeheny);
                    name_sora.add(model);
                }
                break;
            case 13:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkMohamedGbrer[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mohamed_gebrer));
                    model.setUrl_image(R.drawable.mohamedgbrer);
                    name_sora.add(model);
                }
                break;
            case 14:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkNaserElqatamy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.naser_elqatamy));
                    model.setUrl_image(R.drawable.naserelqatamy);
                    name_sora.add(model);
                }
                break;
            case 15:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElagamy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ahmed_elagamy));
                    model.setUrl_image(R.drawable.elagamy);
                    name_sora.add(model);
                }
                break;
            case 16:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElbana[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mahmoud_elbana));
                    model.setUrl_image(R.drawable.elbana);
                    name_sora.add(model);
                }
                break;

            case 17:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesBna_mjwds[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mahmoud_elbana));
                    model.setUrl_image(R.drawable.elbana);
                    name_sora.add(model);
                }
                break;
            case 18:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElqasem[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelrhman_elqasem));
                    model.setUrl_image(R.drawable.elqasem);
                    name_sora.add(model);
                }
                break;
            case 19:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkBder[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.slah_elbadry));
                    model.setUrl_image(R.drawable.bder);

                    name_sora.add(model);
                }
                break;

            case 20:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAhmedKhedrEltrabolsy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ahmed_eltrabolsy));
                    model.setUrl_image(R.drawable.ahmed_khdr_eltrabolsy);

                    name_sora.add(model);
                }
                break;
            case 21:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAhmedEltrabolsy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ahmed_eltrabolsy));
                    model.setUrl_image(R.drawable.ahmed_khdr_eltrabolsy);
                    name_sora.add(model);
                }
                break;
            case 22:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAhmedElhawashy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ahmed_elhawashy));
                    model.setUrl_image(R.drawable.ahmed_elhawashy);
                    name_sora.add(model);
                }
                break;
            case 23:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAhmedSaber[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ahmed_saber));
                    model.setUrl_image(R.mipmap.logo);

                    name_sora.add(model);
                }
                break;
            case 24:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAhmedAmer[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ahmed_amer));
                    model.setUrl_image(R.drawable.ahmed_amer);
                    name_sora.add(model);
                }
                break;
            case 25:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAhmedNeanea[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ahmed_naeane));
                    model.setUrl_image(R.drawable.ahmed_neanea);
                    name_sora.add(model);
                }
                break;
            case 26:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAkramAlAalakmi[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.akram_al_aalakmi));
                    model.setUrl_image(R.drawable.akram_al_aalakmi);
                    name_sora.add(model);
                }
                break;
            case 27:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkEbrahemElakhdar[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ibrahim_al_akhdar));
                    model.setUrl_image(R.drawable.ebrahem_khoder);
                    name_sora.add(model);
                }
                break;
            case 28:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkEbraghemeldosary[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ibrahim_al_dosary));
                    model.setUrl_image(R.drawable.ebrahem_eldosary);
                    name_sora.add(model);
                }
                break;
            case 29:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkEbraghem_eldosaryWarsh[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ibrahim_al_dosary));
                    model.setUrl_image(R.drawable.ebrahem_eldosary);
                    name_sora.add(model);
                }
                break;
            case 30:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkEldokalyMohamed[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.dokaly_mohamed));
                    model.setUrl_image(R.drawable.aldokaly_mohamed);
                    name_sora.add(model);
                }
                break;
            case 31:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElzenMohamed[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.elzen_mohamed));
                    model.setUrl_image(R.drawable.elzen_mohamed);
                    name_sora.add(model);
                }
                break;
            case 32:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkEloyonElkorashy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.al_ouoon_alkushi));
                    model.setUrl_image(R.drawable.al_ouoon_alkushi);
                    name_sora.add(model);
                }
                break;
            case 33:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElfalehMohamed[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.alfateh_mohamed));
                    model.setUrl_image(R.drawable.alfateh_mohamed);
                    name_sora.add(model);
                }
                break;
            case 34:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkYasenElgazaery[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.yasen_elgazaery));
                    model.setUrl_image(R.drawable.yasen_elgazaery);
                    name_sora.add(model);
                }
                break;
            case 35:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkBnderBelela[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.bnder_belela));
                    model.setUrl_image(R.drawable.bnder_belela);
                    name_sora.add(model);
                }
                break;
            case 36:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkJamalShaker[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.jamal_shaker));
                    model.setUrl_image(R.drawable.jamal_shaker);
                    name_sora.add(model);
                }
                break;
            case 37:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkJamanElesemy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.jaman_elesemy));
                    model.setUrl_image(R.drawable.jaman_elesemy);
                    name_sora.add(model);
                }
                break;
            case 38:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkSoufi[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelrshed_sofy));
                    model.setUrl_image(R.drawable.abdelrshed_sofy);
                    name_sora.add(model);
                }
                break;
            case 39:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkSoufiKlf[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelrshed_sofy));
                    model.setUrl_image(R.drawable.abdelrshed_sofy);
                    name_sora.add(model);
                }
                break;
            case 40:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkZaky[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.zaki_daghistani));
                    model.setUrl_image(R.drawable.zaki_daghistani);
                    name_sora.add(model);
                }
                break;
            case 41:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkbdelazezZahrani[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelazez_elzahrany));
                    model.setUrl_image(R.drawable.abdelazez_elzahrany);
                    name_sora.add(model);
                }
                break;
            case 42:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAliElhozefy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ali_elhozefy));
                    model.setUrl_image(R.drawable.ali_elhozefy);
                    name_sora.add(model);
                }
                break;
            case 43:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAliElhozefyHafs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ali_elhozefy));
                    model.setUrl_image(R.drawable.ali_elhozefy);
                    name_sora.add(model);
                }
                break;
            case 44:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAbdelhakemAlabdlah[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mohamed_abd_elhakem));
                    model.setUrl_image(R.drawable.mohamed_abd_elhakem);
                    name_sora.add(model);
                }
                break;

            case 45:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAbdullahDoris[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mohamed_abd_elhakem));
                    model.setUrl_image(R.drawable.mohamed_abd_elhakem);
                    name_sora.add(model);
                }
                break;
            case 46:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkAlyElswesy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ali_swiesy));
                    model.setUrl_image(R.drawable.ali_swiesy);
                    name_sora.add(model);
                }
                break;

            case 47:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkYosefElshereay[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.yosem_elshereay));
                    model.setUrl_image(R.drawable.yosem_elshereay);
                    name_sora.add(model);
                }
                break;
            case 48:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesMoftahElsalatnyEldawary[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.moftah_elslty));
                    model.setUrl_image(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;
            case 49:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesMoftahElsalatnyEnbZakor[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.moftah_elslty));
                    model.setUrl_image(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;
            case 50:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesMoftahHafs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.moftah_elslty));
                    model.setUrl_image(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;
            case 51:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinklinkesMoftahElsltny[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.moftah_elslty));
                    model.setUrl_image(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;

            case 52:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesTwfeaElsayegh[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.tawfik_sayegh));
                    model.setUrl_image(R.drawable.tawfik_sayegh);
                    name_sora.add(model);
                }
                break;
            case 53:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesRamyEldeaas[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ramy_eldabs));
                    model.setUrl_image(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 54:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesSahlYassen[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.sahl_yassen));
                    model.setUrl_image(R.drawable.sahl_yassen);
                    name_sora.add(model);
                }
                break;
            case 55:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesAbdelbariElthebety[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelbara_elthebety));
                    model.setUrl_image(R.drawable.abdelbara_elthebety);
                    name_sora.add(model);
                }
                break;
            case 56:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesAbdelrhmanEloasi[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelrhman_elosa));
                    model.setUrl_image(R.drawable.abdelrhman_elosa);
                    name_sora.add(model);
                }
                break;
            case 57:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkMAHER_ELMEAQLY[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.maher_elmeaqly));
                    model.setUrl_image(R.drawable.almaqely);
                    name_sora.add(model);
                }
                break;
            case 58:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesWadeaElyamany[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.wadea_elyamany));
                    model.setUrl_image(R.drawable.wadea_elyamany);
                    name_sora.add(model);
                }
                break;
            case 59:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkNabelElrefay[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.nabel_elrefaay));
                    model.setUrl_image(R.drawable.nabel_elrefaay);
                    name_sora.add(model);
                }
                break;

            case 60:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkWaledElnaahy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.waled_elnaehy));
                    model.setUrl_image(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 61:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkYaserElmazroay[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.yaser_elmazroay));
                    model.setUrl_image(R.drawable.yaser_elmazroay);
                    name_sora.add(model);
                }
                break;
            case 62:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesHatemFared[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.hatem_fared));
                    model.setUrl_image(R.drawable.hatem_fared);
                    name_sora.add(model);
                }
                break;
            case 63:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesKhalidElmohana[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.khaled_elmehana));
                    model.setUrl_image(R.drawable.khaled_elmehana);
                    name_sora.add(model);
                }
                break;
            case 64:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArraylinkesKhalidAbdElkafy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.khaled_abdelkafy));
                    model.setUrl_image(R.drawable.khaled_abdelkafy);
                    name_sora.add(model);
                }
                break;
            case 65:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesKhaledElgalel[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.jleel));
                    model.setUrl_image(R.drawable.jleel);
                    name_sora.add(model);
                }
                break;
            case 66:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesKhaledElqahtamny[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.qht));
                    model.setUrl_image(R.drawable.qht);
                    name_sora.add(model);
                }
                break;
            case 67:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesKhalefaEltenegy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.tnjy));
                    model.setUrl_image(R.drawable.tnjy);
                    name_sora.add(model);
                }
                break;
            case 68:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesSadElghamdy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.s_gmd));
                    model.setUrl_image(R.drawable.s_gmd);
                    name_sora.add(model);
                }
                break;
            case 69:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkeSadElmeqren[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.saad));
                    model.setUrl_image(R.drawable.saad);
                    name_sora.add(model);
                }
                break;
            case 70:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkeSadElmeqren[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.sayed));
                    model.setUrl_image(R.drawable.syed_rmdan);
                    name_sora.add(model);
                }
                break;
            case 71:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesElshatry[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.shatri));
                    model.setUrl_image(R.drawable.shatri);
                    name_sora.add(model);
                }
                break;
            case 72:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesShahrzadTaher[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.taher));
                    model.setUrl_image(R.drawable.taher);
                    name_sora.add(model);
                }
                break;
            case 73:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesHKM[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.hkm));
                    model.setUrl_image(R.drawable.hkm);
                    name_sora.add(model);
                }
                break;
            case 74:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesSalahHashim[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.salah_hashim_m));
                    model.setUrl_image(R.drawable.salah_hashim_m);
                    name_sora.add(model);
                }
                break;
            case 75:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesBuKhtr[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.bu_khtr));
                    model.setUrl_image(R.drawable.bu_khtr);
                    name_sora.add(model);
                }
                break;
            case 76:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesTareq[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.tareq));
                    model.setUrl_image(R.drawable.tarek_abdelany);
                    name_sora.add(model);
                }
                break;
            case 77:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesRyan[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ryan));
                    model.setUrl_image(R.drawable.ryan);
                    name_sora.add(model);
                }
                break;
            case 78:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesBary[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.bari));
                    model.setUrl_image(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 79:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElsodes[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelrhman_elsodes));
                    model.setUrl_image(R.drawable.elsodes);
                    name_sora.add(model);
                }
                break;
            case 80:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElklbanyHafs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.adel_elkalbany));
                    model.setUrl_image(R.drawable.elklbany);
                    name_sora.add(model);
                }
                break;
            case 81:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAbdelrhmanMaged[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.a_majed));
                    model.setUrl_image(R.drawable.a_majed);
                    name_sora.add(model);
                }
                break;
            case 82:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesSoufiHfs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.soufi_hfs));
                    model.setUrl_image(R.drawable.soufi_hfs);
                    name_sora.add(model);
                }
                break;
            case 83:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAbdelazezAhmed[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.a_ahmed));
                    model.setUrl_image(R.drawable.a_ahmed);
                    name_sora.add(model);
                }
                break;
            case 84:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkeKkhalf[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.khalf));
                    model.setUrl_image(R.drawable.khalf);
                    name_sora.add(model);
                }
                break;
            case 85:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAbdalah[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdullahk));
                    model.setUrl_image(R.drawable.abdullahk);
                    name_sora.add(model);
                }
                break;
            case 86:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesBsfr[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.bsfr));
                    model.setUrl_image(R.drawable.bsfr);
                    name_sora.add(model);
                }
                break;
            case 87:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesKyat[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.kyat));
                    model.setUrl_image(R.drawable.kyat);
                    name_sora.add(model);
                }
                break;
            case 88:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesGulan[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.gulan));
                    model.setUrl_image(R.drawable.gulan);
                    name_sora.add(model);
                }
                break;
            case 89:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesmohsin_harthi[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mohsin_harthi));
                    model.setUrl_image(R.drawable.mohsin_harthi);
                    name_sora.add(model);
                }
                break;
            case 90:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesObk[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.obk));
                    model.setUrl_image(R.drawable.obk);
                    name_sora.add(model);
                }
                break;
            case 91:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesKanakeri[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.kanakeri));
                    model.setUrl_image(R.drawable.kanakeri);
                    name_sora.add(model);
                }
                break;
            case 92:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesWdod[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.wdod));
                    model.setUrl_image(R.drawable.wdod);
                    name_sora.add(model);
                }
                break;
            case 93:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAjbr[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.a_jbr));
                    model.setUrl_image(R.drawable.a_jbr);
                    name_sora.add(model);
                }
                break;
            case 94:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesHafz[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.hafz));
                    model.setUrl_image(R.drawable.hafz);
                    name_sora.add(model);
                }
                break;
            case 95:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesFrs_a[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.frs_a));
                    model.setUrl_image(R.drawable.frs_a);
                    name_sora.add(model);
                }
                break;
            case 96:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesShakshFs[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.shaksh));
                    model.setUrl_image(R.drawable.shaksh);
                    name_sora.add(model);
                }
                break;
            case 97:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesAyyub[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ayyub));
                    model.setUrl_image(R.drawable.ayyub);
                    name_sora.add(model);
                }
                break;
            case 98:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesEarawi[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.earawi));
                    model.setUrl_image(R.drawable.earawi);
                    name_sora.add(model);
                }
                break;


            case 99:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesMkreem_warsh[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.m_krm));
                    model.setUrl_image(R.drawable.m_krm);
                    name_sora.add(model);
                }
                break;
            case 100:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesM_krm[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.m_krm));
                    model.setUrl_image(R.drawable.m_krm);
                    name_sora.add(model);
                }
                break;
            case 101:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesShah[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.shah));
                    model.setUrl_image(R.drawable.shah);
                    name_sora.add(model);
                }
                break;
            case 102:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkOmarElqzabry[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.omar_kzabri));
                    model.setUrl_image(R.drawable.omar_kzabri);
                    name_sora.add(model);
                }
                break;
            case 103:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesRashad[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.rashad));
                    model.setUrl_image(R.drawable.rashad);
                    name_sora.add(model);
                }
                break;


            case 104:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesLhdan[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.lhdan));
                    model.setUrl_image(R.drawable.lhdan);
                    name_sora.add(model);
                }
                break;


            case 105:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesKhan[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.khan));
                    model.setUrl_image(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 106:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesMrifai[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mrifai));
                    model.setUrl_image(R.drawable.mrifai);
                    name_sora.add(model);
                }
                break;
            case 107:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesSheimy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.sheimy));
                    model.setUrl_image(R.drawable.sheimy);
                    name_sora.add(model);
                }
                break;
            case 108:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(ArrayLinkElqarashy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.yaser_elqarashy));
                    model.setUrl_image(R.drawable.elqarashy);
                    name_sora.add(model);
                }
                break;

            case 109:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesMustafa[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mustafa));
                    model.setUrl_image(R.drawable.mustafa);
                    name_sora.add(model);
                }
                break;
            case 110:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesLahoni[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.lahoni));
                    model.setUrl_image(R.drawable.lahoni);
                    name_sora.add(model);
                }
                break;
            case 111:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesRa3ad[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.ra3ad));
                    model.setUrl_image(R.drawable.ra3ad);
                    name_sora.add(model);
                }
                break;
            case 112:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesHarthi[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.harthi));
                    model.setUrl_image(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 113:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesBilal[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.bilal));
                    model.setUrl_image(R.drawable.bilal);
                    name_sora.add(model);
                }
                break;
            case 114:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesNasser_almajed[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.nasser_almajed));
                    model.setUrl_image(R.drawable.nasser_almajed);
                    name_sora.add(model);
                }
                break;
            case 115:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesNamh[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.namh));
                    model.setUrl_image(R.drawable.namh);
                    name_sora.add(model);
                }
                break;
            case 116:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesHani[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.hani));
                    model.setUrl_image(R.drawable.hani);
                    name_sora.add(model);
                }
                break;
            case 117:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesSalamah[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.salamah));
                    model.setUrl_image(R.drawable.salamah);
                    name_sora.add(model);
                }
                break;
            case 118:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesYahya[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.yahya));
                    model.setUrl_image(R.drawable.yahya);
                    name_sora.add(model);
                }
                break;
            case 119:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setName_sora(soar[i]);
                    model.setSora_link(linkesNoah[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.noah));
                    model.setUrl_image(R.drawable.noah);
                    name_sora.add(model);
                }
                break;
            default:
                Toast.makeText(application.getApplicationContext(), "لم يتم ادخال قائمة بعد", Toast.LENGTH_SHORT).show();
        }
        return name_sora;
    }

}