package com.learn.simpleconsoleapp.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Documentarist and singer are working together to produce a documentary of a tour.
 * The documentarist will basically tell the singer what to do while filming the documentary
 *
 * @see Singer
 */
@Component
public class Documentarist {
    private Singer singer;

    public Documentarist(@Qualifier("betterSinger") Singer singer) {
        this.singer = singer;
    }

    public void execute() {
        singer.sing();
        singer.sing("Requested lyric blah blah blah...");
        singer.talk();
    }

    public void execute(String requestedLyric) {
        singer.sing();
        singer.sing(requestedLyric);
        singer.talk();
    }
}
