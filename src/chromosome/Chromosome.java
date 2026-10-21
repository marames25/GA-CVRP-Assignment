package chromosome;

import java.util.ArrayList;
import java.util.List;

public class Chromosome<T> {
    private List<T> genes = new ArrayList<>();
    private int size;
    private Integer id;
    private Float fitness;

    public Chromosome(List<T> genes, Integer id, Float fitness, Integer size) {
        this.genes = genes;
        this.id = id;
        this.fitness = fitness;
        this.size = size;
    }

    public Integer getId() {return this.id;}
    public Float getFitness() {return this.fitness;}
    public T getGeneI(Integer i) {return this.genes.get(i);}
    public List <T> getGenes() {return this.genes;}
    
    public void setId(Integer id) {this.id = id;}
    public void setFitness(Float fitness) {this.fitness = fitness;}
    public void setGeneI(Integer index, T gene) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(); 
        }
        this.genes.set(index, gene);
    }
}