package com.artemissoftware.pokedex.requests.api;

import com.artemissoftware.pokedex.requests.models.Post;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("posts")
    Single<Post> createPost(@Body Post post);

}
