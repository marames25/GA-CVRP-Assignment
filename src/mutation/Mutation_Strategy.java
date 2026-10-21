package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public interface Mutation_Strategy<T> {
    List <Chromosome<T>> mutate(List <Chromosome<T>> candidates, FitnessEvalutor<T> fitness);

    static double validatedRate(double mutationRate) {
        if (mutationRate < 0.0 || mutationRate > 1.0) {
            throw new IllegalArgumentException(
                    "mutationRate must be within [0, 1] but was " + mutationRate);
        }
        return mutationRate;
    }
}
