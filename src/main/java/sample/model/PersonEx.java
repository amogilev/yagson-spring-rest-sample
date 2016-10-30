package sample.model;

/**
 * @author Andrey Mogilev
 */
public class PersonEx extends POJOPerson {

    private String salutation;

    public PersonEx(String salutation, String name, String family) {
        super(name, family);
        this.salutation = salutation;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersonEx personEx = (PersonEx) o;

        return salutation != null ? salutation.equals(personEx.salutation) : personEx.salutation == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (salutation != null ? salutation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonEx{" +
                salutation + ' ' + getName() + ' ' + getFamily() +
                "}";
    }
}
