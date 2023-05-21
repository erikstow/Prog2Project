package edu.ntnu.idatt2001.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MusicManager {
  private final Map<String, MediaPlayer> tracks = new HashMap<>();
  private double volume = 1.0;
  private double unMutedVolume;
  private final double mutedVolume = 0.0;
  private boolean isMuted = false;

  public void loadTrack(String trackName, String filePath) {
    Media media = new Media(new File(filePath).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setVolume(volume);
    tracks.put(trackName, mediaPlayer);
  }

  public void playTrack(String trackName) {
    MediaPlayer mediaPlayer = tracks.get(trackName);
    if (mediaPlayer != null) {
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
      mediaPlayer.play();
    }
  }

  public void stopTrack(String trackName) {
    MediaPlayer mediaPlayer = tracks.get(trackName);
    if (mediaPlayer != null) {
      mediaPlayer.stop();
    }
  }

  public void stopAllTracks() {
    for (MediaPlayer mediaPlayer : tracks.values()) {
      mediaPlayer.stop();
    }
  }

  public void setVolume(double volume) {
    if (volume < 0.0) {
      volume = 0.0;
    } else if (volume > 1.0) {
      volume = 1.0;
    }
    this.volume = volume;
    for (MediaPlayer track : tracks.values()) {
      track.setVolume(volume);
    }
  }

  public void muteToggle() {
    if (isMuted) {
      setVolume(this.unMutedVolume);
      isMuted = false;
    } else {
      unMutedVolume = getVolume();
      setVolume(mutedVolume);
      isMuted = true;
    }
  }

  public double getVolume() {
    return volume;
  }
}