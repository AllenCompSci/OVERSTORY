package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by 226864 on 3/28/2017.
 */
public class MusicDirector {
    public MusicDirector(SongName s) {
        currentSong = s;
    }
    public static enum SongName {
            MEGALOVANIA, SONIC06
    }
    static Music megalovania = Gdx.audio.newMusic(Gdx.files.internal("megalovania/undertale.mp3"));
    SongName currentSong;
    Music nowPlaying;

    public void setNowPlaying(SongName s)
    {
        currentSong = s;
    }

    public void play()
    {
        switch (currentSong)
        {
            case MEGALOVANIA:
                nowPlaying = megalovania;
                break;
        }
        nowPlaying.play();
    }

    public void stop()
    {
        nowPlaying.stop();
    }

    public void switchSong(SongName s)
    {
        setNowPlaying(s);
        stop();
        play();
    }


}
