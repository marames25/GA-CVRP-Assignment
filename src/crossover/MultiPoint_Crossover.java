package crossover;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public abstract class MultiPoint_Crossover<T> implements Crossover_Strategy<T> {
    @Override
    public abstract List<Chromosome<T>> crossover(List<Chromosome<T>> candidates,
                 FitnessEvalutor<T> fitness);

}