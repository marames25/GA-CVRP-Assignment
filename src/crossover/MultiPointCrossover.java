package crossover;

import chromosome.Chromosome;
import scope.FeasibilityChecker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MultiPointCrossover<T> implements CrossoverStrategy<T> {
    private static final int MAX_ATTEMPTS = 100;
    private final Random random = new Random();

    // Generate two offspring using multipoint crossover.
    private List<Chromosome<T>> performCrossover(Chromosome<T> parent1, Chromosome<T> parent2) {
        var parent1Genes = parent1.getGenes();
        var parent2Genes = parent2.getGenes();

        int n = parent1Genes.size();

        // Randomly choose the number of crossover points.
        int numberOfCrossoverPoints = random.nextInt(n - 1) + 1;
        var crossoverPoints = new ArrayList<Integer>();

        // Generate unique crossover points between adjacent genes.
        while (crossoverPoints.size() != numberOfCrossoverPoints) {
            int crossoverPoint = random.nextInt(n - 1) + 1;

            while (crossoverPoints.contains(crossoverPoint)) {
                crossoverPoint = random.nextInt(n - 1) + 1;
            }

            crossoverPoints.add(crossoverPoint);
        }

        crossoverPoints.sort(Collections.reverseOrder());

        var offspring1Genes = new ArrayList<T>(Collections.nCopies(n, null));
        var offspring2Genes = new ArrayList<T>(Collections.nCopies(n, null));

        // Alternate the parent supplying genes whenever a crossover point is reached.
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (!crossoverPoints.isEmpty() && i == crossoverPoints.get(crossoverPoints.size() - 1)) {
                ++j;
                crossoverPoints.remove(crossoverPoints.size() - 1);
            }

            // Copy genes from the selected parent according to the current segment.
            if (j % 2 == 0) {
                offspring1Genes.set(i, parent1Genes.get(i));
                offspring2Genes.set(i, parent2Genes.get(i));
            } else {
                offspring1Genes.set(i, parent2Genes.get(i));
                offspring2Genes.set(i, parent1Genes.get(i));
            }
        }

        var offspring1 = new Chromosome<T>(offspring1Genes, parent1.getId(), parent1.getFitness(), n);
        var offspring2 = new Chromosome<T>(offspring2Genes, parent2.getId(), parent2.getFitness(), n);

        return new ArrayList<>(List.of(offspring1, offspring2));
    }

    @Override
    public List<Chromosome<T>> crossover(
            List<Chromosome<T>> candidates,
            FeasibilityChecker<T> checker,
            Float pc) {
        var offsprings = new ArrayList<Chromosome<T>>();

        // Process candidates in pairs.
        for (int i = 0; i < candidates.size() - 1; i += 2) {
            Chromosome<T> parent1 = candidates.get(i);
            Chromosome<T> parent2 = candidates.get(i + 1);

            if (random.nextFloat() < pc) {
                List<Chromosome<T>> children = null;

                // Retry until both offspring are feasible.
                for (int attempt = 0; attempt < MAX_ATTEMPTS; ++attempt) {
                    var generated = performCrossover(parent1, parent2);

                    if (checker.check(generated.get(0)) && checker.check(generated.get(1))) {
                        children = generated;
                        break;
                    }
                }

                if (children != null) {
                    offsprings.addAll(children);
                } else {
                    offsprings.add(parent1);
                    offsprings.add(parent2);
                }
            } else {
                offsprings.add(parent1);
                offsprings.add(parent2);
            }
        }

        // Preserve the last candidate if the population size is odd.
        if (candidates.size() % 2 != 0) {
            offsprings.add(candidates.get(candidates.size() - 1));
        }

        return offsprings;
    }
}