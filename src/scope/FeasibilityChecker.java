package scope;

import chromosome.Chromosome;

public interface FeasibilityChecker<T> {
    public Boolean check(Chromosome<T> chromosome);    
}