package crossover;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.List;

public abstract class Order1Crossover implements CrossoverStrategy<Integer> {
    @Override
    public abstract List<Chromosome<Integer>> crossover(List<Chromosome<Integer>> candidates, FeasibilityChecker<Integer> checker, Float pc);

}