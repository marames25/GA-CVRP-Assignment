package decoder;

import java.util.List;

public class Route {

    private List<Integer> cities;
    private int load;
    private int distance;

    public Route(List<Integer> cities, int load, int distance) {
        this.cities = cities;
        this.load = load;
        this.distance = distance;
    }
    

    public List<Integer> getCities() {
        return cities;
    }

    public int getLoad() {
        return load;
    }
    public void setLoad(int load) {
        this.load = load;
    }

    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
}