package selection;

import chromosome.Chromosome;
import scope.FintessComparator;

import java.util.List;

public interface Selectionstrategy<T> {
    List <Chromosome<T>> select(List<Chromosome<T>> population, FintessComparator<T> comparator);

}