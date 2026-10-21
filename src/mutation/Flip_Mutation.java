package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Flip_Mutation implements Mutation_Strategy<Boolean> {

    private final double mutationRate;
    private final Random random;

    public Flip_Mutation(double mutationRate) {
        this(mutationRate, new Random());
    }

    public Flip_Mutation(double mutationRate, Random random) {
        this.mutationRate = Mutation_Strategy.validatedRate(mutationRate);
        this.random = random;
    }

    @Override
    public List <Chromosome<Boolean>> mutate(
            List<Chromosome<Boolean>> candidates,
            FitnessEvalutor<Boolean> fitness){

        List<Chromosome<Boolean>> offSprings = new ArrayList<>();

        for (Chromosome<Boolean> candidate : candidates){
            List<Boolean> genes = new ArrayList<>(candidate.get_genes());
            for (int i=0; i<genes.size(); i++){
                if(random.nextDouble() < mutationRate){
                    genes.set(i,!genes.get(i));
                }
            }
            Chromosome<Boolean> offSpring = new Chromosome<>();
            offSpring.set_chromosome(genes);
            offSpring.set_fitness(fitness.evaluate(offSpring));

            offSprings.add(offSpring);
        }
        return offSprings;
    }
}