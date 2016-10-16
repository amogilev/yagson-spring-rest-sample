package sample.model;

/**
 * @author Andrey Mogilev
 */
public class POJOPerson {

    private String name;
    private String family;
    private transient boolean yaGsonMarker = true;

    public POJOPerson() {
    }

    public POJOPerson(String name, String family) {
        this.name = name;
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        POJOPerson that = (POJOPerson) o;

        if (family != null ? !family.equals(that.family) : that.family != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = family != null ? family.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "POJOPerson{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                '}';
    }
}
