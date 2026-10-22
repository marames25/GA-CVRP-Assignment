package decoder;
import problem.CVRPProblem;
import chromosome.Chromosome;
import java.util.ArrayList;
import java.util.List;

public class RouteDecoder {
    private CVRPProblem problem;
    public RouteDecoder(CVRPProblem problem) {
        this.problem = problem;
    }
    public List<Route> decode(Chromosome<Integer> chromosome) {
        int numberOfVehicles = problem.getNumberOfVehicles();
        List<Route> routes = new ArrayList<>();
        for(int i = 0; i < numberOfVehicles;i++)
        {
            routes.add(new Route(new ArrayList<>(), 0, 0));
        }
        List<Integer> cities = chromosome.getGenes();
        for(int i = 0; i < cities.size();i++)
        {
            int city = cities.get(i);
            int vehicleIndex = i % numberOfVehicles;
            routes.get(vehicleIndex).getCities().add(city);
        }
        int [] demands = problem.getDemands();
        for (Route route : routes) {
            int load = 0;
            for (int city : route.getCities()) {
                load += demands[city];
            }
            route.setLoad(load);
        }

        int[][] distanceMatrix = problem.getDistanceMatrix();
        int depot = problem.getDepot();
        for (Route route : routes) {
            int distance = 0,previousCity = depot;
            for (int city : route.getCities()) {
                distance += distanceMatrix[previousCity][city];
                previousCity = city;
            }
            distance += distanceMatrix[previousCity][depot];
            route.setDistance(distance);
        }
        return routes;
    }
}
