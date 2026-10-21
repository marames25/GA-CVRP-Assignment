package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public abstract class Uniform_Mutation implements Mutation_Strategy<Float> {
    @Override
    public abstract List <Chromosome<Float>> mutate(List<Chromosome<Float>> candidates,
                            FitnessEvalutor<Float> fitness);
}