package model;

import java.util.List;

public interface Repository<Tentity,Tkey> {
    List<Tentity> GetAll();
    Tentity Find(Tkey k);
    
    //Outras operações envolvendo coleções
    void Add(Tentity entity);
    void Delete(Tentity entity);
    void Save(Tentity e);
}