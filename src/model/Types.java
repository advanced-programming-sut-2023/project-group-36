package model;

import java.util.ArrayList;

public class Types {
    ArrayList<PeopleType> peopleTypes;

    public void addPeopleTypes(){
        peopleTypes.add(new PeopleType("Archer","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Crossbowmen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Spearmen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Pikemen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Macemen","launchers" ,1, 2,2, 4, 0));
        peopleTypes.add(new PeopleType("Swordsmen","launchers" ,1, 2,2, 4, 0));
        // ...
    }

    public PeopleType getPeopleTypeByType(String type){
        for (PeopleType peopleType : peopleTypes) {
            if(peopleType.type.equals(type)){
                return peopleType;
            }
        }
        return null;
    }

}
