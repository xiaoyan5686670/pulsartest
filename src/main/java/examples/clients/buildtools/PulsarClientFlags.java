package examples.clients.buildtools;

import com.beust.jcommander.Parameter;
import org.apache.bookkeeper.tools.framework.CliFlags;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 14:37 2022/2/17
 @Modified By:
 **********************************/
public class PulsarClientFlags extends CliFlags {
    @Parameter(
            names = {
                    "-bu","--binary-service-url"
            },
            description = "Pulsar Binary service url"
    )
    public String binaryServiceUrl = "pulsar://localhost:6650";
    @Parameter(
            names = {
                    "-hu","--http-service-url"
            },
            description = "Pulsar http service url"
    )
    public String httpServiceUrl = "http://localhost:8080";
}
