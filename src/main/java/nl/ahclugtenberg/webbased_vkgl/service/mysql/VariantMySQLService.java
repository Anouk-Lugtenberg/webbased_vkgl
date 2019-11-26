package nl.ahclugtenberg.webbased_vkgl.service.mysql;

import nl.ahclugtenberg.webbased_vkgl.model.Variant;
import nl.ahclugtenberg.webbased_vkgl.model.VariantRepository;
import nl.ahclugtenberg.webbased_vkgl.model.VariantResource;
import nl.ahclugtenberg.webbased_vkgl.service.HelperMethods;
import nl.ahclugtenberg.webbased_vkgl.service.VariantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nl.ahclugtenberg.webbased_vkgl.model.VariantSpecifications.*;
import static nl.ahclugtenberg.webbased_vkgl.model.VariantSpecifications.withClassificationVumc;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class VariantMySQLService implements VariantService {

    private VariantRepository variantRepository;
    private VariantResource variantResource;

    public VariantMySQLService(VariantRepository variantRepository, VariantResource variantResource) {
        this.variantRepository = variantRepository;
        this.variantResource = variantResource;
    }

    @Override
    public EntityModel<Variant> findByVariantId(int variantId) {
         return variantResource.toResource(variantRepository.findByVariantId(variantId));
    }

    @Override
    public List<EntityModel<Variant>> findAll(int page, int size) {
        int count = variantRepository.findAll().size();
        return variantRepository.findAll(PageRequest.of(page, size)).stream()
                .map(variant -> variantResource.toResource(variant, page, size, count))
                .collect(Collectors.toList());
    }

    @Override
    public List<EntityModel<Variant>> findVariantsByChromosome(String chromosome, int page, int size) {
        int count = variantRepository.findByChromosome(chromosome).size();
        return variantRepository.findByChromosome(chromosome, PageRequest.of(page, size)).stream()
                .map(variant -> variantResource.toResource(variant, chromosome, page, size, count))
                .collect(Collectors.toList());
    }

    @Override
    public List<EntityModel<Variant>> findVariantsByClassification(Map<String, String> classifications, int page, int size) {
        Map<String, String> requestParamsToUpperCase = HelperMethods.convertKeysToUpperCase(classifications);

        requestParamsToUpperCase.remove("PAGE");
        requestParamsToUpperCase.remove("SIZE");

        Page<Variant> foundVariants = variantRepository
                .findAll(where(withClassificationAMC(requestParamsToUpperCase.get("AMC")))
                        .and(withClassificationErasmus(requestParamsToUpperCase.get("ERASMUS")))
                        .and(withClassificationLumc(requestParamsToUpperCase.get("LUMC")))
                        .and(withClassificationNki(requestParamsToUpperCase.get("NKI")))
                        .and(withClassificationRadboud(requestParamsToUpperCase.get("RADBOUD")))
                        .and(withClassificationUmcg(requestParamsToUpperCase.get("UMCG")))
                        .and(withClassificationUmcu(requestParamsToUpperCase.get("UMCU")))
                        .and(withClassificationVumc(requestParamsToUpperCase.get("VUMC"))), PageRequest.of(page, size));

        int count = (int) foundVariants.getTotalElements();
        return foundVariants.stream()
                        .map(variant -> variantResource.toResource(variant, requestParamsToUpperCase, page, size, count))
                        .collect(Collectors.toList());
    }
}