package mutation;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.List;

public abstract class SwapMutation implements MutationStrategy<Integer> {
    @Override
    public abstract List <Chromosome<Integer>> mutate(List<Chromosome<Integer>> candidates, FeasibilityChecker<Integer> checker, Float pm);

}