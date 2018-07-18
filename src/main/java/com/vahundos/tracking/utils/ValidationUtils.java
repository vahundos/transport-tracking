package com.vahundos.tracking.utils;

import com.vahundos.tracking.entity.AbstractBaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {

    public static void validateForCreation(AbstractBaseEntity entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("Cant create transport with existing ID = " + entity.getId());
        }
    }
}
