package nl.ahclugtenberg.webbased_vkgl.controller;

import nl.ahclugtenberg.webbased_vkgl.model.Variant;
import nl.ahclugtenberg.webbased_vkgl.model.VariantResource;
import nl.ahclugtenberg.webbased_vkgl.service.VariantService;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
class VariantControllerTestVariantId {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Mock
    VariantService variantService;

    @InjectMocks
    VariantController variantController;

    private VariantResource variantResource;
    private MockMvc mockMvc;
    private Variant testVariant = new Variant();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        variantController = new VariantController(variantService);
        variantResource = new VariantResource();
        mockMvc = MockMvcBuilders.standaloneSetup(variantController)
                //These lines should be used to create Asciidoctor documentation, but I'm getting a NullPointerException
                //when these lines are not commented. I'm not sure what is going wrong.
//                .apply(documentationConfiguration(restDocumentation))
//                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();

        int variantId = 1;
        testVariant.setVariantId(variantId);
        testVariant.setChromosome("X");
        testVariant.setPosition(1);
        testVariant.setRef("A");
        testVariant.setAlt("T");
        testVariant.setAmc("BENIGN");
        testVariant.setErasmus("PATHOGENIC");
        testVariant.setLumc("BENIGN");
        testVariant.setNki("BENIGN");
    }

    @Test
    public void findByVariantId() throws Exception {
        given(variantService.findByVariantId(anyInt())).willReturn(variantResource.toResource(testVariant));
        mockMvc.perform(get("/variants/" + 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.variantId", equalTo(testVariant.getVariantId())))
                .andExpect(jsonPath("$.chromosome", equalTo("X")))
                .andExpect(jsonPath("$.ref", equalTo("A")))
                .andExpect(jsonPath("$.alt", equalTo("T")))
                .andExpect(jsonPath("$.amc", equalTo("BENIGN")))
                .andExpect(jsonPath("$.erasmus", equalTo("PATHOGENIC")))
                .andExpect(jsonPath("$.lumc", equalTo("BENIGN")))
                .andExpect(jsonPath("$.nki", equalTo("BENIGN")))
                .andExpect(jsonPath("$.radboud", equalTo(null)))
                .andExpect(jsonPath("$.umcg", equalTo(null)))
                .andExpect(jsonPath("$.vumc", equalTo(null)))
                .andExpect(jsonPath("$.links[0].rel", equalTo("self")))
                .andExpect(jsonPath("$.links[0].href", equalTo("/variants/" + 1)))
                .andExpect(jsonPath("$.links[1].rel", equalTo("variants")))
                .andReturn();
    }
}
