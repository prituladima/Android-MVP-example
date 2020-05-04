package com.prituladima.android.redit.model.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@AutoValue
public abstract class DataDTO {

    @SerializedName("modhash")
    public abstract String modhash();

//    @SerializedName("whitelist_status")
//    public abstract String whitelist_status();

    @SerializedName("children")
    public abstract List<ChildrenDTO> children();

    @SerializedName("after")
    public abstract String after();

    @SerializedName("before")
    public abstract String before();

    public static TypeAdapter<DataDTO> typeAdapter(Gson gson) {
        return new AutoValue_DataDTO.GsonTypeAdapter(gson);
    }

}