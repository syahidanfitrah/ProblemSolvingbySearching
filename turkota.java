import java.util.*;

class City {
    String name;
    List<Connection> connections;

    public City(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
    }

    public void addConnection(City destination, int distance) {
        connections.add(new Connection(destination, distance));
    }
}

class Connection {
    City destination;
    int distance;

    public Connection(City destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }
}

public class turkota {
    public static int dijkstra(City start, City goal) {
        Map<City, Integer> distance = new HashMap<>();
        PriorityQueue<City> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        distance.put(start, 0);
        priorityQueue.offer(start);

        while (!priorityQueue.isEmpty()) {
            City current = priorityQueue.poll();

            if (current == goal) {
                return distance.get(current);
            }

            for (Connection connection : current.connections) {
                City neighbor = connection.destination;
                int newDistance = distance.get(current) + connection.distance;

                if (newDistance < distance.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distance.put(neighbor, newDistance);
                    priorityQueue.offer(neighbor);
                }
            }
        }
        return -1; // No route found
    }

    public static void main(String[] args) {
        City nashville = new City("Manchester");
        City newOrleans = new City("New Orleans");
        City seattle = new City("Seattle");
        City austin = new City("Austin");
        City memphis = new City("Memphis");

        nashville.addConnection(memphis, 210);
        newOrleans.addConnection(memphis, 345);
        seattle.addConnection(austin, 260);
        austin.addConnection(memphis, 150);

        int shortestDistance = dijkstra(nashville, newOrleans);

        if (shortestDistance != -1) {
            System.out.println("Rute terpendek untuk tur musik dari Manchester ke New Orleans adalah " + shortestDistance + " satuan jarak.");
        } else {
            System.out.println("Tidak ada rute yang tersedia.");
        }
    }
}
