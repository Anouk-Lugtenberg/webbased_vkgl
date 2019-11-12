package nl.ahclugtenberg.webbased_vkgl.model;

import java.util.List;
import java.util.Map;

public interface VariantRepositoryCustom {
    List<Variant> getVariantsByClassifications(Map<String, String> classifications);
}
