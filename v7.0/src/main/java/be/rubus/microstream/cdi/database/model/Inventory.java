package be.rubus.microstream.cdi.database.model;

import one.microstream.integrations.cdi.types.Storage;

import java.util.*;
import java.util.function.Predicate;

@Storage  // Make it a CDI ApplicationScoped bean that is handled by StorageManager (and @Store)
public class Inventory {

    private final Set<Product> products = new HashSet<>();

    public void add(Product product) {
        Objects.requireNonNull(product, "Product is required");
        products.add(product);
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Optional<Product> findById(long id) {
        return products.stream()
                .filter(isIdEquals(id))
                .limit(1)
                .findFirst();
    }

    public void deleteById(long id) {
        products.removeIf(isIdEquals(id));
    }

    private Predicate<Product> isIdEquals(long id) {
        return p -> p.getId() == id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Inventory inventory = (Inventory) o;
        return Objects.equals(products, inventory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "Inventory{"
                +
                "products="
                + products
                +
                '}';
    }

}
