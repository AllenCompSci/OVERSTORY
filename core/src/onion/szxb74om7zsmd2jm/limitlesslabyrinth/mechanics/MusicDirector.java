package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by 226864 on 3/28/2017.
 */
public class MusicDirector {
    public MusicDirector(SoundName sn)
    {
        currentSound = sn;
    }

    public MusicDirector(SongName s) {
        currentSong = s;
    }
    public static enum SongName {
            MEGALOVANIA, SONIC06, ONEONESEVEN, SUPERSANIC
    }

    public static enum SoundName {
        PLAYERHIT
    }

    static Music megalovania = Gdx.audio.newMusic(Gdx.files.internal("Megalovania/Undertale.mp3"));
    static Music sanic = Gdx.audio.newMusic(Gdx.files.internal("Megalovania/Sanic.mp3"));
    static Music UnoUnoSiete = Gdx.audio.newMusic(Gdx.files.internal("Megalovania/117.mp3"));
    static Music supersanic = Gdx.audio.newMusic(Gdx.files.internal("Megalovania/GOTTAGOFEST.mp3"));
    static Sound weaponFire = Gdx.audio.newSound(Gdx.files.internal("Megalovania/memes.mp3"));
    static Sound playerhit;
    SongName currentSong;
    Music nowPlaying;
    SoundName currentSound;
    Sound soundPlaying;

    public void setNowPlaying(SongName s)
    {
        currentSong = s;
    }

    private void setSoundNowPlaying(SoundName sn)
    {
        currentSound = sn;
    }

    public void playSound(SoundName sn)
    {

        this.setSoundNowPlaying(sn);
        switch (currentSound)
        {
            case PLAYERHIT:
                soundPlaying = weaponFire;
        }

        soundPlaying.play();
    }

    public void play()
    {
        switch (currentSong)
        {
            case MEGALOVANIA:
                nowPlaying = megalovania;
                break;
            case SONIC06:
                nowPlaying = sanic;
                break;
            case ONEONESEVEN:
                nowPlaying = UnoUnoSiete;
                break;
            case SUPERSANIC:
                nowPlaying = supersanic;
                break;
        }
        nowPlaying.play();
        nowPlaying.setLooping(true);
    }

    public void stop()
    {
        nowPlaying.stop();
    }

    public void switchSong(SongName s)
    {
        this.setNowPlaying(s);
        if(this.nowPlaying.isPlaying()) {
            this.stop();
        }
        this.play();
    }


}
