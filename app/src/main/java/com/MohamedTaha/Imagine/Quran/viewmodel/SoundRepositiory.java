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
        getShekhAyat(position);
        mutableLiveDataNameSora.setValue(name_sora);
        return mutableLiveDataNameSora;
    }

    public MutableLiveData<List<ImageModel>> getMutableLiveDataImageModel() {
        imageModels =  new ArrayList<>();
        getData();
        mutableLiveDataImageModel.setValue(imageModels);
        return mutableLiveDataImageModel;
    }

    private void getData() {
        imageModels.add(new ImageModel(application.getString(R.string.abdelrhman_elsodes), R.drawable.elsodes, application.getString(R.string.hafs_an_asem),0));
        imageModels.add(new ImageModel(application.getString(R.string.adel_elkalbany), R.drawable.elklbany, application.getString(R.string.hafs_an_asem),1));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_elmenshawyH), R.drawable.elmenshawy, application.getString(R.string.hafs_an_asem),2));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_elmenshawyM), R.drawable.elmenshawy, application.getString(R.string.ekmoshaf_elmogawad),3));
        imageModels.add(new ImageModel(application.getString(R.string.abdelbaset), R.drawable.abdeelbaset, application.getString(R.string.ekmoshaf_elmogawad),4));
        imageModels.add(new ImageModel(application.getString(R.string.mahmoud_elhosaryH), R.drawable.elhosary, application.getString(R.string.hafs_an_asem),5));
        imageModels.add(new ImageModel(application.getString(R.string.mahmoud_elhosaryM), R.drawable.elhosary, application.getString(R.string.ekmoshaf_elmogawad),6));
        imageModels.add(new ImageModel(application.getString(R.string.maher_elmeaqly), R.drawable.almaqely, application.getString(R.string.hafs_an_asem),7));
        imageModels.add(new ImageModel(application.getString(R.string.mshary_elafasy), R.drawable.msharyelafasy, application.getString(R.string.hafs_an_asem),8));
        imageModels.add(new ImageModel(application.getString(R.string.soud_elsherem), R.drawable.sherem, application.getString(R.string.hafs_an_asem),9));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_eltblawy), R.drawable.eltblawy, application.getString(R.string.hafs_an_asem),10));
        imageModels.add(new ImageModel(application.getString(R.string.yaser_eldosary), R.drawable.eldosary, application.getString(R.string.hafs_an_asem),11));
        imageModels.add(new ImageModel(application.getString(R.string.abdellah_elgeheny), R.drawable.elgeheny, application.getString(R.string.hafs_an_asem),12));
        imageModels.add(new ImageModel(application.getString(R.string.mohamed_gebrer), R.drawable.mohamedgbrer, application.getString(R.string.hafs_an_asem),13));
        imageModels.add(new ImageModel(application.getString(R.string.naser_elqatamy), R.drawable.naserelqatamy, application.getString(R.string.hafs_an_asem),14));
        imageModels.add(new ImageModel(application.getString(R.string.ahmed_elagamy), R.drawable.elagamy, application.getString(R.string.hafs_an_asem),15));
        imageModels.add(new ImageModel(application.getString(R.string.mahmoud_elbana), R.drawable.elbana, application.getString(R.string.hafs_an_asem),16));
        imageModels.add(new ImageModel(application.getString(R.string.yaser_elqarashy), R.drawable.elqarashy, application.getString(R.string.hafs_an_asem),17));
        imageModels.add(new ImageModel(application.getString(R.string.abdelrhman_elqasem), R.drawable.elqasem, application.getString(R.string.hafs_an_asem),18));
        imageModels.add(new ImageModel(application.getString(R.string.slah_elbadry), R.drawable.bder, application.getString(R.string.hafs_an_asem),19));
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
                    model.setSora_link(ArrayLinkElsodes[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.abdelrhman_elsodes));
                    model.setUrl_image(R.drawable.elsodes);
                    name_sora.add(model);
                }
                break;
            case 1:
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
            case 2:
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
            case 3:
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
            case 4:
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
                    model.setSora_link(ArrayLinkElhosaryMgwad[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.mahmoud_elhosaryM));
                    model.setUrl_image(R.drawable.elhosary);
                    name_sora.add(model);
                }
                break;
            case 7:
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
                    model.setSora_link(ArrayLinkElqarashy[i]);
                    model.setPosition(Integer.valueOf(positions[i]));
                    model.setName_shekh(application.getString(R.string.yaser_elqarashy));
                    model.setUrl_image(R.drawable.elqarashy);
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
            default:
                Toast.makeText(application.getApplicationContext(), "لم يتم ادخال قائمة بعد", Toast.LENGTH_SHORT).show();
        }
        return name_sora;
    }

}