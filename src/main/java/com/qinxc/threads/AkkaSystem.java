package com.qinxc.threads;


import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class AkkaSystem {

    private int actor_size = 10;
    ActorSystem actorSystem = ActorSystem.create("test-actor-system");

    private ActorRef actorRef = actorSystem.actorOf(
            Props.create(TestActor.class)
                    .withDispatcher("test-actor-dispatcher")
                    .withRouter(new RoundRobinPool(actor_size)), "test-actor-router");

    public static void main(String[] args) throws Exception {

        AkkaSystem akkaSystem = new AkkaSystem();

        ActorSelection selection =
                akkaSystem.actorSystem.actorSelection("/user/*");

        int i = 0;
        while (true) {
            akkaSystem.actorRef.tell("Current count is : " + i++, null);
            akkaSystem.actorSystem.mailboxes();
            Thread.sleep(100);
        }
    }
}



