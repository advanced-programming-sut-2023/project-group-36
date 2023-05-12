package project.model;

import java.util.ArrayList;

public class Resources {
    ArrayList<Resource> resources = new ArrayList<>();
    public Resources(){
        resources.add(new Resource("Wood",0));
        resources.add(new Resource("Stone",0));
        resources.add(new Resource("Iron",0));
        resources.add(new Resource("Wheat",0));
        resources.add(new Resource("Bread",0));
        resources.add(new Resource("Apple",0));
        resources.add(new Resource("Wine",0));
        resources.add(new Resource("Hop",0));
        resources.add(new Resource("Horse",0));
        resources.add(new Resource("Pitch",0));
        resources.add(new Resource("Flour",0));
        resources.add(new Resource("ProccesedPitch",0));
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
        }
    }
}
