package com.example.hmaservtask.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.hmaservtask.model.CategoryState;
import com.example.hmaservtask.model.Stream;
import com.example.hmaservtask.model.StreamState;
import com.example.hmaservtask.repository.HAMRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@HiltViewModel
public class StreamViewModel extends ViewModel {

    @Inject
    HAMRepository hamRepository;

    @Inject
    public StreamViewModel(
            HAMRepository hamRepository
    ) {
        this.hamRepository = hamRepository;
    }

    private MutableLiveData<StreamState> mutableLiveStream = new MutableLiveData<>();
    public LiveData<StreamState> liveStream = mutableLiveStream;

    public LiveData<StreamState> getLiveStream
            (String userName, String password, String action, int categoryId) {
        Observable<List<Stream>> streamObservable =
                hamRepository.getLiveStreams(
                        userName, password, action, categoryId);
        streamObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Stream>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Stream> streamList) {
                        mutableLiveStream
                                .postValue(new StreamState(streamList, StreamState.Status.SUCCESS, "Loaded Successfully"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mutableLiveStream
                                .postValue(new StreamState(StreamState.Status.ERROR, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveStream;
    }
}
