import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;

/**
 * @author hezhangjian
 */
@Slf4j
public class DummyMessageListener<T> implements MessageListener<T> {

    @Override
    public void received(Consumer<T> consumer, Message<T> msg) {

    }

}
