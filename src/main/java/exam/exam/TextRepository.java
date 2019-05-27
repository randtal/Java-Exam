package exam.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


// Middle-man for turning Java code into SQL queries.
// JpaRepository<TextEntity, Long>, TextEntity stands for the Schema used and Long
// is the main class of the Schemas id value. If id would be an int, it would be Integer.
@RepositoryRestResource  // This annotation is responsible for creating the HATEOAS compliant REST API.
interface TextRepository extends JpaRepository<TextEntity, Long>{

}