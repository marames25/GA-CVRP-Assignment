package scope;

import chromosome.Chromosome;

public interface FintessComparator<T> {
    public Boolean compare(Chromosome<T> a, Chromosome <T> b);
}
