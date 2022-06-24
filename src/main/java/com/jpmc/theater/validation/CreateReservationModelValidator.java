package com.jpmc.theater.validation;

import com.jpmc.theater.model.CreateReservationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreateReservationModelValidator implements ConstraintValidator<CreateReservationModelValidation, CreateReservationModel> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean isValid(CreateReservationModel reservationToValidate, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        logger.info("Validating Request...");
        return isCustomerNameValid(reservationToValidate.getName(), context)
                && isIntegerValueValid(reservationToValidate.getMovieId(), context, "Movie id")
                && isIntegerValueValid(reservationToValidate.getNumberOfTickets(), context, "Number of tickets");
    }

    private boolean isCustomerNameValid(String customerName, ConstraintValidatorContext context) {
        if (!StringUtils.hasLength(customerName)) {
            context.buildConstraintViolationWithTemplate("CUS001: Customer Cannot Be Null or Blank.").addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isIntegerValueValid(Integer value, ConstraintValidatorContext context, String fieldName) {
        if (value == null || value <= 0) {
            String err = "NUM001:" + fieldName + " must be a positive integer value and cannot be null.";
            context.buildConstraintViolationWithTemplate(err).addConstraintViolation();
            return false;
        }
        return true;
    }
}
