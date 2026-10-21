package crossover;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;

public abstract class Order1_Crossover implements Crossover_Strategy<Integer> {
    @Override
    public abstract List<Chromosome<Integer>> crossover(List<Chromosome<Integer>> candidates, 
                            FitnessEvalutor<Integer> fitness);

}