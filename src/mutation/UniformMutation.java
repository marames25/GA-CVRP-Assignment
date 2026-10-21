package mutation;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.List;

public abstract class UniformMutation implements MutationStrategy<Float> {
    @Override
    public abstract List <Chromosome<Float>> mutate(List<Chromosome<Float>> candidates, FeasibilityChecker<Float> checker, Float pm);
}