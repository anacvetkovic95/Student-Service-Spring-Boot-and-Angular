package ana.cvetkovic.springboot.app.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ana.cvetkovic.springboot.app.be.entity.StudentExamEntity;

public interface StudentExamRepository extends JpaRepository<StudentExamEntity, Long>{

}
