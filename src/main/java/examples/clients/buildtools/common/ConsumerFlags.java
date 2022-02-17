package examples.clients.buildtools.common;

import com.beust.jcommander.Parameter;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.api.proto.PulsarApi;

/*********************************
 @Author:xiaoyan.qin
 @Description:
 @Date:Created in 15:13 2022/2/17
 @Modified By:
 **********************************/
public class ConsumerFlags extends TopicFlags{
    @Parameter(
        names ={
                "-sn","--subscription-name"
        },
            description = "Pulsar subscription name",
            required = true
            )
    public String subscriptionName="test-sub";

    @Parameter(
            names = {
                    "-st","--subscription-type"
            },
            description = "Pulsar subscription type",
            required = true
    )
    public SubscriptionType subscriptionType = SubscriptionType.Exclusive;

    @Parameter(
            names = {
                    "-sip","--subscription-initial-position"
            },
            description = "The initial position for the subscription"
    )
    public SubscriptionInitialPosition subscriptionInitialPosition = SubscriptionInitialPosition.Earliest;

    @Parameter(
            names = {
                    "-an","--ack-every-n-messages"
            },
            description = "Ack every N messages"
    )
    public int ackEveryNMessages = 1;

    @Parameter(
            names = {
                    "-at","--ack-type"
            },
            description = "Ack type"
    )
    public PulsarApi.CommandAck.AckType ackType = null;
    @Parameter(
            names = {
                    "-ppn","--print-partition-num"
            },
            description = "Set is print partition num"
    )
    public boolean printPartitionNum = false;
}
