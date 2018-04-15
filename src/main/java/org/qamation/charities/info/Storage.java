package org.qamation.charities.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Storage {
    private static ConcurrentLinkedQueue<CharityInfo> storage = new ConcurrentLinkedQueue();
    private static Logger log = LoggerFactory.getLogger(Storage.class);

    public static void addCharity(CharityInfo charity) {
        storage.add(charity);
    }

    public static Iterator<CharityInfo> getAllCharities() {
        return storage.iterator();
    }

    public static CharityInfo[] getAllCharitiesAsArray() {
        return storage.toArray(new CharityInfo[]{});
    }

    public static void toFile(String fileName) {
        CharityInfo[] charities = getAllCharitiesAsArray();
        if (charities.length == 0) return;
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.print("Charity Name\tYear\tTotal Income\tTotal Spend on Programs\tTotal Cost");
            for (CharityInfo info : charities) {
                writer.print(info.toString());
                writer.flush();
            }
            writer.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.toString());
            e.printStackTrace();
        }

    }


}
