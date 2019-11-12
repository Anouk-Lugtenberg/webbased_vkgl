package nl.ahclugtenberg.webbased_vkgl.model;

import nl.ahclugtenberg.webbased_vkgl.controller.VariantController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;


@Component
public class VariantResource extends RepresentationModel {

    public EntityModel<Variant> toResource(Variant variant) {
        return new EntityModel<>(variant,
                linkTo(methodOn(VariantController.class).findByVariantId(variant.getVariantId())).withSelfRel(),
                linkTo(methodOn(VariantController.class).getAllVariants()).withRel("variants"));
    }

    public EntityModel<Variant> toResource(Variant variant, String chromosome, int page, int size, int count) {
        System.out.println("page = " + page);
        System.out.println("size = " + size);
        System.out.println("count = " + count);
        System.out.println("size/count = " + size/count);
        if (page > 0 && (size * page) < count) {
            return new EntityModel<>(variant,
                    linkTo(methodOn(VariantController.class).findByVariantId(variant.getVariantId())).withSelfRel(),
                    linkTo(methodOn(VariantController.class).getVariantsByChromosome(chromosome, page-1, size)).withRel("previous"),
                    linkTo(methodOn(VariantController.class).getVariantsByChromosome(chromosome, page+1, size)).withRel("next"));
        } else if ((size * page) < count){
            return new EntityModel<>(variant,
                    linkTo(methodOn(VariantController.class).findByVariantId(variant.getVariantId())).withSelfRel(),
                    linkTo(methodOn(VariantController.class).getVariantsByChromosome(chromosome, page+1, size)).withRel("next"));
        } else if (page > 0) {
            return new EntityModel<>(variant,
                    linkTo(methodOn(VariantController.class).findByVariantId(variant.getVariantId())).withSelfRel(),
                    linkTo(methodOn(VariantController.class).getVariantsByChromosome(chromosome, page-1, size)).withRel("previous"));
        } else {
            return new EntityModel<>(variant,
                    linkTo(methodOn(VariantController.class).findByVariantId(variant.getVariantId())).withSelfRel());
        }
    }
}
