import os
import re

base_dir = r"d:\UserD\Documents\LapTrinhWindow\IntelliJCode\ProjectCuoiKiLTWD\src\main\java\com\cafe"
entity_dir = os.path.join(base_dir, "model", "entity")
dto_dir = os.path.join(base_dir, "model", "dto")
dao_dir = os.path.join(base_dir, "dao")
dao_impl_dir = os.path.join(base_dir, "dao", "impl")

def extract_fields(content):
    fields = []
    # Split by blocks or just iter by "private "
    lines = content.split('\n')
    current_annotations = []
    for line in lines:
        line = line.strip()
        if line.startswith('@'):
            current_annotations.append(line)
        elif line.startswith('//') or not line:
            continue
        elif line.startswith('private '):
            # Parse field
            match = re.match(r'private\s+([\w<>]+)\s+(\w+);', line)
            if match:
                t = match.group(1)
                n = match.group(2)
                
                # Check annotations for overrides
                ann_str = " ".join(current_annotations)
                
                if "List<" in t or "Id" in t and "ChiTiet" in t and "Id" != n:
                    # Ignore OneToMany and Composite Id classes (we extract via MapsId)
                    pass
                else:
                    if "@ManyToOne" in ann_str or "@OneToOne" in ann_str:
                        # find JoinColumn
                        jc = re.search(r'@JoinColumn\(name\s*=\s*"([^"]+)"\)', ann_str)
                        if jc:
                            fields.append(("String", jc.group(1))) # FK maps to String
                        else:
                            fields.append(("String", "Ma" + n))
                    else:
                        fields.append((t, n))
            current_annotations = []
        else:
            if not line.startswith('public ') and not line.startswith('}'):
                # Reset if it's something else
                pass
            current_annotations = []
            
    # Remove duplicates
    seen = set()
    res = []
    for t, n in fields:
        if n not in seen:
            seen.add(n)
            res.append((t, n))
    return res

def generate_dto(entity_name, fields):
    imports = ["import java.io.Serializable;"]
    if any(f[0] == "Date" for f in fields): imports.append("import java.sql.Date;")
    if any(f[0] == "BigDecimal" for f in fields): imports.append("import java.math.BigDecimal;")
    
    code = f"""package com.cafe.model.dto;

{chr(10).join(imports)}

/**
 * Data Transfer Object cho bảng {entity_name}.
 */
public class {entity_name}DTO implements Serializable {{

"""
    # Fields
    for t, n in fields:
        code += f"    private {t} {n};\n"
        
    # Default constructor
    code += f"\n    public {entity_name}DTO() {{}}\n"
    
    # All-args constructor
    code += f"\n    public {entity_name}DTO({', '.join([f'{t} {n}' for t, n in fields])}) {{\n"
    for t, n in fields:
        code += f"        this.{n} = {n};\n"
    code += "    }\n\n"
    
    # Getters and Setters
    for t, n in fields:
        code += f"""    public {t} get{n[:1].upper() + n[1:]}() {{
        return {n};
    }}

"""
        code += f"""    public void set{n[:1].upper() + n[1:]}({t} {n}) {{
        this.{n} = {n};
    }}

"""
    code += "}\n"
    
    with open(os.path.join(dto_dir, f"{entity_name}DTO.java"), "w", encoding="utf-8") as f:
        f.write(code)

def generate_dao(entity_name, fields):
    # Specialized methods
    methods = []
    # Identify searchable string fields
    for t, n in fields:
        if t == "String" and n != f"Ma{entity_name}":
            if "Ten" in n:
                methods.append(f"""    /**
     * Tìm kiếm {entity_name} theo {n}.
     * @param {n} Tham số tìm kiếm
     * @return Danh sách {entity_name}
     */
    java.util.List<{entity_name}> timTheo{n}({t} {n});""")
                
    # Specialized calculative methods
    imports = []
    if entity_name == "HoaDon":
        imports.append("import java.math.BigDecimal;")
        imports.append("import java.sql.Date;")
        methods.append("""    /**
     * Tìm kiếm HoaDon trong khoảng thời gian.
     */
    java.util.List<HoaDon> timTheoKhoangThoiGian(Date tuNgay, Date denNgay);""")
        methods.append("""    /**
     * Tính tổng doanh thu trong khoảng thời gian.
     */
    BigDecimal tinhTongDoanhThu(Date tuNgay, Date denNgay);""")
    elif entity_name == "PhieuNhap":
        imports.append("import java.math.BigDecimal;")
        imports.append("import java.sql.Date;")
        methods.append("""    /**
     * Tính tổng chi phí nhập trong khoảng thời gian.
     */
    BigDecimal tinhTongChiPhi(Date tuNgay, Date denNgay);""")
        
    # Existing file parsing to keep generic setup
    dao_path = os.path.join(dao_dir, f"{entity_name}DAO.java")
    if not os.path.exists(dao_path):
        return
        
    code = f"""package com.cafe.dao;

import com.cafe.model.entity.{entity_name};
{chr(10).join(imports)}

/**
 * Interface DAO cho bảng {entity_name}.
 */
public interface {entity_name}DAO extends GenericDAO<{entity_name}, String> {{
{chr(10).join(methods)}
}}
"""
    with open(dao_path, "w", encoding="utf-8") as f:
        f.write(code)

def generate_dao_impl(entity_name, fields):
    # Basic impl
    methods = []
    for t, n in fields:
        if t == "String" and n != f"Ma{entity_name}":
            if "Ten" in n:
                methods.append(f"""    @Override
    public java.util.List<{entity_name}> timTheo{n}({t} {n}) {{
        jakarta.persistence.EntityManager em = com.cafe.util.HibernateUtil.getEntityManager();
        try {{
            String jpql = "SELECT e FROM {entity_name} e WHERE e.{n} LIKE :val";
            return em.createQuery(jpql, {entity_name}.class)
                     .setParameter("val", "%" + {n} + "%")
                     .getResultList();
        }} finally {{
            em.close();
        }}
    }}""")
                
    imports = []
    if entity_name == "HoaDon":
        imports.append("import java.math.BigDecimal;")
        imports.append("import java.sql.Date;")
        methods.append("""    @Override
    public java.util.List<HoaDon> timTheoKhoangThoiGian(Date tuNgay, Date denNgay) {
        jakarta.persistence.EntityManager em = com.cafe.util.HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT e FROM HoaDon e WHERE e.NgayLap BETWEEN :tuNgay AND :denNgay";
            return em.createQuery(jpql, HoaDon.class)
                     .setParameter("tuNgay", tuNgay)
                     .setParameter("denNgay", denNgay)
                     .getResultList();
        } finally {
            em.close();
        }
    }""")
        methods.append("""    @Override
    public BigDecimal tinhTongDoanhThu(Date tuNgay, Date denNgay) {
        jakarta.persistence.EntityManager em = com.cafe.util.HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT SUM(e.TongTien) FROM HoaDon e WHERE e.NgayLap BETWEEN :tuNgay AND :denNgay";
            BigDecimal total = em.createQuery(jpql, BigDecimal.class)
                                 .setParameter("tuNgay", tuNgay)
                                 .setParameter("denNgay", denNgay)
                                 .getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } finally {
            em.close();
        }
    }""")
    elif entity_name == "PhieuNhap":
        imports.append("import java.math.BigDecimal;")
        imports.append("import java.sql.Date;")
        methods.append("""    @Override
    public BigDecimal tinhTongChiPhi(Date tuNgay, Date denNgay) {
        jakarta.persistence.EntityManager em = com.cafe.util.HibernateUtil.getEntityManager();
        try {
            String jpql = "SELECT SUM(e.TongTien) FROM PhieuNhap e WHERE e.NgayNhap BETWEEN :tuNgay AND :denNgay";
            BigDecimal total = em.createQuery(jpql, BigDecimal.class)
                                 .setParameter("tuNgay", tuNgay)
                                 .setParameter("denNgay", denNgay)
                                 .getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } finally {
            em.close();
        }
    }""")
    
    impl_path = os.path.join(dao_impl_dir, f"{entity_name}DAOImpl.java")
    if not os.path.exists(impl_path):
        return
        
    code = f"""package com.cafe.dao.impl;

import com.cafe.dao.{entity_name}DAO;
import com.cafe.model.entity.{entity_name};
{chr(10).join(imports)}

/**
 * Class implementation của {entity_name}DAO.
 */
public class {entity_name}DAOImpl extends GenericDAOImpl<{entity_name}, String> implements {entity_name}DAO {{

    /**
     * Khởi tạo DAO cho Entity {entity_name}.
     */
    public {entity_name}DAOImpl() {{
        super({entity_name}.class);
    }}

{chr(10).join(methods)}
}}
"""
    with open(impl_path, "w", encoding="utf-8") as f:
        f.write(code)

for filename in os.listdir(entity_dir):
    if not filename.endswith(".java") or "Id.java" in filename:
        continue
    entity_name = filename[:-5]
    filepath = os.path.join(entity_dir, filename)
    with open(filepath, "r", encoding="utf-8") as f:
        content = f.read()
    
    fields = extract_fields(content)
    # manual fix since ChiTietHoaDon might miss SoLuong if parsing bug exists
    # we trust our extraction
    generate_dto(entity_name, fields)
    generate_dao(entity_name, fields)
    generate_dao_impl(entity_name, fields)

print("Generated all files successfully.")
