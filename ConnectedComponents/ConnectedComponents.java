import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConnectedComponents {
    /*
     * TODO
     */

    public static <V> void dfs(Graph<V> G, V start, V curr, Map<V, V> representative, Map<V, Boolean> visited){
        visited.put(curr, true);
        representative.put(curr,start);
        for (V vertex: G.adjacent(curr)) {
            if(!visited.get(vertex)){
                dfs(G,start,vertex,representative,visited);
            }
        }

    }

    public static <V> void
    connected_components(Graph<V> G, Map<V, V> representative) {
        Map<V, Boolean> visited = new HashMap<>();
        for (V vertex: G.vertices()) {
            visited.put(vertex, false);
        }

        for (V vertex: G.vertices()) {

            if(!visited.get(vertex)){
                dfs(G,vertex,vertex,representative,visited);
            }
        }
    }

}
