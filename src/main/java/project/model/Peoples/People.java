package project.model.Peoples;

import project.model.ApplicationManager;
import project.model.Block;
import project.model.Government;
import project.model.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class People {

    private PeopleType peopleType;
    private Government government;
    private int hitPoint;
    private Block block;
    private boolean inMove;
    private Block destination;

    private boolean selected;
    public void startMove(Block block){
        destination = block;
        inMove = true;
    }


    People (PeopleType peopleType, Government government, Block block){
        this.peopleType = peopleType;
        this.government = government;
        inMove = false;
        hitPoint = 25;
        selected = false;
        // ... for example
    }


    public void endMove(){
        destination = null;
        inMove = false;
    }

    public void thisTurnMove(){
        int num = 0;
        while (!block.equals(destination) && num<peopleType.speed){
            block = findPath(ApplicationManager.getCurrentGame().getMap(), block.getX(), block.getY(), destination.getX(),destination.getY()).get(0);
            num ++;
        }
        if (block.equals(destination)){
            inMove = false;
            endMove();
        }
    }

    public boolean checkIsDead(){
        return hitPoint <= 0;
    }

    public void nextTurn(){
        thisTurnMove();
        checkIsDead();
    }

    public void hitPointReduce(int amount){
        hitPoint -= Math.max(0,(amount - peopleType.defencePower));
    }


    public int getHitPoint() {
        return hitPoint;
    }

    public Government getGovernment() {
        return government;
    }

    public void select(){
        selected = true;
    }

    public void reSelect(){
        selected = false;
    }

    public boolean IsSelected() {
        return selected;
    }

    public void stop() {
        inMove = false;
        destination = null;
    }

    public Block getBlock() {
        return block;
    }



    private ArrayList<Block> findPath(Map map, int startX, int startY, int endX, int endY) {
        Point start = new Point(startX,startY);
        Point end = new Point(endX,endY);
        ArrayList<Block> path = new ArrayList<>();
        if (start.equals(end)) return path;
        boolean[][] visited = new boolean[map.getSize()][map.getSize()];
        LinkedList<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            Block currBlock = map.getBlockByPosition(curr.x, curr.y);
            if (!currBlock.isPassable(this)) {
                continue;
            }
            if (curr.equals(end)) {
                path.add(currBlock);
                break;
            }
            for (Point neighbor : getNeighbors(curr, map)) {
                Block neighborBlock = map.getBlockByPosition(neighbor.x, neighbor.y);
                if (!visited[neighbor.x][neighbor.y] && neighborBlock.isPassable(this)) {
                    visited[neighbor.x][neighbor.y] = true;
                    queue.add(neighbor);
                    path.add(neighborBlock);
                }
            }
        }
        if (path.size() == 0 || !path.get(path.size()-1).equals(map.getBlockByPosition(endX, endY))) {
            return null;
        }
        Collections.reverse(path);
        return path;
    }

    private Point[] getNeighbors(Point curr, Map map) {
        int x = curr.x;
        int y = curr.y;
        Point[] neighbors = new Point[4];
        int count = 0;
        if (x > 0) {
            neighbors[count++] = new Point(x-1,y);
        }
        if (x < map.getSize()-1) {
            neighbors[count++] = new Point(x+1,y);
        }
        if (y > 0) {
            neighbors[count++] =new Point(x,y-1);
        }
        if (y < map.getSize()-1) {
            neighbors[count++] = new Point(x,y+1);
        }
        return Arrays.copyOf(neighbors, count);
    }
}
