package crossover;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public interface Crossover_Strategy<T> {
    List<Chromosome<T>> crossover(List<Chromosome<T>> candidates, FitnessEvalutor<T> fitness);

}