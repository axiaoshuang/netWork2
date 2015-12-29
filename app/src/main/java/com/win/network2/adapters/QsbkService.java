package com.win.network2.adapters;

import com.win.network2.domain.Item;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * author：WangShuang
 * Date: 2015/12/29 14:02
 * email：m15046658245_1@163.com
 */
public interface QsbkService {
    @GET("article/list/{Type}")
    Call<List<Item>> getList(@Path("type") String type,@Query("page") int page);

}
