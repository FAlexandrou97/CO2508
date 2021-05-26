package com.example.assignmentstart;

import androidx.annotation.NonNull;

public class LyricsJson {
    Message message;

    public LyricsJson(Message message) {
        this.message = message;
    }


    class Message {
        Header header;
        Body body;

        public Message(Header header, Body body) {
            this.header = header;
            this.body = body;
        }
    }

    class Header {
        int status_code;
        float execute_time;

        public Header(int status_code, float execute_time) {
            this.status_code = status_code;
            this.execute_time = execute_time;
        }
    }

    class Body {
        Lyrics lyrics;

        public Body(Lyrics lyrics) {
            this.lyrics = lyrics;
        }
    }

    class Lyrics {
        int lyrics_id;
        String lyrics_body;
        String lyrics_copyright;

        public Lyrics(int lyrics_id, String lyrics_body, String lyrics_copyright) {
            this.lyrics_id = lyrics_id;
            this.lyrics_body = lyrics_body;
            this.lyrics_copyright = lyrics_copyright;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return this.message.body.lyrics.lyrics_body;
    }
}
