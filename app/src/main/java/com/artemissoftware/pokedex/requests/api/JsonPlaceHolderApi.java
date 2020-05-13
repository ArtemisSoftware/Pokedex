package com.artemissoftware.pokedex.requests.api;

import com.artemissoftware.pokedex.requests.models.Post;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("posts")
    Observable<Post> createPost(@Body Post post);

}
