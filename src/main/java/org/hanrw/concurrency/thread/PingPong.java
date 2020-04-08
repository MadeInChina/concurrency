package org.hanrw.concurrency.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hanrw
 * @date 2020/4/8 5:08 PM
 */
public class PingPong {

  static ReentrantLock lock = new ReentrantLock(true);


  public void ping() {
    lock.lock();
    try {
      System.out.println("ping");
    } finally {
      lock.unlock();
    }
  }

  public void pong() {
    lock.lock();
    try {
      System.out.println("pong");
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    PingPong pingPong = new PingPong();
    Thread pingThread =
        new Thread(
            () -> {
              while (true) {
                pingPong.ping();
              }
            });

    Thread pongThread =
        new Thread(
            () -> {
              while (true) {
                pingPong.pong();
              }
            });
    pingThread.start();
    pongThread.start();
  }
}
