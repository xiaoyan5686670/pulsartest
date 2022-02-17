package examples.clients.buildtools.common;

import com.beust.jcommander.Parameter;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 15:39 2022/2/17
 @Modified By:
 **********************************/
public class ProducerFlags extends TopicFlags{
    @Parameter(
            names = {
                    "-r","--messages-rate"
            },
            description = "Messages rate to produce"
    )
    public double rate = -1;
}
