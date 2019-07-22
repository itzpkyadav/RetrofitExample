package com.pramod.retrofitexample.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pramod.retrofitexample.R;
import com.pramod.retrofitexample.interfaces.ApiInterface;
import com.pramod.retrofitexample.model.Comment;
import com.pramod.retrofitexample.model.Post;
import com.pramod.retrofitexample.retrofit.ApiClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView_Results;
    private Button button;
    private ProgressDialog progressDialog;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getInstance(this).create(ApiInterface.class);

        textView_Results = findViewById(R.id.text_view_results);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Loading data...Please wait");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.show();

                // hitApi();
                // getPosts();
                // getPosts_Id();
                getPosts_Hash();
                // getComments();
                // getComments_Id();
                // getComments_Url();
                createPosts_Hash();
            }
        });


    }

    private void createPosts_Hash() {
        Post post = new Post(23, "New Title", "New Text");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "New Title");

        Call<Post> call = apiInterface.createPost_Hash(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textView_Results.setText("Code: " + response.code());
                    return;
                }

                progressDialog.dismiss();
                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";
                button.setVisibility(View.GONE);
                textView_Results.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                progressDialog.dismiss();
                textView_Results.setText(t.getMessage());
            }
        });
    }

    private void getPosts_Hash() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = apiInterface.getPosts_Hash(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    textView_Results.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    progressDialog.dismiss();
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    button.setVisibility(View.GONE);
                    textView_Results.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressDialog.dismiss();
                textView_Results.setText(t.getMessage());
            }
        });
    }

    private void getComments_Url() {
        Call<List<Comment>> call = apiInterface.getComments_Url("posts/1/comments"  /*OR*/
                /*"https://jsonplaceholder.typicode.com/posts/1/comments"*/);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    textView_Results.setText("Code : " + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    progressDialog.dismiss();
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post Id: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";
                    button.setVisibility(View.GONE);
                    textView_Results.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                progressDialog.dismiss();
                textView_Results.setText(t.getMessage());
            }
        });
    }

    private void getPosts_Id() {
        /*  Call<List<Post>> call = apiInterface.getPosts_Id(3);*/                   //1. show data according userId
        /*  Call<List<Post>> call = apiInterface.getPosts_Id(1,4);*/                 //2. data between your choice
        /*  Call<List<Post>> call = apiInterface.getPosts_Id(3,"id","desc");*/       //3. show data according in desc order using Id
        Call<List<Post>> call = apiInterface.getPosts_Id(new Integer[]{2, 3, 6}, null, null);   //show data according which which userID

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    textView_Results.setText("Code : " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    progressDialog.dismiss();
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    button.setVisibility(View.GONE);
                    textView_Results.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView_Results.setText(t.getMessage());
            }
        });
    }

    private void getComments_Id() {
        Call<List<Comment>> call = apiInterface.getComments_Id(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    textView_Results.setText("Code : " + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    progressDialog.dismiss();
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post Id: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";
                    button.setVisibility(View.GONE);
                    textView_Results.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView_Results.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comment>> call = apiInterface.getComments();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    textView_Results.setText("Code : " + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    progressDialog.dismiss();
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post Id: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";
                    button.setVisibility(View.GONE);
                    textView_Results.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView_Results.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = apiInterface.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    textView_Results.setText("Code : " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    progressDialog.dismiss();
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    button.setVisibility(View.GONE);
                    textView_Results.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView_Results.setText(t.getMessage());
            }
        });
    }

    private void hitApi() {
    }
}
