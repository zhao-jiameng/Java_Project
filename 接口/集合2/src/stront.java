import java.util.Objects;

public class stront {
    private String name;

    public stront(String name) {
        this.name = name;
    }

    public stront() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        stront stront = (stront) o;
        return Objects.equals(name, stront.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
