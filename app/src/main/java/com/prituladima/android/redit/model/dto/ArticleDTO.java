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

    @SerializedName("score")
    public abstract Long score();

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

    public static TypeAdapter<ArticleDTO> typeAdapter(Gson gson) {
        return new AutoValue_ArticleDTO.GsonTypeAdapter(gson);
    }


}
/**
 * "domain":"imgur.com",
 * "id":"7ngxb3",
 * "author":"D5R",
 * "score":117927,
 * <p>
 * "thumbnail":"https://a.thumbs.redditmedia.com/WGN6QBBnjBw97Ms0bBQOTQEao2daBnX2mEZmi0jj720.jpg",
 * <p>
 * "name":"t3_7ngxb3",
 * <p>
 * "permalink":"/r/gifs/comments/7ngxb3/an_incredible_540_spinning_kick/",
 * <p>
 * <p>
 * "url":"https://imgur.com/w9uXSV9.gifv",
 * <p>
 * "title":"An incredible 540 spinning kick",
 * "created_utc":1514834072.0,
 */