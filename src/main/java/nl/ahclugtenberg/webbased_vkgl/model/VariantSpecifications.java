package nl.ahclugtenberg.webbased_vkgl.model;

import org.springframework.data.jpa.domain.Specification;

public class VariantSpecifications {

    public static Specification<Variant> withClassificationAMC(String classificationAMC) {
        if (classificationAMC == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("amc"), classificationAMC);
        }
    }

    public static Specification<Variant> withClassificationErasmus(String classificationErasmus) {
        if (classificationErasmus == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("erasmus"), classificationErasmus);
        }
    }

    public static Specification<Variant> withClassificationLumc(String classificationLumc) {
        if (classificationLumc == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("lumc"), classificationLumc);
        }
    }

    public static Specification<Variant> withClassificationNki(String classificationNki) {
        if (classificationNki == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("nki"), classificationNki);
        }
    }

    public static Specification<Variant> withClassificationRadboud(String classificationRadboud) {
        if (classificationRadboud == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("radboud"), classificationRadboud);
        }
    }

    public static Specification<Variant> withClassificationUmcg(String classificationUmcg) {
        if (classificationUmcg == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("umcg"), classificationUmcg);
        }
    }

    public static Specification<Variant> withClassificationUmcu(String classificationUmcu) {
        if (classificationUmcu == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("umcu"), classificationUmcu);
        }
    }

    public static Specification<Variant> withClassificationVumc(String classificationVumc) {
        if (classificationVumc == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("vumc"), classificationVumc);
        }
    }
}
