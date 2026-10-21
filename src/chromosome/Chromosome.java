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
}