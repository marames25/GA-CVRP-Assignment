package initialization;

import chromosome.Chromosome;
import scope.CVRPFeasibilityChecker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CVRPInitializer {
    private static final int POPULATION_SIZE = 100;
    private final CVRPFeasibilityChecker feasibilityChecker;

    public CVRPInitializer(CVRPFeasibilityChecker feasibilityChecker) {
        this.feasibilityChecker = feasibilityChecker;
    }

    // Generates a random permutation of location IDs from 1 to n.
    // The depot is excluded, assuming it is represented by 0.
    private List<Integer> generatePermutation(int n) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; ++i) {
            list.add(i);
        }

        // Randomizes the visiting order without duplicating or removing locations.
        Collections.shuffle(list);

        return list;
    }

    // Creates the initial population with POPULATION_SIZE feasible chromosomes.
    public List<Chromosome<Integer>> initializePopulation(int numberOfLocations) {
        var population = new ArrayList<Chromosome<Integer>>();

        // Continue generating chromosomes until the population reaches the target size.
        while (population.size() < POPULATION_SIZE) {
            var chromosome = new Chromosome<Integer>(generatePermutation(numberOfLocations), 0, 0f, numberOfLocations);

            // Add only chromosomes that satisfy the CVRP feasibility constraints.
            if (feasibilityChecker.check(chromosome)) {
                population.add(chromosome);
            }
        }

        return population;
    }
}
