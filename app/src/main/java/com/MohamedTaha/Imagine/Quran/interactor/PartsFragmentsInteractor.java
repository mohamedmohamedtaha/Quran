package com.MohamedTaha.Imagine.Quran.interactor;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.MohamedTaha.Imagine.Quran.presenter.PartsFragmentPresenter;
import com.MohamedTaha.Imagine.Quran.view.PartsFragmentView;
import com.MohamedTaha.Imagine.Quran.viewmodel.ImagesViewHolder;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PartsFragmentsInteractor implements PartsFragmentPresenter {
    private PartsFragmentView partsFragmentView;
    private String[] name_parts;
    private Context context;
    List<Integer> images = new ArrayList<>();
    private List<ModelSora> name_part_list = new ArrayList<>();
    public static final String SAVE_POSITION = "save_position";

    public PartsFragmentsInteractor(PartsFragmentView partsFragmentView, FragmentActivity fragmentActivity) {
        this.context = fragmentActivity;
        this.partsFragmentView = partsFragmentView;
    }

    public void addImages() {
        images.add(R.drawable.page1);
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
        images.add(R.drawable.page20);
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
        images.add(R.drawable.page52);
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
        images.add(R.drawable.page100);
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
        images.add(R.drawable.page205);
        images.add(R.drawable.page206);
        images.add(R.drawable.page207);
        images.add(R.drawable.page208);
        images.add(R.drawable.page209);
        images.add(R.drawable.page210);
        images.add(R.drawable.page211);
        images.add(R.drawable.page212);
        images.add(R.drawable.page213);
        images.add(R.drawable.page214);
        images.add(R.drawable.page215);
        images.add(R.drawable.page216);
        images.add(R.drawable.page217);
        images.add(R.drawable.page218);
        images.add(R.drawable.page219);
        images.add(R.drawable.page220);
        images.add(R.drawable.page221);
        images.add(R.drawable.page222);
        images.add(R.drawable.page223);
        images.add(R.drawable.page224);
        images.add(R.drawable.page225);
        images.add(R.drawable.page226);
        images.add(R.drawable.page227);
        images.add(R.drawable.page228);
        images.add(R.drawable.page229);
        images.add(R.drawable.page230);
        images.add(R.drawable.page231);
        images.add(R.drawable.page232);
        images.add(R.drawable.page233);
        images.add(R.drawable.page234);
        images.add(R.drawable.page235);
        images.add(R.drawable.page236);
        images.add(R.drawable.page237);
        images.add(R.drawable.page238);
        images.add(R.drawable.page239);
        images.add(R.drawable.page240);
        images.add(R.drawable.page241);
        images.add(R.drawable.page242);
        images.add(R.drawable.page243);
        images.add(R.drawable.page244);
        images.add(R.drawable.page245);
        images.add(R.drawable.page246);
        images.add(R.drawable.page247);
        images.add(R.drawable.page248);
        images.add(R.drawable.page249);
        images.add(R.drawable.page250);
        images.add(R.drawable.page251);
        images.add(R.drawable.page252);
        images.add(R.drawable.page253);
        images.add(R.drawable.page254);
        images.add(R.drawable.page255);
        images.add(R.drawable.page256);
        images.add(R.drawable.page257);
        images.add(R.drawable.page258);
        images.add(R.drawable.page259);
        images.add(R.drawable.page260);
        images.add(R.drawable.page261);
        images.add(R.drawable.page262);
        images.add(R.drawable.page263);
        images.add(R.drawable.page264);
        images.add(R.drawable.page265);
        images.add(R.drawable.page266);
        images.add(R.drawable.page267);
        images.add(R.drawable.page268);
        images.add(R.drawable.page269);
        images.add(R.drawable.page270);
        images.add(R.drawable.page271);
        images.add(R.drawable.page272);
        images.add(R.drawable.page273);
        images.add(R.drawable.page274);
        images.add(R.drawable.page275);
        images.add(R.drawable.page276);
        images.add(R.drawable.page277);
        images.add(R.drawable.page278);
        images.add(R.drawable.page279);
        images.add(R.drawable.page280);
        images.add(R.drawable.page281);
        images.add(R.drawable.page282);
        images.add(R.drawable.page283);
        images.add(R.drawable.page284);
        images.add(R.drawable.page285);
        images.add(R.drawable.page286);
        images.add(R.drawable.page287);
        images.add(R.drawable.page288);
        images.add(R.drawable.page289);
        images.add(R.drawable.page290);
        images.add(R.drawable.page291);
        images.add(R.drawable.page292);
        images.add(R.drawable.page293);
        images.add(R.drawable.page294);
        images.add(R.drawable.page295);
        images.add(R.drawable.page296);
        images.add(R.drawable.page297);
        images.add(R.drawable.page298);
        images.add(R.drawable.page299);
        images.add(R.drawable.page300);
        images.add(R.drawable.page301);
        images.add(R.drawable.page302);
        images.add(R.drawable.page303);
        images.add(R.drawable.page304);
        images.add(R.drawable.page305);
        images.add(R.drawable.page306);
        images.add(R.drawable.page307);
        images.add(R.drawable.page308);
        images.add(R.drawable.page309);
        images.add(R.drawable.page310);
        images.add(R.drawable.page311);
        images.add(R.drawable.page312);
        images.add(R.drawable.page313);
        images.add(R.drawable.page314);
        images.add(R.drawable.page315);
        images.add(R.drawable.page316);
        images.add(R.drawable.page317);
        images.add(R.drawable.page318);
        images.add(R.drawable.page319);
        images.add(R.drawable.page320);
        images.add(R.drawable.page321);
        images.add(R.drawable.page322);
        images.add(R.drawable.page323);
        images.add(R.drawable.page324);
        images.add(R.drawable.page325);
        images.add(R.drawable.page326);
        images.add(R.drawable.page327);
        images.add(R.drawable.page328);
        images.add(R.drawable.page329);
        images.add(R.drawable.page330);
        images.add(R.drawable.page331);
        images.add(R.drawable.page332);
        images.add(R.drawable.page333);
        images.add(R.drawable.page334);
        images.add(R.drawable.page335);
        images.add(R.drawable.page336);
        images.add(R.drawable.page337);
        images.add(R.drawable.page338);
        images.add(R.drawable.page339);
        images.add(R.drawable.page340);
        images.add(R.drawable.page341);
        images.add(R.drawable.page342);
        images.add(R.drawable.page343);
        images.add(R.drawable.page344);
        images.add(R.drawable.page345);
        images.add(R.drawable.page346);
        images.add(R.drawable.page347);
        images.add(R.drawable.page348);
        images.add(R.drawable.page349);
        images.add(R.drawable.page350);
        images.add(R.drawable.page351);
        images.add(R.drawable.page352);
        images.add(R.drawable.page353);
        images.add(R.drawable.page354);
        images.add(R.drawable.page355);
        images.add(R.drawable.page356);
        images.add(R.drawable.page357);
        images.add(R.drawable.page358);
        images.add(R.drawable.page359);
        images.add(R.drawable.page360);
        images.add(R.drawable.page361);
        images.add(R.drawable.page362);
        images.add(R.drawable.page363);
        images.add(R.drawable.page364);
        images.add(R.drawable.page365);
        images.add(R.drawable.page366);
        images.add(R.drawable.page367);
        images.add(R.drawable.page368);
        images.add(R.drawable.page369);
        images.add(R.drawable.page370);
        images.add(R.drawable.page371);
        images.add(R.drawable.page372);
        images.add(R.drawable.page373);
        images.add(R.drawable.page374);
        images.add(R.drawable.page375);
        images.add(R.drawable.page376);
        images.add(R.drawable.page377);
        images.add(R.drawable.page378);
        images.add(R.drawable.page379);
        images.add(R.drawable.page380);
        images.add(R.drawable.page381);
        images.add(R.drawable.page382);
        images.add(R.drawable.page383);
        images.add(R.drawable.page384);
        images.add(R.drawable.page385);
        images.add(R.drawable.page386);
        images.add(R.drawable.page387);
        images.add(R.drawable.page388);
        images.add(R.drawable.page389);
        images.add(R.drawable.page390);
        images.add(R.drawable.page391);
        images.add(R.drawable.page392);
        images.add(R.drawable.page393);
        images.add(R.drawable.page394);
        images.add(R.drawable.page395);
        images.add(R.drawable.page396);
        images.add(R.drawable.page397);
        images.add(R.drawable.page398);
        images.add(R.drawable.page399);
        images.add(R.drawable.page400);
        images.add(R.drawable.page401);
        images.add(R.drawable.page402);
        images.add(R.drawable.page403);
        images.add(R.drawable.page404);
        images.add(R.drawable.page405);
        images.add(R.drawable.page406);
        images.add(R.drawable.page407);
        images.add(R.drawable.page408);
        images.add(R.drawable.page409);
        images.add(R.drawable.page410);
        images.add(R.drawable.page411);
        images.add(R.drawable.page412);
        images.add(R.drawable.page413);
        images.add(R.drawable.page414);
        images.add(R.drawable.page415);
        images.add(R.drawable.page416);
        images.add(R.drawable.page417);
        images.add(R.drawable.page418);
        images.add(R.drawable.page419);
        images.add(R.drawable.page420);
        images.add(R.drawable.page421);
        images.add(R.drawable.page422);
        images.add(R.drawable.page423);
        images.add(R.drawable.page424);
        images.add(R.drawable.page425);
        images.add(R.drawable.page426);
        images.add(R.drawable.page427);
        images.add(R.drawable.page428);
        images.add(R.drawable.page429);
        images.add(R.drawable.page430);
        images.add(R.drawable.page431);
        images.add(R.drawable.page432);
        images.add(R.drawable.page433);
        images.add(R.drawable.page434);
        images.add(R.drawable.page435);
        images.add(R.drawable.page436);
        images.add(R.drawable.page437);
        images.add(R.drawable.page438);
        images.add(R.drawable.page439);
        images.add(R.drawable.page440);
        images.add(R.drawable.page441);
        images.add(R.drawable.page442);
        images.add(R.drawable.page443);
        images.add(R.drawable.page444);
        images.add(R.drawable.page445);
        images.add(R.drawable.page446);
        images.add(R.drawable.page447);
        images.add(R.drawable.page448);
        images.add(R.drawable.page449);
        images.add(R.drawable.page450);
        images.add(R.drawable.page451);
        images.add(R.drawable.page452);
        images.add(R.drawable.page453);
        images.add(R.drawable.page454);
        images.add(R.drawable.page455);
        images.add(R.drawable.page456);
        images.add(R.drawable.page457);
        images.add(R.drawable.page458);
        images.add(R.drawable.page459);
        images.add(R.drawable.page460);
        images.add(R.drawable.page461);
        images.add(R.drawable.page462);
        images.add(R.drawable.page463);
        images.add(R.drawable.page464);
        images.add(R.drawable.page465);
        images.add(R.drawable.page466);
        images.add(R.drawable.page467);
        images.add(R.drawable.page468);
        images.add(R.drawable.page469);
        images.add(R.drawable.page470);
        images.add(R.drawable.page471);
        images.add(R.drawable.page472);
        images.add(R.drawable.page473);
        images.add(R.drawable.page474);
        images.add(R.drawable.page475);
        images.add(R.drawable.page476);
        images.add(R.drawable.page477);
        images.add(R.drawable.page478);
        images.add(R.drawable.page479);
        images.add(R.drawable.page480);
        images.add(R.drawable.page481);
        images.add(R.drawable.page482);
        images.add(R.drawable.page483);
        images.add(R.drawable.page484);
        images.add(R.drawable.page485);
        images.add(R.drawable.page486);
        images.add(R.drawable.page487);
        images.add(R.drawable.page488);
        images.add(R.drawable.page489);
        images.add(R.drawable.page490);
        images.add(R.drawable.page491);
        images.add(R.drawable.page492);
        images.add(R.drawable.page493);
        images.add(R.drawable.page494);
        images.add(R.drawable.page495);
        images.add(R.drawable.page496);
        images.add(R.drawable.page497);
        images.add(R.drawable.page498);
        images.add(R.drawable.page499);
        images.add(R.drawable.page500);
        images.add(R.drawable.page501);
        images.add(R.drawable.page502);
        images.add(R.drawable.page503);
        images.add(R.drawable.page504);
        images.add(R.drawable.page505);
        images.add(R.drawable.page506);
        images.add(R.drawable.page507);
        images.add(R.drawable.page508);
        images.add(R.drawable.page509);
        images.add(R.drawable.page510);
        images.add(R.drawable.page511);
        images.add(R.drawable.page512);
        images.add(R.drawable.page513);
        images.add(R.drawable.page514);
        images.add(R.drawable.page515);
        images.add(R.drawable.page516);
        images.add(R.drawable.page517);
        images.add(R.drawable.page518);
        images.add(R.drawable.page519);
        images.add(R.drawable.page520);
        images.add(R.drawable.page521);
        images.add(R.drawable.page522);
        images.add(R.drawable.page523);
        images.add(R.drawable.page524);
        images.add(R.drawable.page525);
        images.add(R.drawable.page526);
        images.add(R.drawable.page527);
        images.add(R.drawable.page528);
        images.add(R.drawable.page529);
        images.add(R.drawable.page530);
        images.add(R.drawable.page531);
        images.add(R.drawable.page532);
        images.add(R.drawable.page533);
        images.add(R.drawable.page534);
        images.add(R.drawable.page535);
        images.add(R.drawable.page536);
        images.add(R.drawable.page537);
        images.add(R.drawable.page538);
        images.add(R.drawable.page539);
        images.add(R.drawable.page540);
        images.add(R.drawable.page541);
        images.add(R.drawable.page542);
        images.add(R.drawable.page543);
        images.add(R.drawable.page544);
        images.add(R.drawable.page545);
        images.add(R.drawable.page546);
        images.add(R.drawable.page547);
        images.add(R.drawable.page548);
        images.add(R.drawable.page549);
        images.add(R.drawable.page550);
        images.add(R.drawable.page551);
        images.add(R.drawable.page552);
        images.add(R.drawable.page553);
        images.add(R.drawable.page554);
        images.add(R.drawable.page555);
        images.add(R.drawable.page556);
        images.add(R.drawable.page557);
        images.add(R.drawable.page558);
        images.add(R.drawable.page559);
        images.add(R.drawable.page560);
        images.add(R.drawable.page561);
        images.add(R.drawable.page562);
        images.add(R.drawable.page563);
        images.add(R.drawable.page564);
        images.add(R.drawable.page565);
        images.add(R.drawable.page566);
        images.add(R.drawable.page567);
        images.add(R.drawable.page568);
        images.add(R.drawable.page569);
        images.add(R.drawable.page570);
        images.add(R.drawable.page571);
        images.add(R.drawable.page572);
        images.add(R.drawable.page573);
        images.add(R.drawable.page574);
        images.add(R.drawable.page575);
        images.add(R.drawable.page576);
        images.add(R.drawable.page577);
        images.add(R.drawable.page578);
        images.add(R.drawable.page579);
        images.add(R.drawable.page580);
        images.add(R.drawable.page581);
        images.add(R.drawable.page582);
        images.add(R.drawable.page583);
        images.add(R.drawable.page584);
        images.add(R.drawable.page585);
        images.add(R.drawable.page586);
        images.add(R.drawable.page587);
        images.add(R.drawable.page588);
        images.add(R.drawable.page589);
        images.add(R.drawable.page590);
        images.add(R.drawable.page591);
        images.add(R.drawable.page592);
        images.add(R.drawable.page593);
        images.add(R.drawable.page594);
        images.add(R.drawable.page595);
        images.add(R.drawable.page596);
        images.add(R.drawable.page597);
        images.add(R.drawable.page598);
        images.add(R.drawable.page599);
        images.add(R.drawable.page600);
        images.add(R.drawable.page601);
        images.add(R.drawable.page602);
        images.add(R.drawable.page603);
        images.add(R.drawable.page604);
        images.add(R.drawable.page605);
        images.add(R.drawable.page606);
        images.add(R.drawable.page607);
        images.add(R.drawable.page608);
        images.add(R.drawable.page609);
        images.add(R.drawable.page610);
        images.add(R.drawable.page611);
        images.add(R.drawable.page612);
        images.add(R.drawable.page613);
        images.add(R.drawable.page614);
        images.add(R.drawable.page615);
        images.add(R.drawable.page616);
        images.add(R.drawable.page617);

    }

    @Override
    public void getAllImages() {
        if (partsFragmentView != null) {
            partsFragmentView.showProgress();
            addImages();
            Observable<List<Integer>> modelAzkarObservable = Observable.just(images)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
            modelAzkarObservable.subscribe(new Subscriber<List<Integer>>() {
                @Override
                public void onCompleted() {
                    if (partsFragmentView != null) {
                        partsFragmentView.hideProgress();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (partsFragmentView != null) {
                        partsFragmentView.hideProgress();
                    }
                }

                @Override
                public void onNext(List<Integer> integers) {
                    if (partsFragmentView != null) {
                        partsFragmentView.showAllImages(integers);
                    }
                }
            });
        }
    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        switch (position) {
            case 0:
                bundle.putInt(SAVE_POSITION, 0);
                break;
            case 1:
                bundle.putInt(SAVE_POSITION, 21);
                break;
            case 2:
                bundle.putInt(SAVE_POSITION, 41);
                break;
            case 3:
                bundle.putInt(SAVE_POSITION, 61);
                break;
            case 4:
                bundle.putInt(SAVE_POSITION, 81);
                break;
            case 5:
                bundle.putInt(SAVE_POSITION, 101);
                break;
            case 6:
                bundle.putInt(SAVE_POSITION, 120);
                break;
            case 7:
                bundle.putInt(SAVE_POSITION, 141);
                break;
            case 8:
                bundle.putInt(SAVE_POSITION, 161);
                break;
            case 9:
                bundle.putInt(SAVE_POSITION, 181);
                break;
            case 10:
                bundle.putInt(SAVE_POSITION, 200);
                break;
            case 11:
                bundle.putInt(SAVE_POSITION, 221);
                break;
            case 12:
                bundle.putInt(SAVE_POSITION, 240);
                break;
            case 13:
                bundle.putInt(SAVE_POSITION, 261);
                break;
            case 14:
                bundle.putInt(SAVE_POSITION, 281);
                break;
            case 15:
                bundle.putInt(SAVE_POSITION, 301);
                break;
            case 16:
                bundle.putInt(SAVE_POSITION, 321);
                break;
            case 17:
                bundle.putInt(SAVE_POSITION, 341);
                break;
            case 18:
                bundle.putInt(SAVE_POSITION, 361);
                break;
            case 19:
                bundle.putInt(SAVE_POSITION, 381);
                break;
            case 20:
                bundle.putInt(SAVE_POSITION, 401);
                break;
            case 21:
                bundle.putInt(SAVE_POSITION, 421);
                break;
            case 22:
                bundle.putInt(SAVE_POSITION, 441);
                break;
            case 23:
                bundle.putInt(SAVE_POSITION, 461);
                break;
            case 24:
                bundle.putInt(SAVE_POSITION, 481);
                break;
            case 25:
                bundle.putInt(SAVE_POSITION, 501);
                break;
            case 26:
                bundle.putInt(SAVE_POSITION, 521);
                break;
            case 27:
                bundle.putInt(SAVE_POSITION, 541);
                break;
            case 28:
                bundle.putInt(SAVE_POSITION, 561);
                break;
            case 29:
                bundle.putInt(SAVE_POSITION, 581);
                break;
            case 30:
                bundle.putInt(SAVE_POSITION, 604);
                break;
        }
    }

    @Override
    public void getAllPartSoura() {
        Observable<List<ModelSora>> modelAzkarObservable = Observable.create(new Observable.OnSubscribe<List<ModelSora>>() {
            @Override
            public void call(Subscriber<? super List<ModelSora>> subscriber) {
                try {
                    if (partsFragmentView != null) {
                        name_parts = context.getResources().getStringArray(R.array.allParts);
                        for (int i = 0; i < name_parts.length; i++) {
                            ModelSora name_Sroa_local = new ModelSora();
                            name_Sroa_local.setName_part(name_parts[i]);
                            name_Sroa_local.setPosition(i);
                            name_part_list.add(name_Sroa_local);
                        }
                    }
                    subscriber.onNext(name_part_list);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List<ModelSora>> observer = new Observer<List<ModelSora>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ModelSora> modelSoras) {
                if (partsFragmentView != null) {
                    partsFragmentView.showAllINamePart(modelSoras);
                    partsFragmentView.showAnimation();
                }
            }
        };
        modelAzkarObservable.subscribe(observer);
    }

    @Override
    public void onDestroy() {
        this.partsFragmentView = null;
    }

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                partsFragmentView.showAfterSearch();
                partsFragmentView.thereData();
            }
        });
    }

    @Override
    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_part) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelSora> stringList = new ArrayList<>();
                    for (ModelSora item : name_part) {
                        if (item.getName_part().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        partsFragmentView.showAfterQueryText(stringList);
                        partsFragmentView.thereData();
                    } else {
                        partsFragmentView.isEmpty();
                    }

                } else {
                    partsFragmentView.thereData();
                    partsFragmentView.showAllINamePart(name_part);
                }
                return false;
            }
        });
    }
}