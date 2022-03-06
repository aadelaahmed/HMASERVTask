package com.example.hmaservtask.ui.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.hmaservtask.model.Category;
import com.example.hmaservtask.model.CategoryState;
import com.example.hmaservtask.repository.HAMRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@HiltViewModel
public class CategoryViewModel extends ViewModel {
    private static final String TAG = "CategoryViewModel";
    @Inject
    HAMRepository hamRepository;

    @Inject
    public CategoryViewModel(HAMRepository hamRepository) {
        this.hamRepository = hamRepository;
    }

    private MutableLiveData<CategoryState> mutableLiveCategory = new MutableLiveData<>();
    public LiveData<CategoryState> liveCategory = mutableLiveCategory;

    public LiveData<CategoryState> getLiveCategory(String userName, String password, String action) {
        Observable<List<Category>> categoryObservable =
                hamRepository.getLiveCategories(
                        userName, password, action);
        categoryObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Category> categoryList) {
                        mutableLiveCategory
                                .postValue(new CategoryState(categoryList, CategoryState.Status.SUCCESS, "Loaded Successfully"));
                        //Log.d(TAG, "onNext: fetch live categories "+ categoryList.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mutableLiveCategory
                                .postValue(new CategoryState(CategoryState.Status.ERROR, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //Log.d(TAG, "getLiveCategory: "+liveCategory.getValue().toString());
        return liveCategory;
    }
}
