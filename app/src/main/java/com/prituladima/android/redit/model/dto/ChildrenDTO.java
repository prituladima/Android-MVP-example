package com.prituladima.android.redit.model.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


/**
 * Created by prituladima on 1/2/18.
 */
@AutoValue
public abstract class ChildrenDTO {

    @SerializedName("kind")
    public abstract String kind();

    @SerializedName("data")
    public abstract ArticleDTO data();

    public static TypeAdapter<ChildrenDTO> typeAdapter(Gson gson) {
        return new AutoValue_ChildrenDTO.GsonTypeAdapter(gson);
    }

}
/**
 * {
 "kind":"t3",
 "data":{
 "domain":"imgur.com",
 "approved_at_utc":null,
 "mod_reason_by":null,
 "banned_by":null,
 "num_reports":null,
 "subreddit_id":"t5_2qt55",
 "thumbnail_width":140,
 "subreddit":"gifs",
 "selftext_html":null,
 "selftext":"",
 "likes":null,
 "suggested_sort":null,
 "user_reports":[
 ],
 "secure_media":null,
 "is_reddit_media_domain":false,
 "link_flair_text":null,
 "id":"7ngxb3",
 "banned_at_utc":null,
 "mod_reason_title":null,
 "view_count":null,
 "archived":false,
 "clicked":false,
 "media_embed":{
 },
 "report_reasons":null,
 "author":"D5R",
 "num_crossposts":3,
 "saved":false,
 "mod_reports":[
 ],
 "can_mod_post":false,
 "is_crosspostable":false,
 "pinned":false,
 "score":117927,
 "approved_by":null,
 "over_18":false,
 "hidden":false,
 "preview":{
 "images":[],
 "enabled":true
 },
 "thumbnail":"https://a.thumbs.redditmedia.com/WGN6QBBnjBw97Ms0bBQOTQEao2daBnX2mEZmi0jj720.jpg",
 "edited":false,
 "link_flair_css_class":null,
 "author_flair_css_class":null,
 "contest_mode":false,
 "gilded":0,
 "downs":0,
 "brand_safe":true,
 "secure_media_embed":{
 },
 "removal_reason":null,
 "post_hint":"link",
 "author_flair_text":null,
 "stickied":false,
 "can_gild":false,
 "thumbnail_height":140,
 "parent_whitelist_status":"all_ads",
 "name":"t3_7ngxb3",
 "spoiler":false,
 "permalink":"/r/gifs/comments/7ngxb3/an_incredible_540_spinning_kick/",
 "subreddit_type":"public",
 "locked":false,
 "hide_score":false,
 "created":1514862872.0,
 "url":"https://imgur.com/w9uXSV9.gifv",
 "whitelist_status":"all_ads",
 "quarantine":false,
 "title":"An incredible 540 spinning kick",
 "created_utc":1514834072.0,
 "subreddit_name_prefixed":"r/gifs",
 "ups":117927,
 "media":null,
 "num_comments":2736,
 "is_self":false,
 "visited":false,
 "mod_note":null,
 "is_video":false,
 "distinguished":null
 }
 },
 * */