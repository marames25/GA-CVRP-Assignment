import core.GA;
import crossover.Order1Crossover;
import fitness.CVRPFitnessEvaluator;
import initialization.CVRPInitializer;
import mutation.SwapMutation;
import problem.CVRPProblem;
import replacement.GenerationalReplacement;
import scope.CVRPFeasibilityChecker;
import scope.CVRPFintessComparator;
import selection.TournamentSelection;


public class Main {
    public static void main(String[] args) {
        int[][] distanceMatrix = {
                {0, 2, 9, 10, 7, 3, 8, 6},
                {2, 0, 4, 8, 5, 6, 7, 9},
                {9, 4, 0, 3, 6, 8, 5, 7},
                {10, 8, 3, 0, 4, 7, 6, 5},
                {7, 5, 6, 4, 0, 3, 9, 8},
                {3, 6, 8, 7, 3, 0, 4, 5},
                {8, 7, 5, 6, 9, 4, 0, 2},
                {6, 9, 7, 5, 8, 5, 2, 0}
        };

        int numberOfVehicles = 4;
        int[] vehiclesCapacity = {10, 10, 10, 10};
        int[] demands = {0, 4, 3, 5, 2, 6, 4, 3};

        var problem = new CVRPProblem(distanceMatrix, numberOfVehicles, vehiclesCapacity, demands);

        var feasibilityChecker = new CVRPFeasibilityChecker(problem);

        var initializer = new CVRPInitializer(feasibilityChecker);
        var population = initializer.initializePopulation(7);

        var fitnessEvaluator = new CVRPFitnessEvaluator(problem);
        var fintessComparator = new CVRPFintessComparator(fitnessEvaluator);

        var selection = new TournamentSelection<Integer>();

        var crossover = new Order1Crossover();

        var mutation = new SwapMutation();

        var replacement = new GenerationalReplacement<Integer>();

        var ga = new GA<Integer>(
                population,
                selection,
                crossover,
                mutation,
                replacement,
                population.size(),
                100,
                fitnessEvaluator,
                feasibilityChecker,
                0.4f,
                0.8f,
                fintessComparator,
                7
                );
        ga.run();
    }
}