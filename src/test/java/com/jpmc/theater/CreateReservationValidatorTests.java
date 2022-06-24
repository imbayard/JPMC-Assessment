package com.jpmc.theater;

import com.jpmc.theater.model.CreateReservationModel;
import com.jpmc.theater.validation.CreateReservationModelValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class CreateReservationValidatorTests {

    @InjectMocks
    CreateReservationModelValidator validator;

    @Mock
    ConstraintValidatorContext cxt;
    @Mock
    ConstraintValidatorContext.ConstraintViolationBuilder builder;

    @BeforeEach
    public void init() { MockitoAnnotations.openMocks(this);}

    @Test
    public void testSuccess() {
        CreateReservationModel createReservationModel = new CreateReservationModel("Bayard Eton", 1, 3);

        Mockito.when(cxt.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        Mockito.when(builder.addConstraintViolation()).thenReturn(cxt);

        assertTrue(validator.isValid(createReservationModel, cxt));
    }

    @Test
    public void testNullCustomerName() {
        CreateReservationModel createReservationModel = new CreateReservationModel(null, 1, 3);

        Mockito.when(cxt.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        Mockito.when(builder.addConstraintViolation()).thenReturn(cxt);

        assertFalse(validator.isValid(createReservationModel, cxt));
    }

    @Test
    public void testNullIntegerValue() {
        CreateReservationModel createReservationModel = new CreateReservationModel("Bayard Eton", null, 3);

        Mockito.when(cxt.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        Mockito.when(builder.addConstraintViolation()).thenReturn(cxt);

        assertFalse(validator.isValid(createReservationModel, cxt));
    }

    @Test
    public void testNegativeIntegerValue() {
        CreateReservationModel createReservationModel = new CreateReservationModel("Bayard Eton", 1, -2);

        Mockito.when(cxt.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        Mockito.when(builder.addConstraintViolation()).thenReturn(cxt);

        assertFalse(validator.isValid(createReservationModel, cxt));
    }
}
