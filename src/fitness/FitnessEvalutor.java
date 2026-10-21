package fitness;

import chromosome.Chromosome;

public interface FitnessEvalutor<T> {
    public Float evaluate(Chromosome <T> chromosome);
}
