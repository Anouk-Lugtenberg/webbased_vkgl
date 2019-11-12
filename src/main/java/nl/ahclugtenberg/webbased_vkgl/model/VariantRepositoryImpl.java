package nl.ahclugtenberg.webbased_vkgl.model;

import nl.ahclugtenberg.webbased_vkgl.service.VariantRetrieverByClassification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VariantRepositoryImpl implements  VariantRepositoryCustom {

    @Override
    public List<Variant> getVariantsByClassifications(Map<String, String> classifications) {
        Variant variant = new Variant();
        variant.setVariantId(99);
        variant.setAlt("Hello");
        List<Variant> variants = new ArrayList<>();
        variants.add(variant);
        return variants;
    }
}
