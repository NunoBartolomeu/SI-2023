package business_logic;

public interface UnitOfWork {
    void NotifyInsert(Object entity);
    void NotifyUpdate(Object entity);
    void NotifyDelete(Object entity);
    void Commit();
    void Rollback();
}