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
            MEGALOVANIA
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
        nowPlaying.play();
    }


}
