package se.anders_raberg.adventofcode.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day18b {
    private static class Program implements Runnable {
        private static final Logger LOGGER = Logger.getLogger(Program.class.getName());
        private final Map<String, Long> _regs = new HashMap<>();

        private List<List<String>> _instructions;
        private long _id;
        private BlockingQueue<Long> _recvQueue;
        private BlockingQueue<Long> _sendQueue;

        public Program(List<List<String>> instructions, long id, BlockingQueue<Long> recvQueue, 
                BlockingQueue<Long> sendQueue)  {
            _instructions = instructions;
            _id = id;
            _recvQueue = recvQueue;
            _sendQueue = sendQueue;
            _regs.put("p", _id);
        }

        @Override
        public void run() {
            long sendCounter = 0;
            long instructionCounter = 0;
            boolean terminated = false;
            while (!terminated) {
                List<String> instr = _instructions.get((int) instructionCounter);

                switch (instr.get(0)) {
                case "snd":
                    _sendQueue.add(value(instr.get(1), _regs));
                    sendCounter++;
                    instructionCounter++;
                    break;
                case "set":
                    _regs.put(instr.get(1), value(instr.get(2), _regs));
                    instructionCounter++;
                    break;
                case "add":
                    _regs.put(instr.get(1), value(instr.get(1), _regs) + value(instr.get(2), _regs));
                    instructionCounter++;
                    break;
                case "mul":
                    _regs.put(instr.get(1), value(instr.get(1), _regs) * value(instr.get(2), _regs));
                    instructionCounter++;
                    break;
                case "mod":
                    _regs.put(instr.get(1), value(instr.get(1), _regs) % value(instr.get(2), _regs));
                    instructionCounter++;
                    break;
                case "rcv":
                    Long poll = null;
                    try {
                        poll = _recvQueue.poll(1, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    if (poll == null) {
                        terminated = true;
                    } else {
                        _regs.put(instr.get(1), poll);
                    }

                    instructionCounter++;
                    break;
                case "jgz":
                    if (value(instr.get(1), _regs) > 0) {
                        instructionCounter = instructionCounter + value(instr.get(2), _regs);
                    } else {
                        instructionCounter++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException(instr.get(0));
                }
            }
            LOGGER.info("ID: " + _id + ", send counter: " + sendCounter);
        }

    }

    public static void run(String inputPath) throws IOException, InterruptedException {

        List<List<String>> instructions = new ArrayList<>();

        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input18.txt"))) {
            input.forEach(l -> {
                List<String> parsedLine = Arrays.stream(l.split("\\s+")).collect(Collectors.toList());
                instructions.add(parsedLine);
            });
        }

        BlockingQueue<Long> _queue0to1 = new LinkedBlockingQueue<>();
        BlockingQueue<Long> _queue1to0 = new LinkedBlockingQueue<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new Program(instructions, 0, _queue1to0, _queue0to1));
        executorService.submit(new Program(instructions, 1, _queue0to1, _queue1to0));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static long value(String s, Map<String, Long> registers) {
        if (s.matches("[a-z]")) {
            return registers.getOrDefault(s, 0L);
        }
        return Integer.parseInt(s);
    }

}