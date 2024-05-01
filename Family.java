import java.util.ArrayList;

class Family {
    ArrayList<Person> family = new ArrayList<Person>();

    public void addPerson(String name, int parent1, int parent2) {
        family.add(new Person(name,parent1,parent2));
    }

    public int getIDFromName(String name) {
        for(Person x : family) {
            if(x.getName()==name) {
                return x.getID();
            }
        }
        return -1;
    }

    public String getNameFromID(int id) {
        for(Person x : family) {
            if(x.getID()==id) {
                return x.getName();
            }
        }
        return "Unknown";
    }

    public String parentRelationship(String current) {
        if(current.equals("Self")) {
            return "Parent";
        }
        else if(current.equals("Parent")) {
            return "GrandParent";
        }
        else {
            return "Great "+current;
        }
    }

    public String childRelationship(String current) {
        if(current.equals("Self")) {
            return "Child";
        }
        else if(current.equals("Child")) {
            return "GrandChild";
        }
        else {
            return "Great "+current;
        }
    }

    public String everyone() {
        String returnString="";
        for(Person x : family) {
            returnString+=x.getID()+") "+x.getName()+"\n";
        }
        return returnString;
    }

    public ArrayList<Integer> getChildrenIDs(int id) {
        ArrayList<Integer> childrenIDs = new ArrayList<>();
        for (Person person : family) {
            if (person.getParent1() == id || person.getParent2() == id) {
                childrenIDs.add(person.getID());
            }
        }
        return childrenIDs;
    }

    public int[] getParents(int ID) {
        int[] parents = new int[]{-1, -1};
        for (Person person : family) {
            if (person.getID() == ID) {
                parents[0] = person.getParent1();
                parents[1] = person.getParent2();
                break;
            }
        }
        return parents;
    }

    public void printParents(int childID,String relationship) {
        if (childID == -1) {
            return;
        }
        System.out.println(relationship + " " + getNameFromID(childID));
        int[] parents = getParents(childID);
        if (parents[0] != -1) {
            printParents(parents[0], parentRelationship(relationship));
        }
        if (parents[1] != -1) {
            printParents(parents[1], parentRelationship(relationship));
        }
    }

    public void printChildren(int parentID,String relationship) {
        if (parentID == -1) {
            return;
        }
        System.out.println(relationship + " " + getNameFromID(parentID));
        ArrayList<Integer> childrenIDs = getChildrenIDs(parentID);
        for (int childID : childrenIDs) {
            printChildren(childID, childRelationship(relationship));
        }
    }
}