

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class RegistrationInfo {
    private final String city;
    private final Date date;
    private final String name;
    private final String phone;
}
