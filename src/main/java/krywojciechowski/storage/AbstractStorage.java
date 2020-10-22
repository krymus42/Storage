package krywojciechowski.storage;

import krywojciechowski.storage.Interfaces.Cacheable;

public abstract class AbstractStorage {
public abstract void saveInCache(Cacheable cacheable);
public abstract Object find(Cacheable cacheable);
}
