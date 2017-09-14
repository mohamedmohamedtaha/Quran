package com.MohamedTaha.Imagine.Quran.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.MohamedTaha.Imagine.Quran.Adapter.ListAdapterIndex;
import com.MohamedTaha.Imagine.Quran.CustomReadKoran;
import com.MohamedTaha.Imagine.Quran.MainFragmentTab;
import com.MohamedTaha.Imagine.Quran.getData.Parts;
import com.MohamedTaha.Imagine.Quran.R;

import java.util.ArrayList;

import static com.MohamedTaha.Imagine.Quran.Adapter.ListAdapterIndex.NAMESORA;
import static com.MohamedTaha.Imagine.Quran.Adapter.ListAdapterIndex.TEXTCONTENT;
import static com.MohamedTaha.Imagine.Quran.Adapter.ListAdapterIndex.TEXTSTART;
public class FragmentListSwar extends Fragment {
    private ListView listViewSwar;
    ArrayList<Parts> ayatArrayList = new ArrayList<>();
    ListAdapterIndex listAdapterIndex ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_list_parts,null);
        getAllWidgets(rootView);
        //Fetch the data in String.xml file
        //Names the sora in the book
        String [] arrayNameSwar= getResources().getStringArray(R.array.name_allSwar);
        //Number the sora in the book
        String [] arrayNumberSora= getResources().getStringArray(R.array.textStart);
        //Fetch all data the Sora
        String [] arrayContent= getResources().getStringArray(R.array.content_swar);
        //Fetch all data Time Nozol
        String [] arrayTimeNzol= getResources().getStringArray(R.array.nzolElswar);

        ayatArrayList.clear();
        for (int i =0 ; i<arrayNameSwar.length; i++){
            Parts data = new Parts(arrayNameSwar[i],arrayNumberSora[i],arrayContent[i],arrayTimeNzol[i]);
            ayatArrayList.add(data);
        }
        listAdapterIndex =new ListAdapterIndex(MainFragmentTab.getInstance(),ayatArrayList);
        listViewSwar.setAdapter(listAdapterIndex);
        listViewSwar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Parts ayat= ayatArrayList.get(position);
                Intent intent = new Intent(getContext(),CustomReadKoran.class);
                intent.putExtra(NAMESORA,ayat.getNameSora());
                intent.putExtra(TEXTSTART,ayat.getNumberPageOrLink());
                intent.putExtra(TEXTCONTENT,ayat.getData());
                startActivity(intent);
            }
        });
        return rootView;
    }
    private void getAllWidgets(View view) {
        listViewSwar = (ListView) view.findViewById(R.id.listViewParts);
    }
}
