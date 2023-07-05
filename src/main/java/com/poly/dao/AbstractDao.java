package com.poly.dao;

import com.poly.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbstractDao<T> {
    public static final EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    protected void finalize() throws Throwable {
        entityManager.close();
        super.finalize();
    }

    public T findById(Class<T> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll(Class<T> clazz, boolean existIsactive) {
        String entiyName = clazz.getSimpleName(); // Lấy được tên class truyền vào

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT o FROM ").append(entiyName).append(" o");
        if (existIsactive == true) {
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);

        return query.getResultList();
        // Câu lệnh sql
        // SELECT o FROM History o WHERE isActive = 1;
    }

    // PageSize là số lường phần tử trong 1 trang
    public List<T> findAll(Class<T> clazz, boolean existIsactive, int pageNumber, int pageSize) {
        String entiyName = clazz.getSimpleName(); // Lấy được tên class truyền vào

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT o FROM ").append(entiyName).append(" o");
        if (existIsactive == true) {
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);

        query.setFirstResult((pageNumber - 1) * pageSize); // Đ ánh index từ trang thứ 0 nhưng người dùng là -1
        query.setMaxResults(pageSize);
        return query.getResultList();
        // Câu lệnh sql
        // SELECT o FROM History o WHERE isActive = 1;
        /*
            Ví dụ có 5 phần tử

         */
    }

    // Ví duhj select o from user where o.userName = ?0 and o.password
    // findOne(User.class, sql, "duynt", "111")
    public T findOne(Class<T> clazz, String sql, Object... params) {
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        // Muốn xét param thì phải biết có bao nhiêu parrams
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        // Tại sao findOne mà getResultList
        // Tránh trường hợp k ra 1 kết quả nào hết. Như vậy sẽ bị lỗi

        List<T> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);

    }

    public List<T> finMany(Class<T> clazz, String sql, Object... params) {
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }

        return query.getResultList();
    }
    /*
    Dòng 1: Sử dụng chú thích @SuppressWarnings(“unchecked”) để bỏ qua các cảnh báo về việc sử dụng các kiểu chưa được kiểm tra (như List<Object[]>).
    Dòng 2: Khai báo một phương thức có tên là findManyByNativeQuery, có kiểu trả về là List<Object[]>,
        nhận vào ba tham số: một lớp Class<T> để chỉ định kiểu của các đối tượng trong danh sách,
        một chuỗi String sql để chứa câu lệnh SQL, và một số lượng tùy ý các tham số Object để điền
        vào các tham số trong câu lệnh SQL.
    Dòng 3: Tạo một đối tượng Query bằng cách gọi phương thức createQuery của đối tượng entityManager, truyền vào chuỗi sql và lớp clazz.
    Đối tượng Query là một giao diện để thực hiện các truy vấn trên cơ sở dữ liệu.
    Dòng 4: Sử dụng một vòng lặp for để duyệt qua các tham số Object trong mảng params,
    bắt đầu từ chỉ số 0.
    Dòng 5: Gọi phương thức setParameter của đối tượng Query, truyền vào chỉ số i và giá trị params[i].
    Phương thức này sẽ gán giá trị cho các tham số trong câu lệnh SQL theo thứ tự.
    Dòng 6: Kết thúc vòng lặp for.
    Dòng 7: Gọi phương thức getResultList của đối tượng Query, trả về một danh sách các mảng đối tượng chứa kết quả của truy vấn.
    Kết thúc phương thức findManyByNativeQuery.
     */


    // Trả về 1 list chứa 1 mảng object
    @SuppressWarnings("unchecked")
    public List<Object[]> findManyByNativeQuery(Class<T> clazz, String sql, Object... params) {
        Query query = entityManager.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }


    public T create(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            System.out.println("Tạo thành công");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot insert entity " + entity.getClass().getSimpleName() + "toDB");
            throw new RuntimeException(e);
        }
    }

    public T update(T entity) {

        try {
            entityManager.getTransaction().begin();

            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            System.out.println("Update thành công");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot update entity " + entity.getClass().getSimpleName() + "toDB");
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            System.out.println("Delete thành công");
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Cannot delete entity " + entity.getClass().getSimpleName() + "toDB");
            throw new RuntimeException(e);
        }
    }


}
