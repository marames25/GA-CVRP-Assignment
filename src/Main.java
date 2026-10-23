import core.GA;
import crossover.Order1Crossover;
import fitness.CVRPFitnessEvaluator;
import initialization.CVRPInitializer;
import mutation.SwapMutation;
import problem.CVRPProblem;
import replacement.ElitistReplacement;
import replacement.GenerationalReplacement;
import scope.CVRPFeasibilityChecker;
import scope.CVRPFintessComparator;
import selection.TournamentSelection;


public class Main {
    public static void main(String[] args) {
        int[][] distanceMatrix = {
                { 0, 12, 18, 25, 14, 30, 22, 16, 28, 19, 24, 11, 27, 20, 15, 32, 21, 17, 26, 13, 23 },
                {12,  0, 10, 19,  8, 22, 15,  9, 21, 14, 18,  7, 20, 16, 11, 25, 13, 12, 19,  6, 17 },
                {18, 10,  0, 12, 16, 14,  9, 20, 13,  7, 11, 17, 15,  8, 19, 16, 10, 22, 12, 14,  9 },
                {25, 19, 12,  0, 11,  9, 17, 14,  8, 13,  7, 21, 10, 15, 12, 18, 16,  9, 14, 20, 11 },
                {14,  8, 16, 11,  0, 13, 20,  7, 15, 18, 12,  9, 16, 10,  6, 21, 14, 11, 17,  8, 19 },
                {30, 22, 14,  9, 13,  0, 11, 18,  7, 10, 16, 24,  8, 12, 15,  9, 20, 14,  6, 17, 13 },
                {22, 15,  9, 17, 20, 11,  0, 12, 16,  8, 14, 19, 13,  7, 18, 11,  9, 15, 10, 21,  6 },
                {16,  9, 20, 14,  7, 18, 12,  0, 11, 15,  9,  8, 17, 13, 10, 19,  6, 14, 12,  5, 16 },
                {28, 21, 13,  8, 15,  7, 16, 11,  0, 12,  9, 18,  6, 14, 17, 10, 13,  8,  5, 19, 11 },
                {19, 14,  7, 13, 18, 10,  8, 15, 12,  0,  6, 11, 14,  9, 16, 13,  7, 18, 10, 12,  5 },
                {24, 18, 11,  7, 12, 16, 14,  9,  9,  6,  0, 13, 11,  8, 14, 17, 10,  6, 12, 15,  9 },
                {11,  7, 17, 21,  9, 24, 19,  8, 18, 11, 13,  0, 22, 16, 10, 27, 15, 12, 20,  6, 18 },
                {27, 20, 15, 10, 16,  8, 13, 17,  6, 14, 11, 22,  0, 12, 19,  7, 14,  9,  5, 18, 10 },
                {20, 16,  8, 15, 10, 12,  7, 13, 14,  9,  8, 16, 12,  0, 11, 14,  5, 17,  9, 13,  6 },
                {15, 11, 19, 12,  6, 15, 18, 10, 17, 16, 14, 10, 19, 11,  0, 20,  8, 13, 16,  7, 12 },
                {32, 25, 16, 18, 21,  9, 11, 19, 10, 13, 17, 27,  7, 14, 20,  0, 16, 12,  8, 22, 15 },
                {21, 13, 10, 16, 14, 20,  9,  6, 13,  7, 10, 15, 14,  5,  8, 16,  0, 11, 12,  9, 14 },
                {17, 12, 22,  9, 11, 14, 15, 14,  8, 18,  6, 12,  9, 17, 13, 12, 11,  0, 10, 16,  7 },
                {26, 19, 12, 14, 17,  6, 10, 12,  5, 10, 12, 20,  5,  9, 16,  8, 12, 10,  0, 15, 11 },
                {13,  6, 14, 20,  8, 17, 21,  5, 19, 12, 15,  6, 18, 13,  7, 22,  9, 16, 15,  0, 12 },
                {23, 17,  9, 11, 19, 13,  6, 16, 11,  5,  9, 18, 10,  6, 12, 15, 14,  7, 11, 12,  0 }
        };

        int numberOfVehicles = 4;
        int[] vehiclesCapacity = {10, 10, 10, 10};
        int[] demands = { 0, 2, 3, 1, 2, 3, 1, 2, 2, 3, 1, 2, 3, 2, 1, 2, 3, 1, 2, 2, 2 };

        var problem = new CVRPProblem(distanceMatrix, numberOfVehicles, vehiclesCapacity, demands);

        var feasibilityChecker = new CVRPFeasibilityChecker(problem);

        var initializer = new CVRPInitializer(feasibilityChecker);
        var population = initializer.initializePopulation(19);

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
                1000,
                fitnessEvaluator,
                feasibilityChecker,
                0.4f,
                0.8f,
                fintessComparator,
                19
                );

//        var bestSolution = population.getFirst();
//        for (var chromosome : population) {
//            if (fintessComparator.compare(chromosome, bestSolution))  {
//                bestSolution = chromosome;
//            }
//        }
//
//        for (var gene : bestSolution.getGenes()) {
//            System.out.printf("%d ", gene);
//        }
//        System.out.println(fitnessEvaluator.evaluate(bestSolution));
//
//        ga.run();
//
//        var optimalSolution = ga.getBestSolution();
//        for (var gene : optimalSolution.getGenes()) {
//            System.out.printf("%d ", gene);
//        }
//        System.out.println(fitnessEvaluator.evaluate(optimalSolution));


//        for (var chromosome : population) {
//            for (var gene : chromosome.getGenes()) {
//                System.out.printf("%d ", gene);
//            }
//            System.out.println(fitnessEvaluator.evaluate(chromosome));
//        }
//
//        System.out.println();
//
//        for (var chromosome : ga.run()) {
//            for (var gene : chromosome.getGenes()) {
//                System.out.printf("%d ", gene);
//            }
//            System.out.println(chromosome.getFitness());
//        }

    }
}