// Imports for the parameters of flood

import java.util.*;

public class Flood {

    public static void flood1(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {
        ArrayList<Coord> a1 = new ArrayList<>(flooded_list);
        HashSet<Coord> h1 = new HashSet<>(flooded_list);

        for (int i = 0; i < h1.size(); i++) {
            Coord tile = a1.get(i);
            for (int j = 0; j < tile.neighbors(board_size).size(); j++) {
                int x = tile.neighbors(board_size).get(j).getX();
                int y = tile.neighbors(board_size).get(j).getY();
                if(!h1.contains(tile.neighbors(board_size).get(j)) && tiles[y][x].getColor() == color){
                    flooded_list.add(tile.neighbors(board_size).get(j));
                    h1.add(tile.neighbors(board_size).get(j));
                    a1.add(tile.neighbors(board_size).get(j));
                }
            }

        }

    }


    public static void flood(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {

        for (int i = 0; i < flooded_list.size(); i++) {
            Coord tile = flooded_list.get(i);
            if(tile.up().onBoard(board_size) && !flooded_list.contains(tile.up())){
                if(tiles[tile.up().getY()][tile.up().getX()].getColor() == color){
                    flooded_list.add(tile.up());
                }
            }
            if(tile.down().onBoard(board_size) && !flooded_list.contains(tile.down())){
                if(tiles[tile.down().getY()][tile.down().getX()].getColor() == color){
                    flooded_list.add(tile.down());
                }
            }
            if(tile.left().onBoard(board_size) && !flooded_list.contains(tile.left())){
                if(tiles[tile.left().getY()][tile.left().getX()].getColor() == color){
                    flooded_list.add(tile.left());
                }
            }
            if(tile.right().onBoard(board_size) && !flooded_list.contains(tile.right())){
                if(tiles[tile.right().getY()][tile.right().getX()].getColor() == color){
                    flooded_list.add(tile.right());
                }
            }
        }

    }

    public static void flood2(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {

        for (int i = 0; i < flooded_list.size(); i++){
            Coord tile = flooded_list.get(i);
            for (int j = 0; j < tile.neighbors(board_size).size(); j++) {
                int x = tile.neighbors(board_size).get(j).getX();
                int y = tile.neighbors(board_size).get(j).getY();
                if(!flooded_list.contains(tile.neighbors(board_size).get(j)) && tiles[y][x].getColor() == color){
                    flooded_list.add(tile.neighbors(board_size).get(j));
                }

            }
        }

    }

}
