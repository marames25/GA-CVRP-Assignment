package mutation;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UniformMutation implements MutationStrategy<Float> {

    private final Random random;
    private final float lowerBound;
    private final float upperBound;

    public UniformMutation(float lowerBound, float upperBound) {
        this(lowerBound, upperBound, new Random());
    }

    public UniformMutation(float lowerBound,
                           float upperBound,
                           Random random) {

        if (!Float.isFinite(lowerBound)
                || !Float.isFinite(upperBound)
                || lowerBound > upperBound) {
            throw new IllegalArgumentException("Invalid bounds");
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.random = random;
    }

    @Override
    public List <Chromosome<Float>> mutate(
            List<Chromosome<Float>> candidates,
            FeasibilityChecker<Float> checker,
            Float pm){

        if (pm == null
                || !Float.isFinite(pm)
                || pm < 0.0f
                || pm > 1.0f) {
            throw new IllegalArgumentException("pm must be between 0 and 1");
        }

        List<Chromosome<Float>> offSprings = new ArrayList<>();

        for (Chromosome<Float> candidate : candidates) {
            Chromosome<Float> child;

            do {
                List<Float> genes = new ArrayList<>(candidate.getGenes());

                for (int i = 0; i < genes.size(); i++) {
                     if (random.nextDouble() < pm) {

                        float xi = genes.get(i);

                        float deltaL = xi - lowerBound;
                        float deltaR = upperBound - xi;

                        if (random.nextDouble() <= 0.5) {
                            float ri2 = random.nextFloat() * deltaL;
                            genes.set(i, xi - ri2);
                        } else {
                            float ri2 = random.nextFloat() * deltaR;
                            genes.set(i, xi + ri2);
                        }
                    }
                }

                for (int i = 0; i < genes.size(); i++) {
                    genes.set(i, Math.max(lowerBound, Math.min(upperBound, genes.get(i))));
                }

                child = new Chromosome<>(
                        genes,
                        candidate.getId(),
                        candidate.getFitness(),
                        genes.size()
                );
            } while (checker != null && !checker.check(child));

            offSprings.add(child);
        }

        return offSprings;
    }
}

