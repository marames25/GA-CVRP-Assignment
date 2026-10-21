
package replacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chromosome.Chromosome;

public class ElitistReplacement<T> implements ReplacementStrategy<T> {

    private final boolean maximize;

    public ElitistReplacement(boolean maximize) {
        this.maximize = maximize;
    }

    @Override
    public List<Chromosome<T>> replace(
            List<Chromosome<T>> parents,
            List<Chromosome<T>> offSprings) {

        List<Chromosome<T>> combined = new ArrayList<>(parents);
        combined.addAll(offSprings);

        combined.sort((a, b) -> maximize
                ? Float.compare(b.getFitness(), a.getFitness())
                : Float.compare(a.getFitness(), b.getFitness())
        );

        return new ArrayList<>(
                combined.subList(0, parents.size())
        );
    }
}
