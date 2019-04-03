package com.objectfrontier.land.service;

import com.objectfrontier.land.model.Reference;

/**
 * @author karthik.n
 * @since v1.0
 */

public interface ReferenceService {

    public Reference create(Reference reference);

    public Reference update(Reference reference);

    public void delete(long id);

    public Reference read(long id);
}
