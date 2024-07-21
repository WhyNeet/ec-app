package dev.whyneet.ec_api.core.abstracts;

import org.springframework.data.domain.Example;

import java.util.Optional;

public interface IDataRepository<Entity, Id> {
    Optional<Entity> getById(Id id);
    <S extends Entity> Optional<S> findOne(Example<S> example);
    Entity save(Entity entity);
    void deleteById(Id id);
}
