package ba.unsa.etf.pnwt.repository;

import ba.unsa.etf.pnwt.dto.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
