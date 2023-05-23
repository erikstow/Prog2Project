package edu.ntnu.idatt2001.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * MusicManager is a utility class responsible for managing and playing music tracks.
 * It provides methods for loading music tracks, playing them,
 * stopping them, and managing the volume.
 * The music tracks are stored in a HashMap, where the keys are user-defined names for the tracks,
 * and the values are instances of MediaPlayer.
 */
public class MusicManager {
  private final Map<String, MediaPlayer> tracks = new HashMap<>();
  private double volume = 1.0;
  private double unMutedVolume;
  private boolean isMuted = false;

  /**
   * Public constructor initializes the MusicManager and loads predefined music tracks.
   */
  public MusicManager() {
    initMusic();
  }

  /**
   * This method initializes the MusicManager with predefined tracks.
   */
  private void initMusic() {
    loadTrack("title", "src/main/resources/sound/1.MainTheme-320bit.mp3");
    loadTrack("game", "src/main/resources/sound/the-epic-2-by-rafael-krux.mp3");
    loadTrack("boss", "src/main/resources/sound/Dragon-Castle.mp3");
  }

  /**
   * This method loads a music track into the MusicManager.
   *
   * @param trackName The name assigned to the track.
   * @param filePath The file path of the music track.
   */
  public void loadTrack(String trackName, String filePath) {
    Media media = new Media(new File(filePath).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setVolume(volume);
    tracks.put(trackName, mediaPlayer);
  }

  /**
   * This method plays a specific music track.
   *
   * @param trackName The name of the track to be played.
   */
  public void playTrack(String trackName) {
    MediaPlayer mediaPlayer = tracks.get(trackName);
    if (mediaPlayer != null) {
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
      mediaPlayer.play();
    }
  }


  /**
   * This method stops a specific music track.
   *
   * @param trackName The name of the track to be stopped.
   */
  public void stopTrack(String trackName) {
    MediaPlayer mediaPlayer = tracks.get(trackName);
    if (mediaPlayer != null) {
      mediaPlayer.stop();
    }
  }

  /**
   * This method stops all music tracks that are currently playing.
   */
  public void stopAllTracks() {
    for (MediaPlayer mediaPlayer : tracks.values()) {
      mediaPlayer.stop();
    }
  }

  /**
   * This method sets the volume for all music tracks.
   * The input is limited to a range between 0.0 and 1.0.
   *
   * @param volume The volume level to set, where 0.0 is mute and 1.0 is maximum volume.
   */
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

  /**
   * This method toggles the mute state of all music tracks.
   */
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

  /**
   * This method returns the current volume level.
   *
   * @return the current volume level.
   */
  public double getVolume() {
    return volume;
  }
}
