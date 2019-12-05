package com.MohamedTaha.Imagine.Quran.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.MohamedTaha.Imagine.Quran.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class SoundViewHolder extends AndroidViewModel {
    SoundRepositiory soundRepositiory;
    public SoundViewHolder(@NonNull Application application) {
        super(application);
        soundRepositiory = new SoundRepositiory(application);
    }
    public LiveData<ArrayList<ImageModel>> getAllNameSora(int position){
        return soundRepositiory.getMutableLiveDataNameSora(position);
    }
}
