package useraddressexample;

import java.util.Objects;

public class Address {
    private final String street;
    private final int houseNumber;
    private final int zip;
    private final String location;

    public Address(String street, int houseNumber, int zip, String location) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zip = zip;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber &&
                zip == address.zip &&
                Objects.equals(street, address.street) &&
                Objects.equals(location, address.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, zip, location);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", zip=" + zip +
                ", location='" + location + '\'' +
                '}';
    }
}
