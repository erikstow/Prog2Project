package edu.ntnu.idatt2001.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
  private final Map<String, MediaPlayer> tracks = new HashMap<>();
  private double volume = 1.0;
  private double unMutedVolume;
  private boolean isMuted = false;

  public MusicManager() {
    initMusic();
  }

  private void initMusic() {
    loadTrack("title", "src/main/resources/sound/1.MainTheme-320bit.mp3");
    loadTrack("game", "src/main/resources/sound/the-epic-2-by-rafael-krux.mp3");
    loadTrack("boss", "src/main/resources/sound/Dragon-Castle.mp3");
  }

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
      double mutedVolume = 0.0;
      setVolume(mutedVolume);
      isMuted = true;
    }
  }

  public double getVolume() {
    return volume;
  }
}
