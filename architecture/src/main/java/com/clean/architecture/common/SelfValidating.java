package com.clean.architecture.common;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

public abstract class SelfValidating<T> {

    // java validator
    private Validator validator;

    /**
     * validator를 구현
     */
    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf() {
        /**
         * (T) this  는 Object를 받는 부분으로 해당 타입의 값을 넣으면 된다 -> validate 할 객체를 넣음
         *
         * set<ConstraintViolation<T>> 로 구현함으로서 원하는 에러메시지를 직접 던질 수 있음
         */
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) { // 위반값이 존재한다면
            throw new ConstraintViolationException(violations); // 해당 객체값이 위반하면 -> 에러 발생
        }
    }

}
