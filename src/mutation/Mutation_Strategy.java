package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public interface Mutation_Strategy<T> {
    List <Chromosome<T>> mutate(List<Chromosome<T>> candidates, FitnessEvalutor<T> fitness);

}