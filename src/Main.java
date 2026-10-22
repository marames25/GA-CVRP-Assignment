import initialization.CVRPInitializer;
import problem.CVRPProblem;
import scope.CVRPFeasibilityChecker;


public class Main {
    public static void main(String[] args) {
        var problem = new CVRPProblem();

        var feasibilityChecker = new CVRPFeasibilityChecker(problem);

        var initializer = new CVRPInitializer(feasibilityChecker);
        var population = initializer.initializePopulation(7);

        for (var chromosome : population) {
            var chromosomeGenes = chromosome.getGenes();

            for (var gene : chromosomeGenes) {
                System.out.printf("%d ", gene);
            }

            System.out.println();
        }
    }
}