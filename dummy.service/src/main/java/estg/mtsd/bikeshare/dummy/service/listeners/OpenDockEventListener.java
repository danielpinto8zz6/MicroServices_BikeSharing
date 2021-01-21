package estg.mtsd.bikeshare.dummy.service.listeners;

import estg.mtsd.bikeshare.shared.library.utils.JsonUtils;
import estg.mtsd.bikeshare.shared.library.vo.OpenDockEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenDockEventListener {

    @Value("${topic.open-dock.consumer")
    private String topicName;

    @KafkaListener(topics = "${topic.open-dock.consumer}", groupId = "dummy")
    public void consume(ConsumerRecord<String, String> payload) {

        OpenDockEvent event = JsonUtils.fromJson(payload.value(), OpenDockEvent.class);

        System.out.println("Opening dock: " + event.getDockId());
    }
}
