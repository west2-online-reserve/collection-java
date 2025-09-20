import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * player 实体类
 *
 * @author LYanl7
 * @since 2025-9-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("Gender")
    private String gender;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("LastName")
    private String lastName;
}
