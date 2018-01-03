package com.prituladima.android.redit.model.db;

import com.orhanobut.hawk.Hawk;
import com.prituladima.android.redit.model.dto.ResponceDTO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HawkLocalStorage {

    @Inject
    public HawkLocalStorage() {
    }

    public ResponceDTO saveTop(ResponceDTO responceDTO, int amount, String page) {
        Hawk.put(HawkKeyStorage.getRedditTopKey(amount, page), responceDTO);
        return responceDTO;
    }

    public ResponceDTO getTop(int amount, String page) {
        return Hawk.get(HawkKeyStorage.getRedditTopKey(amount, page));
    }

}