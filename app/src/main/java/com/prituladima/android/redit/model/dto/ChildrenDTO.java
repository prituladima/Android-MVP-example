package com.prituladima.android.redit.model.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

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