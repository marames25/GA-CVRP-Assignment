package scope;

import chromosome.Chromosome;
import fitness.CVRPFitnessEvaluator;


public class CVRPFintessComparator implements FintessComparator<Integer> {
    private final CVRPFitnessEvaluator fitnessEvaluator;

    public CVRPFintessComparator(CVRPFitnessEvaluator fitnessEvaluator) {
        this.fitnessEvaluator = fitnessEvaluator;
    }

    @Override
    public Boolean compare(Chromosome<Integer> a, Chromosome<Integer> b) {
        return fitnessEvaluator.evaluate(a) < fitnessEvaluator.evaluate(b);
    }
}
