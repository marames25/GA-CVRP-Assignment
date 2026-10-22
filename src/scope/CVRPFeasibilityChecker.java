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

        // TODO: Implement feasibility checks after CVRPProblem is completed.
        return true;
    }
}
