package org.qamation.charities.info;

import java.util.HashMap;

public class Storage {
    private static Storage storage = null;

    private HashMap<String,CharityInfo> data;

    public static Storage getStorage() {
        if (storage == null) storage = new Storage();
        return storage;
    }

    private Storage() {
        data = new HashMap<>();
    }

    public CharityInfo getCharityByName(String name) {
        if (!data.containsKey(name)) {
            data.put(name,new CharityInfo(name));
        }
        return data.get(name);
    }

}
