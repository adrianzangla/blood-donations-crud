package edu.uncuyo.blood_donations.dao;

import java.util.Set;
import edu.uncuyo.blood_donations.entity.Entity;

/**
 *
 * @author adrian
 */
public interface DAO<T extends Entity<U>, U> {
    
    public void create(T t);
    public T read(U u);
    public Set<T> read();
    public void update(T t);
    public void delete(U u);
}
