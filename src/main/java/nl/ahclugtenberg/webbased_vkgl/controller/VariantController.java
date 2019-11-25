package nl.ahclugtenberg.webbased_vkgl.controller;

import nl.ahclugtenberg.webbased_vkgl.model.Variant;
import nl.ahclugtenberg.webbased_vkgl.model.VariantRepository;
import nl.ahclugtenberg.webbased_vkgl.model.VariantResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nl.ahclugtenberg.webbased_vkgl.model.VariantSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RestController
public class VariantController {

    private final VariantRepository variantRepository;
    private final VariantResource variantResource;


    public VariantController(VariantRepository variantRepository, VariantResource variantResource) {
        this.variantRepository = variantRepository;
        this.variantResource = variantResource;
    }

    /**
     * Retrieves variants based on their variant identifier
     * @param variantId identifier from the variant to be retrieved
     * @return EntityModel<Variant> containing the variant
     */
    @GetMapping(value = "/variants/{variantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Variant> findByVariantId(@PathVariable int variantId) {
        Variant variant = variantRepository.findByVariantId(variantId);
        return variantResource.toResource(variant);
    }

    /**
     * Retrieves a resource list of all the variants
     * @return Entity Models of all the variants
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Variant>>> getAllVariants(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        int count = variantRepository.findAll().size();
        List<EntityModel<Variant>> variants = variantRepository.findAll(PageRequest.of(page, size)).stream()
                .map(variant -> variantResource.toResource(variant, page, size, count))
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new CollectionModel<>(variants)
        );
    }

    /**
     * Retrieves variants based on their chromosome
     * @param chromosome the chromosome to get the variants from
     * @return ResponseEntity with the found variants
     */
    @RequestMapping(value = "/chromosome/{chromosome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Variant>>> getVariantsByChromosome(
            @PathVariable String chromosome,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        int count = variantRepository.findByValues(chromosome).size();
        List<EntityModel<Variant>> variantsByChromosome = variantRepository.findByValues(chromosome, PageRequest.of(page, size)).stream()
                .map(variant -> variantResource.toResource(variant, chromosome, page, size, count))
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new CollectionModel<>(variantsByChromosome)
        );
    }

    /**
     * Retrieves variants based on the classifications by the different UMCs
     * @param requestParams a Map with key: name UMC value: classification
     * @return ResponseEntity with the found variants
     */
    @RequestMapping(value = "/classification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Variant>>> getVariantsByClassifications(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam Map<String, String> requestParams) {
        Map<String, String> requestParamsToUpperCase = ConvertKeysToUpperCase(requestParams);

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
        List<EntityModel<Variant>> variantsByClassification =
                foundVariants.stream()
                .map(variant -> variantResource.toResource(variant, requestParamsToUpperCase, page, size, count))
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new CollectionModel<>(variantsByClassification)
        );
    }

    /**
     * Creates a map where the keys are in uppercase
     * @param map the map to be converted
     * @return a new map, with keys in uppercase
     */
    private Map<String, String> ConvertKeysToUpperCase(Map<String, String> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toUpperCase(), Map.Entry::getValue));
    }
}
