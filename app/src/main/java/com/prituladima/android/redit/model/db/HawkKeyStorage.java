package com.prituladima.android.redit.model.db;

import java.util.Locale;

public class HawkKeyStorage {

    private static final String REDDIT_TOP_LIST_KEY = "REDDIT_TOP_LIST_KEY_AMOUNT_%d_PAGE_%s";

    static String getRedditTopKey(int amount, String page) {
        return String.format(Locale.getDefault(), REDDIT_TOP_LIST_KEY, amount, page);
    }

}