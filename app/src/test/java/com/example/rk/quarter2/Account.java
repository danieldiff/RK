mport java.util.ArrayList;

public class Account {
    private String studentName;
    private String studentID;
    private String studentPIN;
    private ArrayList<Infraction> infractions;


    public Account(String name, String ID, String pin){
        this.studentName = name;
        this.studentID = ID;
        this.studentPIN = pin;
        this.infractions = new ArrayList<>();
    }
    public String getName(){
        return studentName;
    }
    public String getID(){
        return studentID;
    }
    public void infractionAdd(Infraction infraction){
        infractions.add(infraction);
    }
    public void printInfractions() {
        for (Infraction infraction : infractions) {
            System.out.println(infraction);
        }
    }
}
