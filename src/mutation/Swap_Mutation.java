package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Swap_Mutation implements Mutation_Strategy<Integer> {
    private final double mutationRate;
    private final Random random;



    public Swap_Mutation(double mutationRate) {
        this(mutationRate, new Random());
    }

    public Swap_Mutation(double mutationRate, Random random) {
        this.mutationRate = Mutation_Strategy.validatedRate(mutationRate);
        this.random = random;
    }

    @Override
    public List <Chromosome<Integer>> mutate(
            List<Chromosome<Integer>> candidates,
            FitnessEvalutor<Integer> fitness) {

        List<Chromosome<Integer>> offSprings = new ArrayList<>();

        for (Chromosome<Integer> candidate : candidates) {
            List<Integer> genes = new ArrayList<>(candidate.get_genes());

            if (random.nextDouble() < mutationRate && genes.size() > 1) {
                int first = random.nextInt(genes.size());
                int second = random.nextInt(genes.size());
                while (second == first) {
                    second = random.nextInt(genes.size());
                }
                Collections.swap(genes, first, second);
            }

            Chromosome<Integer> offSpring = new Chromosome<>();
            offSpring.set_chromosome(genes);
            offSpring.set_fitness(fitness.evaluate(offSpring));

            offSprings.add(offSpring);
        }

        return offSprings;
    }
}
