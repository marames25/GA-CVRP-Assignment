package core;

import chromosome.Chromosome;
import crossover.CrossoverStrategy;
import mutation.MutationStrategy;
import replacement.ReplacementStrategy;
import scope.FeasibilityChecker;
import scope.FintessComparator;
import scope.FitnessEvalutor;
import selection.Selectionstrategy;

import java.util.List;

public class GA<T> {
    protected List<Chromosome<T>> population;
    protected Selectionstrategy<T> selector;
    protected CrossoverStrategy<T> crossover;
    protected MutationStrategy<T> mutator;
    protected ReplacementStrategy<T> replacer;
    protected Integer population_size;
    protected Integer chromosomeSize;
    protected Integer max_iterations;
    protected FitnessEvalutor<T> fitness;
    protected FeasibilityChecker<T> feasibilityChecker;
    protected FintessComparator <T> fintessComparator;
    protected Float pm;
    protected Float pc;

    public GA(List<Chromosome<T>> population, Selectionstrategy<T> selector,
              CrossoverStrategy<T> crossover, MutationStrategy<T> mutator,
              ReplacementStrategy<T> replacer, Integer population_size,
              Integer max_iterations, FitnessEvalutor<T> fitness,
              FeasibilityChecker<T> feasibilityChecker, Float pm, Float pc,
              FintessComparator<T> fintessComparator, Integer chromosomeSize) {
        this.population = population;
        this.selector = selector;
        this.crossover = crossover;
        this.mutator = mutator;
        this.replacer = replacer;
        this.population_size = population_size;
        this.max_iterations = max_iterations;
        this.fitness = fitness;
        this.feasibilityChecker = feasibilityChecker;
        this.fintessComparator = fintessComparator;
        this.chromosomeSize = chromosomeSize;
        this.pm = pm;
        this.pc = pc;
    }

    public void set_population(List<Chromosome<T>> population) {
        this.population = population;
    }

    public void set_selector(Selectionstrategy<T> selector) {
        this.selector = selector;
    }

    public void set_crossover(CrossoverStrategy<T> crossover) {
        this.crossover = crossover;
    }

    public void set_mutator(MutationStrategy<T> mutator) {
        this.mutator = mutator;
    }

    public void set_replacer(ReplacementStrategy<T> replacer) {
        this.replacer = replacer;
    }

    public void set_population_size(int population_size) {
        this.population_size = population_size;
    }

    public void set_max_iterations(int max_iterations) {
        this.max_iterations = max_iterations;
    }

    public void set_fitness(FitnessEvalutor<T> fitness) {
        this.fitness = fitness;
    }

    public List <Chromosome<T>> selection() {
        /*returns the highest fitnesses for crossover and mutation */
        return this.selector.select(this.population, this.fintessComparator);
    }
    
    public List <Chromosome<T>> crossover(List <Chromosome<T>> parents) {
        /*returns the off-springs after crossover */
        return this.crossover.crossover(parents, this.feasibilityChecker, this.pc);
    }

    public List <Chromosome<T>> mutation(List <Chromosome<T>> parents) {
        /*returns the off-springs after mutation */
        return this.mutator.mutate(parents, this.feasibilityChecker, this.pm);
    }

    public List <Chromosome<T>> replacements(List <Chromosome<T>> parents,  List<Chromosome<T>> offSprings) {
        /*returns the new generation after replacement*/
        return this.replacer.replace(parents, offSprings, this.fintessComparator);
    }

    public List <Chromosome<T>> run() {
        for (Integer i = 0; i < max_iterations; i++) {
            // TODO: calculate the fitness for the whole population 
            List <Chromosome<T>> parents = this.selection();
            List <Chromosome<T>> offSprings = this.crossover(parents);
            offSprings = mutation(offSprings);
            this.population = replacements(this.population, offSprings);
        }
        return this.population;
    }
}