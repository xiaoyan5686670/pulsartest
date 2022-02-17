package examples.pubsub;

import examples.clients.buildtools.ExampleRunner;
import examples.clients.buildtools.common.ConsumerFlags;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.common.naming.TopicName;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 15:53 2022/2/17
 @Modified By:
 **********************************/
public class DelayedMessageConsumerExample extends ExampleRunner<ConsumerFlags> {
    @Override
    protected String name(){
        return DelayedMessageConsumerExample.class.getSimpleName();
    }
    @Override
    protected String description(){
        return  "An example demonstrats how to use delayed message delivery featur";
    }

    @Override
    protected ConsumerFlags flags(){
        return new ConsumerFlags();
    }
    @Override
    protected void run(ConsumerFlags flags) throws Exception {
        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(flags.binaryServiceUrl)
                .build()) {
            int numrReceived = 0;
            try (Consumer<String> consumer = client.newConsumer(Schema.STRING)
                    .topic(flags.topic)
                    .subscriptionName(flags.subscriptionName)
                    .subscriptionInitialPosition(flags.subscriptionInitialPosition)
                    .subscribe()) {
                while (flags.numMessages <= 0 || numrReceived < flags.numMessages) {
                    Message<String> msg = consumer.receive();
                    StringBuilder consumerInfo = new StringBuilder();
                    consumerInfo.append("Consumer Receivied message :").append(msg.getValue());

                    if (flags.printPartitionNum) {
                        consumerInfo.append(",Partition:")
                                .append(TopicName.getPartitionIndex(msg.getTopicName()));
                    }
                    consumerInfo.append(";Difference between publish time and receive time = ")
                            .append((System.currentTimeMillis() - msg.getPublishTime()) / 1000)
                            .append(" secodns");
                    System.out.println(consumerInfo);
                    consumer.acknowledge(msg);
                    ++numrReceived;
                }
                System.out.println("Successfully received " + numrReceived + " messages");
            } catch (PulsarClientException ie) {
                if (ie.getCause() instanceof InterruptedException) {
                    System.out.println("Successfully received " + numrReceived + " messages");
                    Thread.currentThread().interrupt();
                } else {
                    throw ie;
                }
            }
        }
    }
    public static void main(String[] args){
        DelayedMessageConsumerExample example = new DelayedMessageConsumerExample();
        example.run(args); //--topic ack-individual-example --subscription-name example-sub  --subscription-initial-position Earliest    --subscription-type Shared
    }

    }


