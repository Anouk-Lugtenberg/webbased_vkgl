package nl.ahclugtenberg.webbased_vkgl.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class VariantTest {

    private Variant variant;

    @BeforeEach
    void setUp() {
        variant = new Variant();
    }

    @Test
    void getVariantId() {
        int idValue = 1;
        variant.setVariantId(idValue);
        assertEquals(idValue, variant.getVariantId());
    }

    @Test
    void getChromosome() {
        String chromosomeValue = "1";
        variant.setChromosome(chromosomeValue);
        assertEquals(chromosomeValue, variant.getChromosome());
    }
}