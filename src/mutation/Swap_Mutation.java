package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public abstract class Swap_Mutation implements Mutation_Strategy<Integer> {
    @Override
    public abstract List <Chromosome<Integer>> mutate(List<Chromosome<Integer>> candidates,
                            FitnessEvalutor<Integer> fitness);

}