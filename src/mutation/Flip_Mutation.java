package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public abstract class Flip_Mutation implements Mutation_Strategy<Boolean> {
    @Override
    public abstract List <Chromosome<Boolean>> mutate(List<Chromosome<Boolean>> candidates,
                            FitnessEvalutor<Boolean> fitness);
}