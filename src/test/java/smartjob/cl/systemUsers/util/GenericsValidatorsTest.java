package smartjob.cl.systemUsers.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class GenericsValidatorsTest {

    @InjectMocks
    private GenericsValidators genericsValidators;

    @BeforeEach
    public void setUp() {
        genericsValidators.setMailRegexp("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    @Test
    public void testMailValidateValid() {
        String validEmail = "user@example.com";
        boolean result = genericsValidators.mailValidate(validEmail);
        assertTrue(result);
    }

    @Test
    public void testMailValidateInvalid() {
        String invalidEmail = "invalid_email";
        boolean result = genericsValidators.mailValidate(invalidEmail);
        assertFalse(result);
    }
}
