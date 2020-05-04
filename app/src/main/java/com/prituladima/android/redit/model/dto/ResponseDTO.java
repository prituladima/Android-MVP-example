package com.prituladima.android.redit.model.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class ResponseDTO {

    @SerializedName("kind")
    public abstract String kind();

    @SerializedName("data")
    public abstract DataDTO data();

    public static TypeAdapter<ResponseDTO> typeAdapter(Gson gson) {
        return new AutoValue_ResponseDTO.GsonTypeAdapter(gson);
    }

}