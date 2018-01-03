package com.prituladima.android.redit.model.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class ArticleDTO {

    @SerializedName("domain")
    public abstract String domain();

    @SerializedName("id")
    public abstract String id();

    @SerializedName("thumbnail")
    public abstract String thumbnail();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("permalink")
    public abstract String permalink();

    @SerializedName("created_utc")
    public abstract Double created_utc();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("title")
    public abstract String title();

    @SerializedName("author")
    public abstract String author();

    @SerializedName("subreddit")
    public abstract String subreddit();

    @SerializedName("num_comments")
    public abstract int num_comments();

    @SerializedName("score")
    public abstract int score();

    public static TypeAdapter<ArticleDTO> typeAdapter(Gson gson) {
        return new AutoValue_ArticleDTO.GsonTypeAdapter(gson);
    }

}