package nl.ahclugtenberg.webbased_vkgl.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VariantRepository extends CrudRepository<Variant, Integer>, JpaRepository<Variant, Integer>,
        JpaSpecificationExecutor<Variant> {

    @Query(value = "SELECT v FROM Variant v WHERE chromosome = ?1")
    List<Variant> findByChromosome(String chromosome);

    @Query(value = "SELECT v FROM Variant v WHERE chromosome = ?1")
    List<Variant> findByChromosome(String chromosome, Pageable pageable);

    @Query(value = "SELECT v FROM Variant v WHERE variant_id = ?1")
    Variant findByVariantId(int variantId);
}
