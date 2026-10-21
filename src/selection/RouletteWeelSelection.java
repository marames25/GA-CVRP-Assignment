package selection;

import chromosome.Chromosome;
import  java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RouletteWeelSelection<T> implements Selectionstrategy<T> {
    @Override
    public List <Chromosome<T>> select(List<Chromosome<T>> population) {
        List <Chromosome<T>> selected = new ArrayList<>();
        Float totalFitness = 0.f;

        //calculating the total fitness
        for (var chromosome : population) {
            totalFitness += chromosome.getFitness();
        }

        // accumulating the fitnesses
        List <Float> cumulatedFitnesses = new ArrayList<>(population.size());
        for (int i = 0; i < population.size(); i++) {
            cumulatedFitnesses.add(0.f);
        }
        for (int i = 0; i < population.size(); i++) {
            // setting xi to fitness/totalFitness
            cumulatedFitnesses.set(i, population.get(i).getFitness() / totalFitness);
            if (i > 0) {
                Float current = cumulatedFitnesses.get(i), prev = cumulatedFitnesses.get(i-1);
                cumulatedFitnesses.set(i, current+prev);
            }
        }

        Random rand = new Random();
        for (Integer i = 0; i < population.size(); i++) {
            Float r = rand.nextFloat();
            Integer low = 0, high = population.size()-1, result = population.size()-1;
            while (low <= high) {
                Integer mid = (low + high) / 2;
                if (r <= cumulatedFitnesses.get(mid)) {
                    high = mid-1;
                    result = mid;
                }
                else 
                    low = mid+1;
            }
            selected.add(population.get(result));
        }
        return selected;
    }

}