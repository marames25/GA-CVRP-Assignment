package replacement;

import java.util.List;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

public abstract class SteadyState_Replacement<T> implements Replacement_Strategy<T> {
    @Override
    public abstract List <Chromosome<T>> replace(List <Chromosome<T>> parents,
                List <Chromosome<T>> offSprings, FitnessEvalutor<T> fitness);

}