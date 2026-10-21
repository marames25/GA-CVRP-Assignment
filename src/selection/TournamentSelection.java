package selection;

import chromosome.Chromosome;

import java.util.List;

public abstract class TournamentSelection<T> implements Selectionstrategy<T> {
    @Override
    public abstract List <Chromosome<T>> select(List<Chromosome<T>> population);

}