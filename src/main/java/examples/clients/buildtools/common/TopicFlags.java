package examples.clients.buildtools.common;

import com.beust.jcommander.Parameter;
import examples.clients.buildtools.PulsarClientFlags;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 15:17 2022/2/17
 @Modified By:
 **********************************/
public class TopicFlags extends PulsarClientFlags {
    @Parameter(
            names = {
                    "-t","--topic"
            },
            description =  "Pulsar topic name",
            required = true
    )
    public String topic;
    @Parameter(
            names = {
                    "-n","--num-messages"
            },
            description = "Number of messages to produce or consume"
    )
    public int numMessages = 10;
    @Parameter(
            names = {
                    "-nk","--num-keys"
            },
            description = "Number of keys"
    )
    public int numKeys = 128;
}
