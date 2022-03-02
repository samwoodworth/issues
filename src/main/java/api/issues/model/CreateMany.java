package api.issues.model;

public class CreateMany {
    private int numToCreate;

    public CreateMany() {}

    public CreateMany(int numToCreate) {
        this.numToCreate = numToCreate;
    }

    public void setNumToCreate(int numToCreate) {
        this.numToCreate = numToCreate;
    }

    public int getNumToCreate() {
        return numToCreate;
    }
}