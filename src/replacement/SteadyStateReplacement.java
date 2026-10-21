package replacement;

import java.util.List;

import chromosome.Chromosome;

public abstract class SteadyStateReplacement<T> implements ReplacementStrategy<T> {
    @Override
    public abstract List <Chromosome<T>> replace(List <Chromosome<T>> parents, List <Chromosome<T>> offSprings);

}