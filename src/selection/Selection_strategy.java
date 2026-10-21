package selection;

import chromosome.Chromosome;
import  fitness.FitnessEvalutor;
import java.util.List;

public interface Selection_strategy<T> {
    List <Chromosome<T>> select(List<Chromosome<T>> population, FitnessEvalutor<T> fitness);

}