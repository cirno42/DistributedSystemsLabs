package ru.nsu.nikolotov.osmstat;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class OsmStats {

    private final Map<String, Integer> userChanges = new HashMap<>();
    private final Map<String, Integer> tagCountPerKey = new HashMap<>();

    public void incrementUserChanges(String userName) {
        incrementMapValue(userChanges, userName);
    }

    public void incrementTagCount(String tagName) {
        incrementMapValue(tagCountPerKey, tagName);
    }

    private static void incrementMapValue(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            Integer prevValue = map.get(key);
            map.put(key, prevValue + 1);
        } else {
            map.put(key, 1);
        }
    }
}
