package mutation;

import chromosome.Chromosome;
import fitness.FitnessEvalutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Uniform_Mutation implements Mutation_Strategy<Float> {
    private final double mutationRate;
    private final float lowerBound;
    private final float upperBound;
    private final Random random;

    public Uniform_Mutation(double mutationRate, float lowerBound, float upperBound) {
        this(mutationRate, lowerBound, upperBound, new Random());
    }

    public Uniform_Mutation(double mutationRate, float lowerBound, float upperBound, Random random) {
        this.mutationRate = Mutation_Strategy.validatedRate(mutationRate);
        this.lowerBound = Math.min(lowerBound, upperBound);
        this.upperBound = Math.max(lowerBound, upperBound);
        this.random = random;
    }

    @Override
    public List <Chromosome<Float>> mutate(
            List<Chromosome<Float>> candidates,
            FitnessEvalutor<Float> fitness){

        List<Chromosome<Float>> offSprings = new ArrayList<>();

        for (Chromosome<Float> candidate : candidates){
            List<Float> genes = new ArrayList<>(candidate.get_genes());

            for (int i=0; i<genes.size(); i++){
                float xi = genes.get(i);

                if(random.nextDouble() < mutationRate){
                    double r1 = random.nextDouble();

                    float newXi;

                    if (r1 <= 0.5){
                        float deltaLeft = xi - lowerBound;
                        newXi = xi - random.nextFloat() * deltaLeft;
                    } else {
                        float deltaRight = upperBound - xi;
                        newXi = xi + random.nextFloat() * deltaRight;
                    }

                    xi = newXi;
                }

                genes.set(i, Math.max(lowerBound, Math.min(upperBound, xi)));
            }

            Chromosome<Float> offSpring = new Chromosome<>();
            offSpring.set_chromosome(genes);
            offSpring.set_fitness(fitness.evaluate(offSpring));

            offSprings.add(offSpring);
        }
        return offSprings;
    }
}
