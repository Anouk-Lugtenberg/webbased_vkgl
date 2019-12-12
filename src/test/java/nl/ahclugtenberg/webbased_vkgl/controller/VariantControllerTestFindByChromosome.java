package nl.ahclugtenberg.webbased_vkgl.controller;

import nl.ahclugtenberg.webbased_vkgl.model.Variant;
import nl.ahclugtenberg.webbased_vkgl.model.VariantResource;
import nl.ahclugtenberg.webbased_vkgl.service.VariantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class VariantControllerTestFindByChromosome {

    @Mock
    VariantService variantService;

    @InjectMocks
    VariantController variantController;

    private VariantResource variantResource;
    private MockMvc mockMvc;

    private List<Variant> variants = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        variantController = new VariantController(variantService);
        variantResource = new VariantResource();
        mockMvc = MockMvcBuilders.standaloneSetup(variantController).build();

        //Create 10 variants for the same chromosome
        for (int i = 0; i < 15; i++) {
            Variant variant = new Variant();
            variant.setVariantId(i);
            variant.setChromosome("X");
            variants.add(variant);
        }
    }

    @Test
    public void findByChromosomePageZeroSizeFive() throws Exception {
        int page = 0;
        int size = 5;
        int count = variants.size();
        List<EntityModel<Variant>> entityModelsVariantPageZero = variants.stream()
                .map(variant -> variantResource.toResource(variant, "X", page, size, count))
                .collect(Collectors.toList());

        given(variantService.findVariantsByChromosome(anyString(), anyInt(), anyInt()))
                .willReturn(entityModelsVariantPageZero);

        for (int variantId = 0; variantId < 5; variantId++) {
            //size of page is 5, so all variants with an id less than 5 should be on the first page (page 0)
            mockMvc.perform(get("/chromosome/" + "X" + "?page=0&size=5")
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[" + variantId + "].variantId", equalTo(variantId)))
                    .andExpect(jsonPath("$.content[" + variantId + "].links[0].rel", equalTo("self")))
                    .andExpect(jsonPath("$.content[" + variantId + "].links[0].href", equalTo("/variants/" + variantId)))
                    .andExpect(jsonPath("$.content[" + variantId + "].links[1].rel", equalTo("next")))
                    .andExpect(jsonPath("$.content[" + variantId + "].links[1].href", equalTo("/chromosome/X?page=1&size=5")));

        }
    }

    @Test
    public void findByChromosomePageOneSizeFive() throws Exception {
        int page = 1;
        int size = 5;
        int count = variants.size();

        List<EntityModel<Variant>> entityModelsVariantPageOne = variants.stream()
                .filter(variant -> variant.getVariantId() >= 5)
                .map(variant -> variantResource.toResource(variant, "X", page, size, count))
                .collect(Collectors.toList());

        given(variantService.findVariantsByChromosome("X", page, size))
                .willReturn(entityModelsVariantPageOne);

        int variantId = 5;
        for (int placeInPageArray = 0; placeInPageArray < 5; placeInPageArray++) {
            mockMvc.perform(get("/chromosome/" + "X" + "?page=1&size=5")
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[" + placeInPageArray + "].variantId", equalTo(variantId)))
                    .andExpect(jsonPath("$.content[" + placeInPageArray + "].links[0].rel", equalTo("self")))
                    .andExpect(jsonPath("$.content[" + placeInPageArray + "].links[0].href", equalTo("/variants/" + variantId)))
                    .andExpect(jsonPath("$.content[" + placeInPageArray + "].links[1].rel", equalTo("previous")))
                    .andExpect(jsonPath("$.content[" + placeInPageArray + "].links[1].href", equalTo("/chromosome/X?page=0&size=5")))
                    .andExpect(jsonPath("$.content[" + placeInPageArray + "].links[2].rel", equalTo("next")))
                    .andExpect(jsonPath("$.content[" + placeInPageArray + "].links[2].href", equalTo("/chromosome/X?page=2&size=5")));
           variantId++;
        }
    }
}
