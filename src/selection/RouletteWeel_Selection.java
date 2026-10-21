package selection;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public abstract class RouletteWeel_Selection<T> implements Selection_strategy<T> {
    @Override
    public abstract List <Chromosome<T>> select(List<Chromosome<T>> population, 
                            FitnessEvalutor<T> fitness);

}