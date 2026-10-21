package selection;

import chromosome.Chromosome;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;;

public class TournamentSelection<T> implements Selectionstrategy<T> {
    @Override
    public List <Chromosome<T>> select(List<Chromosome<T>> population) {
        List <Chromosome<T>> selected = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < population.size(); i++) {
            Integer r1 = rand.nextInt(population.size()), r2 = rand.nextInt(population.size());
            while (r1.equals(r2)) {
                r1 = rand.nextInt(population.size());
                r2 = rand.nextInt(population.size());
            }
            
            if (population.get(r1).getFitness() < population.get(r2).getFitness()) 
                selected.add(population.get(r1));
            else 
                selected.add(population.get(r2));
        }
        return selected;
    }

}