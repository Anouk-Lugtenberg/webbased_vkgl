package nl.ahclugtenberg.webbased_vkgl.model;

import javax.persistence.*;

@Entity
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int variantId;

    private String chromosome;
    private int position;
    private String ref;
    private String alt;
    private String amc;
    private String erasmus;
    private String lumc;
    private String nki;
    private String radboud;
    private String umcg;
    private String umcu;
    private String vumc;

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int id) {
        this.variantId = id;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAmc() {
        return amc;
    }

    public void setAmc(String amc) {
        this.amc = amc;
    }

    public String getErasmus() {
        return erasmus;
    }

    public void setErasmus(String erasmus) {
        this.erasmus = erasmus;
    }

    public String getLumc() {
        return lumc;
    }

    public void setLumc(String lumc) {
        this.lumc = lumc;
    }

    public String getNki() {
        return nki;
    }

    public void setNki(String nki) {
        this.nki = nki;
    }

    public String getRadboud() {
        return radboud;
    }

    public void setRadboud(String radboud) {
        this.radboud = radboud;
    }

    public String getUmcg() {
        return umcg;
    }

    public void setUmcg(String umcg) {
        this.umcg = umcg;
    }

    public String getUmcu() {
        return umcu;
    }

    public void setUmcu(String umcu) {
        this.umcu = umcu;
    }

    public String getVumc() {
        return vumc;
    }

    public void setVumc(String vumc) {
        this.vumc = vumc;
    }
}
