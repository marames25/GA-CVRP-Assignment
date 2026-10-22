package problem;

public class CVRPProblem {
    private int[][] distanceMatrix;
    private int numberOfVehicles;
    private int[] vehiclesCapacity;
    private int depot = 0;
    private int [] demands;

    public CVRPProblem(int [][] distanceMatrix, int numberOfVehicles, int[] vehiclesCapacity, int [] demands)
    {
        this.distanceMatrix = distanceMatrix;
        this.numberOfVehicles = numberOfVehicles;
        this.vehiclesCapacity = vehiclesCapacity;
        this.demands = demands;
        if (demands[0] != 0) {
            throw new IllegalArgumentException("Depot demand must be 0.");
        }
        if (vehiclesCapacity.length != numberOfVehicles) {
            throw new IllegalArgumentException(
                "Number of vehicle capacities must match number of vehicles."
            );
        }
    }
    
    public int[][] getDistanceMatrix()
    {
        return distanceMatrix;
    }
    public int getNumberOfVehicles()
    {
        return numberOfVehicles;
    }
    public int[] getVehiclesCapacity()
    {
        return vehiclesCapacity;
    }
    public int getDepot()
    {
        return depot;
    }
    public int[] getDemands()
    {
        return demands;
    }
}
