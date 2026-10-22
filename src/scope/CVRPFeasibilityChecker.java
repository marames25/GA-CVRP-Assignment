package scope;

import chromosome.Chromosome;
import problem.CVRPProblem;


public class CVRPFeasibilityChecker implements FeasibilityChecker<Integer> {
    private final CVRPProblem problem;

    public CVRPFeasibilityChecker(CVRPProblem problem) {
        this.problem = problem;
    }

    @Override
    public Boolean check(Chromosome<Integer> chromosome) {
        var chromosomeGenes = chromosome.getGenes();
        int n = chromosomeGenes.size();

        int numberOfVehicles = problem.getNumberOfVehicles();
        int[] vehicleLoads = new int[numberOfVehicles];

        // Assign each location to a vehicle using round-robin order.
        int vehicleIndex = 0;
        for (int i = 0; i < n; ++i) {
            vehicleLoads[vehicleIndex] += problem.getDemands()[chromosomeGenes.get(i)];
            vehicleIndex = (vehicleIndex + 1) % numberOfVehicles;
        }

        // Reject the chromosome if any vehicle exceeds its capacity.
        for (int i = 0; i < numberOfVehicles; ++i) {
            if (vehicleLoads[i] > problem.getVehiclesCapacity()[i]) {
                return false;
            }
        }

        return true;
    }
}
