package dev.whyneet.ec_api.core.abstracts;

import java.util.Optional;

public interface IDataRepository<Entity, Id> {
    Optional<Entity> getById(Id id);
    Entity save(Entity entity);
    void deleteById(Id id);
}
