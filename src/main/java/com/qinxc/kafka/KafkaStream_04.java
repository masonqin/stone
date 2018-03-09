package com.qinxc.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class KafkaStream_04 {

    public static void main(String[] args) {


        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-logstatic");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());


//        Map<String, String> changelogConfig = new HashMap();
//        changelogConfig.put("min,insyc.replicas", "1");
        StoreBuilder<KeyValueStore<String, Long>> countStoreSupplier = Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore("Counts"),
                Serdes.String(),
                Serdes.Long());
//                .withLoggingEnabled(changelogConfig);

        final Topology topology = new Topology();

        topology.addSource("Source", "streams-plaintext-input")
                .addProcessor("Process", () -> new logCounter(), "Source")
                .addStateStore(countStoreSupplier, "Process")
                .addSink("Sink", "streams-sink", "Process");

        System.out.println(topology.describe());

        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }
}

class logCounter implements Processor<String, String> {

    private ProcessorContext context;
    private KeyValueStore<String, Long> kvStore;


    private final String totalKey = "total";
    private final String successKey = "success";
    private final String failedKey = "failed";

    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
        this.context.schedule(5000);
        kvStore = (KeyValueStore<String, Long>) processorContext.getStateStore("Counts");
    }

    @Override
    public void process(String key, String value) {

        Long counter;
        if (value.contains(totalKey)) {
            counter = kvStore.get("log total");
            if (counter == null)
                kvStore.put("log total", 1L);
            else
                kvStore.put("log total", counter + 1);
        }
        if (value.contains(successKey)) {
            counter = kvStore.get("log success");
            if (counter == null)
                kvStore.put("log success", 1L);
            else
                kvStore.put("log success", counter + 1);
        }
        if (value.contains(failedKey)) {
            counter = kvStore.get("log failed");
            if (counter == null)
                kvStore.put("log failed", 1L);
            else
                kvStore.put("log failed", counter + 1);
        }
        context.commit();
    }

    @Override
    public void punctuate(long timestamp) {
        System.out.println("====System time: " + (new Date()).toString());
        KeyValueIterator<String, Long> iter = kvStore.all();
        while (iter.hasNext()) {
            KeyValue kv = iter.next();
            System.out.println("KEY: " + kv.key + " VALUE: " + kv.value);
        }
        iter.close();
    }

    @Override
    public void close() {
        kvStore.close();
    }
}
