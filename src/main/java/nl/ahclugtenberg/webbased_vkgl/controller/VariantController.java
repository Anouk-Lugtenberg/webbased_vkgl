package nl.ahclugtenberg.webbased_vkgl.controller;

import nl.ahclugtenberg.webbased_vkgl.model.Variant;
import nl.ahclugtenberg.webbased_vkgl.service.VariantService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class VariantController {
    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "index";
    }

    /**
     * Retrieves variants based on their variant identifier
     * @param variantId identifier from the variant to be retrieved
     * @return EntityModel<Variant> containing the variant
     */
    @GetMapping(value = "/variants/{variantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Variant> findByVariantId(@PathVariable int variantId) {
        return variantService.findByVariantId(variantId);
    }

    /**
     * Retrieves a resource list of all the variants
     * @return Entity Models of all the variants
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Variant>>> getAllVariants(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(
                new CollectionModel<>(variantService.findAll(page, size))
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
        return ResponseEntity.ok(
                new CollectionModel<>(variantService.findVariantsByChromosome(chromosome, page, size))
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
        return ResponseEntity.ok(
                new CollectionModel<>(variantService.findVariantsByClassification(requestParams, page, size))
        );
    }
}
