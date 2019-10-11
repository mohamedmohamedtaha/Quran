package com.MohamedTaha.Imagine.Quran.viewmodel;

import android.app.Application;
import android.content.pm.PackageManager;

import androidx.lifecycle.MutableLiveData;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreImagesRepository {
    List<Integer> images = new ArrayList<>();
    private List<ModelSora> name_Sroa = new ArrayList<>();
    private MutableLiveData<List<ModelSora>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Integer>> mutableLiveDataImage = new MutableLiveData<>();

    private Application application;
    private String [] a =null;

    public StoreImagesRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<ModelSora>> getMutableLiveData(){
      // mutableLiveData.setValue(Arrays.asList(a));
        name_Sroa  = new ArrayList<>();
          setName_Sroa();
          mutableLiveData.setValue(name_Sroa);

        return mutableLiveData;
    }
    public MutableLiveData<List<Integer>> getMutableLiveDataImage(){
         addImages ();
        mutableLiveDataImage.setValue(images);
        return mutableLiveDataImage;
    }
    private void setName_Sroa(){
        a = application.getResources().getStringArray(R.array.name_allSwar);
        for (int i = 0; i < a.length; i++) {
            ModelSora name_Sroa_local = new ModelSora();
            name_Sroa_local.setName_sora(a[i]);
            name_Sroa_local.setPosition(i);
            name_Sroa.add(name_Sroa_local);

        }
    }


    public void addImages() {
        images.add(R.drawable.page2);
        images.add(R.drawable.page3);
        images.add(R.drawable.page4);
        images.add(R.drawable.page5);
        images.add(R.drawable.page6);
        images.add(R.drawable.page7);
        images.add(R.drawable.page8);
        images.add(R.drawable.page9);
        images.add(R.drawable.page10);
        images.add(R.drawable.page11);
        images.add(R.drawable.page12);
        images.add(R.drawable.page13);
        images.add(R.drawable.page14);
        images.add(R.drawable.page15);
        images.add(R.drawable.page16);
        images.add(R.drawable.page17);
        images.add(R.drawable.page18);
        images.add(R.drawable.page19);
        images.add(R.drawable.page21);
        images.add(R.drawable.page22);
        images.add(R.drawable.page23);
        images.add(R.drawable.page24);
        images.add(R.drawable.page25);
        images.add(R.drawable.page26);
        images.add(R.drawable.page27);
        images.add(R.drawable.page28);
        images.add(R.drawable.page29);
        images.add(R.drawable.page30);
        images.add(R.drawable.page31);
        images.add(R.drawable.page32);
        images.add(R.drawable.page33);
        images.add(R.drawable.page34);
        images.add(R.drawable.page35);
        images.add(R.drawable.page36);
        images.add(R.drawable.page37);
        images.add(R.drawable.page38);
        images.add(R.drawable.page39);
        images.add(R.drawable.page40);
        images.add(R.drawable.page41);
        images.add(R.drawable.page42);
        images.add(R.drawable.page43);
        images.add(R.drawable.page44);
        images.add(R.drawable.page45);
        images.add(R.drawable.page46);
        images.add(R.drawable.page47);
        images.add(R.drawable.page48);
        images.add(R.drawable.page49);
        images.add(R.drawable.page50);
        images.add(R.drawable.page51);
      /*  images.add(R.drawable.page52);
        images.add(R.drawable.page53);
        images.add(R.drawable.page54);
        images.add(R.drawable.page55);
        images.add(R.drawable.page56);
        images.add(R.drawable.page57);
        images.add(R.drawable.page58);
        images.add(R.drawable.page59);
        images.add(R.drawable.page60);
        images.add(R.drawable.page61);
        images.add(R.drawable.page62);
        images.add(R.drawable.page63);
        images.add(R.drawable.page64);
        images.add(R.drawable.page65);
        images.add(R.drawable.page66);
        images.add(R.drawable.page67);
        images.add(R.drawable.page68);
        images.add(R.drawable.page69);
        images.add(R.drawable.page70);
        images.add(R.drawable.page71);
        images.add(R.drawable.page72);
        images.add(R.drawable.page73);
        images.add(R.drawable.page74);
        images.add(R.drawable.page75);
        images.add(R.drawable.page76);
        images.add(R.drawable.page77);
        images.add(R.drawable.page78);
        images.add(R.drawable.page79);
        images.add(R.drawable.page80);
        images.add(R.drawable.page81);
        images.add(R.drawable.page82);
        images.add(R.drawable.page83);
        images.add(R.drawable.page84);
        images.add(R.drawable.page85);
        images.add(R.drawable.page86);
        images.add(R.drawable.page87);
        images.add(R.drawable.page88);
        images.add(R.drawable.page89);
        images.add(R.drawable.page90);
        images.add(R.drawable.page91);
        images.add(R.drawable.page92);
        images.add(R.drawable.page93);
        images.add(R.drawable.page94);
        images.add(R.drawable.page95);
        images.add(R.drawable.page96);
        images.add(R.drawable.page97);
        images.add(R.drawable.page98);
        images.add(R.drawable.page99);
        images.add(R.drawable.page101);
        images.add(R.drawable.page102);
        images.add(R.drawable.page103);
        images.add(R.drawable.page104);
        images.add(R.drawable.page105);
        images.add(R.drawable.page106);
        images.add(R.drawable.page107);
        images.add(R.drawable.page108);
        images.add(R.drawable.page109);
        images.add(R.drawable.page110);
        images.add(R.drawable.page111);
        images.add(R.drawable.page112);
        images.add(R.drawable.page113);
        images.add(R.drawable.page114);
        images.add(R.drawable.page115);
        images.add(R.drawable.page116);
        images.add(R.drawable.page117);
        images.add(R.drawable.page118);
        images.add(R.drawable.page119);
        images.add(R.drawable.page120);
        images.add(R.drawable.page121);
        images.add(R.drawable.page122);
        images.add(R.drawable.page123);
        images.add(R.drawable.page124);
        images.add(R.drawable.page125);
        images.add(R.drawable.page126);
        images.add(R.drawable.page127);
        images.add(R.drawable.page128);
        images.add(R.drawable.page129);
        images.add(R.drawable.page130);
        images.add(R.drawable.page131);
        images.add(R.drawable.page132);
        images.add(R.drawable.page133);
        images.add(R.drawable.page134);
        images.add(R.drawable.page135);
        images.add(R.drawable.page136);
        images.add(R.drawable.page137);
        images.add(R.drawable.page138);
        images.add(R.drawable.page139);
        images.add(R.drawable.page140);
        images.add(R.drawable.page141);
        images.add(R.drawable.page142);
        images.add(R.drawable.page143);
        images.add(R.drawable.page144);
        images.add(R.drawable.page145);
        images.add(R.drawable.page146);
        images.add(R.drawable.page147);
        images.add(R.drawable.page148);
        images.add(R.drawable.page149);
        images.add(R.drawable.page150);
        images.add(R.drawable.page151);
        images.add(R.drawable.page152);
        images.add(R.drawable.page153);
        images.add(R.drawable.page154);
        images.add(R.drawable.page155);
        images.add(R.drawable.page156);
        images.add(R.drawable.page157);
        images.add(R.drawable.page158);
        images.add(R.drawable.page159);
        images.add(R.drawable.page160);
        images.add(R.drawable.page161);
        images.add(R.drawable.page162);
        images.add(R.drawable.page163);
        images.add(R.drawable.page164);
        images.add(R.drawable.page165);
        images.add(R.drawable.page166);
        images.add(R.drawable.page167);
        images.add(R.drawable.page168);
        images.add(R.drawable.page169);
        images.add(R.drawable.page170);
        images.add(R.drawable.page171);
        images.add(R.drawable.page172);
        images.add(R.drawable.page173);
        images.add(R.drawable.page174);
        images.add(R.drawable.page175);
        images.add(R.drawable.page176);
        images.add(R.drawable.page177);
        images.add(R.drawable.page178);
        images.add(R.drawable.page179);
        images.add(R.drawable.page180);
        images.add(R.drawable.page181);
        images.add(R.drawable.page182);
        images.add(R.drawable.page183);
        images.add(R.drawable.page184);
        images.add(R.drawable.page185);
        images.add(R.drawable.page186);
        images.add(R.drawable.page187);
        images.add(R.drawable.page188);
        images.add(R.drawable.page189);
        images.add(R.drawable.page190);
        images.add(R.drawable.page191);
        images.add(R.drawable.page192);
        images.add(R.drawable.page193);
        images.add(R.drawable.page194);
        images.add(R.drawable.page195);
        images.add(R.drawable.page196);
        images.add(R.drawable.page197);
       images.add(R.drawable.page198);
        images.add(R.drawable.page199);
        images.add(R.drawable.page200);
        images.add(R.drawable.page201);
        images.add(R.drawable.page202);
        images.add(R.drawable.page203);
        images.add(R.drawable.page204);
        images.add(R.drawable.page205);*/

    }
}
