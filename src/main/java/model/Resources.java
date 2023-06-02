package model;

import java.util.ArrayList;

public class Resources {
    ArrayList<Resource> resources = new ArrayList<>();
    public int maximumCapacity=500;
    public Resources(){
        resources.add(new Resource("Wood",100));
        resources.add(new Resource("Stone",100));
        resources.add(new Resource("Iron",100));
        resources.add(new Resource("Wheat",100));
        resources.add(new Resource("Wine",100));
        resources.add(new Resource("Hop",100));
        resources.add(new Resource("Horse",100));
        resources.add(new Resource("Pitch",100));
        resources.add(new Resource("Flour",100));
        resources.add(new Resource("Oil",100));
        resources.add(new Resource("Bow",100));
        resources.add(new Resource("Spike",100));
        resources.add(new Resource("Armor",100));

    }

    public Resource getResource(String name){
        for (Resource resource : resources) {
            if (resource.getName().equals(name)) {
                return resource;
            }
        }
        return null;
    }

    public void changeResourceAmount(String name, int amount){
        getResource(name).changeCount(amount);
    }

    public int getResourceAmount(String name){
        return getResource(name).getCount();
    }

    public boolean validResource(String name){
        return getResource(name) != null;
    }
    public void changeOfResourcesAtTheEndOfTurn(){
        for(Resource resource:resources){
            resource.changeCount(resource.ProductionRate);
            if(resource.getCount()<0)
                resource.changeCount((-1)*resource.getCount());
            if(resource.getCount()>maximumCapacity)
                resource.setAmount(maximumCapacity);
        }
    }
}
