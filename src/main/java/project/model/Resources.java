package project.model;

import java.util.ArrayList;

public class Resources {
    ArrayList<Resource> resources = new ArrayList<>();
    public Resources(){
        resources.add(new Resource("wood",0));
        resources.add(new Resource("stone",0));
        resources.add(new Resource("name3",0));
        resources.add(new Resource("name4",0));
        resources.add(new Resource("name5",0));
        resources.add(new Resource("name6",0));
        resources.add(new Resource("name7",0));
        resources.add(new Resource("name8",0));
        resources.add(new Resource("name9",0));
        resources.add(new Resource("name10",0));
        resources.add(new Resource("name11",0));
        resources.add(new Resource("name12",0));
    }

    private Resource getResource(String name){
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
}
