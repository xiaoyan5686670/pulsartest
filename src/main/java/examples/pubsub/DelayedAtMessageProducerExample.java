package examples.pubsub;

import examples.clients.buildtools.ExampleRunner;
import examples.clients.buildtools.common.ConsumerFlags;
import examples.clients.buildtools.common.ProducerFlags;
import org.apache.commons.lang.time.DateUtils;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

import java.util.Date;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 17:46 2022/2/17
 @Modified By:
 **********************************/
public class DelayedAtMessageProducerExample extends ExampleRunner<ProducerFlags> {
    @Override
    protected String name(){
        return DelayedAtMessageProducerExample.class.getSimpleName();
    }
    @Override
    protected String description(){
        return "An example demonstrates how to use delayed message delivery featrue";
    }

    @Override
    protected ProducerFlags flags() {
        return new ProducerFlags();
    }

    @Override
    protected void run(ProducerFlags flags) throws Exception{
        try(PulsarClient client = PulsarClient.builder()
                .serviceUrl(flags.binaryServiceUrl)
                .build()){
            try(Producer<String> producer = client.newProducer(Schema.STRING)
                    .topic(flags.topic)
                    .create()){
                final int numMessages = Math.max(flags.numMessages,1);

                //immediate delivery
                for (int i = 0; i < numMessages;i++){
                    producer.newMessage()
                            .value("Immeidate delivery message " + i)
                            .sendAsync();
                }
                producer.flush();
                // delay 5 seconds using DeliverAt
                for (int i = 0; i< numMessages;i++){
                    producer.newMessage()
                            .value("DeliverAt message " + i)
                            .deliverAt(DateUtils.addSeconds(new Date(),5).getTime())
                            .sendAsync();
                }
                producer.flush();
            }
        }
    }
    public static void main(String[] args){
        DelayedAtMessageProducerExample example = new DelayedAtMessageProducerExample();
        example.run(args);
    }

}
