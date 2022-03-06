package com.example.hmaservtask.repository;

import com.example.hmaservtask.data.HAMApi;
import com.example.hmaservtask.model.Category;
import com.example.hmaservtask.model.Stream;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class HAMRepository {
    @Inject
    HAMApi api;

    @Inject
    public HAMRepository(HAMApi api) {
        this.api = api;
    }

    public Observable<List<Category>> getLiveCategories(String userName, String Password, String action) {
        return api.getCategories(
                userName, Password, action);
    }


    public Observable<List<Stream>> getLiveStreams(String userName, String Password, String action, int categoryId) {
        return api.getStreams(
                userName, Password, action, categoryId);
    }
}
