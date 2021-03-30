package turntabl.io.exchange_connectivity.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query()
    Optional<Product> findProductByTicker(String ticker);
}
