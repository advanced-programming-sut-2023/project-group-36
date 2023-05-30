package model.Peoples;

import model.ApplicationManager;
import model.Block;
import model.Government;

public class People {
   /* private PeopleType peopleType;
    private Government government;
    private double hitPoint;
    private Block block;
    private boolean inMove;

    private boolean inPatrol;
    private Block destination1;
    private Block destination2;

    private boolean selected;
    public void startMove(Block block){
        destination1 = block;
        inMove = true;
    }

    public void startPatrol(Block block1 , Block block2){
        destination1 = block1;
        destination2 = block2;
        inPatrol = true;
        startMove(block1);
    }


    People (PeopleType peopleType, Government government, Block block){
        this.peopleType = peopleType;
        this.government = government;
        this.block = block;
        inMove = false;
        hitPoint = 25;
        selected = false;
    }

    public String isInMove() {
        if (inMove)
            return "M";
        return "S";
    }
    public void endMove(){
        if (inPatrol){
            Block temp = destination1;
            destination1 = destination2;
            destination2 = temp;
            startMove(destination1);
            return;
        }
        destination1 = null;
        inMove = false;
    }

    public void thisTurnMove(){
        int num = 0;
        while (!block.equals(destination1) && num<peopleType.speed && destination1!=null){
            if (findPath(ApplicationManager.getCurrentGame().getMap(), block.getX(), block.getY(), destination1.getX(), destination1.getY())==null){
                destination1 = null;
                destination2 = null;
                inMove = false;
                return;
            }
            block.removePeople(this);
            block = findPath(ApplicationManager.getCurrentGame().getMap(), block.getX(), block.getY(), destination1.getX(), destination1.getY()).get(0);
            //block.addPeople(this);
            System.out.println(block.getX()+","+block.getY());
            num ++;
        }
        if (block.equals(destination1)){
            inMove = false;
            endMove();
        }
    }

    public boolean checkIsDead(){
        return (hitPoint <= 0);
    }

    public void nextTurn(){
        thisTurnMove();
        if(checkIsDead()){
            death();
        }
        if (this instanceof Militia){
            ((Militia) this).stateTasks();
        }
    }

    private void death() {
        government.removePeople(this);
        block.removePeople(this);
    }

    public void hitPointReduce(double amount){
        hitPoint -= Math.max(0,(amount - peopleType.defencePower));
    }


    public double getHitPoint() {
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
        inPatrol = false;
        destination1 = null;
        destination2 = null;
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
            for (Block neighbor : getNeighbors(curr, map)) {
                Block neighborBlock = map.getBlockByPosition(neighbor.getX(), neighbor.getY());
                if (!visited[neighbor.getX()][neighbor.getY()] && neighborBlock.isPassable(this)) {
                    visited[neighbor.getX()][neighbor.getY()] = true;
                    queue.add(new Point(neighbor.getX(),neighbor.getY()));
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

    private ArrayList<Block> getNeighbors(Point curr, Map map) {
        return Tools.getBlacksInRadius(map.getSize(), curr.x, curr.y , 1 ,map);
    }


    public PeopleType getPeopleType() {
        return peopleType;
    }*/
}
