import java.util.*;

public class Routing {

    /**
     * TODO
     * <p>
     * The findPaths function takes a board and a list of goals that contain
     * endpoints that need to be connected. The function returns a list of
     * Paths that connect the points.
     */

    public static ArrayList<Wire>
    findPaths(Board board, ArrayList<Endpoints> goals) {
        ArrayList<Endpoints> goals2 = (ArrayList<Endpoints>) goals.clone();
        goals2.sort(Comparator.comparingInt(Routing::manhattan_distance));

        Wire[] wires = new Wire[goals.size()];


        for (Endpoints goal : goals2) {
            Map<Coord, Coord> parents = new HashMap<>();
            HashSet<Coord> visited = new HashSet<>();
            Queue<Coord> queue = new LinkedList<>();

            queue.add(goal.start);
            parents.put(goal.start, goal.start);
            while (!queue.isEmpty()) {
                if (queue.peek().equals(goal.end)) {
                    break;
                }
                Coord curr = queue.poll();
                ArrayList<Coord> neighbors = board.adj(curr);
                for (Coord neighbor : neighbors) {
                    if(!visited.contains(neighbor) && (board.getValue(neighbor) == 0 || board.getValue(neighbor) == goal.id) ) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        parents.put(neighbor,curr);
                    }

                }
            }

            Coord parent_track = goal.end;
            ArrayList<Coord> wire = new ArrayList<>();
//            System.out.println("goal id start and end");
//            System.out.println(goal.id);
//            System.out.println(goal.start);
//            System.out.println(goal.end);
//            System.out.println();
            if(parents.containsKey(parent_track)){
                while (!parent_track.equals(goal.start)){
//                System.out.println(parent_track);
                    wire.add(parent_track);
                    parent_track = parents.get(parent_track);
                }
//            System.out.println(parent_track);
                wire.add(parent_track);
                Collections.reverse(wire);
                Wire place_wire = new Wire(goal.id,wire);
                wires[goal.id - 1] = (place_wire);
                board.placeWire(place_wire);
            }




//            System.out.println();
//            board.show();
        }
        System.out.println(goals);
        System.out.println(Arrays.toString(wires));
        return new ArrayList<>(List.of(wires));

    }

    private static int manhattan_distance(Endpoints e){
        int xDist = Math.abs(e.start.x - e.end.x);
        int yDist = Math.abs(e.start.y - e.end.y);

        return xDist + yDist;
    }
}
