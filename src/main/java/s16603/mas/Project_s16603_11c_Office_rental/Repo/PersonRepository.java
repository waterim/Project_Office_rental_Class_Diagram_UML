package s16603.mas.Project_s16603_11c_Office_rental.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
}
