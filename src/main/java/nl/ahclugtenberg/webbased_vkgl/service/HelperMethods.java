package nl.ahclugtenberg.webbased_vkgl.service;

import java.util.Map;
import java.util.stream.Collectors;

public class HelperMethods {

    /**
     * Creates a map where the keys are in uppercase
     * @param map the map to be converted
     * @return a new map, with keys in uppercase
     */
    public static Map<String, String> convertKeysToUpperCase(Map<String, String> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toUpperCase(), Map.Entry::getValue));
    }
}
