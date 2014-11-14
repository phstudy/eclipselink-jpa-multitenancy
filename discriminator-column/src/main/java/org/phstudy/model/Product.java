package org.phstudy.model;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.eclipse.persistence.annotations.MultitenantType.*;
import static org.eclipse.persistence.config.PersistenceUnitProperties.*;

/**
 * Created by study on 11/14/14.
 */
@Entity
@Multitenant(SINGLE_TABLE)
@TenantDiscriminatorColumn(contextProperty = MULTITENANT_PROPERTY_DEFAULT)
public class Product {
    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
