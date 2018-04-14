package org.qamation.charities.info;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private static ConcurrentHashMap<String,CharityInfo> storage = new ConcurrentHashMap<>();

    public static void addCharity(CharityInfo charity) {
        storage.put(charity.getFundName(), charity);
    }

    public Enumeration<CharityInfo> getAllCharities() {
        return storage.elements();
    }


}
