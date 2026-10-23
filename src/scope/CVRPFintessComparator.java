package scope;

import chromosome.Chromosome;

public class CVRPFintessComparator implements FintessComparator<Integer> {
    
    @Override
    public Boolean compare(Chromosome<Integer> a, Chromosome<Integer> b) {
        return a.getFitness() < b.getFitness();
    }
}
