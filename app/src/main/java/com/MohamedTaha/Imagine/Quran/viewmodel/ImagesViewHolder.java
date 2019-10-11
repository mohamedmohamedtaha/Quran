package com.MohamedTaha.Imagine.Quran.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.MohamedTaha.Imagine.Quran.model.ModelSora;

import java.util.List;

public class ImagesViewHolder extends AndroidViewModel {
    private StoreImagesRepository storeRepository;
    public ImagesViewHolder(@NonNull Application application) {
        super(application);
        storeRepository = new StoreImagesRepository(application);
    }
    public LiveData<List<ModelSora>> getAllNameSwar(){
        return storeRepository.getMutableLiveData();

    }

    public LiveData<List<Integer>> getAllImages(){
        return storeRepository.getMutableLiveDataImage();

    }
}
