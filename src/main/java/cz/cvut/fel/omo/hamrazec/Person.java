package cz.cvut.fel.omo.hamrazec;

public abstract class Person implements FactoryWorker{

    protected String firstname;
    protected String lastname;


    public String getFirstname() {

        return firstname;
    }


    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }


    public String getLastname() {

        return lastname;
    }


    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

}
