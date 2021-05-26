package com.example.assignmentstart;


public class TrackListJson {
    Message message;


    public TrackListJson(Message message) {
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
        Track_list[] track_list;


        public Body(Track_list[] track_list) {
            this.track_list = track_list;
        }

    }

    class Track_list {
        Track track;

        public Track_list(Track track) {
            this.track = track;
        }
    }

    class Track {
        private int track_id;
        private int commontrack_id;
        private String track_name;
        private String album_name;
        private String artist_name;
        private boolean liked;


        public Track(int track_id, int commontrack_id, String track_name, String album_name, String artist_name) {
            this.commontrack_id = commontrack_id;
            this.track_name = track_name;
            this.album_name = album_name;
            this.artist_name = artist_name;
            this.track_id = track_id;
        }

        public int getTrack_id() {
            return track_id;
        }

        public void setTrack_id(int track_id) {
            this.track_id = track_id;
        }

        public int getCommontrack_id() {
            return commontrack_id;
        }

        public void setCommontrack_id(int commontrack_id) {
            this.commontrack_id = commontrack_id;
        }

        public String getTrack_name() {
            return track_name;
        }

        public void setTrack_name(String track_name) {
            this.track_name = track_name;
        }

        public String getAlbum_name() {
            return album_name;
        }

        public void setAlbum_name(String album_name) {
            this.album_name = album_name;
        }

        public String getArtist_name() {
            return artist_name;
        }

        public void setArtist_name(String artist_name) {
            this.artist_name = artist_name;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        @Override
        public String toString() {
            return this.track_name;
        }
    }
}
