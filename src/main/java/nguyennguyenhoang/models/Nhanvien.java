package nguyennguyenhoang.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "nhanvien")
public class Nhanvien {
	@Id
	@NotNull(message = "mã nhân viên không được để trống")
	@Column(name = "ma_nv")
	private String id;
	@NotNull(message = "tên nhân viên không được để trống")
	@Column(name = "ten_nv")
	private String name;
	@Column(name = "phai")
	private String phai;
	@Column(name = "noi_sinh")
	private String noisinh;
	@Column(name = "ma_phong")
	private String maphong;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhai() {
		return phai;
	}

	public void setPhai(String phai) {
		this.phai = phai;
	}

	public String getNoisinh() {
		return noisinh;
	}

	public void setNoisinh(String noisinh) {
		this.noisinh = noisinh;
	}

	public String getMaphong() {
		return maphong;
	}

	public void setMaphong(String maphong) {
		this.maphong = maphong;
	}

	public Phongban getPhongban() {
		return phongban;
	}

	public void setPhongban(Phongban phongban) {
		this.phongban = phongban;
	}

	public int getLuong() {
		return luong;
	}

	public void setLuong(int luong) {
		this.luong = luong;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maphong")
	private Phongban phongban;
	@Column(name = "luong")
	private int luong;

}
