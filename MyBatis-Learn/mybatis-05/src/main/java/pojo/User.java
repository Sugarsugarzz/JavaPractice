package pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Data
@AllArgsConstructor
public class User {

    private int id;
    private String name;
    private String password;
}
