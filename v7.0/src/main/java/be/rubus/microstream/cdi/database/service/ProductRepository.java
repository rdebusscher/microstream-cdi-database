package be.rubus.microstream.cdi.database.service;

import be.rubus.microstream.cdi.database.model.Inventory;
import be.rubus.microstream.cdi.database.model.Product;
import one.microstream.integrations.cdi.types.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;


@ApplicationScoped
public class ProductRepository {
    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());

    @Inject
    private Inventory inventory;

    public Collection<Product> getAll() {
        return inventory.getProducts();
    }

    @Store  // Save at end of method
    public Product save(Product item) {
        inventory.add(item);
        return item;
    }

    public Optional<Product> findById(long id) {
        LOGGER.info("Finding the item by id: " + id);
        return inventory.findById(id);
    }

    @Store // Save at end of method
    public void deleteById(long id) {
        inventory.deleteById(id);
    }
}
