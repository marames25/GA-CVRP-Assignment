package crossover;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.List;

public interface CrossoverStrategy<T> {
    List<Chromosome<T>> crossover(List<Chromosome<T>> candidates, FeasibilityChecker<T> checker, Float pc);

}