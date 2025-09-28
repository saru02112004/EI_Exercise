// Strategy Interface
interface RouteStrategy {
    void planRoute(String from, String to);
}

// Concrete Strategies
class FastestRoute implements RouteStrategy {
    @Override
    public void planRoute(String from, String to) {
        System.out.printf("Fastest route from %s to %s: Highway via Toll%n", from, to);
    }
}

class CheapestRoute implements RouteStrategy {
    @Override
    public void planRoute(String from, String to) {
        System.out.printf("Cheapest route from %s to %s: Local Roads%n", from, to);
    }
}

class ScenicRoute implements RouteStrategy {
    @Override
    public void planRoute(String from, String to) {
        System.out.printf("Scenic route from %s to %s: Coastal Road%n", from, to);
    }
}

// Context
class TravelPlanner {
    private RouteStrategy strategy;

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void planTrip(String from, String to) {
        strategy.planRoute(from, to);
    }
}

// Usage
public class TravelPlannerDemo {
    public static void main(String[] args) {
        TravelPlanner planner = new TravelPlanner();
        planner.setStrategy(new FastestRoute());
        planner.planTrip("New York", "Boston");

        planner.setStrategy(new CheapestRoute());
        planner.planTrip("New York", "Boston");

        planner.setStrategy(new ScenicRoute());
        planner.planTrip("New York", "Boston");
    }
}
