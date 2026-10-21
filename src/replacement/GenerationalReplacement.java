package replacement;

import java.util.ArrayList;
import java.util.List;

import chromosome.Chromosome;

public class GenerationalReplacement<T> implements ReplacementStrategy<T> {
    @Override
    public List <Chromosome<T>> replace(
            List <Chromosome<T>> parents,
            List <Chromosome<T>> offSprings){

        return new ArrayList<>(offSprings);
    }

}
