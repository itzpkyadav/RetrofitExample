package com.pramod.retrofitexample.interfaces;

import com.pramod.retrofitexample.model.Comment;
import com.pramod.retrofitexample.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    /***
     * get data from server then use GET
     * */
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts")
    Call<List<Post>> getPosts_Hash(@QueryMap Map<String, String> parameters);

    @GET("posts")
    Call<List<Post>> getPosts_Id(

            /* @Query("userId") int userId);*/            //1. show data according userId

            /*@Query("userId") Integer userId1,           //2. data between your choice
              @Query("userId") Integer userId2);*/

            /*@Query("userId") int userId         //3.
              @Query("_sort") String sort,        //for sorting data by Id
              @Query("_desc") String order);      //for desc order data */

            @Query("userId") Integer[] userId,  //show data according which which userID
            @Query("_sort") String sort,        //for sorting data by Id
            @Query("_desc") String order);      //for desc order data






    @GET("posts/1/comments")
    Call<List<Comment>> getComments();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments_Id(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments_Url(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost_Id(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost_Hash(@FieldMap Map<String, String> fields);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
