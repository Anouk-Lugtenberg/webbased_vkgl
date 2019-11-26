package nl.ahclugtenberg.webbased_vkgl.controller;

import nl.ahclugtenberg.webbased_vkgl.model.Variant;
import nl.ahclugtenberg.webbased_vkgl.model.VariantRepository;
import nl.ahclugtenberg.webbased_vkgl.model.VariantResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VariantController.class)
class VariantControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

    @Mock
    VariantRepository variantRepository;

    @Mock
    VariantResource variantResource;

    @Mock
    VariantController controller;



    @Autowired
    private MockMvc mockMvc;



    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("test"));
    }

    @Test
    void getVariantsById() {
        Variant variant = new Variant();
        List<Variant> variantData = new ArrayList<>();
        variantData.add(variant);

        when(variantRepository.findAll()).thenReturn(variantData);
    }
}