package nguyennguyenhoang.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import nguyennguyenhoang.models.Nhanvien;
import nguyennguyenhoang.repository.NhanvienRepository;
@Service
public class NhanvienService {
	@Autowired
	private NhanvienRepository nhanvienRepository;
	public List<Nhanvien> GetAll(){
        return (List<Nhanvien>) nhanvienRepository.findAll();
    }

    public void Add(Nhanvien newProduct){
        nhanvienRepository.save(newProduct);
    }

    public Nhanvien get(String id) {
        return nhanvienRepository.findById(id).get();
    }
       
    public void save(Nhanvien nhanvien) {
        nhanvienRepository.save(nhanvien);
    }

    public void delete(String id) {
        nhanvienRepository.deleteById(id);
    }
    public void update(Nhanvien nhanvien) {
        nhanvienRepository.save(nhanvien);
    }
	public Page<Nhanvien> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		     Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.nhanvienRepository.findAll(pageable);
	}

}

