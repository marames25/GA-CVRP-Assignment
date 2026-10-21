package chromosome;

import java.util.ArrayList;
import java.util.List;

public class Chromosome<T> {
    private List<T> genes = new ArrayList<>();
    private Integer id;
    private Float fitness;

    public void set_chromosome(List<T> genes) {
        this.genes = genes;
    }

    public List<T> get_genes() {
        return this.genes;
    }

    public Integer get_id() {
        return this.id;
    }

    public void set_id(Integer id) {
        this.id = id;
    }

    public Float get_fitness() {
        return this.fitness;
    }

    public void set_fitness(Float fitness) {
        this.fitness = fitness;
    }
}