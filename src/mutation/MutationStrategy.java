package mutation;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.List;

public interface MutationStrategy<T> {
    List <Chromosome<T>> mutate(List<Chromosome<T>> candidates, FeasibilityChecker<T> checker, Float pm);

}