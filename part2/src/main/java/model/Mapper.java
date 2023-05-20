package model;

public interface Mapper<T, Tid> {
    void Create(T entity) throws Exception;
    T Read(Tid id) throws Exception;
    void Update(T entity) throws Exception;
    void Delete(T entity) throws Exception;
}