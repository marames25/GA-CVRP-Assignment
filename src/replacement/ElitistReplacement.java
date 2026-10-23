
package replacement;

import java.util.ArrayList;
import java.util.List;

import chromosome.Chromosome;
import scope.FintessComparator;

public class ElitistReplacement<T> implements ReplacementStrategy<T> {

    @Override
    public List<Chromosome<T>> replace(
            List<Chromosome<T>> parents,
            List<Chromosome<T>> offSprings, 
            FintessComparator<T> comparator      
        ) {

        List<Chromosome<T>> combined = new ArrayList<>(parents);
        combined.addAll(offSprings);

        combined.sort((a, b) -> {
            if (comparator.compare(a, b)) {
                return -1;
            }
            if (comparator.compare(b, a)) {
                return 1;
            }
            return 0;
        });

        return new ArrayList<>(
                combined.subList(0, parents.size())
        );
    }
}
