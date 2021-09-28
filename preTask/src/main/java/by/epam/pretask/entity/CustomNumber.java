package by.epam.pretask.entity;

public class CustomNumber {
    private double number;

    public CustomNumber(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomNumber other = (CustomNumber) o;

        return this.number == other.getNumber();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(number);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomNumber{");
        sb.append(number);
        sb.append('}');
        return sb.toString();
    }
}
