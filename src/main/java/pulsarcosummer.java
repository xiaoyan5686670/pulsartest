
import org.apache.pulsar.client.api.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 9:28 2022/2/15
 @Modified By:
 **********************************/
public class pulsarcosummer {
    public static void main(String[] args) throws Exception {
        List<String> qxy = new ArrayList<String>();
        qxy.add("test");
        PulsarClient pulsarClient = PulsarClientInitFactory.acquirePulsarFactory(8080);
        TimeUnit.SECONDS.sleep(3);

        Consumer<byte[]> consumer = pulsarClient.newConsumer().topic("test").subscriptionName("sd").subscribe();
        Message<byte[]> qxy2 =   consumer.receive();
        consumer.acknowledge(qxy2);
        System.out.println(new String(qxy2.getValue()));






  //      System.out.println(message.getValue());

//        PulsarClient client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();
//        Consumer<byte[]> consumer =  client.newConsumer()
//                .topic("test")
//                .subscriptionName("qxy")
//                .subscriptionType(SubscriptionType.Shared)
//                .ackTimeout(1L, TimeUnit.MINUTES)
//                .acknowledgmentGroupTime(0, TimeUnit.MILLISECONDS)
//                .subscribe();
//
//
//        Message<byte[]> message = consumer.receive();
//        consumer.acknowledge(message);
//        System.out.println(new String(message.getValue()));

    }
}
