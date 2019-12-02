package nl.ahclugtenberg.webbased_vkgl.service;

import nl.ahclugtenberg.webbased_vkgl.model.Variant;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Map;

public interface VariantService {
    EntityModel<Variant> findByVariantId(int variantId);
    List<EntityModel<Variant>> findAll(int page, int size);
    List<EntityModel<Variant>> findVariantsByChromosome(String chromosome, int page, int size);
    List<EntityModel<Variant>> findVariantsByClassification(Map<String, String> classifications, int page, int size);

    Variant test(int id);
}
