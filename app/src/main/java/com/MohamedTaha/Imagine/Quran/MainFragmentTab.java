package com.MohamedTaha.Imagine.Quran;

import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.MohamedTaha.Imagine.Quran.Adapter.TabAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainFragmentTab extends AppCompatActivity {
    public static MainFragmentTab instance;
    int notificationId;


    private TabLayout allTabs;
    private int [] tabIconsImage = {R.drawable.read,R.drawable.speakerlow};


    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2,sendMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_tab);

        notificationId = getIntent().getIntExtra("notificationOpen",1);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);


        instance = this;
        getAllWidgets();
        final String appPackageName = getPackageName();

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        sendMe =(FloatingActionButton)findViewById(R.id.sendMe);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,R.string.nameSora);
                String about = "\n قِرَاءَةُ القُرْآنِ الكَرِيمِ"+"\n  وَالاِسْتِمَاعُ وَالتَّحْمِيلُ بِأَكْثَرَ مِنْ شَيْخٍ بِسُهُولَةٍ وَتَشْغِيلِهِ بِدُونِ أَنْتِرْنِت." ;
                    about = about + "https://play.google.com/store/apps/details?id=" + appPackageName ;
                intent.putExtra(Intent.EXTRA_TEXT,about);
                startActivity(Intent.createChooser(intent,"مُشَارَكَةُ التَّطْبِيقِ"));
            }catch (Exception e){
                e.toString();
            }            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                try{
                    //Open the Store and show the App
                    Intent rateApp = new Intent(Intent.ACTION_VIEW);
                    rateApp.setData(Uri.parse("market://details?id=" + appPackageName));
                    startActivity(rateApp);
                }catch (android.content.ActivityNotFoundException e){

                    //In state store there is not open by The prowser
                    Intent webRate = new Intent(Intent.ACTION_VIEW);
                    webRate.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName));
                    startActivity(webRate);
                }


            }
        });
        sendMe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent  intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setData(Uri.parse("mailto:"));
                intentEmail.setType("message/rfc822");
                intentEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"mohamed01007919166@gmail.com"});
                intentEmail.putExtra(Intent.EXTRA_SUBJECT,"Subject");
                intentEmail.putExtra(Intent.EXTRA_TEXT,"Message Body");
                intentEmail.createChooser(intentEmail,"Send mail...");

                if (intentEmail.resolveActivity(getPackageManager())!= null){
                    startActivity(intentEmail);
                }else {
                    Toast.makeText(getApplicationContext(),"This Mobile Not Support Any App To Send Email",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public static MainFragmentTab getInstance(){
        return instance;
    }

    private void getAllWidgets(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabAdapter tabAdapter = new TabAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        allTabs = (TabLayout) findViewById(R.id.tabs);
        allTabs.setupWithViewPager(viewPager);
        setUpTabIcon();


    }
    private void setUpTabIcon(){
        for (int i = 0; i <allTabs.getTabCount();i++){
            allTabs.getTabAt(i).setIcon(tabIconsImage[i]);
        }
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainFragmentTab.this);
        builder.setTitle(R.string.tetileClose);
        builder.setMessage(R.string.messageClose);
        builder.setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
}



















