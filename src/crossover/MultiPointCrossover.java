package crossover;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.List;

public abstract class MultiPointCrossover<T> implements CrossoverStrategy<T> {
    @Override
    public abstract List<Chromosome<T>> crossover(List<Chromosome<T>> candidates, FeasibilityChecker<T> checker, Float pc);

}