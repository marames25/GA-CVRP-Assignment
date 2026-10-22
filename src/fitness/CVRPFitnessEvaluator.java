package fitness;


import chromosome.Chromosome;
import decoder.Route;
import decoder.RouteDecoder;
import problem.CVRPProblem;
import scope.FitnessEvalutor;

import java.util.List;

public class CVRPFitnessEvaluator implements FitnessEvalutor<Integer> {

    private RouteDecoder routeDecoder;

    public CVRPFitnessEvaluator(CVRPProblem problem) {
        this.routeDecoder = new RouteDecoder(problem);
    }

    @Override
    public Float evaluate(Chromosome<Integer> chromosome) {

        List<Route> routes = routeDecoder.decode(chromosome);

        int totalDistance = 0;

        for (Route route : routes) {
            totalDistance += route.getDistance();
        }

        return (float) totalDistance;
    }
}