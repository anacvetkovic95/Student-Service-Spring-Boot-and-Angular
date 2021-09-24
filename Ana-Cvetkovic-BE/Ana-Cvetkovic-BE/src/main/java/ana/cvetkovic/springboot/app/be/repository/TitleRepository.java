package ana.cvetkovic.springboot.app.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ana.cvetkovic.springboot.app.be.entity.TitleEntity;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long>{

}
