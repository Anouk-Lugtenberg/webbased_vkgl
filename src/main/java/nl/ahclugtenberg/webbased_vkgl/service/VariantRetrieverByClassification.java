package nl.ahclugtenberg.webbased_vkgl.service;

import java.util.Map;

public class VariantRetrieverByClassification {
    private Map<String, String> classifications;

    public VariantRetrieverByClassification(Map<String, String> classifications) {
        this.classifications = classifications;
        retrieve();
    }

    private void retrieve() {

    }
}
