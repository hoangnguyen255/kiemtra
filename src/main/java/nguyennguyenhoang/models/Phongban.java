package nguyennguyenhoang.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "phongban")
public class Phongban {
	@Id
	@NotNull(message = "mã phòng ban không được để trống")
	@Column(name = "ma_phong")
	private String maphong;
	@NotNull(message = "tên phòng không được để trống")
	@Column(name = "ten_phong")
	private String tenphong;
	public String getMaphong() {
		return maphong;
	}
	public void setMaphong(String maphong) {
		this.maphong = maphong;
	}
	public String getTenphong() {
		return tenphong;
	}
	public void setTenphong(String tenphong) {
		this.tenphong = tenphong;
	}
	
}


	