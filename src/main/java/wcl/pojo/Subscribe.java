package wcl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Subscribe {
    private Integer subscribeId;

    private String roomId;

    private String roomTitle;

    private String userName;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;




}