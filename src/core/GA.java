package core;

import chromosome.Chromosome;
import crossover.Crossover_Strategy;
import fitness.FitnessEvalutor;
import mutation.Mutation_Strategy;
import replacement.Replacement_Strategy;
import selection.Selection_strategy;

import java.util.List;

public class GA<T> {
    protected List<Chromosome<T>> population;
    protected Selection_strategy<T> selector;
    protected Crossover_Strategy<T> crossover;
    protected Mutation_Strategy<T> mutator;
    protected Replacement_Strategy<T> replacer;
    protected int population_size;
    protected int max_iterations;
    protected FitnessEvalutor<T> fitness;

    public GA(List<Chromosome<T>> population, Selection_strategy<T> selector,
              Crossover_Strategy<T> crossover, Mutation_Strategy<T> mutator,
              Replacement_Strategy<T> replacer, int population_size,
              int max_iterations, FitnessEvalutor<T> fitness) {
        this.population = population;
        this.selector = selector;
        this.crossover = crossover;
        this.mutator = mutator;
        this.replacer = replacer;
        this.population_size = population_size;
        this.max_iterations = max_iterations;
        this.fitness = fitness;
    }

    public void set_population(List<Chromosome<T>> population) {
        this.population = population;
    }

    public void set_selector(Selection_strategy<T> selector) {
        this.selector = selector;
    }

    public void set_crossover(Crossover_Strategy<T> crossover) {
        this.crossover = crossover;
    }

    public void set_mutator(Mutation_Strategy<T> mutator) {
        this.mutator = mutator;
    }

    public void set_replacer(Replacement_Strategy<T> replacer) {
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
        return this.selector.select(this.population, this.fitness);
    }
    
    public List <Chromosome<T>> crossover(List <Chromosome<T>> parents) {
        /*returns the off-springs after crossover */
        return this.crossover.crossover(parents, this.fitness);
    }

    public List <Chromosome<T>> mutation(List <Chromosome<T>> parents) {
        /*returns the off-springs after mutation */
        return this.mutator.mutate(parents, this.fitness);
    }

    public List <Chromosome<T>> replacements(List <Chromosome<T>> parents,  List<Chromosome<T>> offSprings) {
        /*returns the new generation after replacement*/
        return this.replacer.replace(parents, offSprings, this.fitness);
    }

    public List <Chromosome<T>> run() {
        for (Integer i = 0; i < max_iterations; i++) {
            List <Chromosome<T>> parents = this.selection();
            List <Chromosome<T>> offSprings = this.crossover(parents);
            offSprings = mutation(offSprings);
            this.population = replacements(this.population, offSprings);
        }
        return this.population;
    }
}