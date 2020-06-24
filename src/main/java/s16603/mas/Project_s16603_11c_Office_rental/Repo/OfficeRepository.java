package s16603.mas.Project_s16603_11c_Office_rental.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Office;

import java.util.Optional;

public interface OfficeRepository extends CrudRepository<Office,Long> {

    @Query("select office from Office office join fetch office.owners where office.officeId = :id")
    public Optional<Office> findById(@Param("id") Long id);
}
