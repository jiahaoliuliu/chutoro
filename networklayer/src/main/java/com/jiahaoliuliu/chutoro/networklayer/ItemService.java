package com.jiahaoliuliu.chutoro.networklayer;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ItemService {

    @GET("")
    Single<ItemsList> getItemsList();

}
