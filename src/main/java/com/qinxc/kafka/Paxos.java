package com.qinxc.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Phase 1
 * (a) A proposer selects a proposal number n and sends a prepare request with number n to a majority of acceptors.
 * (b) If an acceptor receives a prepare request with number n greater than that of any prepare request to which it has already responded, then it responds to the request with a promise not to accept any more proposals numbered less than n and with the highest-numbered pro-posal (if any) that it has accepted.
 * Phase 2
 * (a) If the proposer receives a response to its prepare requests (numbered n) from a majority of acceptors, then it sends an accept request to each of those acceptors for a proposal numbered n with a value v , where v is the value of the highest-numbered proposal among the responses, or is any value if the responses reported no proposals.
 * (b) If an acceptor receives an accept request for a proposal numbered n, it accepts the proposal unless it has already responded to a prepare request having a number greater than n.
 */
public class Paxos {
    public static void main(String[] args) {
        ComputerManager computerManager = new Paxos().new ComputerManager();
        try {
            computerManager.start(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ComputerManager {
        private List<Computer> computers = new ArrayList();
        private Integer startSize;

        /**
         * 启动所有服务器
         *
         * @throwsException
         */
        public void start(Integer startSize) throws Exception {
            if (computers != null && computers.size() > 0)
                throw new Exception("restart error");
            this.startSize = startSize;
            Paxos paxos = new Paxos();
            for (int i = 0; i < startSize; i++) {
                Computer computer = paxos.new Computer(this);
                Thread thread = new Thread(computer);
                thread.start();
            }
        }

        /**
         * 启动完成的服务器注册
         *
         * @return
         */
        public void register(Computer computer) {
            computers.add(computer);
        }

        /**
         * 获取所有服务器
         *
         * @return
         */
        public Integer getHelfSize() {
            return computers.size() / 2 + 1;
//            return startSize / 2 + 1;
        }

        /**
         * 获取一个法定集合
         *
         * @return
         */
        public synchronized List getLegalComputers() {
            List<Computer> list = new ArrayList();
            int count = 0;
            int computerSize = computers.size();
            int helfCount = computerSize / 2 + 1;
            Random random = new Random();
            while (count < helfCount) {
                int _random = Math.abs(random.nextInt(computerSize));
                if (_random >= 0 && _random < computerSize) {
                    Computer _computer = computers.get(_random);
                    if (!list.contains(_computer)) {
                        list.add(_computer);
                        count++;
                    }
                }
            }
            return list;
        }
    }

    class Computer implements Runnable {
        private Integer id = Math.abs(new Random().nextInt());//服务器ID
        private Integer maxN;//当前接收到的提案号
        private Integer acceptN;//已经同意的提案号
        private Integer acceptV;//已经同意的提案号对应的值
        private ComputerManager computerManager;

        Computer(ComputerManager computerManager) {
            this.computerManager = computerManager;
        }

        public synchronized Object[] prepaer(Integer acceptN) {
            System.out.println("---------------------------------------------------分割线------------------------------");
            System.out.println(acceptN + "申请提案:" + this.id + ".........." + this.maxN + "........" + this.acceptN + "......" + this.acceptV);
            /*这里模拟一个断网情况，如果随机为2则断网*/
            Random random = new Random();
            int state = random.nextInt(10);
            if (state == 2)
                return null;
            /*以下为正常情况*/
            //如果之前没有接受过提案，直接返回null
            if (maxN == null) {
                this.maxN = acceptN;
                //令当前接收到的提案号=当前申请的提案号
                return new Object[]{"pok", null, null};
            }
            if (maxN > acceptN) {
                //由于当前申请提案号小于已经同意的提案号，所以不接收提案申请
                return new Object[]{"error", null, null};
            }
            if (acceptN > maxN) {
                //判断新申请的提案是否为新提案
                this.maxN = acceptN;
                //令当前接收到的提案号=当前申请的提案号
                if (this.acceptN == null) {//如果之前没有通过任何提案，返回null
                    return new Object[]{"pok", null, null};
                } else {
                    //如果之前同意过提案，返回最后同意的提案编号和提案值
                    return new Object[]{"pok", this.acceptN, this.acceptV};
                }
            }
            return null;
        }

        public synchronized String accept(Integer acceptN, Integer acceptV) {
            //首先当前申请的提案号acceptN不能小于maxN
            if (maxN <= acceptN) {
                maxN = acceptN;
                this.acceptN = acceptN;
                this.acceptV = acceptV;
                return "aok";
            }
            return "error";
        }

        /**
         * 进行选举
         */
        public void paxos(Computer computer) {
            //获取一个法定集合
            List<Computer> computers = computerManager.getLegalComputers();
            Integer _acceptN = 0;
            Integer _acceptV = 0;
            int count = 0;
            Integer cid = CId.getCid();
            for (Computer _computer : computers) {
                Object[] prepaer = _computer.prepaer(cid);//申请提交提案
                if (prepaer == null)
                    continue;
                System.out.println(cid + "(" + _acceptN + ":" + _acceptV + ")" + "返回提案:" + _computer.id + ".........." + prepaer[0] + "........" + prepaer[1] + "......" + prepaer[2]);
                String state = (String) prepaer[0];
                if ("pok".equals(state))//接收到申请的情况
                {
                    count++;
                    if (_acceptN == 0 && prepaer[1] == null) {
                        //生成一个新的acceptV
                        _acceptV = computer.id;
                    } else {
                        Integer acceptN = (Integer) prepaer[1];
                        Integer acceptV = (Integer) prepaer[2];
                        //使用返回的acceptV
                        if (acceptN >= _acceptN) {
                            _acceptN = acceptN;
                            _acceptV = acceptV;
                        }
                    }
                }
            }
            //如果接收到的回复超过了半数，则正式提交提案
            if (count >= computerManager.getHelfSize()) {
                _acceptN = cid;
                //获取一个法定集合
                List<Computer> computers1 = computerManager.getLegalComputers();
                int acount = 0;
                for (Computer _computer : computers1) {
                    System.out.println(_acceptN + "(" + _acceptV + ")" + "提交提案:" + _computer.id + ".........." + _computer.maxN + "........" + _computer.acceptN + "......" + _computer.acceptV);
                    String accept = _computer.accept(_acceptN, _acceptV);//申请提交提案
                    if ("aok".equals(accept)) {
                        acount++;
                    }
                }
                if (acount >= computerManager.getHelfSize()) {
                    System.out.println("提案被多数通过:" + _acceptN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + _acceptV);
                    for (Computer _computer : computers1) {
                        System.out.println(_computer.id + ".........." + _computer.maxN + "........" + _computer.acceptN + "......" + _computer.acceptV);
                    }
                }
            }
        }

        /**
         * 启动命令
         */
        public void run() {
//            Random random = new Random();
//            try {
//                Thread.sleep(random.nextInt(10) * 1000);//随机延迟几秒，模拟消息发送过程或启动过程
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Computer computer = this;
            computerManager.register(computer);//注册到启动集群中
            paxos(computer);
        }
    }

    /**
     * 提案号管理类d
     */
    static class CId {
        private Integer cid = 1;

        private CId() {
        }

        private static CId instance = new CId();

        public synchronized static Integer getCid() {
            return instance.cid++;
        }
    }
}
