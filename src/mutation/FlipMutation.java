package mutation;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.List;

public abstract class FlipMutation implements MutationStrategy<Boolean> {
    @Override
    public abstract List <Chromosome<Boolean>> mutate(List<Chromosome<Boolean>> candidates, FeasibilityChecker<Boolean> checker, Float pm);
}