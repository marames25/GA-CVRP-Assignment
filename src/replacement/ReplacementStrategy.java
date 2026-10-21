package replacement;

import java.util.List;

import chromosome.Chromosome;
import scope.FintessComparator;

public interface ReplacementStrategy<T> {
    List <Chromosome<T>> replace(
        List <Chromosome<T>> parents,
        List <Chromosome<T>> offSprings, 
        FintessComparator<T> comparator
    );
}
