package com.example.hmaservtask.data;

import com.example.hmaservtask.model.Category;
import com.example.hmaservtask.model.Stream;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HAMApi {
    @GET("player_api.php")
    Observable<List<Category>> getCategories(
        @Query("username") String userName,
        @Query("password") String password,
        @Query("action") String action
    );

    @GET("player_api.php")
    Observable<List<Stream>> getStreams(
            @Query("username") String userName,
            @Query("password") String password,
            @Query("action") String action,
            @Query("category_id") int categoryId
    );
}
