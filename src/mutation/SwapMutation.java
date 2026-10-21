package mutation;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwapMutation implements MutationStrategy<Integer> {
    private final Random random;


    public SwapMutation() {
        this(new Random());
    }

    public SwapMutation(Random random) {
        this.random = random;
    }

    @Override
    public List<Chromosome<Integer>> mutate(
            List<Chromosome<Integer>> candidates,
            FeasibilityChecker<Integer> checker,
            Float pm) {

        if (pm == null
                || !Float.isFinite(pm)
                || pm < 0.0f
                || pm > 1.0f) {
            throw new IllegalArgumentException("pm must be between 0 and 1");
        }

        List<Chromosome<Integer>> offSprings = new ArrayList<>();

        for (Chromosome<Integer> candidate : candidates) {
            Chromosome<Integer> offSpring;

            do {
                List<Integer> genes = new ArrayList<>(candidate.getGenes());

                if (genes.size() > 1 && random.nextDouble() < pm) {
                    int first = random.nextInt(genes.size());
                    int second = random.nextInt(genes.size());
                    while (second == first) {
                        second = random.nextInt(genes.size());
                    }
                    Collections.swap(genes, first, second);
                }

                offSpring = new Chromosome<>(
                        genes, candidate.getId(), candidate.getFitness(), genes.size());
            } while (checker != null && !checker.check(offSpring));

            offSprings.add(offSpring);
        }

        return offSprings;
    }
}
