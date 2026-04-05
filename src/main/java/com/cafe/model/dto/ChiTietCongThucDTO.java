package com.cafe.model.dto;

import com.cafe.model.entity.ChiTietCongThuc;
import java.io.Serializable;

/**
 * Data Transfer Object cho bảng ChiTietCongThuc.
 * Composite key (MaCongThuc, MaNguyenLieu) được flatten trực tiếp thành các trường.
 */
public class ChiTietCongThucDTO implements Serializable {

    private String MaCongThuc;
    private String MaNguyenLieu;
    private String TenNguyenLieu;
    private Integer SoLuong;

    public ChiTietCongThucDTO() {}

    public ChiTietCongThucDTO(String MaCongThuc, String MaNguyenLieu, String TenNguyenLieu, Integer SoLuong) {
        this.MaCongThuc = MaCongThuc;
        this.MaNguyenLieu = MaNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.SoLuong = SoLuong;
    }

    public String getMaCongThuc() { return MaCongThuc; }
    public void setMaCongThuc(String MaCongThuc) { this.MaCongThuc = MaCongThuc; }

    public String getMaNguyenLieu() { return MaNguyenLieu; }
    public void setMaNguyenLieu(String MaNguyenLieu) { this.MaNguyenLieu = MaNguyenLieu; }

    public String getTenNguyenLieu() { return TenNguyenLieu; }
    public void setTenNguyenLieu(String TenNguyenLieu) { this.TenNguyenLieu = TenNguyenLieu; }

    public Integer getSoLuong() { return SoLuong; }
    public void setSoLuong(Integer SoLuong) { this.SoLuong = SoLuong; }

    public static ChiTietCongThucDTO fromEntity(ChiTietCongThuc entity) {
        if (entity == null) return null;
        ChiTietCongThucDTO dto = new ChiTietCongThucDTO();
        if (entity.getId() != null) {
            dto.setMaCongThuc(entity.getId().getMaCongThuc());
            dto.setMaNguyenLieu(entity.getId().getMaNguyenLieu());
        }
        dto.setSoLuong(entity.getSoLuong());
        if (entity.getNguyenLieu() != null) {
            dto.setTenNguyenLieu(entity.getNguyenLieu().getTenNguyenLieu());
        }
        return dto;
    }
}
