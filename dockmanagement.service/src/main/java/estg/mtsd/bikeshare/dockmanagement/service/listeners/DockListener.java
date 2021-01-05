package estg.mtsd.bikeshare.dockmanagement.service.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import estg.mtsd.bikeshare.dockmanagement.service.service.DockService;
import estg.mtsd.bikeshare.dockmanagement.service.vo.DockEvent;
import estg.mtsd.bikeshare.shared.library.vo.DockVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DockListener {

    @Autowired
    DockService dockService;

    @Value("${topic.name.consumer")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, DockEvent> payload) {
        log.info("Tópico: " + topicName);
        log.info("key: " + payload.key());
        log.info("Headers: " + payload.headers());
        log.info("Partion: " + payload.partition());
        log.info("Order: " + payload.value());

        DockEvent event = payload.value();

        DockVo dock = null;
        switch (event.getEvent()) {
            case BIKE_INSERTED:
                dock = dockService.get(event.getDockId());
                if (dock != null) {
                    if (dock.getBikeId() != null) {
                        log.error("Dock already has a bike attached!");
                    }

                    dock.setBikeId(event.getBikeId());

                    dockService.update(dock);
                }
                break;
            case BIKE_REMOVED:
                dock = dockService.get(event.getDockId());
                if (dock != null) {
                    dock.setBikeId(null);

                    dockService.update(dock);
                }
                break;
            default:
                break;
        }
    }
}