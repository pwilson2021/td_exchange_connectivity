package turntabl.io.exchange_connectivity.model.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
//    Optional<Object> findAllById(int portfolioId);
}
