import core.GA;
import crossover.Order1Crossover;
import decoder.RouteDecoder;
import fitness.CVRPFitnessEvaluator;
import initialization.CVRPInitializer;
import mutation.SwapMutation;
import problem.CVRPInputReader;
import problem.CVRPProblem;
import replacement.GenerationalReplacement;
import scope.CVRPFeasibilityChecker;
import scope.CVRPFintessComparator;
import selection.TournamentSelection;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            run(scanner);
        } catch (IOException exception) {
            System.err.println("Unable to read input file: " + exception.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException exception) {
            System.err.println("Error: " + exception.getMessage());
            System.exit(1);
        }
    }

    private static void run(Scanner scanner) throws IOException {
        System.out.print("Enter CVRP input file path: ");
        String inputPath = readRequiredLine(scanner, "input file path").trim();
        CVRPProblem problem = CVRPInputReader.read(Path.of(inputPath));

        int popSize = readPositiveInt(scanner, "Enter population size: ");
        int maxIterations = readPositiveInt(scanner, "Enter maximum iterations: ");
        float mutationProbability = readProbability(scanner, "Enter mutation probability (0-1): ");
        float crossoverProbability = readProbability(scanner, "Enter crossover probability (0-1): ");

        int chromosomeSize = problem.getDemands().length - 1;
        var feasibilityChecker = new CVRPFeasibilityChecker(problem);
        var initializer = new CVRPInitializer(feasibilityChecker, popSize);
        var population = initializer.initializePopulation(chromosomeSize);

        var fitnessEvaluator = new CVRPFitnessEvaluator(problem);
        var fintessComparator = new CVRPFintessComparator();

        var selection = new TournamentSelection<Integer>();

        var crossover = new Order1Crossover();

        var mutation = new SwapMutation();

        var replacement = new GenerationalReplacement<Integer>();

        // TODO: replace the choromosome size(last parameter) with demands.size()-1 and read pm, pc and max iterations as input from the terminal with proper prompt
        var ga = new GA<Integer>(
                population,
                selection,
                crossover,
                mutation,
                replacement,
                population.size(),
                maxIterations,
                fitnessEvaluator,
                feasibilityChecker,
                mutationProbability,
                crossoverProbability,
                fintessComparator,
                chromosomeSize
                );

        ga.run();

        var optimalSolution = ga.getBestSolution();
        var decoder = new RouteDecoder(problem);
        var result = decoder.decode(optimalSolution);
        var loads = problem.getDemands();
        int totalDistance = 0, totalLoad = 0;

        for (int i = 0; i < result.size(); ++i) {
            System.out.printf("Route for vehicle %d:\n\t", i);

            int cumulativeLoad = 0;
            var route = result.get(i);
            var cities = route.getCities();

            System.out.print("0 Load(0) -> ");
            for (var city : cities) {
                System.out.printf("%d Load(%d) -> ", city, cumulativeLoad);
                cumulativeLoad += loads[city];
            }
            System.out.printf("0 Load(%d)\n", cumulativeLoad);

            int routeDistance = route.getDistance();
            int routeLoad = route.getLoad();
            totalDistance += routeDistance;
            totalLoad += routeLoad;

            System.out.printf("Distance of the route: %dm\n", routeDistance);
            System.out.printf("Load of the route: %d\n", routeLoad);

            System.out.println();
        }

        System.out.printf("Total Distance of all routes: %dm\n", totalDistance);
        System.out.printf("Total Load of all routes: %d\n", totalLoad);
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = readRequiredLine(scanner, "numeric input").trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Re-prompt for invalid terminal input.
            }
            System.out.println("Please enter a positive integer.");
        }
    }

    private static float readProbability(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = readRequiredLine(scanner, "probability").trim();
            try {
                float value = Float.parseFloat(input);
                if (Float.isFinite(value) && value >= 0.0f && value <= 1.0f) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Re-prompt for invalid terminal input.
            }
            System.out.println("Please enter a number between 0 and 1.");
        }
    }

    private static String readRequiredLine(Scanner scanner, String description) {
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("No " + description + " was provided.");
        }
        return scanner.nextLine();
    }
}