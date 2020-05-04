package com.prituladima.android.redit.model.db;

import com.orhanobut.hawk.Hawk;
import com.prituladima.android.redit.model.dto.ResponseDTO;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HawkLocalStorage {

//    private static Map<String, ResponseDTO> storage = new HashMap<>();
    @Inject
    public HawkLocalStorage() {
    }

    public ResponseDTO saveTop(ResponseDTO responseDTO, int amount, String page) {
        Hawk.put(HawkKeyStorage.getRedditTopKey(amount, page), responseDTO);
//        storage.put(HawkKeyStorage.getRedditTopKey(amount, page), responseDTO);
        return responseDTO;
    }

    public ResponseDTO getTop(int amount, String page) {
        return Hawk.get(HawkKeyStorage.getRedditTopKey(amount, page));
//        return storage.get(HawkKeyStorage.getRedditTopKey(amount, page));
    }

}