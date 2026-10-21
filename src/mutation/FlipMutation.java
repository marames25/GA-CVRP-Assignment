package mutation;

import chromosome.Chromosome;
import scope.FeasibilityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlipMutation implements MutationStrategy<Boolean> {

    private final Random random;

    public FlipMutation() {
        this(new Random());
    }

    public FlipMutation(Random random) {
        this.random = random;
    }

    @Override
    public List<Chromosome<Boolean>> mutate(
            List<Chromosome<Boolean>> candidates,
            FeasibilityChecker<Boolean> checker,
            Float pm) {

        if (pm == null
                || !Float.isFinite(pm)
                || pm < 0.0f
                || pm > 1.0f) {
            throw new IllegalArgumentException("pm must be between 0 and 1");
        }

        List<Chromosome<Boolean>> offSprings = new ArrayList<>();

        for (Chromosome<Boolean> candidate : candidates) {
            Chromosome<Boolean> child;

            do {
                List<Boolean> genes = new ArrayList<>(candidate.getGenes());

                for (int i = 0; i < genes.size(); i++) {
                    if (random.nextDouble() < pm) {
                        genes.set(i, !genes.get(i));
                    }
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
