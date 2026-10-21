package replacement;

import java.util.ArrayList;
import java.util.List;

import chromosome.Chromosome;
import scope.FintessComparator;

public class GenerationalReplacement<T> implements ReplacementStrategy<T> {
    @Override
    public List <Chromosome<T>> replace(
            List <Chromosome<T>> parents,
            List <Chromosome<T>> offSprings, 
            FintessComparator<T> comparator
        ){

        return new ArrayList<>(offSprings);
    }

}
