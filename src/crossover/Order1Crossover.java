package crossover;

import chromosome.Chromosome;
import scope.FeasibilityChecker;
import java.util.*;


public class Order1Crossover implements CrossoverStrategy<Integer> {
    private static final int MAX_ATTEMPTS = 100;
    private final Random random = new Random();

    // Fill remaining positions using the other parent's genes.
    private void fillOffspring(
            List<Integer> offspringGenes,
            List<Integer> otherParentGenes,
            int point1,
            int point2,
            int n) {
        int i = (point2 + 1) % n;
        int j = i;

        while (i != point1) {
            int gene = otherParentGenes.get(j);

            if (!offspringGenes.contains(gene)) {
                offspringGenes.set(i, gene);
                i = (i + 1) % n;
            }

            j = (j + 1) % n;
        }
    }

    // Generate two offspring using OX1 crossover.
    private List<Chromosome<Integer>> performCrossover(Chromosome<Integer> parent1, Chromosome<Integer> parent2) {
        var parent1Genes = parent1.getGenes();
        var parent2Genes = parent2.getGenes();

        int n = parent1Genes.size();

        var offspring1Genes = new ArrayList<Integer>(Collections.nCopies(n, null));
        var offspring2Genes = new ArrayList<Integer>(Collections.nCopies(n, null));

        // Select two distinct cut points.
        int point1 = random.nextInt(n);
        int point2 = random.nextInt(n);

        while (point1 == point2) {
            point2 = random.nextInt(n);
        }

        if (point2 < point1) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        // Copy the selected segments from both parents.
        for (int i = point1; i <= point2; ++i) {
            offspring1Genes.set(i, parent1Genes.get(i));
            offspring2Genes.set(i, parent2Genes.get(i));
        }

        fillOffspring(offspring1Genes, parent2Genes, point1, point2, n);
        fillOffspring(offspring2Genes, parent1Genes, point1, point2, n);

        var offspring1 = new Chromosome<Integer>(offspring1Genes, parent1.getId(), parent1.getFitness(), n);
        var offspring2 = new Chromosome<Integer>(offspring2Genes, parent2.getId(), parent2.getFitness(), n);

        return new ArrayList<>(List.of(offspring1, offspring2));
    }

    @Override
    public List<Chromosome<Integer>> crossover(
            List<Chromosome<Integer>> candidates,
            FeasibilityChecker<Integer> checker,
            Float pc) {
        var offsprings = new ArrayList<Chromosome<Integer>>();

        // Process candidates in pairs.
        for (int i = 0; i < candidates.size() - 1; i += 2) {
            Chromosome<Integer> parent1 = candidates.get(i);
            Chromosome<Integer> parent2 = candidates.get(i + 1);

            if (random.nextFloat() < pc) {
                List<Chromosome<Integer>> children = null;

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