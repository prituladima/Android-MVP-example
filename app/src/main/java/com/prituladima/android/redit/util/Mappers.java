package com.prituladima.android.redit.util;

import com.prituladima.android.redit.model.dto.ArticleDTO;
import com.prituladima.android.redit.model.dto.ChildrenDTO;
import com.prituladima.android.redit.model.dto.ResponceDTO;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

public final class Mappers {

    public static Func1<ResponceDTO, List<ArticleDTO>> getRedditMapper(){
        return (responceDTO) -> {
                List<ChildrenDTO> children = responceDTO.data().children();
                List<ArticleDTO> list = new ArrayList<>();
                for(ChildrenDTO current:children)
                    list.add(current.data());
                return list;
            };
    }

}