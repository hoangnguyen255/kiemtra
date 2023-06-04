package nguyennguyenhoang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nguyennguyenhoang.models.Nhanvien;
@Repository
public interface NhanvienRepository extends JpaRepository<Nhanvien, String> {

}
