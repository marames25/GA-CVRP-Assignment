package replacement;

import java.util.List;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

public interface Replacement_Strategy<T> {
    List <Chromosome<T>> replace(List <Chromosome<T>> parents, List <Chromosome<T>> offSprings, FitnessEvalutor<T> fitness);
}
