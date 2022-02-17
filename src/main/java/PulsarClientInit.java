import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;

/**
 * @author hezhangjian
 */
@Slf4j
public class PulsarClientInit {

    private static final PulsarClientInit INSTANCE = new PulsarClientInit();

    public void setPulsarClient(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
    }

    private PulsarClient pulsarClient;

    public static PulsarClientInit getInstance() {
        return INSTANCE;
    }

    public PulsarClientInit init() throws Exception {
        pulsarClient = PulsarClient.builder()
                .serviceUrl(PulsarConstant.SERVICE_URL)
                .build();
        return null;
    }

    public PulsarClient getPulsarClient() {
        return pulsarClient;
    }
}
